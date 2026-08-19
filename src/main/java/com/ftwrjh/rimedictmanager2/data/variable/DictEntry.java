package com.ftwrjh.rimedictmanager2.data.variable;

import com.ftwrjh.rimedictmanager2.data.constant.DictType;
import javafx.beans.property.*;

public class DictEntry {
    private final StringProperty word = new SimpleStringProperty();
    private final StringProperty code = new SimpleStringProperty();
    private final IntegerProperty weight = new SimpleIntegerProperty();
    private final IntegerProperty lineNumber = new SimpleIntegerProperty();
    private final ObjectProperty<DictType> dictType = new SimpleObjectProperty<>();


    public DictEntry() {
    }

    public DictEntry(String word, String code, Integer weight, Integer lineNumber, DictType dictType) {
        this.setWord(word);
        this.setCode(code);
        this.setWeight(weight);
        this.setLineNumber(lineNumber);
        this.dictType.set(dictType);
    }

    public String getWord() {
        return this.word.get();
    }

    public void setWord(String word) {
        this.word.set(word);
    }

    public StringProperty wordProperty() {
        return this.word;
    }

    public String getCode() {
        return this.code.get();
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public StringProperty codeProperty() {
        return this.code;
    }

    public int getWeight() {
        return this.weight.get();
    }

    public void setWeight(Integer weight) {
        this.weight.set(weight);
    }

    public IntegerProperty weightProperty() {
        return this.weight;
    }

    public int getLineNumber() {
        return this.lineNumber.get();
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber.set(lineNumber);
    }

    public IntegerProperty lineNumberProperty() {
        return this.lineNumber;
    }

    public void setDictType(DictType dictType) {
        this.dictType.set(dictType);
    }

    public DictType getDictType() {
        return dictType.get();
    }

    public ObjectProperty<DictType> dictTypeProperty() {
        return dictType;
    }
}
