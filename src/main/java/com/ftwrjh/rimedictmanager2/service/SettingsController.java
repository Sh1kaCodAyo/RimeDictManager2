package com.ftwrjh.rimedictmanager2.service;

import com.ftwrjh.rimedictmanager2.data.constant.AppConst;
import com.ftwrjh.rimedictmanager2.env.AppConfig;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    private ColorPicker bgColorPicker;
    @FXML
    private ColorPicker textColorPicker;
    @FXML
    private ColorPicker borderColorPicker;
    @FXML
    private Button applyBtn;
    @FXML
    private Button resetBtn;

    @Setter
    private Parent root;  // 由 Application 传入

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 加载当前配置
        AppConfig config = AppConfig.getInstance();

        // 设置初始颜色
        bgColorPicker.setValue(Color.web(config.getProperty(AppConst.AppConfigConst.COLOR_BG_HEX, AppConst.Style.INIT_RGB_THEME_BG)));
        textColorPicker.setValue(Color.web(config.getProperty(AppConst.AppConfigConst.COLOR_TEXT_HEX, AppConst.Style.INIT_RGB_THEME_TEXT)));
        borderColorPicker.setValue(Color.web(config.getProperty(AppConst.AppConfigConst.COLOR_BORDER_HEX, AppConst.Style.INIT_RGB_THEME_BORDER)));

        // 应用按钮
        applyBtn.setOnAction(e -> this.applyTheme());

        // 重置按钮
        resetBtn.setOnAction(e -> this.resetTheme());

        // 实时预览（可选）
        bgColorPicker.setOnAction(e -> this.previewTheme());
        textColorPicker.setOnAction(e -> this.previewTheme());
    }

    private void applyTheme() {
        String bgHex = this.toHex(bgColorPicker.getValue());
        String textHex = this.toHex(textColorPicker.getValue());
        String borderHex = this.toHex(borderColorPicker.getValue());

        // 应用样式
        if (root != null) {
            root.setStyle(String.format(AppConst.Style.THEME_PREVIRE, bgHex, textHex, borderHex));
        }

        // 保存到配置文件
        AppConfig config = AppConfig.getInstance();
        config.setProperty(AppConst.AppConfigConst.COLOR_BG_HEX, bgHex);
        config.setProperty(AppConst.AppConfigConst.COLOR_TEXT_HEX, textHex);
        config.setProperty(AppConst.AppConfigConst.COLOR_BORDER_HEX, borderHex);
        config.save();

        this.showAlert("✅ 主题已保存", "新主题已应用并保存");
    }

    private void resetTheme() {
        // 恢复默认颜色
        bgColorPicker.setValue(Color.web(AppConst.Style.INIT_RGB_THEME_BG));
        textColorPicker.setValue(Color.web(AppConst.Style.INIT_RGB_THEME_TEXT));
        borderColorPicker.setValue(Color.web(AppConst.Style.INIT_RGB_THEME_BORDER));
        this.applyTheme();
    }

    private void previewTheme() {
        // 实时预览（不保存）
        String bgHex = this.toHex(bgColorPicker.getValue());
        String textHex = this.toHex(textColorPicker.getValue());
        String borderHex = this.toHex(borderColorPicker.getValue());

        if (root != null) {
            root.setStyle(String.format(AppConst.Style.THEME_PREVIRE, bgHex, textHex, borderHex));
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
}