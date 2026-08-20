package com.ftwrjh.rimedictmanager2.data.variable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DictionaryEntry {
    private final StringProperty word = new SimpleStringProperty();
    private final StringProperty code = new SimpleStringProperty();
    private final IntegerProperty weight = new SimpleIntegerProperty();
    private final IntegerProperty lineNumber = new SimpleIntegerProperty();
    private final StringProperty source = new SimpleStringProperty();
    private final StringProperty fullPath = new SimpleStringProperty();

    public DictionaryEntry() {
    }

    public DictionaryEntry(String line) {
        String[] split = line.split("\t");
        if (split.length >= 3) {
            this.word.set(split[0]);
            this.code.set(split[1]);
            this.weight.set(Integer.parseInt(split[2]));
        } else if (split.length == 2) {
            this.word.set(split[0]);
            this.code.set(split[1]);
            this.weight.set(10);
            log.warn("line:[{}], weight missing, init weight = 10", line);
        } else {
            log.warn("skip line:[{}]", line);
        }
    }

    @Override
    public String toString() {
        return "DictionaryEntry{" +
                "word=" + word +
                ", code=" + code +
                ", weight=" + weight +
                ", lineNumber=" + lineNumber +
                ", source=" + source +
                '}';
    }

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

    public String getFullPath() {
        return fullPath.get();
    }

    public StringProperty fullPathProperty() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath.set(fullPath);
    }
}
