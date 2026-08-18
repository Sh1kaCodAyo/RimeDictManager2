package com.ftwrjh.rimedictmanager2.controller;

import com.alibaba.fastjson2.JSONObject;
import com.ftwrjh.rimedictmanager2.env.AppConfig;
import com.ftwrjh.rimedictmanager2.env.Const;
import com.ftwrjh.rimedictmanager2.env.GlobalContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class MainController implements Initializable {
    @FXML
    private VBox sidebarContainer;  // 对应 FXML 中的 fx:id

    private Button selectedButton;

    @FXML
    private Menu menuFile;

    @FXML
    private void print(ActionEvent event) {
        System.out.println("按钮被点击了！");
        // 可以在这里执行业务逻辑
        JSONObject mainConfig = GlobalContext.Global.getContext().getJSONObject("mainConfig");
        log.info("config={}", mainConfig);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // ⭐ 在初始化方法中构建侧边栏
        buildSidebar();
        initMenu();
    }

    private void initMenu() {
        MenuItem menuItemNew = new MenuItem("新建");
        MenuItem menuItemOpen = new MenuItem("打开...");
        MenuItem menuItemExit = new MenuItem("退出");

        // 为菜单项添加事件
        menuItemNew.setOnAction(e -> System.out.println("新建文件"));
        menuItemOpen.setOnAction(e -> System.out.println("打开文件"));
//        BorderPane rootPane = GlobalContext.Global.getContext().getObject("root", BorderPane.class);
//        Stage stage = (Stage) rootPane.getScene().getWindow();
//        menuItemExit.setOnAction(e -> stage.close());

        // 将菜单项添加到 "文件" 菜单
        menuFile.getItems().addAll(menuItemNew, menuItemOpen, new SeparatorMenuItem(), menuItemExit);
        menuFile.setMnemonicParsing(true);
    }

    private void buildSidebar() {
        // Logo
        Label logo = new Label("📧 邮箱管理");
        logo.getStyleClass().add("logo");
        sidebarContainer.getChildren().add(logo);

        // 分组标题
        Label title1 = new Label("全部邮件");
        title1.getStyleClass().add("section-title");
        sidebarContainer.getChildren().add(title1);

        // ⭐ 调用 createNavItem() 创建按钮
        sidebarContainer.getChildren().addAll(
                createNavItem(Const.Emoji.KEYBOARD, "输入法管理"),
                createNavItem(Const.Emoji.BOOKS, "词库管理"),
                createNavItem(Const.Emoji.OPENED_BOOK, "词条管理")
        );

        // 分组标题
        Label title2 = new Label("标签");
        title2.getStyleClass().add("section-title");
        sidebarContainer.getChildren().add(title2);

        sidebarContainer.getChildren().addAll(
                createNavItem("💼", "工作"),
                createNavItem("👤", "个人"),
                createNavItem("📬", "订阅")
        );

        // 底部留白
        VBox spacer = new VBox();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        sidebarContainer.getChildren().add(spacer);

        // 底部设置
        sidebarContainer.getChildren().add(createNavItem("⚙️", "设置"));
    }

    private Button createNavItem(String icon, String text) {
        Label iconLabel = new Label(icon);
        iconLabel.getStyleClass().add("icon-label");

        Label textLabel = new Label(text);

        HBox content = new HBox(8);
        content.setAlignment(Pos.CENTER_LEFT);
        content.getChildren().addAll(iconLabel, textLabel);

        Button btn = new Button();
        btn.setGraphic(content);
        btn.getStyleClass().add("nav-item");
        btn.setMaxWidth(Double.MAX_VALUE);

        btn.setOnAction(e -> {
            if (selectedButton != null) {
                selectedButton.getStyleClass().remove("selected");
            }
            btn.getStyleClass().add("selected");
            selectedButton = btn;

            log.info("选中: {}", text);
            log.info("test:{}", AppConfig.getInstance().getProperty("testkey"));
            // 切换右侧内容...
        });

//        FontIcon icon1 = new FontIcon(MaterialDesignM.MAGNIFY_PLUS);
//        btn.setGraphic(icon1);

        return btn;
    }
}