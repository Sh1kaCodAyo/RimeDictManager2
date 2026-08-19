package com.ftwrjh.rimedictmanager2.application;

import com.ftwrjh.rimedictmanager2.env.AppContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {
    public static void showMemory() {
        AppContext instance = AppContext.getInstance();
        log.debug("debug:{}", instance);
    }
}
