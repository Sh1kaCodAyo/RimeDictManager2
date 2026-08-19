package com.ftwrjh.rimedictmanager2.env;

import javafx.application.Platform;
import org.yaml.snakeyaml.Yaml;

import java.util.concurrent.ConcurrentHashMap;

public class AppContext {
    // 使用枚举实现线程安全的单例
    private enum Singleton {
        INSTANCE;
        private final AppContext instance = new AppContext();
    }

    private final ConcurrentHashMap<String, Object> cache;
    private final ConcurrentHashMap<String, Runnable> onChangedListeners;

    private AppContext() {
        this.cache = new ConcurrentHashMap<>();
        this.onChangedListeners = new ConcurrentHashMap<>();
    }

    public static AppContext getInstance() {
        return Singleton.INSTANCE.instance;
    }

    // ========== 常用缓存对象 ==========

    public static Yaml getYamlInstance() {
        return AppContext.getInstance().getTyped(Const.ContextKey.OBJ_YAML, Yaml.class);
    }

    // ========== 基础存取方法 ==========

    public <T> T get(String key) {
        return (T) cache.get(key);
    }

    public <T> T get(String key, T defaultValue) {
        T value = get(key);
        return value != null ? value : defaultValue;
    }

    public void set(String key, Object value) {
        if (value == null) {
            return;
        }
        cache.put(key, value);
        // 触发变更监听器
        notifyListeners(key, value);
    }

    public void remove(String key) {
        cache.remove(key);
        notifyListeners(key, null);
    }

    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }

    public void clear() {
        cache.clear();
    }

    // ========== 类型安全的泛型方法 ==========

    @SuppressWarnings("unchecked")
    public <T> T getTyped(String key, Class<T> type) {
        Object value = cache.get(key);
        if (value != null && type.isAssignableFrom(value.getClass())) {
            return (T) value;
        }
        return null;
    }

    // ========== 变更监听机制 ==========

    public void addListener(String key, Runnable listener) {
        onChangedListeners.put(key, listener);
    }

    public void removeListener(String key) {
        onChangedListeners.remove(key);
    }

    private void notifyListeners(String key, Object newValue) {
        Runnable listener = onChangedListeners.get(key);
        if (listener != null) {
            Platform.runLater(listener);
        }
    }
}