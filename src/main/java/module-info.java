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
    requires java.desktop;
    requires org.apache.commons.lang3;
    requires org.apache.commons.collections4;

    exports com.ftwrjh.rimedictmanager2.application;
    exports com.ftwrjh.rimedictmanager2.service;

    opens com.ftwrjh.rimedictmanager2.data.variable to javafx.base;
    opens com.ftwrjh.rimedictmanager2.application to javafx.base, javafx.fxml;
    opens com.ftwrjh.rimedictmanager2.service to javafx.base, javafx.fxml;
    exports com.ftwrjh.rimedictmanager2.data.constant;
    opens com.ftwrjh.rimedictmanager2.data.constant to javafx.base, javafx.fxml;
    //    opens com.ftwrjh.rimedictmanager2.service to javafx.fxml;
}