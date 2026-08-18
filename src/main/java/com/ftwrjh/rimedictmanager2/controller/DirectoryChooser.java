package com.ftwrjh.rimedictmanager2.controller;

import com.alibaba.fastjson2.JSONObject;
import com.ftwrjh.rimedictmanager2.application.node.BottomStatusBarGenerator;
import com.ftwrjh.rimedictmanager2.data.InputSchema;
import com.ftwrjh.rimedictmanager2.env.AppContext;
import com.ftwrjh.rimedictmanager2.env.Const;
import com.ftwrjh.rimedictmanager2.env.GlobalContext;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class DirectoryChooser {
    public static EventHandler<ActionEvent> getActionEventEventHandler(Stage primaryStage) {
        return event -> {
            javafx.stage.DirectoryChooser directoryChooser = new javafx.stage.DirectoryChooser();
            directoryChooser.setTitle("请选择Rime用户文件夹");
            directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File selectedDirectory = directoryChooser.showDialog(primaryStage);

            if (selectedDirectory != null) {
                String rimeHomeDirPath = selectedDirectory.getAbsolutePath();
                String msg = "Rime主目录: " + rimeHomeDirPath;
                log.info(msg);
                BottomStatusBarGenerator.getInstance().setStatusLeft(msg);

                String defaultConfigPath = rimeHomeDirPath + File.separator + Const.Path.DEFAULT_CUSTOM_YAML;
                log.info("主配置文件: {}", defaultConfigPath);

                File mainConfigFile = new File(defaultConfigPath);

                if (mainConfigFile.exists()) {

                    String successMsg = "已加载配置目录";
                    log.info(successMsg);
                    BottomStatusBarGenerator.getInstance().setStatusLeft(successMsg);

                    File rimeHomeDir = new File(rimeHomeDirPath);
                    if (!rimeHomeDir.isDirectory()) {
                        // impossible
                        return;
                    }

                    File[] files = rimeHomeDir.listFiles();
                    if (ArrayUtils.isEmpty(files)) {
                        // impossible
                        return;
                    }


//                    Arrays.stream(files).filter(File::isFile).filter(file -> Strings.CS.endsWith(file.getName(), ".schema.yaml"));
                    List<InputSchema> collect = Arrays.stream(files)
                            .filter(File::isFile)
                            .map(File::getName)
                            .filter(filename -> Strings.CS.endsWith(filename, Const.Path.DICT_FILENAME_SUFFIX))
                            .map(str -> Strings.CS.replace(str, Const.Path.DICT_FILENAME_SUFFIX, ""))
                            .map(InputSchema::new)
                            .map(is -> {
                                String filePath = rimeHomeDirPath + File.separator + is.getInputSchemaId() + Const.Path.DICT_FILENAME_SUFFIX;
                                try (FileInputStream fis = new FileInputStream(filePath)) {
                                    Yaml yaml = new Yaml();
                                    Map<String, Object> data = yaml.load(fis);
                                    JSONObject json = new JSONObject(data);
                                    String name = json.getJSONObject("schema").getString("name");
                                    is.setInputSchemaName(name);
                                    return is;
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            })
                            .collect(Collectors.toList());


                    log.info("save000");
                    ObservableList<InputSchema> list = AppContext.getInstance().getInputSchemaObservableList();
                    list.clear();
//                    list.add(new InputSchema("新输入法"));
                    list.addAll(collect);
                    AppContext.getInstance().setInputSchemaObservableList(list);

                    // todo parse yaml
//                    Yaml yaml = new Yaml();
                    // 解析为通用的 Map 对象
//                    Map<String, Object> data = yaml.load(inputStream);
//                    GlobalContext.Global.getContext().put("mainConfig", data);
//                    try (InputStream inputStream = new FileInputStream(mainConfigFile)) {
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                } else {
                    String warnMsg = "所选目录「" + rimeHomeDirPath + "」中没有「default.custom.yaml」文件";
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
