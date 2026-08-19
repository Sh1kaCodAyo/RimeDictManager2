package com.ftwrjh.rimedictmanager2.application;

import com.ftwrjh.rimedictmanager2.application.node.*;
import com.ftwrjh.rimedictmanager2.env.AppContext;
import com.ftwrjh.rimedictmanager2.env.Const;
import com.ftwrjh.rimedictmanager2.env.GlobalContext;
import javafx.application.Application;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.yaml.snakeyaml.Yaml;

public class MainApplication extends Application {

    @Override
    public void init() throws Exception {
        super.init();
        AppContext.getInstance().set(Const.ContextKey.OBJ_YAML, new Yaml());
        AppContext.getInstance().set(Const.ContextKey.TABLE_DATA_SCHEMA, new SimpleListProperty<>(FXCollections.observableArrayList()));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 加载 FXML（控制器会自动创建并调用 initialize）
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Const.Path.FXML_MAIN));
        BorderPane root = loader.load();
//        GlobalContext.Global.getContext().put("root", root);
        AppContext.getInstance().set("root", root);

        root.setTop(TopComponentGenerator.getInstance().getNode(primaryStage));
        root.setLeft(LeftComponentGenerator.getInstance().getNode(primaryStage));
        root.setCenter(CenterComponentGenerator.getInstance().getNode(primaryStage));
        root.setRight(RightComponentGenerator.getInstance().getNode(primaryStage));
        root.setBottom(BottomComponentGenerator.getInstance().getNode(primaryStage));

        Scene scene = new Scene(root, 900, 700);
        scene.getStylesheets().add(getClass().getResource(Const.Path.CSS_MAIN).toExternalForm());

        Image icon = new Image(getClass().getResourceAsStream(Const.Path.ICON));
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene);
        primaryStage.setTitle(Const.APP_NAME);
        primaryStage.show();
    }

}