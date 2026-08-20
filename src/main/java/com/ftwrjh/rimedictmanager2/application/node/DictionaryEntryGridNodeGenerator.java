package com.ftwrjh.rimedictmanager2.application.node;

import com.ftwrjh.rimedictmanager2.data.constant.AppConst;
import com.ftwrjh.rimedictmanager2.data.variable.DictionaryEntry;
import com.ftwrjh.rimedictmanager2.env.AppContext;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * tab3 - 词条管理
 */
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
        tcWord.setCellValueFactory(new PropertyValueFactory<>("word"));
        TableColumn<DictionaryEntry, String> tcCode = new TableColumn<>("编码");
        tcCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        tcCode.setEditable(true);
        TableColumn<DictionaryEntry, Integer> tcWeight = new TableColumn<>("权重");
        tcWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        tcWeight.setEditable(true);
        TableColumn<DictionaryEntry, String> tcSource = new TableColumn<>("来源");
        tcSource.setCellValueFactory(new PropertyValueFactory<>("source"));
        TableColumn<DictionaryEntry, Integer> tcLineNumber = new TableColumn<>("行号");
        tcLineNumber.setCellValueFactory(new PropertyValueFactory<>("lineNumber"));

        // 4. 创建表格并设置数据和列
        TableView<DictionaryEntry> tableView = new TableView<>(dataList);
        tcCode.setCellFactory(LimitedTextFieldTableCell.lettersOnly(4));
        tcWeight.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        tcCode.setOnEditCommit(event -> {
            DictionaryEntry entry = event.getRowValue();
            String newValue = event.getNewValue();
            entry.setCode(newValue);
            log.info("词条「{}」编码已更新为「{}」", entry.getWord(), newValue);
        });
        tcWeight.setOnEditCommit(event -> {
            DictionaryEntry entry = event.getRowValue();
            Integer newValue = event.getNewValue();
            entry.setWeight(newValue);
            log.info("词条「{}」权重已更新为「{}」", entry.getWord(), newValue);
        });


        tcSource.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String fileName, boolean empty) {
                super.updateItem(fileName, empty);

                if (empty || fileName == null) {
                    setText(null);
                    setTooltip(null);
                } else {
                    // 显示文件名
                    setText(fileName);

                    // 获取当前行的完整数据对象
                    DictionaryEntry entry = getTableRow().getItem();
                    if (entry != null) {
                        String fullPath = entry.getFullPath();  // 获取完整路径
                        // 创建 Tooltip 并设置
                        Tooltip tooltip = new Tooltip(fullPath);
                        tooltip.setShowDelay(Duration.millis(300));
                        setTooltip(tooltip);
                    }
                }
            }
        });

        // ⭐ 自定义空数据提示
        Label placeholder = new Label("当前未选择输入法词库或词库无词条");
        placeholder.setStyle(AppConst.Style.CENTER_TABLE_PLACEHOLDER);
        tableView.setPlaceholder(placeholder);
        tableView.setEditable(true);

        tableView.getColumns().addAll(tcWord, tcCode, tcWeight/*, tcSource*/, tcLineNumber);
//        tableView.setPadding(new Insets(21, 0, 12, 0));
        tableView.getStyleClass().add("dict-table");
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // 2. 为每列设置 prefWidth 作为权重比例
        tcWord.setPrefWidth(50);
        tcCode.setPrefWidth(50);
        tcWeight.setPrefWidth(50);
//        tcSource.setPrefWidth(200);
        tcLineNumber.setPrefWidth(50);
        return tableView;
    }
}
