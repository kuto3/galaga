package utils;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Utilitaire pour gérer les ressources des entités du jeu.
 * 
 * Fournit des méthodes pour charger les informations de sprites depuis des
 * fichiers.
 * 
 * @version 1.0
 */
public class EntityUtils {

    /**
     * Charge les informations d'un sprite à partir d'un fichier.
     * 
     * Chaque ligne du fichier contient des caractères représentant les pixels.
     * Les caractères sont convertis en couleurs via ColorUtils.
     * 
     * Format: chaque caractère = un pixel
     * 
     * @param sprite Le nom du sprite (sans extension .spr)
     * @return Une matrice 2D de couleurs représentant le sprite,
     *         ou null si le fichier n'existe pas
     */
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
