module com.ftwrjh.rimedictmanager2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.ftwrjh.rimedictmanager2 to javafx.fxml;
    exports com.ftwrjh.rimedictmanager2;
}