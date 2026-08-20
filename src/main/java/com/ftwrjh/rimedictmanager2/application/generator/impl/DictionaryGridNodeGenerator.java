package com.ftwrjh.rimedictmanager2.application.generator.impl;

import com.ftwrjh.rimedictmanager2.data.constant.AppConst;
import com.ftwrjh.rimedictmanager2.data.variable.Dictionary;
import com.ftwrjh.rimedictmanager2.data.variable.DictionaryEntry;
import com.ftwrjh.rimedictmanager2.env.AppContext;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * tab2 - 词库管理
 */
@Slf4j
public class DictionaryGridNodeGenerator extends CenterNodeGenerator {
    private DictionaryGridNodeGenerator() {
    }

    @Getter
    private static final DictionaryGridNodeGenerator instance = new DictionaryGridNodeGenerator();

    @Override
    public Node getNode(Stage primaryStage) {
        // 1. 获取 AppContext 中的列表（如果为空则初始化）
        ObservableList<Dictionary> dataList = AppContext.getInstance().getTyped(AppConst.AppContextConst.TABLE_DATA_DICTIONARY, ObservableList.class);

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

        tableView.setRowFactory(tv -> this.clickableTableRow());

        // ⭐ 自定义空数据提示
        Label placeholder = new Label("当前未选择输入法");
        placeholder.setStyle(AppConst.Style.CENTER_TABLE_PLACEHOLDER);
        tableView.setPlaceholder(placeholder);
        tableView.setEditable(true);

        tableView.getColumns().addAll(colId, colName, colActive);
//        tableView.setPadding(new Insets(21, 0, 12, 0));
        tableView.getStyleClass().add("dict-table");
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        colId.setPrefWidth(300);
        colName.setPrefWidth(100);
        colActive.setPrefWidth(100);
        return tableView;
    }

    private TableRow<Dictionary> clickableTableRow() {
        TableRow<Dictionary> row = new TableRow<>();
        row.setOnMouseClicked(event -> this.mouseClickedEvent(event, row));
        return row;
    }

    private void mouseClickedEvent(MouseEvent event, TableRow<Dictionary> row) {
        if (row.isEmpty()) {
            return;
        }
        if (event.getClickCount() == 1) {
            log.info("click once, no action");
        } else if (event.getClickCount() == 2) {
            try {
                this.loadDictionaryEntryListByDictionary(row);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void loadDictionaryEntryListByDictionary(TableRow<Dictionary> row) throws IOException {
        Dictionary dictionary = row.getItem();
        ObservableList<DictionaryEntry> observableList = AppContext.getInstance().getTyped(AppConst.AppContextConst.TABLE_DATA_DICTIONARY_ENTRY, ObservableList.class);
        observableList.clear();


        log.info(dictionary.toString()); // id = wubi86_jidian_user.dict.yaml

        String workspacePath = AppContext.getInstance().getTyped(AppConst.AppContextConst.ENV_RIME_HOME_DIR, String.class);
        String dictionaryId = dictionary.getDictionaryId();
        String dictionaryFilePathStr = workspacePath + File.separator + dictionaryId;

        Path baseDictPath = Paths.get(dictionaryFilePathStr);
        List<String> lines = Files.readAllLines(baseDictPath, java.nio.charset.StandardCharsets.UTF_8);
        if (CollectionUtils.isNotEmpty(lines)) {
            String firstLine = lines.get(0);
            if (firstLine.startsWith("\uFEFF")) {
                lines.set(0, firstLine.substring(1));
            }
        }

        // 1.3 找到 YAML 部分的开始和结束
        int startLine = -1;  // "---" 的位置

        boolean start = false;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.startsWith("#")) {
                continue;
            }
            if (start) {
                DictionaryEntry dictionaryEntry = new DictionaryEntry(line);
                dictionaryEntry.setSource(dictionaryId);
                dictionaryEntry.setLineNumber(i + 1);
                dictionaryEntry.setFullPath(dictionaryFilePathStr);
                observableList.addAll(dictionaryEntry);
            } else if (line.equals("...") && startLine == -1) {
                start = true;
            }
        }

        // fire按钮3
        AppContext.getInstance().getTyped(AppConst.AppContextConst.BTN_DICTIONARY_ENTRY_MANAGE, Button.class).fire();
    }
}
