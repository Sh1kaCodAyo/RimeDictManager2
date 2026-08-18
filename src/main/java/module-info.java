module com.ftwrjh.rimedictmanager2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.slf4j;
    requires org.kordamp.bootstrapfx.core;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;

    requires static lombok;
    requires org.kordamp.ikonli.materialdesign2;
    requires org.yaml.snakeyaml;
    requires com.alibaba.fastjson2;

    exports com.ftwrjh.rimedictmanager2.application;
    exports com.ftwrjh.rimedictmanager2.controller;

    opens com.ftwrjh.rimedictmanager2.controller to javafx.fxml;
    opens com.ftwrjh.rimedictmanager2.data to javafx.base;
    opens com.ftwrjh.rimedictmanager2.application to javafx.base, javafx.fxml;
//    opens com.ftwrjh.rimedictmanager2.controller to javafx.fxml;
}