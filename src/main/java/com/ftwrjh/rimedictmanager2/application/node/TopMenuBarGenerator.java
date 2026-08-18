package com.ftwrjh.rimedictmanager2.application.node;

import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.Stage;

public class TopMenuBarGenerator implements NodeGenerator{
    @Override
    public Node getNode(Stage primaryStage) {
        // 1. 创建一个菜单栏
        MenuBar menuBar = new MenuBar();

        // --- 2. 创建菜单和菜单项 ---

        // 创建 "文件" 菜单
        Menu menuFile = new Menu("文件");
        MenuItem menuItemNew = new MenuItem("新建");
        MenuItem menuItemOpen = new MenuItem("打开...");
        MenuItem menuItemExit = new MenuItem("退出");

        // 为菜单项添加事件
        menuItemNew.setOnAction(e -> System.out.println("新建文件"));
        menuItemOpen.setOnAction(e -> System.out.println("打开文件"));
        menuItemExit.setOnAction(e -> primaryStage.close());

        // 将菜单项添加到 "文件" 菜单
        menuFile.getItems().addAll(menuItemNew, menuItemOpen, new SeparatorMenuItem(), menuItemExit);

        // 创建 "帮助" 菜单
        Menu menuHelp = new Menu("帮助");
        MenuItem menuItemAbout = new MenuItem("关于");
        menuItemAbout.setOnAction(e -> System.out.println("关于本软件"));
        menuHelp.getItems().add(menuItemAbout);

        // 将菜单添加到菜单栏
        menuBar.getMenus().addAll(menuFile, menuHelp);

        return menuBar;    }
}
