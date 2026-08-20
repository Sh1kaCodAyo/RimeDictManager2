package com.ftwrjh.rimedictmanager2.application.node;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

import java.util.regex.Pattern;

public class LimitedTextFieldTableCell<S> extends TextFieldTableCell<S, String> {

    private final int maxLength;
    private final Pattern pattern;

    // ⭐ 使用 StringConverter<String> 类型
    private LimitedTextFieldTableCell(StringConverter<String> converter, int maxLength, String regex) {
        super(converter);
        this.maxLength = maxLength;
        this.pattern = Pattern.compile(regex);
    }

    // ⭐ 便捷创建方法
    public static <S> Callback<TableColumn<S, String>, TableCell<S, String>> forTableColumn(
            int maxLength, String regex) {
        return column -> new LimitedTextFieldTableCell<>(
                new DefaultStringConverter(),  // ⭐ 明确传入 DefaultStringConverter
                maxLength,
                regex
        );
    }

    // 预设：只允许字母
    public static <S> Callback<TableColumn<S, String>, TableCell<S, String>> lettersOnly(int maxLength) {
        return forTableColumn(maxLength, "[a-zA-Z]*");
    }

    // 预设：只允许数字
    public static <S> Callback<TableColumn<S, String>, TableCell<S, String>> digitsOnly(int maxLength) {
        return forTableColumn(maxLength, "\\d*");
    }

    // 预设：允许字母和数字
    public static <S> Callback<TableColumn<S, String>, TableCell<S, String>> alphanumeric(int maxLength) {
        return forTableColumn(maxLength, "[a-zA-Z0-9]*");
    }

    @Override
    public void startEdit() {
        if (!isEditable() || !getTableColumn().isEditable()) {
            return;
        }

        super.startEdit();

        TextField textField = getTextField();
        if (textField != null) {
            textField.textProperty().addListener((obs, oldVal, newVal) -> {
                // 长度限制
                if (newVal.length() > maxLength) {
                    textField.setText(oldVal);
                    return;
                }
                // 正则校验
                if (!pattern.matcher(newVal).matches() && !newVal.isEmpty()) {
                    textField.setText(oldVal);
                }
            });

            textField.setText(getItem());
            textField.selectAll();
            textField.requestFocus();
        }
    }

    private TextField getTextField() {
        return (TextField) getGraphic();
    }
}