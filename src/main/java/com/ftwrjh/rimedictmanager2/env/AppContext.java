package com.ftwrjh.rimedictmanager2.env;

import com.ftwrjh.rimedictmanager2.data.DictEntry;
import com.ftwrjh.rimedictmanager2.data.InputSchema;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 应用全局上下文 - 单例模式
 * 存储当前用户、全局配置、共享数据等
 */
public class AppContext {
    
    // 单例实例
    private static final AppContext INSTANCE = new AppContext();
    
    // 私有构造器（防止外部实例化）
    private AppContext() {}
    
    public static AppContext getInstance() {
        return INSTANCE;
    }


    private final ListProperty<InputSchema> inputSchemaObservableList = new SimpleListProperty<>(FXCollections.observableArrayList());

    public ObservableList<InputSchema> getInputSchemaObservableList() {
        return inputSchemaObservableList.get();    }

    public ListProperty<InputSchema> inputSchemaObservableListProperty() {
        return inputSchemaObservableList;
    }

    public void setInputSchemaObservableList(ObservableList<InputSchema> inputSchemaObservableList) {
        this.inputSchemaObservableList.set(inputSchemaObservableList);
    }
}