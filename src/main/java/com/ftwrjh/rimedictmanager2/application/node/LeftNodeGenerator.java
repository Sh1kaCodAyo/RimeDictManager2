package com.ftwrjh.rimedictmanager2.application.node;

import com.ftwrjh.rimedictmanager2.data.constant.AppConst;
import com.ftwrjh.rimedictmanager2.env.AppConfig;
import com.ftwrjh.rimedictmanager2.env.AppContext;
import com.ftwrjh.rimedictmanager2.service.ColorUtils;
import com.ftwrjh.rimedictmanager2.service.SettingsController;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

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
        // Logo
        VBox sidebarContainer = new VBox();
        Label logo = new Label("📧 全部工具");
        logo.getStyleClass().add("logo");
        sidebarContainer.getChildren().add(logo);

        // 分组标题
        Label labelManage = new Label("管理工具");
        labelManage.getStyleClass().add("section-title");
        sidebarContainer.getChildren().add(labelManage);
        Button btnISManage = createNavItem(
                AppConst.Emoji.KEYBOARD,
                "输入法管理",
                () -> switchTab(InputSchemaGridNodeGenerator.getInstance().getNode(primaryStage), "输入法管理", sidebarContainer)
        );

        Button btnDictManage = createNavItem(
                AppConst.Emoji.BOOKS,
                "词库管理",
                () -> switchTab(DictionaryGridNodeGenerator.getInstance().getNode(primaryStage), "词库管理", sidebarContainer)
        );

        Button btnDEManage = createNavItem(
                AppConst.Emoji.OPENED_BOOK,
                "词条管理",
                () -> switchTab(DictionaryEntryGridNodeGenerator.getInstance().getNode(primaryStage), "词条管理", sidebarContainer)
        );
        AppContext.getInstance().set(AppConst.ContextKey.BTN_INPUT_SCHEMA_MANAGE, btnISManage);
        AppContext.getInstance().set(AppConst.ContextKey.BTN_DICTIONARY_MANAGE, btnDictManage);
        AppContext.getInstance().set(AppConst.ContextKey.BTN_DICTIONARY_ENTRY_MANAGE, btnDEManage);

        // ⭐ 调用 createNavItem() 创建按钮
        sidebarContainer.getChildren().addAll(btnISManage, btnDictManage, btnDEManage);

        // 分组标题
        Label title2 = new Label("标签");
        title2.getStyleClass().add("section-title");
        sidebarContainer.getChildren().add(title2);

        sidebarContainer.getChildren().addAll(
                createNavItem("💼", "工作", null),
                createNavItem("👤", "个人", null),
                createNavItem("📬", "订阅", null)
        );

        // 底部留白
        VBox spacer = new VBox();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        sidebarContainer.getChildren().add(spacer);

        // 底部设置
        // 在侧边栏底部
//        Button themeBtn = createNavItem("🎨", "自定义主题", getRunnable(primaryStage));
//        Button themeBtn = createNavItem("🎨", "自定义主题", () -> openThemeSettings(primaryStage));
        Button themeBtn = createNavItem("🎨", "自定义主题", () -> openThemeSettings(primaryStage, sidebarContainer));
        sidebarContainer.getChildren().addAll(themeBtn, createNavItem("⚙️", "设置", null));
        sidebarContainer.setPrefWidth(220);
        sidebarContainer.setSpacing(2);
        sidebarContainer.setPadding(new Insets(12, 12, 12, 12));
        AppContext.getInstance().set(AppConst.ContextKey.NODE_LEFT_SIDEBAR, sidebarContainer);
        return sidebarContainer;
    }
//
//    private static Runnable getRunnable(Stage primaryStage) {
//        return () -> {
//
//            // 创建 ColorPicker 对话框
//            ColorPicker picker = new ColorPicker(Color.web("#3d6e87"));
//
//            picker.setValue(Color.web("#3d6e87"));  // 初始颜色
//            picker.setPromptText("选择颜色");
//            picker.setPrefWidth(200);
//
//            // 监听颜色变化
//            picker.valueProperty().addListener((obs, oldVal, newVal) -> {
//                System.out.println("颜色变化: " + ColorUtils.toHex(newVal));
//            });
//
//            Dialog<Color> dialog = new Dialog<>();
//            dialog.setTitle("选择主题色");
//            dialog.getDialogPane().setContent(picker);
//            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
//
//            dialog.setResultConverter(button -> {
//                if (button == ButtonType.OK) {
//                    return picker.getValue();
//                }
//                return null;
//            });
//
//            Optional<Color> result = dialog.showAndWait();
//            result.ifPresent(color -> {
//                String hex = ColorUtils.toHex(color);
//                primaryStage.getScene().getRoot().setStyle("-item-bg-hover: " + hex + ";");
//                // 保存到配置
//            });
//
//        };
//    }

    private void openThemeSettings(Stage primaryStage, VBox sidebarContainer) {
        try {
//            // 加载 FXML，返回的是一个 Node（Parent 的子类）
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/your-view.fxml"));
//            Parent node = loader.load();
//
//// 然后像普通 Node 一样添加到任何容器
//            VBox container = new VBox();
//            container.getChildren().add(node);
//
//// 或者放到 StackPane、BorderPane 等
//            stackPane.getChildren().add(node);
//            borderPane.setCenter(node);

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/ftwrjh/rimedictmanager2/application/settings-view.fxml")
            );
            Parent root = loader.load();

//            // 传入主界面的根节点，以便实时预览
            SettingsController controller = loader.getController();
            controller.setRoot(primaryStage.getScene().getRoot());
//
//            Stage stage = new Stage();
//            stage.initOwner(primaryStage);
//            stage.initModality(Modality.WINDOW_MODAL);  // 模态窗口
//            stage.setTitle("🎨 自定义主题");
//            stage.setScene(new Scene(root, 400, 450));
//            stage.show();
//
            StackPane center = AppContext.getInstance().getTyped(AppConst.ContextKey.NODE_CENTER_STACK_PANE, StackPane.class);
            ObservableList<Node> children = center.getChildren();
            children.clear();
            children.add(root);
            Label logo1 = new Label("自定义主题");
            sidebarContainer.getChildren().set(0, logo1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void openThemeSettings(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/ftwrjh/rimedictmanager2/application/settings-view.fxml")
            );
            Parent root = loader.load();

            // 传入主界面的根节点，以便实时预览
            SettingsController controller = loader.getController();
            controller.setRoot(primaryStage.getScene().getRoot());

            Stage stage = new Stage();
            stage.initOwner(primaryStage);
            stage.initModality(Modality.WINDOW_MODAL);  // 模态窗口
            stage.setTitle("🎨 自定义主题");
            stage.setScene(new Scene(root, 400, 450));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void switchTab(Node node, String tabName, VBox sidebarContainer) {
        StackPane center = AppContext.getInstance().getTyped(AppConst.ContextKey.NODE_CENTER_STACK_PANE, StackPane.class);
        ObservableList<Node> children = center.getChildren();
        children.clear();
        children.add(node);
        Label logo1 = new Label(tabName);
        sidebarContainer.getChildren().set(0, logo1);
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
