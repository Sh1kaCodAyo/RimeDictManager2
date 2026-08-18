package com.ftwrjh.rimedictmanager2;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainApplication extends Application implements Initializable {

    @FXML
    private Label statusLabel;

    @FXML
    private MenuItem newFileMenuItem;
    @FXML
    private MenuItem openFileMenuItem;
    @FXML
    private MenuItem saveFileMenuItem;
    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private MenuItem undoMenuItem;
    @FXML
    private MenuItem redoMenuItem;
    @FXML
    private MenuItem cutMenuItem;
    @FXML
    private MenuItem copyMenuItem;
    @FXML
    private MenuItem pasteMenuItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 文件菜单快捷键
        newFileMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+N"));
        openFileMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        saveFileMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        exitMenuItem.setAccelerator(KeyCombination.keyCombination("Alt+F4"));

        // 编辑菜单快捷键
        undoMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Z"));
        redoMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Y"));
        cutMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        copyMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
        pasteMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+V"));
    }

    // ===== 文件菜单事件 =====

    @FXML
    private void onNewFile() {
        statusLabel.setText("📄 新建文件");
        System.out.println("新建文件");
    }

    @FXML
    private void onOpenFile() {
        statusLabel.setText("📂 打开文件");
        System.out.println("打开文件");
    }

    @FXML
    private void onSaveFile() {
        statusLabel.setText("💾 保存文件");
        System.out.println("保存文件");
    }

    @FXML
    private void onExit() {
        System.exit(0);
    }

    // ===== 编辑菜单事件 =====

    @FXML
    private void onUndo() {
        statusLabel.setText("↩️ 撤销");
        System.out.println("撤销");
    }

    @FXML
    private void onRedo() {
        statusLabel.setText("↪️ 重做");
        System.out.println("重做");
    }

    @FXML
    private void onCut() {
        statusLabel.setText("✂️ 剪切");
        System.out.println("剪切");
    }

    @FXML
    private void onCopy() {
        statusLabel.setText("📋 复制");
        System.out.println("复制");
    }

    @FXML
    private void onPaste() {
        statusLabel.setText("📎 粘贴");
        System.out.println("粘贴");
    }
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("RimeDictManager2");
        stage.setScene(scene);
        stage.show();

    }
}
