package com.ftwrjh.rimedictmanager2.data.constant;

import javafx.scene.control.Alert;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ServiceResult {
    SUCCESS(true, Alert.AlertType.INFORMATION, "成功", "已成功保存所有配置"),
    UNKNOWN(false, Alert.AlertType.ERROR, "保存失败", "未知错误");

    private final boolean success;
    private final Alert.AlertType alertType;
    private final String title;
    private final String message;
}
