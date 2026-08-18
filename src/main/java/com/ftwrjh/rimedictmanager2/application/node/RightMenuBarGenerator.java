package com.ftwrjh.rimedictmanager2.application.node;

import com.ftwrjh.rimedictmanager2.controller.DirectoryChooser;
import com.ftwrjh.rimedictmanager2.env.Const;
import com.ftwrjh.rimedictmanager2.env.GlobalContext;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

@Slf4j
public class RightMenuBarGenerator implements NodeGenerator {
    private RightMenuBarGenerator() {
    }

    @Getter
    private static final RightMenuBarGenerator instance = new RightMenuBarGenerator();

    @Override
    public Node getNode(Stage primaryStage) {
        VBox rightPanel = new VBox(15);
        rightPanel.setPadding(new Insets(20));
        rightPanel.setAlignment(Pos.TOP_CENTER);
        rightPanel.setStyle("-fx-background-color: #f8fafc;");
        Button btnChooseRimeHomeDir = new Button("📂 选择目录");
        btnChooseRimeHomeDir.getStyleClass().add("action-button");
        btnChooseRimeHomeDir.setMaxWidth(Double.MAX_VALUE);
        btnChooseRimeHomeDir.setOnAction(DirectoryChooser.getActionEventEventHandler(primaryStage));

        Button btnSave = new Button("💾 全部保存"); // 💾
        btnSave.getStyleClass().add("action-button");
        btnSave.setMaxWidth(Double.MAX_VALUE);
        btnSave.setOnAction(event -> log.info("save000"));
//
//        Button btn1 = new Button("📄 新建词条");
//        Button btn2 = new Button("📂 导入词库");
//        Button btn3 = new Button("📊 统计分析");
//
// 给右侧按钮应用这个样式
//        btn1.getStyleClass().add("action-button");
//        btn2.getStyleClass().add("action-button");
//        btn3.getStyleClass().add("action-button");
//
// 可以设置按钮宽度一致
//        btn1.setMaxWidth(Double.MAX_VALUE);
//        btn2.setMaxWidth(Double.MAX_VALUE);
//        btn3.setMaxWidth(Double.MAX_VALUE);

        rightPanel.getChildren().addAll(btnChooseRimeHomeDir, btnSave);

        return rightPanel;
    }

}
