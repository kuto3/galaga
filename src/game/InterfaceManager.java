package game;

import engine.StdDraw;
import java.awt.Font;

/**
 * Gestionnaire de l'interface utilisateur du jeu.
 * 
 * Gère l'affichage du score, du highscore, du logo et du message de game over.
 * 
 * @version 1.0
 */
public class InterfaceManager {

    /**
     * Le score actuel affiché.
     */
    private static int score = 0;

    /**
     * Le meilleur score affiché.
     */
    private static int highscore = 0;

    /**
     * Dessine l'interface utilisateur.
     * 
     * Affiche le score actuel en haut à gauche, le meilleur score en haut à droite,
     * et le logo du jeu au centre en haut de l'écran.
     */
    public static void draw() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setFont(new Font("Arial", Font.BOLD, 24));
        StdDraw.text(0.1, 0.95, "SCORE: " + score); // Coordonnées normalisées
        StdDraw.text(0.85, 0.95, "HIGH SCORE: " + highscore); // Coordonnées normalisées

        StdDraw.picture(0.5, 0.93, "ressources/logo/logo.png"); // centre de l'écran
    }

    /**
     * Affiche l'écran de game over.
     * 
     * Affiche un message "GAME OVER" et des instructions pour recommencer le jeu.
     */
    public static void over() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setFont(new Font("Arial", Font.BOLD, 48));
        StdDraw.text(0.5, 0.5, "GAME OVER");
        StdDraw.text(0.5, 0.4, "Appuyez sur Esc pour recommencer");
    }

    /**
     * Met à jour l'interface utilisateur.
     */
    public static void update() {

    }

    /**
     * Définit le score affiché.
     * 
     * @param value La nouvelle valeur du score
     */
    public static void setScore(int value) {
        score = value;
    }

    /**
     * Définit le meilleur score affiché.
     * 
     * @param value La nouvelle valeur du meilleur score
     */
    public static void setHighscore(int value) {
        highscore = value;
    }
}
