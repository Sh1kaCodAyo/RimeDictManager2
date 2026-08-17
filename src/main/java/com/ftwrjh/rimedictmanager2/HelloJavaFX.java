package com.ftwrjh.rimedictmanager2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 第一个 JavaFX 程序：简单的问候应用
 * 功能：输入姓名，点击按钮后显示问候语
 */
public class HelloJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. 创建 UI 控件
        Label titleLabel = new Label("请输入您的名字：");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        TextField nameInput = new TextField();
        nameInput.setPromptText("在此输入姓名...");
        nameInput.setMaxWidth(200);

        Button greetButton = new Button("打招呼");
        greetButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");

        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #2196F3;");

        // 2. 设置按钮点击事件（核心逻辑）
        greetButton.setOnAction(event -> {
            String name = nameInput.getText().trim();
            if (name.isEmpty()) {
                resultLabel.setText("⚠️ 请输入姓名！");
                resultLabel.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
            } else {
                resultLabel.setText("👋 你好，" + name + "！欢迎来到 JavaFX！");
                resultLabel.setStyle("-fx-text-fill: #2196F3; -fx-font-size: 18px; -fx-font-weight: bold;");
            }
        });

        // 3. 按下回车键也可以触发同样的逻辑
        nameInput.setOnAction(event -> greetButton.fire());

        // 4. 将控件放入布局容器（垂直盒子）
        VBox root = new VBox(15); // 间距 15px
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.getChildren().addAll(titleLabel, nameInput, greetButton, resultLabel);

        // 5. 创建场景并设置窗口
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("我的第一个 JavaFX 程序");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // 启动 JavaFX 应用
    }
}