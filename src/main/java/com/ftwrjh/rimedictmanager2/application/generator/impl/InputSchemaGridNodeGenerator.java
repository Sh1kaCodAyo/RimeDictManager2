package com.ftwrjh.rimedictmanager2.application.generator.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ftwrjh.rimedictmanager2.data.constant.AppConst;
import com.ftwrjh.rimedictmanager2.data.constant.DictionaryType;
import com.ftwrjh.rimedictmanager2.data.variable.Dictionary;
import com.ftwrjh.rimedictmanager2.data.variable.InputSchema;
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
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * tab1 - 输入法管理
 */
@Slf4j
public class InputSchemaGridNodeGenerator extends CenterNodeGenerator {
    private InputSchemaGridNodeGenerator() {
    }

    @Getter
    private static final InputSchemaGridNodeGenerator instance = new InputSchemaGridNodeGenerator();

    @Override
    public Node getNode(Stage primaryStage) {
        // 1. 获取 AppContext 中的列表（如果为空则初始化）
        ObservableList<InputSchema> dataList = AppContext.getInstance().getTyped(AppConst.AppContextConst.TABLE_DATA_INPUT_SCHEMA, ObservableList.class);

//        AppContext.getInstance().set(AppConst.ContextKey.TABLE_DATA_INPUT_SCHEMA, dataList);

        // 3. 定义列（使用 PropertyValueFactory 自动匹配属性）
        TableColumn<InputSchema, String> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("inputSchemaId"));
        TableColumn<InputSchema, String> colName = new TableColumn<>("输入法");
        colName.setCellValueFactory(new PropertyValueFactory<>("inputSchemaName"));
        TableColumn<InputSchema, Boolean> colActive = new TableColumn<>("是否启用");
        // 绑定数据到 BooleanProperty
        colActive.setCellValueFactory(cellData -> cellData.getValue().activeProperty());
        colActive.setCellFactory(CheckBoxTableCell.forTableColumn(colActive));
        colActive.setEditable(true);

        // 4. 创建表格并设置数据和列
        TableView<InputSchema> tableView = new TableView<>(dataList);

        tableView.setRowFactory(tv -> this.clickableTableRow());

        // ⭐ 自定义空数据提示
        Label placeholder = new Label("请关联Rime用户文件夹");
        placeholder.setStyle(AppConst.Style.CENTER_TABLE_PLACEHOLDER);
        tableView.setPlaceholder(placeholder);
        tableView.setEditable(true);

        tableView.getColumns().addAll(colId, colName, colActive);
//        tableView.setPadding(new Insets(21, 0, 12, 0));
        tableView.getStyleClass().add(AppConst.Style.CENTER_TABLE_VIEW);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        // 2. 为每列设置 prefWidth 作为权重比例
        colId.setPrefWidth(100);
        colName.setPrefWidth(120);
        return tableView;
    }

    private TableRow<InputSchema> clickableTableRow() {
        TableRow<InputSchema> row = new TableRow<>();
        row.setOnMouseClicked(event -> mouseClickedEvent(event, row));
        return row;
    }
    private void mouseClickedEvent(MouseEvent event, TableRow<InputSchema> row) {
        if (row.isEmpty()) {
            return;
        }
        if (event.getClickCount() == 1) {
            log.info("click once, no action");
        } else if (event.getClickCount() == 2) {
            try {
                this.loadDictionaryListByInputSchema(row);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void loadDictionaryListByInputSchema(TableRow<InputSchema> row) throws IOException {
        InputSchema inputSchema = row.getItem();
        String inputSchemaId = inputSchema.getInputSchemaId();
        String patternStr = "^" + inputSchemaId + ".*\\.dict\\.yaml$";
        AppContext context = AppContext.getInstance();

        ObservableList<Dictionary> observableList = context.getTyped(AppConst.AppContextConst.TABLE_DATA_DICTIONARY, ObservableList.class);
        observableList.clear();

        String workspacePath = context.getTyped(AppConst.AppContextConst.ENV_WORKSPACE, String.class);
        File workspace = new File(workspacePath);
        File[] files = workspace.listFiles();
        List<String> dictionarysByInputSchema = Arrays.stream(files).map(File::getName).filter(name -> name.matches(patternStr)).toList();
        log.info("dictionarysByInputSchema:{}", dictionarysByInputSchema);

        observableList.addAll(dictionarysByInputSchema.stream().map(dictionaryId -> {
            Dictionary dictionary = new Dictionary();
            dictionary.setDictionaryId(dictionaryId);
            String section = this.removePrefixSuffix(dictionaryId, inputSchemaId, AppConst.Path.DICT_FILENAME_SUFFIX);
            if (StringUtils.isEmpty(section)) {
                dictionary.setDictionaryName(DictionaryType.BASE_DICT.getDictName());
            } else if (Strings.CI.contains(section, "user")) {
                dictionary.setDictionaryName(DictionaryType.USER_DCIT.getDictName());
            } else if (Strings.CI.contains(section, "extra")) {
                dictionary.setDictionaryName(DictionaryType.EXTRA_DICT.getDictName());
            }
            return dictionary;
        }).toList());

        this.checkIsActive(observableList);

        // fire按钮2
        context.getTyped(AppConst.AppContextConst.BTN_DICTIONARY_MANAGE, Button.class).fire();
    }

    private String removePrefixSuffix(String str, String prefix, String suffix) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        if (str.startsWith(prefix) && str.endsWith(suffix)) {
            int start = prefix.length();
            int end = str.length() - suffix.length();
            return str.substring(start, end);
        }
        return "";
    }

    private void checkIsActive(List<Dictionary> dictionaryList) throws IOException {
        if (CollectionUtils.isEmpty(dictionaryList)) {
            return;
        }
        AppContext context = AppContext.getInstance();
        // 1. 单独处理基本词库，并读取其中的活动词库列表
        // 1.1 排序列表，并取出第一个词库即基本词库
        dictionaryList.sort(Comparator
                .<Dictionary>comparingInt(dictionary -> DictionaryType.fromDisplayName(dictionary.getDictionaryName()).getPriority())
                .thenComparingInt(dictionary -> DictionaryType.fromDisplayName(dictionary.getDictionaryName()).getPriority())
        );
        Dictionary baseDict = dictionaryList.get(0);
        baseDict.setActive(true);
        final String workspace = context.getTyped(AppConst.AppContextConst.ENV_WORKSPACE, String.class);
        final String fileFullPath = workspace + File.separator + baseDict.getDictionaryId();
        Path baseDictPath = Paths.get(fileFullPath);

        // 1.2 处理文件开头处的 BOM 标志
        List<String> lines = Files.readAllLines(baseDictPath, java.nio.charset.StandardCharsets.UTF_8);
        if (CollectionUtils.isNotEmpty(lines)) {
            String firstLine = lines.get(0);
            if (firstLine.startsWith("\uFEFF")) {
                lines.set(0, firstLine.substring(1));
            }
        }

        // 1.3 找到 YAML 部分的开始和结束
        int startLine = -1;  // "---" 的位置
        int endLine = -1;    // "..." 的位置

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.equals("---") && startLine == -1) {
                startLine = i;
            }
            if (line.equals("...") && startLine != -1 && endLine == -1) {
                endLine = i;
                break;
            }
        }

        // 1.4 截取 YAML 文件中 startLine 到 endLine 之前的部分
        String content = Files.lines(baseDictPath).skip(startLine).limit(endLine - startLine - 1).collect(Collectors.joining(System.lineSeparator()));

        // 1.5 读取截取到的yaml配置
        Yaml yaml = context.getTyped(AppConst.AppContextConst.OBJ_YAML, Yaml.class);
        Map<String, Object> load = yaml.load(content);
        JSONObject json = new JSONObject(load);
        JSONArray jsonArray = json.getJSONArray("import_tables");
        log.info("已开启的词库列表={}", jsonArray);

        // 2. 处理其他词库
        for (int i = 1; i < dictionaryList.size(); i++) {
            Dictionary dictionary = dictionaryList.get(i);
            String dictFileName = this.removePrefixSuffix(dictionary.getDictionaryId(), "", AppConst.Path.DICT_FILENAME_SUFFIX);
            dictionary.setActive(jsonArray.contains(dictFileName));
        }
    }

    @Override
    public String getTableName() {
        return AppConst.UserInterface.LEFT_BTN_INPUT_SCHAMA_MANAGE;
    }
}
