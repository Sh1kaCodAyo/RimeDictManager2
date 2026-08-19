package com.ftwrjh.rimedictmanager2.application.node;

import com.ftwrjh.rimedictmanager2.data.constant.AppConst;
import com.ftwrjh.rimedictmanager2.data.variable.InputSchema;
import com.ftwrjh.rimedictmanager2.env.AppContext;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputSchemaGridNodeGenerator extends CenterNodeGenerator {
    private InputSchemaGridNodeGenerator() {
    }

    @Getter
    private static final InputSchemaGridNodeGenerator instance = new InputSchemaGridNodeGenerator();

    @Override
    public Node getNode(Stage primaryStage) {
        // 1. 获取 AppContext 中的列表（如果为空则初始化）
        ObservableList<InputSchema> dataList = AppContext.getInstance().getTyped(AppConst.ContextKey.TABLE_DATA_INPUT_SCHEMA, ObservableList.class);

        AppContext.getInstance().set(AppConst.ContextKey.TABLE_DATA_INPUT_SCHEMA, dataList);

        // 3. 定义列（使用 PropertyValueFactory 自动匹配属性）
        TableColumn<InputSchema, String> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("inputSchemaId"));
        TableColumn<InputSchema, String> colWord = new TableColumn<>("输入法");
        colWord.setCellValueFactory(new PropertyValueFactory<>("inputSchemaName"));
        TableColumn<InputSchema, Boolean> colAvailable = new TableColumn<>("是否启用");
        // 绑定数据到 BooleanProperty
        colAvailable.setCellValueFactory(cellData -> cellData.getValue().availableProperty());
        colAvailable.setCellFactory(CheckBoxTableCell.forTableColumn(colAvailable));
        colAvailable.setEditable(true);

        // 4. 创建表格并设置数据和列
        TableView<InputSchema> tableView = new TableView<>(dataList);

        tableView.setRowFactory(tv -> {
            TableRow<InputSchema> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    log.info("click");
                }
                if (!row.isEmpty() && event.getClickCount() == 2) {
                    log.info("double click");
                    AppContext.getInstance().getTyped(AppConst.ContextKey.BTN_DICTIONARY_MANAGE, Button.class).fire();
                }
            });
            return row;
        });

        // ⭐ 自定义空数据提示
        Label placeholder = new Label("请关联Rime用户文件夹");
        placeholder.setStyle(AppConst.Style.CENTER_TABLE_PLACEHOLDER);
        tableView.setPlaceholder(placeholder);
        tableView.setEditable(true);

        tableView.getColumns().addAll(colId, colWord, colAvailable);
//        tableView.setPadding(new Insets(21, 0, 12, 0));
        tableView.getStyleClass().add("dict-table");
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // 2. 为每列设置 prefWidth 作为权重比例
        colId.setPrefWidth(100);     // 词条列宽权重大
        colWord.setPrefWidth(120);     // 词条列宽权重大
        return tableView;
    }
}
