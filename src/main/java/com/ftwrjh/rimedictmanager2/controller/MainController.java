package com.ftwrjh.rimedictmanager2.controller;

import com.ftwrjh.rimedictmanager2.env.AppConfig;
import com.ftwrjh.rimedictmanager2.env.Const;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignM;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class MainController implements Initializable {

    @FXML
    private VBox sidebarContainer;  // 对应 FXML 中的 fx:id

    private Button selectedButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // ⭐ 在初始化方法中构建侧边栏
        buildSidebar();
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