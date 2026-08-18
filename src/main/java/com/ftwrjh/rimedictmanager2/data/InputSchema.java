package com.ftwrjh.rimedictmanager2.data;

import javafx.beans.property.*;

public class InputSchema {
    private final StringProperty inputSchemaId = new SimpleStringProperty();
    private final StringProperty inputSchemaName = new SimpleStringProperty();

    public InputSchema() {
    }

    public InputSchema(String inputSchemaId) {
        this.inputSchemaId.set(inputSchemaId);
    }
    public InputSchema(String inputSchemaId, String inputSchemaName) {
        this.inputSchemaId.set(inputSchemaId);
        this.inputSchemaName.set(inputSchemaName);
    }

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
}
