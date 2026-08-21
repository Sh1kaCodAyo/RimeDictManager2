package com.ftwrjh.rimedictmanager2.data.constant;

/**
 * 常量值
 */
public final class AppConst {
    public static final String APP_NAME = "RimeDictManager2";

    public static class AppConfigConst {
        // CONFIG_FILE
        public static final String USER_HOME = "user.home";
        public static final String USER_CONFIG_HOME = ".rdm2";
        public static final String CONFIG_FILENAME = "rdm2.properties";
        // CONFIG_FILE_CONTENT
        public static final String WORKSPACE = "rimeHomeDir";
        public static final String COLOR_BG_HEX = "theme.bg";
        public static final String COLOR_TEXT_HEX = "theme.text";
        public static final String COLOR_BORDER_HEX = "theme.border";
    }

    public static class AppContextConst {
        public static final String ENV_WORKSPACE = "env:workspace";
        public static final String OBJ_YAML = "obj:yaml";
        // NODE
        public static final String NODE_ROOT = "node:root";
        public static final String NODE_CENTER = "node:center";
        public static final String NODE_LEFT = "node:left";
        public static final String NODE_LEFT_SIDEBAR = "node:leftSidebar";
        // LEFT_BUTTON
        public static final String BTN_INPUT_SCHEMA_MANAGE = "btn:inputSchemaManage";
        public static final String BTN_DICTIONARY_MANAGE = "btn:dictionaryManage";
        public static final String BTN_DICTIONARY_ENTRY_MANAGE = "btn:dictionaryEntryManage";
        // CENTER_TABLE_ObservableList
        public static final String TABLE_DATA_INPUT_SCHEMA = "tableData:inputSchema";
        public static final String TABLE_DATA_DICTIONARY = "tableData:dictionary";
        public static final String TABLE_DATA_DICTIONARY_ENTRY = "tableData:dictionaryEntry";
    }

    public static class UserInterface {
        // LEFT_SIDEBAR
        public static final String GROUP1_MANAGE_TOOLS = "管理工具";
        public static final String LEFT_BTN_INPUT_SCHAMA_MANAGE = "输入法管理";
        public static final String LEFT_BTN_DICTIONARY_MANAGE = "词库管理";
        public static final String LEFT_BTN_DICTIONARY_ENTRY_MANAGE = "词条管理";
        public static final String GROUP2_SETTINGS = "设置";
        public static final String LEFT_BTN_CUSTOM_THEME = "自定义主题";
        public static final String LEFT_BTN_SELECT_WORKSPACE = "选择主目录";
    }

    public static class Path {
        public static final String FXML_MAIN = "main-view.fxml";
        public static final String FXML_SETTINGS = "/com/ftwrjh/rimedictmanager2/application/settings-view.fxml";
        public static final String CSS_MAIN = "/css/main.css";
        public static final String ICON_MAIN = "/images/RimeDictManager2.png";
        public static final String YAML_DEFAULT_CUSTOM = "default.custom.yaml";
        public static final String DICT_SCHEMA_FILENAME_SUFFIX = ".schema.yaml";
        public static final String DICT_FILENAME_SUFFIX = ".dict.yaml";
    }

    public static class Link {
        public static final String WEBSITE_HOME = "https://github.com/Sh1kaCodAyo/RimeDictManager2";
        public static final String WEBSITE_RDH = "https://github.com/Sh1kaCodAyo/RimeDictHelper2";
        public static final String WEBSITE_RIME = "https://rime.im/";
    }

    public static class About {
        public static final String TITLE = "About RimeDictManager2";
        public static final String HEADER = "RimeDictManager2 by Ftwrjh, Version 1.0";
        public static final String CONTENT = "Copyright (c) 2026";
    }

    public static class Emoji {
        public static final String KEYBOARD = "⌨"; // ⌨️
        public static final String PAPER_PEN = "📝";
        public static final String BOOKS = "📚"; // 📚
        public static final String OPENED_BOOK = "📖";
        public static final String PALETTE = "🎨";
        public static final String FOLDER = "📂";
    }

    public static class Style {
        // STYLE_CONTENT
        public static final String CENTER_TABLE_PLACEHOLDER = "-fx-font-size: 16px; -fx-text-fill: #9ca3af; -fx-font-weight: 500; -fx-padding: 0 0 100 0; -fx-alignment: CENTER;";
        public static final String RIGHT_VBOX = "-fx-background-color: #f8fafc;";
        public static final String BOTTOM_STATUS_BAR = "-fx-background-color: #e9ecef; -fx-border-color: #ced4da; -fx-border-width: 1 0 0 0;";
        // STYLE_CLASS
        public static final String LEFT_MENU_GROUP_TITLE = "section-title";
        public static final String CENTER_TABLE_VIEW = "dict-table";
        public static final String LEFT_TOP_CURRENTTAB_TITLE = "tab-title";
    }
}
