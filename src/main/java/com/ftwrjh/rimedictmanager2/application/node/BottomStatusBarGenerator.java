package com.ftwrjh.rimedictmanager2.application.node;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class BottomStatusBarGenerator implements NodeGenerator{
    @Override
    public Node getNode(Stage primaryStage) {
        // 1. 创建一个 HBox 作为状态栏容器
        HBox statusBar = new HBox(10);
        statusBar.setPadding(new Insets(5, 10, 5, 10));
        statusBar.setAlignment(Pos.CENTER_LEFT);
        statusBar.setStyle("-fx-background-color: #e9ecef; -fx-border-color: #ced4da; -fx-border-width: 1 0 0 0;");

        // 2. 创建状态栏元素
        Label leftStatus = new Label("就绪");
        Label rightStatus = new Label("用户: admin");
        ProgressBar progress = new ProgressBar(0.3);
        progress.setPrefWidth(100);

        // 3. 让左侧标签可以伸展，推动右侧元素靠右
        HBox.setHgrow(leftStatus, Priority.ALWAYS);

        // 4. 将元素放入状态栏
        statusBar.getChildren().addAll(leftStatus, progress, rightStatus);
        return statusBar;
    }
}
