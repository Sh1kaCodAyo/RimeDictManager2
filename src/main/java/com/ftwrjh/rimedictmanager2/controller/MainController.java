package com.ftwrjh.rimedictmanager2.controller;

import com.alibaba.fastjson2.JSONObject;
import com.ftwrjh.rimedictmanager2.application.node.BottomComponentGenerator;
import com.ftwrjh.rimedictmanager2.env.GlobalContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
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
        BottomComponentGenerator.getInstance().setStatusLeft("ssssssaaaa");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // ⭐ 在初始化方法中构建侧边栏
//        buildSidebar();
//        initMenu();
    }


}