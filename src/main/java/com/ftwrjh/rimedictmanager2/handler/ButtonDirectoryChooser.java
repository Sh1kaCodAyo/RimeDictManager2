package com.ftwrjh.rimedictmanager2.handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class ButtonDirectoryChooser {
    public static EventHandler<ActionEvent> getHandler(Stage stage, Button btn) {
        return e -> {
            // 1. 创建 DirectoryChooser
            DirectoryChooser directoryChooser = new DirectoryChooser();

            // 2. 设置标题
            directoryChooser.setTitle("请选择目标文件夹");

            // 3. 设置初始目录（可选）
            directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));

            // 4. 显示对话框并获取用户选择的文件夹
            File selectedDirectory = directoryChooser.showDialog(stage);

            // 5. 处理结果
            if (selectedDirectory != null) {
                log.info("选中的文件夹: {}", selectedDirectory.getAbsolutePath());
                btn.setText("已选择: " + selectedDirectory.getName());
            } else {
                log.info("用户取消了选择");
            }
        };
    }
}
