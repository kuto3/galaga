package game;

import engine.StdDraw;
import game.levels.Level;
import game.levels.LevelManager;
import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Classe du jeu principal.
 * 
 * Gère la création de l'espace de jeu et la boucle de jeu en temps réel.
 * Cette classe est responsable de l'initialisation du jeu, de la gestion de la
 * boucle
 * principale, du dessin et de la mise à jour de tous les éléments du jeu.
 * Elle gère également le score, le highscore et l'état du jeu (en cours ou game
 * over).
 * 
 * @version 1.0
 */
public class Game {

    /**
     * Largeur de l'écran de jeu en pixels.
     */
    public static int SCREEN_WIDTH = 1000;

    /**
     * Hauteur de l'écran de jeu en pixels.
     */
    public static int SCREEN_HEIGHT = 1000;

    /**
     * Le temps écoulé depuis le début du jeu en millisecondes.
     */
    public static int time;

    /**
     * Indique si le jeu est terminé.
     */
    private static boolean gameOver = false;

    /**
     * Le meilleur score enregistré.
     */
    public static int highscore = loadHighsScore();

    /**
     * Le score actuel.
     */
    public static int score = 0;

    /**
     * Initialise l'espace de jeu et les éléments nécessaires.
     * 
     * Configure la taille de la fenêtre, active le double buffering
     * et démarre le gestionnaire de niveaux.
     */
    private void init() {
        StdDraw.setCanvasSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        StdDraw.enableDoubleBuffering();
        LevelManager.start();
    }

    /**
     * Initialise le jeu et lance la boucle de jeu en temps réel.
     * 
     * Cette méthode contient la boucle principale du jeu qui:
     * - Efface l'écran
     * - Met à jour les éléments du jeu
     * - Dessine les éléments
     * - Affiche l'écran
     * - Attend 30 millisecondes avant la prochaine itération
     * 
     * Elle gère également la sauvegarde du highscore à chaque itération.
     */
    public void launch() {
        init();
        updateScore(0);

        while (isGameRunning()) {
            StdDraw.clear(); // On efface tous ce qu'il y a sur l'interface

            update(); // on met a jour les attributs de chaque éléments
            draw(); // on dessine chaques éléments

            StdDraw.show(); // on montre l'interface
            StdDraw.pause(30); // on attend 30 milisecondes avant de recommencer

            time += 30;

            try {
                Files.writeString(Path.of("ressources/highscore/highscore.sc"), String.valueOf(highscore));
            } catch (IOException e) {
                System.out.println("Impossible d'écrire le highscore : " + e.getMessage());
            }
        }
    }

    /**
     * Charge le meilleur score enregistré depuis le fichier de configuration.
     * 
     * Si le fichier n'existe pas ou est corrompu, retourne 0.
     * 
     * @return Le meilleur score enregistré, ou 0 s'il n'existe pas
     */
    private static int loadHighsScore() {
        try {
            String content = Files.readString(Path.of("ressources/highscore/highscore.sc"));
            return Integer.parseInt(content.trim());
        } catch (IOException e) {
            System.out.println("Aucun highscore trouvé, valeur par défaut = 0");
            return 0;
        } catch (NumberFormatException e) {
            System.out.println("Highscore corrompu, reset = 0");
            return 0;
        }
    }

    /**
     * Met à jour le score actuel et le highscore.
     * 
     * Ajoute la valeur au score actuel et met à jour le highscore
     * s'il est dépassé. Notifie également le gestionnaire d'interface
     * des changements.
     * 
     * @param value La valeur à ajouter au score actuel
     */
    public static void updateScore(int value) {
        score += value;
        highscore = Math.max(score, highscore);
        InterfaceManager.setHighscore(highscore);
        InterfaceManager.setScore(score);
    }

    /**
     * Réinitialise le score actuel à 0.
     */
    public static void resetScore() {
        score = 0;
    }

    /**
     * Vérifie si le jeu est en cours d'exécution.
     * 
     * @return true si le jeu doit continuer, false sinon
     */
    private boolean isGameRunning() {
        return true;
    }

    /**
     * Obtient le niveau actuel.
     * 
     * @return Le niveau actuel du gestionnaire de niveaux
     */
    private Level getLevel() {
        return LevelManager.getCurrentLevel();
    }

    /**
     * Dessine tous les éléments du jeu.
     * 
     * Dessine le fond noir, puis appelle les méthodes de dessin du gestionnaire
     * de niveaux et du gestionnaire d'interface. Affiche également un message
     * de game over si le jeu est terminé.
     */
    public void draw() {
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.filledRectangle(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);

        if (gameOver) {
            InterfaceManager.over();
            return;
        }

        LevelManager.draw();
        InterfaceManager.draw();
    }

    /**
     * Met à jour les attributs de tous les éléments du jeu.
     * 
     * Appelle les méthodes de mise à jour du gestionnaire de niveaux
     * et du gestionnaire d'interface. Gère également la détection du game over
     * et le redémarrage du jeu.
     */
    private void update() {
        LevelManager.update();
        InterfaceManager.update();
        if (getLevel() != null) {
            if (!getLevel().getPlayer().isAlive()) {
                gameOver = true;
                resetScore();
            }
        }

        if (StdDraw.isKeyPressed(27) && gameOver) {
            gameOver = false;
            LevelManager.restart();
        }
    }
}
