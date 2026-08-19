package com.ftwrjh.rimedictmanager2.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DictType {
    BASE_DICT("基本"), USER_DCIT("用户"), EXTRA_DICT("扩展");
    private final String dictName;
}
