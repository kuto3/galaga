package utils;

import java.awt.Color;

public final class ColorUtils {
    public static Color toColor(char character) {
        switch (character) {
            case 'N':
                return Color.BLACK;

            default:
                return Color.RED;
        }
    }
}
