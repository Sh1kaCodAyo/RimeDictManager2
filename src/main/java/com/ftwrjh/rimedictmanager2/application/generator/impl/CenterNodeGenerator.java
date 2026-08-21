package com.ftwrjh.rimedictmanager2.application.generator.impl;

import com.ftwrjh.rimedictmanager2.application.generator.NodeGenerator;
import com.ftwrjh.rimedictmanager2.data.constant.AppConst;
import com.ftwrjh.rimedictmanager2.env.AppContext;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 生成中部组件，即表格区域
 */
public abstract class CenterNodeGenerator implements NodeGenerator {
    public abstract String getTableName();

    /**
     * 切换页面
     *
     * @param primaryStage
     */
    public void switchTabBtn(Stage primaryStage) {
        AppContext context = AppContext.getInstance();
        Node node = this.getNode(primaryStage);
        String tabName = this.getTableName();
        StackPane center = context.getTyped(AppConst.AppContextConst.NODE_CENTER_STACK_PANE, StackPane.class);
        ObservableList<Node> children = center.getChildren();
        children.clear();
        children.add(node);
        Label tabTitle = new Label(tabName);
        tabTitle.getStyleClass().add("tab-title");
        VBox left = context.getTyped(AppConst.AppContextConst.NODE_LEFT_TOP_TITLE, VBox.class);
        left.getChildren().set(0, tabTitle);
    }

}
