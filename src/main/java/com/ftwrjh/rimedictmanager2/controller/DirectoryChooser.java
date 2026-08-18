package com.ftwrjh.rimedictmanager2.controller;

import com.ftwrjh.rimedictmanager2.application.node.BottomStatusBarGenerator;
import com.ftwrjh.rimedictmanager2.env.Const;
import com.ftwrjh.rimedictmanager2.env.GlobalContext;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

@Slf4j
public class DirectoryChooser {
    public static EventHandler<ActionEvent> getActionEventEventHandler(Stage primaryStage) {
        return event -> {
            javafx.stage.DirectoryChooser directoryChooser = new javafx.stage.DirectoryChooser();
            directoryChooser.setTitle("请选择Rime用户文件夹");
            directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File selectedDirectory = directoryChooser.showDialog(primaryStage);

            if (selectedDirectory != null) {
                String rimeHomeDir = selectedDirectory.getAbsolutePath();
                String msg = "Rime主目录: " + rimeHomeDir;
                log.info(msg);
                BottomStatusBarGenerator.getInstance().setStatusLeft(msg);

                String defaultConfigPath = rimeHomeDir + File.separator + Const.Path.DEFAULT_CUSTOM_YAML;
                log.info("主配置文件: {}", defaultConfigPath);

                File mainConfigFile = new File(defaultConfigPath);

                if (mainConfigFile.exists()) {
                    Yaml yaml = new Yaml();
                    try (InputStream inputStream = new FileInputStream(mainConfigFile)) {

                        // 解析为通用的 Map 对象
                        Map<String, Object> data = yaml.load(inputStream);
                        GlobalContext.Global.getContext().put("mainConfig", data);

                        String successMsg = "已加载配置目录";
                        log.info(successMsg);
                        BottomStatusBarGenerator.getInstance().setStatusLeft(successMsg);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    String warnMsg = "所选目录「" + rimeHomeDir + "」中没有「default.custom.yaml」文件";
                    log.warn(warnMsg);
                    BottomStatusBarGenerator.getInstance().setStatusLeft(warnMsg);
                }

//                btn.setText("已选择: " + selectedDirectory.getName());
            } else {
                log.info("用户取消了选择");
            }
        };
    }

}
