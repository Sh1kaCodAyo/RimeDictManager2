package com.ftwrjh.rimedictmanager2.application.node;

import com.ftwrjh.rimedictmanager2.env.AppContext;
import com.ftwrjh.rimedictmanager2.data.constant.AppConst;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import lombok.Getter;

public class BottomNodeGenerator implements NodeGenerator {
    private BottomNodeGenerator() {
    }

    @Getter
    private static final BottomNodeGenerator instance = new BottomNodeGenerator();

    @Override
    public Node getNode(Stage primaryStage) {
        HBox statusBar = new HBox(10);
        statusBar.setPadding(new Insets(5, 10, 5, 10));
        statusBar.setAlignment(Pos.CENTER_LEFT);
        statusBar.setStyle(AppConst.Style.BOTTOM_STATUS_BAR);

        Label leftStatus = new Label("请先选择Rime用户文件夹");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Label rightStatus = new Label("用户: admin");

        statusBar.getChildren().addAll(leftStatus, spacer, rightStatus);
        return statusBar;
    }

    public void setStatusLeft(String msg) {
        BorderPane root = AppContext.getInstance().getTyped("root", BorderPane.class);
        HBox statusBar = (HBox) root.getBottom();
        statusBar.getChildren().set(0, new Label(msg));
    }

    public void setStatusRight(String msg) {
        BorderPane root = AppContext.getInstance().getTyped("root", BorderPane.class);
        HBox statusBar = (HBox) root.getBottom();
        statusBar.getChildren().set(2, new Label(msg));
    }
}
