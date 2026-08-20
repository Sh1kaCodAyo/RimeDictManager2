package com.ftwrjh.rimedictmanager2.application;

import com.ftwrjh.rimedictmanager2.application.generator.impl.*;
import com.ftwrjh.rimedictmanager2.data.constant.AppConst;
import com.ftwrjh.rimedictmanager2.env.AppConfig;
import com.ftwrjh.rimedictmanager2.env.AppContext;
import com.ftwrjh.rimedictmanager2.service.WorkspaceService;
import javafx.application.Application;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

public class MainApplication extends Application {

    /**
     * 初级初始化，时机较早
     *
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
        super.init();
        AppContext.getInstance().set(AppConst.AppContextConst.OBJ_YAML, new Yaml());
        AppContext.getInstance().set(AppConst.AppContextConst.TABLE_DATA_INPUT_SCHEMA, new SimpleListProperty<>(FXCollections.observableArrayList()));
        AppContext.getInstance().set(AppConst.AppContextConst.TABLE_DATA_DICTIONARY, new SimpleListProperty<>(FXCollections.observableArrayList()));
        AppContext.getInstance().set(AppConst.AppContextConst.TABLE_DATA_DICTIONARY_ENTRY, new SimpleListProperty<>(FXCollections.observableArrayList()));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 加载 FXML（控制器会自动创建并调用 initialize）
        FXMLLoader loader = new FXMLLoader(getClass().getResource(AppConst.Path.FXML_MAIN));
        BorderPane root = loader.load();
        AppContext.getInstance().set("root", root);

        root.setTop(TopNodeGenerator.getInstance().getNode(primaryStage));
        VBox left = new VBox();
//        root.setLeft(LeftNodeGenerator.getInstance().getNode(primaryStage));
        root.setLeft(left);
        AppContext.getInstance().set(AppConst.AppContextConst.NODE_LEFT_TOP_TITLE, left);
        Label tabTitle = new Label();
        tabTitle.getStyleClass().add("tab-title");
//        sidebarContainer.getChildren().add(tabTitle);
        left.getChildren().addAll(tabTitle, LeftNodeGenerator.getInstance().getNode(primaryStage));
        StackPane center = new StackPane();
        root.setCenter(center);
        center.getChildren().add(InputSchemaGridNodeGenerator.getInstance().getNode(primaryStage));
        AppContext.getInstance().set(AppConst.AppContextConst.NODE_CENTER_STACK_PANE, center);
        root.setRight(RightNodeGenerator.getInstance().getNode(primaryStage));
        root.setBottom(BottomNodeGenerator.getInstance().getNode(primaryStage));

        Scene scene = new Scene(root, 900, 700);
        scene.getStylesheets().add(getClass().getResource(AppConst.Path.CSS_MAIN).toExternalForm());

        Image icon = new Image(getClass().getResourceAsStream(AppConst.Path.ICON_MAIN));
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene);
        primaryStage.setTitle(AppConst.APP_NAME);
        primaryStage.show();

        this.initSettings(root);
    }

    /**
     * 次级初始化，时机靠后
     *
     * @param root
     */
    private void initSettings(Pane root) {
        // 初始化工作主目录
        String property = com.ftwrjh.rimedictmanager2.env.AppConfig.getInstance().getProperty(AppConst.AppConfigConst.RIME_HOME_DIR);
        if (StringUtils.isNotEmpty(property)) {
            WorkspaceService.load(property);
        }

        // 初始化主题色
        AppConfig config = com.ftwrjh.rimedictmanager2.env.AppConfig.getInstance();
        String bgHex = config.getProperty(AppConst.AppConfigConst.COLOR_BG_HEX);
        String textHex = config.getProperty(AppConst.AppConfigConst.COLOR_TEXT_HEX);
        String borderHex = config.getProperty(AppConst.AppConfigConst.COLOR_BORDER_HEX);

        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotEmpty(bgHex)) {
            sb.append(String.format("-item-bg-hover: %s;", bgHex));
        }
        if (StringUtils.isNotEmpty(textHex)) {
            sb.append(String.format("-text-hover: %s;", textHex));
        }
        if (StringUtils.isNotEmpty(borderHex)) {
            sb.append(String.format("-border-color: %s;", borderHex));
        }
        root.setStyle(sb.toString());
        AppContext.getInstance().getTyped(AppConst.AppContextConst.BTN_INPUT_SCHEMA_MANAGE, Button.class).fire();
    }
}