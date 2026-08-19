package com.ftwrjh.rimedictmanager2.controller;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ftwrjh.rimedictmanager2.application.node.BottomComponentGenerator;
import com.ftwrjh.rimedictmanager2.data.InputSchema;
import com.ftwrjh.rimedictmanager2.env.AppContext;
import com.ftwrjh.rimedictmanager2.env.Const;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Strings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
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
                BottomComponentGenerator.getInstance().setStatusLeft(msg);

                String defaultConfigPath = rimeHomeDirPath + File.separator + Const.Path.DEFAULT_CUSTOM_YAML;
                log.info("主配置文件: {}", defaultConfigPath);

                File mainConfigFile = new File(defaultConfigPath);

                if (mainConfigFile.exists()) {
                    String successMsg = "已加载配置目录";
                    log.info(successMsg);
                    BottomComponentGenerator.getInstance().setStatusLeft(successMsg);

                    Set<String> activeSchemaSet = null;
                    try (InputStream inputStream = new FileInputStream(mainConfigFile)) {
                        Map<String, Object> yaml = AppContext.getYamlInstance().load(inputStream);
                        JSONObject mainConfig = new JSONObject(yaml);
                        JSONArray jsonArray = mainConfig.getJSONObject("patch").getJSONArray("schema_list");
                        activeSchemaSet = jsonArray.stream()
                                .filter(item -> item instanceof Map<?, ?>)
                                .map(item -> ((Map) item).get("schema"))
                                .filter(Objects::nonNull)
                                .map(String::valueOf)
                                .collect(Collectors.toSet());
                    } catch (Exception e) {
                        log.error("exception.e:{}", e);
                    }
                    File rimeHomeDir = new File(rimeHomeDirPath);
                    File[] files = rimeHomeDir.listFiles();

                    Set<String> finalActiveSchemaSet = ObjectUtils.getIfNull(activeSchemaSet, new HashSet<>());
                    List<InputSchema> collect = Arrays.stream(files)
                            .filter(File::isFile)
                            .map(File::getName)
                            .filter(filename -> Strings.CS.endsWith(filename, Const.Path.DICT_FILENAME_SUFFIX))
                            .map(str -> Strings.CS.replace(str, Const.Path.DICT_FILENAME_SUFFIX, ""))
                            .map(InputSchema::new)
                            .map(is -> {
                                String filePath = rimeHomeDirPath + File.separator + is.getInputSchemaId() + Const.Path.DICT_FILENAME_SUFFIX;
                                try (FileInputStream fis = new FileInputStream(filePath)) {
                                    Map<String, Object> data = AppContext.getYamlInstance().load(fis);
                                    JSONObject json = new JSONObject(data);
                                    String name = json.getJSONObject("schema").getString("name");
                                    is.setInputSchemaName(name);
                                    is.setAvailable(CollectionUtils.containsAny(finalActiveSchemaSet, is.getInputSchemaId()));
                                    return is;
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            })
                            .collect(Collectors.toList());


                    ObservableList<InputSchema> list = AppContext.getInstance().getTyped(Const.ContextKey.TABLE_DATA_SCHEMA, ObservableList.class);
                    list.clear();
                    list.addAll(collect);
                    AppContext.getInstance().set(Const.ContextKey.TABLE_DATA_SCHEMA, list);

                } else {
                    String warnMsg = "所选目录「" + rimeHomeDirPath + "」中没有「default.custom.yaml」文件";
                    log.warn(warnMsg);
                    BottomComponentGenerator.getInstance().setStatusLeft(warnMsg);
                }
            } else {
                log.info("用户取消了选择");
            }
        };
    }


}
