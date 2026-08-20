package com.ftwrjh.rimedictmanager2.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ftwrjh.rimedictmanager2.application.node.BottomNodeGenerator;
import com.ftwrjh.rimedictmanager2.data.constant.AppConst;
import com.ftwrjh.rimedictmanager2.data.variable.InputSchema;
import com.ftwrjh.rimedictmanager2.env.AppConfig;
import com.ftwrjh.rimedictmanager2.env.AppContext;
import javafx.collections.ObservableList;
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
public class WorkspaceService {
    /**
     * 从指定路径加载工作主目录
     * 读取 `*.schema.yaml` 文件以获取安装了哪些输入法
     * 读取 `default.custom.yaml` 文件内容以获取启用了哪些输入法
     *
     * @param workspacePath 工作目录路径
     */
    public static void load(String workspacePath) {
        File workspace = new File(workspacePath);
        if (workspace.isDirectory()) {
            load(workspace);
        } else {
            throw new RuntimeException("加载工作主目录异常");
        }
    }

    /**
     * 从指定路径加载工作主目录
     * 读取 `*.schema.yaml` 文件以获取安装了哪些输入法
     * 读取 `default.custom.yaml` 文件内容以获取启用了哪些输入法
     *
     * @param workspace 工作目录的 {@link java.io.File} 类对象
     */
    public static void load(File workspace) {
        String workspacePath = workspace.getAbsolutePath();
        String msg = "Rime主目录: " + workspacePath;
        AppContext.getInstance().set(AppConst.ContextKey.ENV_RIME_HOME_DIR, workspacePath);
        log.info(msg);
        BottomNodeGenerator.getInstance().setStatusLeft(msg);

        String defaultCustomYamlPath = workspacePath + File.separator + AppConst.Path.YAML_DEFAULT_CUSTOM;
        log.info("主配置文件: {}", defaultCustomYamlPath);

        File defaultCustomYamlFile = new File(defaultCustomYamlPath);

        if (defaultCustomYamlFile.exists()) {
            String successMsg = "已加载配置目录「" + workspacePath + "」";
            log.info(successMsg);
            BottomNodeGenerator.getInstance().setStatusLeft(successMsg);

            Set<String> activeSchemaSet = null;
            try (InputStream inputStream = new FileInputStream(defaultCustomYamlFile)) {
                Map<String, Object> yaml = AppContext.getYamlInstance().load(inputStream);
                JSONObject mainConfig = new JSONObject(yaml);
                JSONArray jsonArray = mainConfig.getJSONObject("patch").getJSONArray("schema_list");
                activeSchemaSet = jsonArray.stream()
                        .filter(item -> item instanceof Map<?, ?>)
                        .map(item -> ((Map<?, ?>) item).get("schema"))
                        .filter(Objects::nonNull)
                        .map(String::valueOf)
                        .collect(Collectors.toSet());
            } catch (Exception e) {
                log.error("加载主配置文件失败", e);
            }
            File[] files = workspace.listFiles(); // defaultCustomYamlFile.exists() 则files一定非空

            Set<String> finalActiveSchemaSet = ObjectUtils.getIfNull(activeSchemaSet, new HashSet<>());
            List<InputSchema> collect = Arrays.stream(files)
                    .filter(File::isFile)
                    .map(File::getName)
                    .filter(filename -> Strings.CS.endsWith(filename, AppConst.Path.DICT_FILENAME_SUFFIX))
                    .map(str -> Strings.CS.replace(str, AppConst.Path.DICT_FILENAME_SUFFIX, ""))
                    .map(InputSchema::new)
                    .map(is -> {
                        String filePath = workspacePath + File.separator + is.getInputSchemaId() + AppConst.Path.DICT_FILENAME_SUFFIX;
                        try (FileInputStream fis = new FileInputStream(filePath)) {
                            Map<String, Object> data = AppContext.getYamlInstance().load(fis);
                            JSONObject json = new JSONObject(data);
                            String name = json.getJSONObject("schema").getString("name");
                            is.setInputSchemaName(name);
                            is.setActive(CollectionUtils.containsAny(finalActiveSchemaSet, is.getInputSchemaId()));
                            return is;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .toList();

            ObservableList<InputSchema> list = AppContext.getInstance().getTyped(AppConst.ContextKey.TABLE_DATA_INPUT_SCHEMA, ObservableList.class);
            list.clear();
            list.addAll(collect);
            AppContext.getInstance().set(AppConst.ContextKey.TABLE_DATA_INPUT_SCHEMA, list);

            // update user config
            AppConfig.getInstance().setProperty(AppConst.ConfigKey.RIME_HOME_DIR, workspacePath);
            AppConfig.getInstance().saveAndReload();
        } else {
            String warnMsg = "所选目录「" + workspacePath + "」中没有「default.custom.yaml」文件";
            log.warn(warnMsg);
            BottomNodeGenerator.getInstance().setStatusLeft(warnMsg);
        }
    }

}
