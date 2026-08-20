package com.ftwrjh.rimedictmanager2.application.generator.impl;

import com.ftwrjh.rimedictmanager2.application.Test;
import com.ftwrjh.rimedictmanager2.application.generator.NodeGenerator;
import com.ftwrjh.rimedictmanager2.data.constant.AppConst;
import com.ftwrjh.rimedictmanager2.service.DirectoryChooser;
import com.ftwrjh.rimedictmanager2.service.PersistenceService;
import com.ftwrjh.rimedictmanager2.data.constant.ServiceResult;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RightNodeGenerator implements NodeGenerator {
    private RightNodeGenerator() {
    }

    @Getter
    private static final RightNodeGenerator instance = new RightNodeGenerator();

    @Override
    public Node getNode(Stage primaryStage) {
        VBox rightPanel = new VBox(15);
        rightPanel.setPadding(new Insets(20));
        rightPanel.setAlignment(Pos.TOP_CENTER);
        rightPanel.setStyle(AppConst.Style.RIGHT_VBOX);
//        Button btnChooseRimeHomeDir = new Button("📂 选择目录");
//        btnChooseRimeHomeDir.getStyleClass().add("action-button");
//        btnChooseRimeHomeDir.setMaxWidth(Double.MAX_VALUE);
//        btnChooseRimeHomeDir.setOnAction(DirectoryChooser.getActionEventEventHandler(primaryStage));

        Button btnSave = new Button("💾 全部保存"); // 💾
        btnSave.getStyleClass().add("action-button");
        btnSave.setMaxWidth(Double.MAX_VALUE);
        btnSave.setOnAction(event -> {
            Test.showMemory();
            ServiceResult saveResult = PersistenceService.saveAll();
            Alert alert = new Alert(saveResult.getAlertType());
            alert.setTitle("保存结果");
            alert.setHeaderText(saveResult.getTitle());
            alert.setContentText(saveResult.getMessage());
            alert.showAndWait();
        });
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

        rightPanel.getChildren().addAll(btnSave);

        return rightPanel;
    }

}
