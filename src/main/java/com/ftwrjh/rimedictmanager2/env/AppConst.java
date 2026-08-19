package com.ftwrjh.rimedictmanager2.env;

public class AppConst {
    public static final String APP_NAME = "RimeDictManager2";

    public static class ContextKey {
        public static final String TABLE_DATA_SCHEMA = "tableData:schema";
        public static final String OBJ_YAML = "obj:yaml";
        public static final String ENV_RIME_HOME_DIR = "env:rimeHomeDir";
    }

    public static class Path {
        public static final String FXML_MAIN = "main-view.fxml";
        public static final String CSS_MAIN = "/css/main.css";
        public static final String ICON_MAIN = "/images/RimeDictManager2.png";
        public static final String YAML_DEFAULT_CUSTOM = "default.custom.yaml";
        public static final String DICT_FILENAME_SUFFIX = ".schema.yaml";
    }

    public static class Link {
        public static final String WEBSITE_HOME = "https://github.com/Sh1kaCodAyo/RimeDictManager2";
        public static final String WEBSITE_RDH = "https://github.com/Sh1kaCodAyo/RimeDictHelper2";
        public static final String WEBSITE_RIME = "https://rime.im/";
    }

    public static class Emoji {
        public static final String KEYBOARD = "⌨"; // ⌨️
        public static final String PAPER_PEN = "📝";
        public static final String BOOKS = "📚"; // 📚
        public static final String OPENED_BOOK = "📖";

    }

    public static class Style {
        public static final String CENTER_TABLE_PLACEHOLDER = "-fx-font-size: 16px; -fx-text-fill: #9ca3af; -fx-font-weight: 500; -fx-padding: 0 0 100 0; -fx-alignment: CENTER;";
        public static final String RIGHT_VBOX = "-fx-background-color: #f8fafc;";
        public static final String BOTTOM_STATUS_BAR = "-fx-background-color: #e9ecef; -fx-border-color: #ced4da; -fx-border-width: 1 0 0 0;";
    }
}
