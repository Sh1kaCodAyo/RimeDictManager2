package com.ftwrjh.rimedictmanager2.data.variable;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Dictionary {
    private final StringProperty dictionaryId = new SimpleStringProperty();
    private final StringProperty dictionaryName = new SimpleStringProperty();
    private final BooleanProperty active = new SimpleBooleanProperty();

    public Dictionary() {
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "dictionaryId=" + dictionaryId +
                ", dictionaryName=" + dictionaryName +
                ", active=" + active +
                '}';
    }

    public String getDictionaryId() {
        return dictionaryId.get();
    }

    public StringProperty dictionaryIdProperty() {
        return dictionaryId;
    }

    public void setDictionaryId(String dictionaryId) {
        this.dictionaryId.set(dictionaryId);
    }

    public String getDictionaryName() {
        return dictionaryName.get();
    }

    public StringProperty dictionaryNameProperty() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName.set(dictionaryName);
    }

    public boolean isActive() {
        return active.get();
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }

}
