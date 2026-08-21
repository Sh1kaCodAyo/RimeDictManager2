package com.ftwrjh.rimedictmanager2.data.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DictionaryType {
    BASE_DICT("基本词库", 1),
    USER_DCIT("用户词库", 2),
    EXTRA_DICT("扩展词库", 3);

    private final String dictionaryName;
    private final int priority;

    public static DictionaryType fromDictionaryName(String dictionaryName) {
        for (DictionaryType type : DictionaryType.values()) {
            if (type.dictionaryName.equals(dictionaryName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("未知等级: " + dictionaryName);
    }
}
