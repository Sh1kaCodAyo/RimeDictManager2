package com.ftwrjh.rimedictmanager2;

import com.ftwrjh.rimedictmanager2.env.AppContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {
    public static void showMemory() {
        AppContext instance = AppContext.getInstance();
        log.debug("debug:{}", instance);
    }

    public static void main(String[] args) {
        String format = String.format("""
                    -item-bg-hover: %s;
                    -text-hover: %s;
                    -border-color: %s;
                """, "#dd", "rr", "ff");
        log.info(format);
//        LeftNodeGenerator instance = LeftNodeGenerator.getInstance().getInstance();
    }
}
