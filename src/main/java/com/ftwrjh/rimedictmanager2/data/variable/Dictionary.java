package com.ftwrjh.rimedictmanager2.data.variable;

import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Dictionary {
    private final StringProperty inputSchemaId = new SimpleStringProperty();
    private final StringProperty inputSchemaName = new SimpleStringProperty();
    private final MapProperty<String, Boolean> extraDicts = new SimpleMapProperty<>(FXCollections.observableHashMap());

    public String getInputSchemaId() {
        return inputSchemaId.get();
    }

    public StringProperty inputSchemaIdProperty() {
        return inputSchemaId;
    }

    public void setInputSchemaId(String inputSchemaId) {
        this.inputSchemaId.set(inputSchemaId);
    }

    public String getInputSchemaName() {
        return inputSchemaName.get();
    }

    public StringProperty inputSchemaNameProperty() {
        return inputSchemaName;
    }

    public void setInputSchemaName(String inputSchemaName) {
        this.inputSchemaName.set(inputSchemaName);
    }

    public ObservableMap<String, Boolean> getExtraDicts() {
        return extraDicts.get();
    }

    public MapProperty<String, Boolean> extraDictsProperty() {
        return extraDicts;
    }

    public void setExtraDicts(ObservableMap<String, Boolean> extraDicts) {
        this.extraDicts.set(extraDicts);
    }
}
