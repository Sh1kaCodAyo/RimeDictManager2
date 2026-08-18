package com.ftwrjh.rimedictmanager2.env;

import lombok.Getter;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class AppConfig {

    private static final String CONFIG_FILE = "rdm2.properties";
    @Getter
    private static final AppConfig instance = new AppConfig();
    private final Properties props = new Properties();

    private AppConfig() {
        // 从 classpath 加载默认配置 (打包在 jar 内)
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {
            if (input != null) {
                props.load(input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 【可选】从用户目录加载外部配置，覆盖默认值
        // 这样用户在 jar 包外修改配置也不会影响包内的默认值
        try {
            Path userConfigPath = Paths.get(System.getProperty("user.home"),
                    ".myapp", CONFIG_FILE);
            if (Files.exists(userConfigPath)) {
                try (InputStream userInput = Files.newInputStream(userConfigPath)) {
                    props.load(userInput);
                    System.out.println("已加载用户配置: " + userConfigPath);
                }
            }
        } catch (Exception e) {
            // 外部配置文件不存在也是正常情况，可以忽略
        }
    }

    public String getProperty(String key) {
        return props.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }
}