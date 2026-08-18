package com.ftwrjh.rimedictmanager2.application.node;

import com.ftwrjh.rimedictmanager2.env.Const;
import com.ftwrjh.rimedictmanager2.env.GlobalContext;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import lombok.Getter;

public class BottomStatusBarGenerator implements NodeGenerator {
    private BottomStatusBarGenerator() {
    }

    @Getter
    private static final BottomStatusBarGenerator instance = new BottomStatusBarGenerator();

    @Override
    public Node getNode(Stage primaryStage) {
        HBox statusBar = new HBox(10);
        statusBar.setPadding(new Insets(5, 10, 5, 10));
        statusBar.setAlignment(Pos.CENTER_LEFT);
        statusBar.setStyle(Const.Style.BOTTOM_STATUS_BAR);

        Label leftStatus = new Label("请先选择Rime用户文件夹");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Label rightStatus = new Label("用户: admin");

        statusBar.getChildren().addAll(leftStatus, spacer, rightStatus);
        return statusBar;
    }

    public void setStatusLeft(String msg) {
        BorderPane root = GlobalContext.Global.getContext().getObject("root", BorderPane.class);
        HBox statusBar = (HBox) root.getBottom();
        statusBar.getChildren().set(0, new Label(msg));
    }

    public void setStatusRight(String msg) {
        BorderPane root = GlobalContext.Global.getContext().getObject("root", BorderPane.class);
        HBox statusBar = (HBox) root.getBottom();
        statusBar.getChildren().set(2, new Label(msg));
    }
}
