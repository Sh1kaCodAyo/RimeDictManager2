package com.ftwrjh.rimedictmanager2.application.node;

import com.ftwrjh.rimedictmanager2.data.InputSchema;
import com.ftwrjh.rimedictmanager2.data.InputSchema;
import com.ftwrjh.rimedictmanager2.env.AppContext;
import com.ftwrjh.rimedictmanager2.env.DictType;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Getter;

public class DictEntryTableViewGenerator implements NodeGenerator {
    private DictEntryTableViewGenerator() {
    }

    @Getter
    private static final DictEntryTableViewGenerator instance = new DictEntryTableViewGenerator();

    @Override
    public Node getNode(Stage primaryStage) {
        // 1. 获取 AppContext 中的列表（如果为空则初始化）
        ObservableList<InputSchema> dataList = AppContext.getInstance().getInputSchemaObservableList();


        AppContext.getInstance().setInputSchemaObservableList(dataList);
//        btnDirChooser.setOnAction(e -> data.add(1, new InputSchema("new", "sk", 10, 3)));

        // 3. 定义列（使用 PropertyValueFactory 自动匹配属性）
        TableColumn<InputSchema, String> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("inputSchemaId"));
        TableColumn<InputSchema, String> colWord = new TableColumn<>("输入法");
        colWord.setCellValueFactory(new PropertyValueFactory<>("inputSchemaName"));

        // 4. 创建表格并设置数据和列
        TableView<InputSchema> tableView = new TableView<>(dataList);

        // ⭐ 自定义空数据提示
        Label placeholder = new Label("暂无数据，请添加输入法");
        placeholder.setStyle("""
                    -fx-font-size: 16px;
                    -fx-text-fill: #9ca3af;
                    -fx-font-weight: 500;
                    -fx-padding: 0 0 100 0;
                    -fx-alignment: CENTER;
                """);
        tableView.setPlaceholder(placeholder);

        tableView.getColumns().addAll(colId, colWord);
//        tableView.setPadding(new Insets(21, 0, 12, 0));
        tableView.getStyleClass().add("dict-table");
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // 2. 为每列设置 prefWidth 作为权重比例
        colId.setPrefWidth(100);     // 词条列宽权重大
        colWord.setPrefWidth(120);     // 词条列宽权重大
        return tableView;
    }
}
