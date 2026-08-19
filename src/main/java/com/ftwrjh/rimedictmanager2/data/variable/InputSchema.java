package com.ftwrjh.rimedictmanager2.data.variable;

import javafx.beans.property.*;

public class InputSchema {
    private final StringProperty inputSchemaId = new SimpleStringProperty();
    private final StringProperty inputSchemaName = new SimpleStringProperty();
    private final BooleanProperty available = new SimpleBooleanProperty();

    public InputSchema() {
    }

    public InputSchema(String inputSchemaId) {
        this.inputSchemaId.set(inputSchemaId);
    }
    public InputSchema(String inputSchemaId, String inputSchemaName) {
        this.inputSchemaId.set(inputSchemaId);
        this.inputSchemaName.set(inputSchemaName);
    }
    public InputSchema(String inputSchemaId, String inputSchemaName, Boolean available) {
        this.inputSchemaId.set(inputSchemaId);
        this.inputSchemaName.set(inputSchemaName);
        this.available.set(available);
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

    public boolean isAvailable() {
        return available.get();
    }

    public BooleanProperty availableProperty() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available.set(available);
    }
}
