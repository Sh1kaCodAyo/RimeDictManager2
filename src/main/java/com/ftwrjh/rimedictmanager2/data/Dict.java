package com.ftwrjh.rimedictmanager2.data;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Dict {
    private final StringProperty dictName = new SimpleStringProperty();
    private final ListProperty<DictEntry> baseDict = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final ListProperty<DictEntry> userDict = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final ListProperty<DictEntry> extraDict = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final BooleanProperty extraAvailable = new SimpleBooleanProperty();

    public void setDictName(String dictName) {
        this.dictName.set(dictName);
    }

    public void setBaseDict(ObservableList<DictEntry> list) {
        this.baseDict.set(list);
    }

    public void setUserDict(ObservableList<DictEntry> list) {
        this.userDict.set(list);
    }

    public void setExtraDict(ObservableList<DictEntry> list) {
        this.extraDict.set(list);
    }

    public void setExtraAvailable(Boolean extraAvailable) {
        this.extraAvailable.set(extraAvailable);
    }

    public String getDictName() {
        return dictName.get();
    }

    public StringProperty dictNameProperty() {
        return dictName;
    }

    public ObservableList<DictEntry> getBaseDict() {
        return baseDict.get();
    }

    public ListProperty<DictEntry> baseDictProperty() {
        return baseDict;
    }

    public ObservableList<DictEntry> getUserDict() {
        return userDict.get();
    }

    public ListProperty<DictEntry> userDictProperty() {
        return userDict;
    }

    public ObservableList<DictEntry> getExtraDict() {
        return extraDict.get();
    }

    public ListProperty<DictEntry> extraDictProperty() {
        return extraDict;
    }

    public boolean isExtraAvailable() {
        return extraAvailable.get();
    }

    public BooleanProperty extraAvailableProperty() {
        return extraAvailable;
    }
}
