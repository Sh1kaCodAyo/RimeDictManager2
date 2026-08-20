package com.ftwrjh.rimedictmanager2.data.variable;

import javafx.beans.property.*;

public class InputSchema {
    private final StringProperty inputSchemaId = new SimpleStringProperty();
    private final StringProperty inputSchemaName = new SimpleStringProperty();
    private final BooleanProperty active = new SimpleBooleanProperty();

    @Override
    public String toString() {
        return "InputSchema{" +
                "inputSchemaId=" + inputSchemaId +
                ", inputSchemaName=" + inputSchemaName +
                ", active=" + active +
                '}';
    }

    public InputSchema() {
    }

    public InputSchema(String inputSchemaId) {
        this.inputSchemaId.set(inputSchemaId);
    }
    public InputSchema(String inputSchemaId, String inputSchemaName) {
        this.inputSchemaId.set(inputSchemaId);
        this.inputSchemaName.set(inputSchemaName);
    }
    public InputSchema(String inputSchemaId, String inputSchemaName, Boolean active) {
        this.inputSchemaId.set(inputSchemaId);
        this.inputSchemaName.set(inputSchemaName);
        this.active.set(active);
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

    public boolean getActive() {
        return active.get();
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }
}
