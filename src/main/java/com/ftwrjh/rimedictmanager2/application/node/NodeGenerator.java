package com.ftwrjh.rimedictmanager2.application.node;

import javafx.scene.Node;
import javafx.stage.Stage;

public interface NodeGenerator {
    Node getNode(Stage primaryStage);
}
