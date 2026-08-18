package com.ftwrjh.rimedictmanager2.application.node;

import com.ftwrjh.rimedictmanager2.controller.DirectoryChooser;
import com.ftwrjh.rimedictmanager2.env.Const;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public class TopMenuBarGenerator implements NodeGenerator {
    private TopMenuBarGenerator() {
    }

    @Getter
    private static final TopMenuBarGenerator instance = new TopMenuBarGenerator();

    @Override
    public Node getNode(Stage primaryStage) {
        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("文件(_F)");
        MenuItem menuItemChoose = new MenuItem("选择主目录(_O)...");
        MenuItem menuSave = new MenuItem("保存(_S)");
        MenuItem menuItemExit = new MenuItem("退出(_Q)");

        menuItemChoose.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        menuSave.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        menuItemExit.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));

        menuItemChoose.setOnAction(DirectoryChooser.getActionEventEventHandler(primaryStage));
        menuSave.setOnAction(event -> log.info("save.."));
        menuItemExit.setOnAction(e -> primaryStage.close());

        menuFile.getItems().addAll(menuItemChoose, menuSave, new SeparatorMenuItem(), menuItemExit);

        Menu menuHelp = new Menu("帮助(_H)");
        MenuItem menuItemPortal = new MenuItem("主页(_P)");
        MenuItem menuItemAbout = new MenuItem("关于(_A)");

        menuItemPortal.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));

        menuItemAbout.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About RimeDictManager2");
            alert.setHeaderText("RimeDictManager2 by Ftwrjh, Version 1.0");
            alert.setContentText("Copyright (c) 2026");
            alert.showAndWait();  // 阻塞等待用户点击
        });
        menuItemPortal.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI(Const.WEBSITE));
            } catch (IOException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        });
        menuHelp.getItems().addAll(menuItemPortal, menuItemAbout);

        // 将菜单添加到菜单栏
        menuBar.getMenus().addAll(menuFile, menuHelp);
        menuBar.getStyleClass().add("menu-bar");
        return menuBar;
    }
}
