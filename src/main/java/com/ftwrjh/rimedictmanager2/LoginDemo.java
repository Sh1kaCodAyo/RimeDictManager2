package com.ftwrjh.rimedictmanager2;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        // 用户名行
        Label userLabel = new Label("用户名：");
        TextField userField = new TextField();
        grid.add(userLabel, 0, 0);
        grid.add(userField, 1, 0);

        // 密码行
        Label passLabel = new Label("密码：");
        PasswordField passField = new PasswordField();
        grid.add(passLabel, 0, 1);
        grid.add(passField, 1, 1);

        // 登录按钮（跨两列居中）
        Button loginBtn = new Button("登录");
        grid.add(loginBtn, 0, 2, 2, 1);
        GridPane.setHalignment(loginBtn, HPos.CENTER);

        // 点击事件
        loginBtn.setOnAction(e -> {
            System.out.println("用户名：" + userField.getText());
            System.out.println("密码：" + passField.getText());
        });

        Scene scene = new Scene(grid, 300, 150);
        primaryStage.setTitle("登录");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}