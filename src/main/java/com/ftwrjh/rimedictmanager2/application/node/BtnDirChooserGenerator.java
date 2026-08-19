//package com.ftwrjh.rimedictmanager2.application.node;
//
//import com.ftwrjh.rimedictmanager2.env.Const;
//import com.ftwrjh.rimedictmanager2.env.GlobalContext;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Node;
//import javafx.scene.control.Button;
//import javafx.stage.DirectoryChooser;
//import javafx.stage.Stage;
//import lombok.Getter;
//import lombok.extern.slf4j.Slf4j;
//import org.yaml.snakeyaml.Yaml;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.util.Map;
//
//@Slf4j
//public class BtnDirChooserGenerator implements NodeGenerator {
//    private BtnDirChooserGenerator() {}
//    @Getter
//    private static final BtnDirChooserGenerator instance = new BtnDirChooserGenerator();
//    @Override
//    public Node getNode(Stage primaryStage) {
//        Button btnDirChooser = new Button(Const.UserInterface.BTN_DIR_CHOOSER);
//        btnDirChooser.setOnAction(ev -> {
//            // 1. 创建 DirectoryChooser
//            DirectoryChooser directoryChooser = new DirectoryChooser();
//
//            // 2. 设置标题
//            directoryChooser.setTitle("请选择目标文件夹");
//
//            // 3. 设置初始目录（可选）
//            directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
//
//            // 4. 显示对话框并获取用户选择的文件夹
//            File selectedDirectory = directoryChooser.showDialog(primaryStage);
//
//            // 5. 处理结果
//            if (selectedDirectory != null) {
//                String rimeHomeDir = selectedDirectory.getAbsolutePath();
//                log.info("选中的文件夹: {}", rimeHomeDir);
//
//                String defaultCustom = "default.custom.yaml";
//
//
//                String defaultConfigPath = rimeHomeDir + File.separator + defaultCustom;
//
//
//                log.info("主配置文件: {}", defaultConfigPath);
//
//                File mainConfigFile = new File(defaultConfigPath);
//
//                if (mainConfigFile.exists()) {
//                    Yaml yaml = new Yaml();
//                    try (InputStream inputStream = new FileInputStream(mainConfigFile)) {
//
//                        // 解析为通用的 Map 对象
//                        Map<String, Object> data = yaml.load(inputStream);
//                        GlobalContext.Global.getContext().put("mainConfig", data);
//
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    btnDirChooser.setText("没有文件: " + defaultCustom);
//                }
//
////                btn.setText("已选择: " + selectedDirectory.getName());
//            } else {
//                log.info("用户取消了选择");
//            }
//        });
//
//        return btnDirChooser;
//    }
//
//
//}
