package com.ftwrjh.rimedictmanager2.application;

import com.ftwrjh.rimedictmanager2.data.DictEntry;
import com.ftwrjh.rimedictmanager2.env.Const;
import com.ftwrjh.rimedictmanager2.env.DictType;
import com.ftwrjh.rimedictmanager2.handler.ButtonDirectoryChooser;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

        Button btnDirChooser = new Button(Const.UserInterface.BTN_DIR_CHOOSER);
        btnDirChooser.setOnAction(ButtonDirectoryChooser.getHandler(primaryStage, btnDirChooser));
        root.setRight(btnDirChooser);

        // 2. 准备数据
        ObservableList<DictEntry> data = FXCollections.observableArrayList(
                new DictEntry("可", "sk", 10, 1, DictType.BASE_DICT),
                new DictEntry("楫", "sk", 10, 2, DictType.USER_DCIT),
                new DictEntry("杏", "sk", 10, 3, DictType.EXTRA_DICT)
        );
//        btnDirChooser.setOnAction(e -> data.add(1, new DictEntry("new", "sk", 10, 3)));

        // 3. 定义列（使用 PropertyValueFactory 自动匹配属性）
        TableColumn<DictEntry, String> colWord = new TableColumn<>("词条");
        colWord.setCellValueFactory(new PropertyValueFactory<>("word"));

        TableColumn<DictEntry, String> colCode = new TableColumn<>("编码");
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));

        TableColumn<DictEntry, String> colWeight = new TableColumn<>("权重");
        colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));

        TableColumn<DictEntry, String> colDictType = new TableColumn<>("所属词库");
        colDictType.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDictType().getDictName()));

        TableColumn<DictEntry, String> colLineNumber = new TableColumn<>("行号");
        colLineNumber.setCellValueFactory(new PropertyValueFactory<>("lineNumber"));

        // 4. 创建表格并设置数据和列
        TableView<DictEntry> tableView = new TableView<>(data);
        tableView.getColumns().addAll(colWord, colCode, colWeight, colDictType, colLineNumber);

        root.setCenter(tableView);


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