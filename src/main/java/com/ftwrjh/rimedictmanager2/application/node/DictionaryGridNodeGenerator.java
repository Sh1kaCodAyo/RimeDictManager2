package com.ftwrjh.rimedictmanager2.application.node;

import com.ftwrjh.rimedictmanager2.data.constant.AppConst;
import com.ftwrjh.rimedictmanager2.data.variable.Dictionary;
import com.ftwrjh.rimedictmanager2.data.variable.InputSchema;
import com.ftwrjh.rimedictmanager2.env.AppContext;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DictionaryGridNodeGenerator extends CenterNodeGenerator {
    private DictionaryGridNodeGenerator() {
    }

    @Getter
    private static final DictionaryGridNodeGenerator instance = new DictionaryGridNodeGenerator();

    @Override
    public Node getNode(Stage primaryStage) {
        // 1. 获取 AppContext 中的列表（如果为空则初始化）
        ObservableList<Dictionary> dataList = AppContext.getInstance().getTyped(AppConst.ContextKey.TABLE_DATA_DICTIONARY, ObservableList.class);

//        AppContext.getInstance().set(AppConst.ContextKey.TABLE_DATA_DICTIONARY, dataList);

        // 3. 定义列（使用 PropertyValueFactory 自动匹配属性）
        TableColumn<Dictionary, String> colId = new TableColumn<>("词库ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("dictionaryId"));
        TableColumn<Dictionary, String> colName = new TableColumn<>("词库名称");
        colName.setCellValueFactory(new PropertyValueFactory<>("dictionaryName"));
        TableColumn<Dictionary, Boolean> colActive = new TableColumn<>("是否启用");
        colActive.setCellValueFactory(cellData -> cellData.getValue().activeProperty());
        colActive.setCellFactory(CheckBoxTableCell.forTableColumn(colActive));
        colActive.setEditable(true);

        // 4. 创建表格并设置数据和列
        TableView<Dictionary> tableView = new TableView<>(dataList);

        // ⭐ 自定义空数据提示
        Label placeholder = new Label("请关联Rime用户文件夹");
        placeholder.setStyle(AppConst.Style.CENTER_TABLE_PLACEHOLDER);
        tableView.setPlaceholder(placeholder);
        tableView.setEditable(true);

        tableView.getColumns().addAll(colId, colName, colActive);
//        tableView.setPadding(new Insets(21, 0, 12, 0));
        tableView.getStyleClass().add("dict-table");
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colId.setPrefWidth(300);
        colName.setPrefWidth(100);
        colActive.setPrefWidth(100);
        return tableView;
    }
}
