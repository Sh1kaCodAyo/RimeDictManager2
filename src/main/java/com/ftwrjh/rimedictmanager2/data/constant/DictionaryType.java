package com.ftwrjh.rimedictmanager2.data.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DictionaryType {
    BASE_DICT("基本词库", 1), USER_DCIT("用户词库", 2), EXTRA_DICT("扩展词库", 3);
    private final String dictName;
    private final int priority;
    // 根据显示名称查找枚举（用于从数据库/前端反查）
    public static DictionaryType fromDisplayName(String displayName) {
        for (DictionaryType rank : DictionaryType.values()) {
            if (rank.dictName.equals(displayName)) {
                return rank;
            }
        }
        throw new IllegalArgumentException("未知等级: " + displayName);
    }

    // 根据编码查找枚举
    public static DictionaryType fromCode(String code) {
        return DictionaryType.valueOf(code); // 直接使用枚举名
    }
}
