package com.ftwrjh.rimedictmanager2.application.node;

import com.ftwrjh.rimedictmanager2.data.constant.AppConst;
import com.ftwrjh.rimedictmanager2.data.variable.Dictionary;
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

        AppContext.getInstance().set(AppConst.ContextKey.TABLE_DATA_DICTIONARY, dataList);

        // 3. 定义列（使用 PropertyValueFactory 自动匹配属性）
        TableColumn<Dictionary, String> tcInputSchemaId = new TableColumn<>("输入法ID");
        tcInputSchemaId.setCellValueFactory(new PropertyValueFactory<>("inputSchemaId"));
        TableColumn<Dictionary, String> tcInputSchemaName = new TableColumn<>("输入法名称");
        tcInputSchemaName.setCellValueFactory(new PropertyValueFactory<>("inputSchemaName"));
//        TableColumn<Dictionary, Boolean> colAvailable = new TableColumn<>("是否启用");
        // 绑定数据到 BooleanProperty
//        colAvailable.setCellValueFactory(cellData -> cellData.getValue().availableProperty());
//        colAvailable.setCellFactory(CheckBoxTableCell.forTableColumn(colAvailable));
//        colAvailable.setEditable(true);

        // 4. 创建表格并设置数据和列
        TableView<Dictionary> tableView = new TableView<>(dataList);

        // ⭐ 自定义空数据提示
        Label placeholder = new Label("请关联Rime用户文件夹");
        placeholder.setStyle(AppConst.Style.CENTER_TABLE_PLACEHOLDER);
        tableView.setPlaceholder(placeholder);
        tableView.setEditable(true);

        tableView.getColumns().addAll(tcInputSchemaId, tcInputSchemaName);
//        tableView.setPadding(new Insets(21, 0, 12, 0));
        tableView.getStyleClass().add("dict-table");
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // 2. 为每列设置 prefWidth 作为权重比例
        tcInputSchemaId.setPrefWidth(100);     // 词条列宽权重大
        tcInputSchemaName.setPrefWidth(120);     // 词条列宽权重大
        return tableView;
    }
}
