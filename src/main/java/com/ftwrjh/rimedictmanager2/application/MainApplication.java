package com.ftwrjh.rimedictmanager2.application;

import com.ftwrjh.rimedictmanager2.application.node.BottomStatusBarGenerator;
import com.ftwrjh.rimedictmanager2.application.node.BtnDirChooserGenerator;
import com.ftwrjh.rimedictmanager2.application.node.DictEntryTableViewGenerator;
import com.ftwrjh.rimedictmanager2.application.node.TopMenuBarGenerator;
import com.ftwrjh.rimedictmanager2.env.Const;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 加载 FXML（控制器会自动创建并调用 initialize）
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Const.Path.FXML_MAIN));
        BorderPane root = loader.load();

        root.setTop(new TopMenuBarGenerator().getNode(primaryStage));
        root.setCenter(new DictEntryTableViewGenerator().getNode(primaryStage));
        root.setRight(new BtnDirChooserGenerator().getNode(primaryStage));
        root.setBottom(new BottomStatusBarGenerator().getNode(primaryStage));


        Scene scene = new Scene(root, 900, 700);
        URL cssUrl = getClass().getResource(Const.Path.CSS_SIDEBAR);
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        }

        Image icon = new Image(getClass().getResourceAsStream(Const.Path.ICON));
        primaryStage.getIcons().add(icon);

        primaryStage.setScene(scene);
        primaryStage.setTitle(Const.APP_NAME);
        primaryStage.show();
    }


}