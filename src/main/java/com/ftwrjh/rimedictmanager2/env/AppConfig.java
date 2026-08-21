package com.ftwrjh.rimedictmanager2.env;

import com.ftwrjh.rimedictmanager2.data.constant.AppConst;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Slf4j
public class AppConfig {
    @Getter
    private static final AppConfig instance = new AppConfig();

    private final Properties props = new Properties();
    private final Path userConfigPath;  // 保存用户配置文件的路径

    private AppConfig() {
        // 1. 确定用户配置目录和文件路径
        String userHome = System.getProperty(AppConst.AppConfigConst.USER_HOME);
        Path appDir = Paths.get(userHome, AppConst.AppConfigConst.USER_CONFIG_HOME);  // 应用配置目录
        userConfigPath = appDir.resolve(AppConst.AppConfigConst.CONFIG_FILENAME);

        // 2. 加载默认配置（从 JAR 内部）
        loadDefaultConfig();

        // 3. 加载用户配置（覆盖默认值）
        loadUserConfig();

        // 4. 如果用户配置不存在，从默认配置创建
        createUserConfigIfNotExists();
    }

    /**
     * 从 classpath 加载默认配置（打包在 jar 内）
     */
    private void loadDefaultConfig() {
        try (InputStream input = this.getClass().getClassLoader()
                .getResourceAsStream(AppConst.AppConfigConst.CONFIG_FILENAME)) {
            if (input != null) {
                props.load(input);
                log.info("加载默认配置成功");
            } else {
                log.warn("未找到默认配置文件: {}", AppConst.AppConfigConst.CONFIG_FILENAME);
            }
        } catch (Exception e) {
            log.error("加载默认配置失败", e);
        }
    }

    /**
     * 从用户目录加载外部配置，覆盖默认值
     */
    private void loadUserConfig() {
        if (Files.exists(userConfigPath)) {
            try (InputStream userInput = Files.newInputStream(userConfigPath)) {
                props.load(userInput);
                log.info("已加载用户配置: {}", userConfigPath);
            } catch (Exception e) {
                log.error("加载用户配置失败", e);
            }
        } else {
            log.info("用户配置不存在，将使用默认配置");
        }
    }

    /**
     * 如果用户配置不存在，从默认配置创建
     */
    private void createUserConfigIfNotExists() {
        if (!Files.exists(userConfigPath)) {
            try {
                // 确保配置目录存在
                Path parentDir = userConfigPath.getParent();
                if (parentDir != null && !Files.exists(parentDir)) {
                    Files.createDirectories(parentDir);
                }

                // 从 JAR 内部的默认配置复制
                try (InputStream defaultInput = this.getClass().getClassLoader()
                        .getResourceAsStream(AppConst.AppConfigConst.CONFIG_FILENAME)) {
                    if (defaultInput != null) {
                        Files.copy(defaultInput, userConfigPath);
                        log.info("已创建用户配置文件: {}", userConfigPath);
                    } else {
                        // 如果 JAR 内没有默认配置，创建一个空的配置文件
                        Files.createFile(userConfigPath);
                        log.info("已创建空配置文件: {}", userConfigPath);
                    }
                }
            } catch (Exception e) {
                log.error("创建用户配置文件失败", e);
            }
        }
    }

    /**
     * ⭐ 保存配置到外部文件
     */
    public void save() {
        save(userConfigPath);
    }

    /**
     * ⭐ 保存配置到指定路径
     */
    public void save(Path path) {
        try (OutputStream output = Files.newOutputStream(path)) {
            props.store(output, "RimeDictManager2 Configuration - " + new java.util.Date());
            log.info("配置已保存: {}", path);
        } catch (Exception e) {
            log.error("保存配置失败", e);
        }
    }

    /**
     * ⭐ 保存配置并立即重新加载
     */
    public void saveAndReload() {
        save();
        reload();
    }

    /**
     * ⭐ 重新加载配置
     */
    public void reload() {
        props.clear();
        loadDefaultConfig();
        loadUserConfig();
        log.info("配置已重新加载");
    }

    /**
     * ⭐ 设置配置值
     */
    public void setProperty(String key, String value) {
        props.setProperty(key, value);
    }

    /**
     * ⭐ 批量设置配置
     */
    public void setProperties(Properties properties) {
        props.putAll(properties);
    }

    /**
     * ⭐ 删除配置项
     */
    public void removeProperty(String key) {
        props.remove(key);
    }

    /**
     * ⭐ 获取配置值
     */
    public String getProperty(String key) {
        return props.getProperty(key);
    }

    /**
     * ⭐ 获取配置值（带默认值）
     */
    public String getProperty(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    /**
     * ⭐ 获取所有配置（用于显示）
     */
    public Properties getAllProperties() {
        return props;
    }

    /**
     * ⭐ 获取用户配置文件路径
     */
    public Path getUserConfigPath() {
        return userConfigPath;
    }
}