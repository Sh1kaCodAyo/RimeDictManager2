package com.ftwrjh.rimedictmanager2.data.variable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DictionaryEntry {
    private final StringProperty word = new SimpleStringProperty();
    private final StringProperty code = new SimpleStringProperty();
    private final IntegerProperty weight = new SimpleIntegerProperty();
    private final IntegerProperty lineNumber = new SimpleIntegerProperty();
    private final StringProperty source = new SimpleStringProperty();

    public String getWord() {
        return word.get();
    }

    public StringProperty wordProperty() {
        return word;
    }

    public void setWord(String word) {
        this.word.set(word);
    }

    public String getCode() {
        return code.get();
    }

    public StringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public int getWeight() {
        return weight.get();
    }

    public IntegerProperty weightProperty() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight.set(weight);
    }

    public int getLineNumber() {
        return lineNumber.get();
    }

    public IntegerProperty lineNumberProperty() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber.set(lineNumber);
    }

    public String getSource() {
        return source.get();
    }

    public StringProperty sourceProperty() {
        return source;
    }

    public void setSource(String source) {
        this.source.set(source);
    }
}
