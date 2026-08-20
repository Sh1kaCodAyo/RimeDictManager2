package com.ftwrjh.rimedictmanager2.application.node;

import com.ftwrjh.rimedictmanager2.data.constant.AppConst;
import com.ftwrjh.rimedictmanager2.data.variable.DictionaryEntry;
import com.ftwrjh.rimedictmanager2.env.AppContext;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

@Slf4j
public class DictionaryEntryGridNodeGenerator extends CenterNodeGenerator {
    private DictionaryEntryGridNodeGenerator() {
    }

    @Getter
    private static final DictionaryEntryGridNodeGenerator instance = new DictionaryEntryGridNodeGenerator();

    @Override
    public Node getNode(Stage primaryStage) {
        // 1. 获取 AppContext 中的列表（如果为空则初始化）
        ObservableList<DictionaryEntry> dataList = AppContext.getInstance().getTyped(AppConst.ContextKey.TABLE_DATA_DICTIONARY_ENTRY, ObservableList.class);

//        AppContext.getInstance().set(AppConst.ContextKey.TABLE_DATA_DICTIONARY_ENTRY, dataList);

        // 3. 定义列（使用 PropertyValueFactory 自动匹配属性）
        TableColumn<DictionaryEntry, String> tcWord = new TableColumn<>("词条");
        tcWord.setCellValueFactory(new PropertyValueFactory<>("inputSchemaId"));
        TableColumn<DictionaryEntry, String> tcCode = new TableColumn<>("编码");
        tcCode.setCellValueFactory(new PropertyValueFactory<>("inputSchemaName"));
        TableColumn<DictionaryEntry, String> tcWeight = new TableColumn<>("权重");
        tcWeight.setCellValueFactory(new PropertyValueFactory<>("inputSchemaName"));
        TableColumn<DictionaryEntry, String> tcLineNumber = new TableColumn<>("行号");
        tcLineNumber.setCellValueFactory(new PropertyValueFactory<>("inputSchemaName"));
        TableColumn<DictionaryEntry, String> tcSource = new TableColumn<>("来源");
        tcSource.setCellValueFactory(new PropertyValueFactory<>("inputSchemaName"));

        // 4. 创建表格并设置数据和列
        TableView<DictionaryEntry> tableView = new TableView<>(dataList);

        // ⭐ 自定义空数据提示
        Label placeholder = new Label("请关联Rime用户文件夹");
        placeholder.setStyle(AppConst.Style.CENTER_TABLE_PLACEHOLDER);
        tableView.setPlaceholder(placeholder);
        tableView.setEditable(true);

        tableView.getColumns().addAll(tcWord, tcCode, tcWeight, tcSource, tcLineNumber);
//        tableView.setPadding(new Insets(21, 0, 12, 0));
        tableView.getStyleClass().add("dict-table");
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // 2. 为每列设置 prefWidth 作为权重比例
        tcWord.setPrefWidth(30);
        tcCode.setPrefWidth(20);
        tcWeight.setPrefWidth(20);
        tcSource.setPrefWidth(80);
        tcLineNumber.setPrefWidth(30);
        return tableView;
    }
}
