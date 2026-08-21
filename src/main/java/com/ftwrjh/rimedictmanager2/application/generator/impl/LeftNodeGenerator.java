package com.ftwrjh.rimedictmanager2.application.generator.impl;

import com.ftwrjh.rimedictmanager2.application.generator.NodeGenerator;
import com.ftwrjh.rimedictmanager2.data.constant.AppConst;
import com.ftwrjh.rimedictmanager2.env.AppContext;
import com.ftwrjh.rimedictmanager2.service.DirectoryChooser;
import com.ftwrjh.rimedictmanager2.service.SettingsController;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LeftNodeGenerator implements NodeGenerator {
    private Button selectedButton;
    private static final PseudoClass SELECTED = PseudoClass.getPseudoClass("selected");

    private LeftNodeGenerator() {
    }

    @Getter
    private static final LeftNodeGenerator instance = new LeftNodeGenerator();

    @Override
    public Node getNode(Stage primaryStage) {
        // Group1 - Manage Tools
        Label group1manageTools = new Label(AppConst.UserInterface.GROUP1_MANAGE_TOOLS);
        group1manageTools.getStyleClass().add(AppConst.Style.LEFT_MENU_GROUP_TITLE);

        Button btnInputSchemaManage = createNavItem(AppConst.Emoji.KEYBOARD, AppConst.UserInterface.LEFT_BTN_INPUT_SCHAMA_MANAGE,
                () -> InputSchemaGridNodeGenerator.getInstance().switchTabBtn(primaryStage));
        Button btnDictionaryManage = createNavItem(AppConst.Emoji.BOOKS, AppConst.UserInterface.LEFT_BTN_DICTIONARY_MANAGE,
                () -> DictionaryGridNodeGenerator.getInstance().switchTabBtn(primaryStage));
        Button btnDictionaryEntryManage = createNavItem(AppConst.Emoji.OPENED_BOOK, AppConst.UserInterface.LEFT_BTN_DICTIONARY_ENTRY_MANAGE,
                () -> DictionaryEntryGridNodeGenerator.getInstance().switchTabBtn(primaryStage));

        // Group2 - Settings
        Label group2settings = new Label(AppConst.UserInterface.GROUP2_SETTINGS);
        group2settings.getStyleClass().add(AppConst.Style.LEFT_MENU_GROUP_TITLE);

        Button btnSetTheme = createNavItem(AppConst.Emoji.PALETTE, AppConst.UserInterface.LEFT_BTN_CUSTOM_THEME,
                () -> this.openThemeSettings(primaryStage));
        Button btnChooseWorkspace = createNavItem(AppConst.Emoji.FOLDER, AppConst.UserInterface.LEFT_BTN_SELECT_WORKSPACE, null);
        btnChooseWorkspace.setOnAction(DirectoryChooser.getActionEventEventHandler(primaryStage));

        // 创建容器，并将所有组件放入
        VBox sidebarContainer = new VBox();
        sidebarContainer.getChildren().addAll(group1manageTools,
                btnInputSchemaManage, btnDictionaryManage, btnDictionaryEntryManage,
                group2settings,
                btnChooseWorkspace, btnSetTheme);
//        sidebarContainer.getChildren().addAll(btnSetTheme, createNavItem("⚙", "设置", null)); // ⚙️
        sidebarContainer.setPrefWidth(220);
        sidebarContainer.setSpacing(2);
        sidebarContainer.setPadding(new Insets(0, 12, 12, 12));

        // 将相关组件置入缓存，方便其他位置使用
        AppContext context = AppContext.getInstance();
        context.set(AppConst.AppContextConst.NODE_LEFT_SIDEBAR, sidebarContainer);
        context.set(AppConst.AppContextConst.BTN_INPUT_SCHEMA_MANAGE, btnInputSchemaManage);
        context.set(AppConst.AppContextConst.BTN_DICTIONARY_MANAGE, btnDictionaryManage);
        context.set(AppConst.AppContextConst.BTN_DICTIONARY_ENTRY_MANAGE, btnDictionaryEntryManage);

        return sidebarContainer;
    }

    private void openThemeSettings(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(AppConst.Path.FXML_SETTINGS));
            Parent root = loader.load();

            // 传入主界面的根节点，以便实时预览
            SettingsController controller = loader.getController();
            controller.setRoot(primaryStage.getScene().getRoot());

            AppContext context = AppContext.getInstance();
            StackPane center = context.getTyped(AppConst.AppContextConst.NODE_CENTER_STACK_PANE, StackPane.class);
            ObservableList<Node> children = center.getChildren();
            children.clear();
            children.add(root);
            root.getStyleClass().add(AppConst.Style.CENTER_TABLE_VIEW);
            Label tabTitle = new Label(AppConst.UserInterface.LEFT_BTN_CUSTOM_THEME);
            tabTitle.getStyleClass().add(AppConst.Style.LEFT_TOP_CURRENTTAB_TITLE);
            VBox left = context.getTyped(AppConst.AppContextConst.NODE_LEFT_TOP_TITLE, VBox.class);
            left.getChildren().set(0, tabTitle);
        } catch (Exception e) {
            log.error("页面加载异常", e);
        }
    }

    private Button createNavItem(String icon, String text, Runnable onAction) {
        Label iconLabel = new Label(icon);
        iconLabel.getStyleClass().add("icon-label");

        Label textLabel = new Label(text);
        textLabel.getStyleClass().add("nav-text");  // ⭐ 添加自定义样式类

        HBox content = new HBox(8);
        content.setAlignment(Pos.CENTER_LEFT);
        content.getChildren().addAll(iconLabel, textLabel);

        Button btn = new Button();
        btn.setGraphic(content);
        btn.getStyleClass().add("nav-item");
        btn.setMaxWidth(Double.MAX_VALUE);

        btn.setOnAction(e -> {
            // 取消旧的选中
            if (selectedButton != null && selectedButton != btn) {
                selectedButton.pseudoClassStateChanged(SELECTED, false);
            }

            // ⭐ 设置新的选中：启用伪类
            btn.pseudoClassStateChanged(SELECTED, true);
            selectedButton = btn;

            if (onAction != null) {
                onAction.run();
            }
            log.info("选中: {}", text);
        });
        return btn;
    }
}
