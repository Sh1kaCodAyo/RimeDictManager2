package com.ftwrjh.rimedictmanager2.application.node;

import com.ftwrjh.rimedictmanager2.env.AppConfig;
import com.ftwrjh.rimedictmanager2.data.constant.AppConst;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LeftComponentGenerator implements NodeGenerator {
    private Button selectedButton;

    private LeftComponentGenerator() {
    }

    @Getter
    private static final LeftComponentGenerator instance = new LeftComponentGenerator();

    @Override
    public Node getNode(Stage primaryStage) {
        // Logo
        VBox sidebarContainer = new VBox();
        Label logo = new Label("📧 全部工具");
        logo.getStyleClass().add("logo");
        sidebarContainer.getChildren().add(logo);

        // 分组标题
        Label labelManage = new Label("管理工具");
        labelManage.getStyleClass().add("section-title");
        sidebarContainer.getChildren().add(labelManage);

        // ⭐ 调用 createNavItem() 创建按钮
        sidebarContainer.getChildren().addAll(
                createNavItem(AppConst.Emoji.KEYBOARD, "输入法管理"),
                createNavItem(AppConst.Emoji.BOOKS, "词库管理"),
                createNavItem(AppConst.Emoji.OPENED_BOOK, "词条管理")
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
        sidebarContainer.setPrefWidth(220);
        sidebarContainer.setSpacing(2);
//        sidebarContainer.getChildren().stream().forEach(item -> item.sett);
        sidebarContainer.setPadding(new Insets(12, 12, 12, 12));
        return sidebarContainer;
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
