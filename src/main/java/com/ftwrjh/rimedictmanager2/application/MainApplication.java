package com.ftwrjh.rimedictmanager2.application;

import com.ftwrjh.rimedictmanager2.handler.ButtonDirectoryChooser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 加载 FXML（控制器会自动创建并调用 initialize）
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        BorderPane root = loader.load();

        Button btn = new Button("选择文件夹");
        btn.setOnAction(ButtonDirectoryChooser.getHandler(primaryStage, btn));
        root.setCenter(btn);

        Scene scene = new Scene(root, 900, 700);
        URL cssUrl = getClass().getResource("sidebar.css");
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        }
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}