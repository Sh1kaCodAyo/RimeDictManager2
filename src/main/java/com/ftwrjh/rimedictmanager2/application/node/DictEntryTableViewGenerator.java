package com.ftwrjh.rimedictmanager2.application.node;

import com.ftwrjh.rimedictmanager2.data.DictEntry;
import com.ftwrjh.rimedictmanager2.env.DictType;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DictEntryTableViewGenerator implements NodeGenerator {
    @Override
    public Node getNode(Stage primaryStage) {
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
        return tableView;
    }
}
