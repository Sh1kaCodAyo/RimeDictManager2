package com.ftwrjh.rimedictmanager2.env;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 应用全局上下文 - 单例模式
 * 存储当前用户、全局配置、共享数据等
 */
public class AppContext {
    
    // 单例实例
    private static final AppContext INSTANCE = new AppContext();
    
    // 私有构造器（防止外部实例化）
    private AppContext() {}
    
    public static AppContext getInstance() {
        return INSTANCE;
    }
    
    // ========== 用户信息 ==========
    private final StringProperty currentUsername = new SimpleStringProperty();
    private final StringProperty currentUserRole = new SimpleStringProperty();
    
    // ========== 应用状态 ==========
    private final StringProperty currentView = new SimpleStringProperty("home");
    
    // ========== 数据缓存 ==========
    private final ObservableList<String> recentFiles = FXCollections.observableArrayList();
    
    // ========== 配置 ==========
    private final StringProperty appTheme = new SimpleStringProperty("light");
    
    // ========== Getter/Setter/Property ==========
    public String getCurrentUsername() { return currentUsername.get(); }
    public void setCurrentUsername(String username) { this.currentUsername.set(username); }
    public StringProperty currentUsernameProperty() { return currentUsername; }
    
    public String getCurrentUserRole() { return currentUserRole.get(); }
    public void setCurrentUserRole(String role) { this.currentUserRole.set(role); }
    public StringProperty currentUserRoleProperty() { return currentUserRole; }
    
    public String getCurrentView() { return currentView.get(); }
    public void setCurrentView(String view) { this.currentView.set(view); }
    public StringProperty currentViewProperty() { return currentView; }
    
    public ObservableList<String> getRecentFiles() { return recentFiles; }
    
    public String getAppTheme() { return appTheme.get(); }
    public void setAppTheme(String theme) { this.appTheme.set(theme); }
    public StringProperty appThemeProperty() { return appTheme; }
}