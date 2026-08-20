package com.ftwrjh.rimedictmanager2.data.variable;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dictionary {
    private final StringProperty dictionaryId = new SimpleStringProperty();
    private final StringProperty dictionaryName = new SimpleStringProperty();
    private BooleanProperty active = new SimpleBooleanProperty();

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
