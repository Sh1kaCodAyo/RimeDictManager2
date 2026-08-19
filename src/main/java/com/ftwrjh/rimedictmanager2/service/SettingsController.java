package com.ftwrjh.rimedictmanager2.service;

import com.ftwrjh.rimedictmanager2.env.AppConfig;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    
    @FXML
    private ColorPicker bgColorPicker;      // 悬停背景色
    @FXML
    private ColorPicker textColorPicker;    // 悬停文字色
    @FXML
    private ColorPicker borderColorPicker;  // 选中边框色
    @FXML
    private Button applyBtn;
    @FXML
    private Button resetBtn;
    
    private Parent root;  // 由 Application 传入
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 加载当前配置
        AppConfig config = AppConfig.getInstance();
        
        // 设置初始颜色
        bgColorPicker.setValue(Color.web(config.getProperty("theme.bg", "#3d6e87")));
        textColorPicker.setValue(Color.web(config.getProperty("theme.text", "#ececec")));
        borderColorPicker.setValue(Color.web(config.getProperty("theme.border", "#294f88")));
        
        // 应用按钮
        applyBtn.setOnAction(e -> applyTheme());
        
        // 重置按钮
        resetBtn.setOnAction(e -> resetTheme());
        
        // 实时预览（可选）
        bgColorPicker.setOnAction(e -> previewTheme());
        textColorPicker.setOnAction(e -> previewTheme());
    }
    
    private void applyTheme() {
        String bgHex = toHex(bgColorPicker.getValue());
        String textHex = toHex(textColorPicker.getValue());
        String borderHex = toHex(borderColorPicker.getValue());
        
        // 应用样式
        if (root != null) {
            root.setStyle(String.format("""
                -item-bg-hover: %s;
                -text-hover: %s;
                -border-color: %s;
            """, bgHex, textHex, borderHex));
        }
        
        // 保存到配置文件
        AppConfig config = AppConfig.getInstance();
        config.setProperty("theme.bg", bgHex);
        config.setProperty("theme.text", textHex);
        config.setProperty("theme.border", borderHex);
        config.save();
        
        showAlert("✅ 主题已保存", "新主题已应用并保存");
    }
    
    private void resetTheme() {
        // 恢复默认颜色
        bgColorPicker.setValue(Color.web("#3d6e87"));
        textColorPicker.setValue(Color.web("#ececec"));
        borderColorPicker.setValue(Color.web("#294f88"));
        applyTheme();
    }
    
    private void previewTheme() {
        // 实时预览（不保存）
        String bgHex = toHex(bgColorPicker.getValue());
        String textHex = toHex(textColorPicker.getValue());
        String borderHex = toHex(borderColorPicker.getValue());
        
        if (root != null) {
            root.setStyle(String.format("""
                -item-bg-hover: %s;
                -text-hover: %s;
                -border-color: %s;
            """, bgHex, textHex, borderHex));
        }
    }
    
    private String toHex(Color color) {
        return String.format("#%02X%02X%02X",
            (int) (color.getRed() * 255),
            (int) (color.getGreen() * 255),
            (int) (color.getBlue() * 255)
        );
    }
    
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    public void setRoot(Parent root) {
        this.root = root;
    }
}