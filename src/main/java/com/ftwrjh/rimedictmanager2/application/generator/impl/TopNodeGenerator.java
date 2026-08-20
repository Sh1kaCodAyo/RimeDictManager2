package com.ftwrjh.rimedictmanager2.application.generator.impl;

import com.ftwrjh.rimedictmanager2.application.generator.NodeGenerator;
import com.ftwrjh.rimedictmanager2.data.constant.AppConst;
import com.ftwrjh.rimedictmanager2.service.DirectoryChooser;
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
public class TopNodeGenerator implements NodeGenerator {
    private TopNodeGenerator() {
    }

    @Getter
    private static final TopNodeGenerator instance = new TopNodeGenerator();

    @Override
    public Node getNode(Stage primaryStage) {
        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("文件(_F)");
        MenuItem menuItemChoose = new MenuItem("关联Rime用户文件夹(_O)...");
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
        Menu menuHelpPage = new Menu("相关网页(_W)");
        MenuItem menuItemPortal = new MenuItem("RimeDictManager2(_M)");
        MenuItem menuItemRdh = new MenuItem("RimeDictHelper2(_H)");
        MenuItem menuItemRimePortal = new MenuItem("Rime输入法主页(_R)");
        menuHelpPage.getItems().addAll(menuItemPortal, menuItemRdh, new SeparatorMenuItem(), menuItemRimePortal);
        MenuItem menuItemAbout = new MenuItem("关于(_A)");

        menuItemPortal.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI(AppConst.Link.WEBSITE_HOME));
            } catch (IOException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        });
        menuItemRdh.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI(AppConst.Link.WEBSITE_RDH));
            } catch (IOException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        });
        menuItemRimePortal.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI(AppConst.Link.WEBSITE_RIME));
            } catch (IOException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        });
        menuItemAbout.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(AppConst.About.TITLE);
            alert.setHeaderText(AppConst.About.HEADER);
            alert.setContentText(AppConst.About.CONTENT);
            alert.showAndWait();
        });
        menuHelp.getItems().addAll(menuHelpPage, new SeparatorMenuItem(), menuItemAbout);

        // 将菜单添加到菜单栏
        menuBar.getMenus().addAll(menuFile, menuHelp);
        menuBar.getStyleClass().add("menu-bar");
        return menuBar;
    }
}
