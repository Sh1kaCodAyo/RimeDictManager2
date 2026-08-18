package com.ftwrjh.rimedictmanager2.env;

import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GlobalContext {
    Global(new JSONObject());
    private final JSONObject context;
}
