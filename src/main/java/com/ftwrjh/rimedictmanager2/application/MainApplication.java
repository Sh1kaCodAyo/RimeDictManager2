package com.ftwrjh.rimedictmanager2.application;

import com.ftwrjh.rimedictmanager2.application.node.*;
import com.ftwrjh.rimedictmanager2.env.Const;
import com.ftwrjh.rimedictmanager2.env.GlobalContext;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 加载 FXML（控制器会自动创建并调用 initialize）
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Const.Path.FXML_MAIN));
        BorderPane root = loader.load();
        GlobalContext.Global.getContext().put("root", root);

        root.setTop(TopMenuBarGenerator.getInstance().getNode(primaryStage));
        root.setLeft(LeftMenuBarGenerator.getInstance().getNode(primaryStage));
        root.setCenter(DictEntryTableViewGenerator.getInstance().getNode(primaryStage));
//        ((VBox) root.getRight()).getChildren().add(BtnDirChooserGenerator.getInstance().getNode(primaryStage));
        root.setRight(RightMenuBarGenerator.getInstance().getNode(primaryStage));
        root.setBottom(BottomStatusBarGenerator.getInstance().getNode(primaryStage));


        Scene scene = new Scene(root, 900, 700);
        scene.getStylesheets().addAll(
                getClass().getResource(Const.Path.CSS_MAIN).toExternalForm()
        );

        Image icon = new Image(getClass().getResourceAsStream(Const.Path.ICON));
        primaryStage.getIcons().add(icon);

        primaryStage.setScene(scene);
        primaryStage.setTitle(Const.APP_NAME);
        primaryStage.show();

//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("信息");
//        alert.setHeaderText("标题内容（可选）");
//        alert.setContentText("这是提示框的具体内容。");
//        alert.showAndWait();  // 阻塞等待用户点击
    }


}