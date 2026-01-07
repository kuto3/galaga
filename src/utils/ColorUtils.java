package utils;

import java.awt.Color;

/**
 * Utilitaire pour convertir des caractères en couleurs.
 * 
 * Fournit une méthode statique pour traduire des caractères en objets Color.
 * 
 * @version 1.0
 */
public final class ColorUtils {

    /**
     * Convertit un caractère en sa couleur correspondante.
     * 
     * Mappages:
     * - 'N' -> Noir (BLACK)
     * - 'B' -> Bleu (BLUE)
     * - 'Y' -> Jaune (YELLOW)
     * - 'R' -> Rouge (RED)
     * - 'W' -> Blanc (WHITE)
     * - 'G' -> Vert (GREEN)
     * - Autres -> Noir par défaut
     * 
     * @param character Le caractère représentant une couleur
     * @return L'objet Color correspondant
     */
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
