package utils;

import java.awt.Color;

public final class ColorUtils {
    public static Color toColor(char character) {
        switch (character) {
            case 'N':
                return Color.BLACK;
            case 'B':
                return Color.BLUE;
            case 'Y':
                return Color.YELLOW;
            case 'R':
                return Color.RED;
            case 'W':
                return Color.WHITE;
            case 'G':
                return Color.GREEN;
            default:
                return Color.BLACK;
        }
    }
}
