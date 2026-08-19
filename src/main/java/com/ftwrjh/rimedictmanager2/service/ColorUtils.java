package com.ftwrjh.rimedictmanager2.service;

import javafx.scene.paint.Color;

public class ColorUtils {
    
    // Color → Hex
    public static String toHex(Color color) {
        return String.format("#%02X%02X%02X",
            (int) (color.getRed() * 255),
            (int) (color.getGreen() * 255),
            (int) (color.getBlue() * 255)
        );
    }
    
    // Hex → Color
    public static Color fromHex(String hex) {
        return Color.web(hex);
    }
    
    // Color → RGB
    public static String toRgb(Color color) {
        return String.format("rgb(%d, %d, %d)",
            (int) (color.getRed() * 255),
            (int) (color.getGreen() * 255),
            (int) (color.getBlue() * 255)
        );
    }
}