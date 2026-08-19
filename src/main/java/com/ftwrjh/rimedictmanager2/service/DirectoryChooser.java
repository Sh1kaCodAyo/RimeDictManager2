package com.ftwrjh.rimedictmanager2.service;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class DirectoryChooser {
    public static EventHandler<ActionEvent> getActionEventEventHandler(Stage primaryStage) {
        return event -> {
            javafx.stage.DirectoryChooser directoryChooser = new javafx.stage.DirectoryChooser();
            directoryChooser.setTitle("请选择Rime用户文件夹");
            directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File selectedDirectory = directoryChooser.showDialog(primaryStage);

            if (selectedDirectory != null) {
                WorkspaceService.load(selectedDirectory);
            } else {
                log.info("用户取消了选择");
            }
        };
    }
}
