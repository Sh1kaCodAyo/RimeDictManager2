module com.ftwrjh.rimedictmanager2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.slf4j;

    requires static lombok;

    exports com.ftwrjh.rimedictmanager2.application;
    exports com.ftwrjh.rimedictmanager2.controller;

    opens com.ftwrjh.rimedictmanager2.application to javafx.fxml;
    opens com.ftwrjh.rimedictmanager2.controller to javafx.fxml;
    opens com.ftwrjh.rimedictmanager2.data to javafx.base;
}