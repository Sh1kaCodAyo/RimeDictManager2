package com.ftwrjh.rimedictmanager2.service;

import javafx.scene.control.Alert;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ServiceResult {
    SUCCESS(true, Alert.AlertType.INFORMATION, "成功", "已成功保存所有配置"),
    FAIL(false, Alert.AlertType.ERROR, "aaaa错误", "aaaa错误");

    private boolean success;
    private Alert.AlertType alertType;
    private String title;
    private String message;
}
