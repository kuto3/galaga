package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Color;

public class EntityUtils {
    public static Color[][] loadSpriteInfo(String sprite) {
        File file = new File("ressources/sprites/" + sprite + ".spr");
        if (!file.exists())
            return null;

        try (BufferedReader reader = new BufferedReader(
                new FileReader(file))) {
            var lines = reader.lines().toArray(String[]::new);
            var linesLength = lines.length;
            var spriteInfo = new Color[linesLength][];

            for (int j = 0; j < linesLength; j++) {
                var line = lines[j];
                var lineLength = line.length();
                var colorLine = new Color[lineLength];

                for (int i = 0; i < lineLength; i++)
                    colorLine[i] = ColorUtils.toColor(line.charAt(i));

                spriteInfo[j] = colorLine;
            }

            return spriteInfo;
        } catch (IOException e) {
            System.out.println("On a pas pu trouver le sprite " + sprite + ": " + e);
        }

        return null;
    }
}
