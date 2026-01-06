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
 * Gère la création de l'espace de jeu et la boucle de jeu en temps réel.
 */
public class Game {
    public static int SCREEN_WIDTH = 1000;
    public static int SCREEN_HEIGHT = 1000;

    public static int time;
    private static boolean gameOver = false;
    public static int highscore = loadHighsScore();
    public static int score = 0;

    /**
     * /**
     * Initialise l'espace de jeu
     */
    private void init() {
        StdDraw.setCanvasSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        StdDraw.enableDoubleBuffering();
        LevelManager.start();
    }

    /**
     * Initialise le jeu et lance la boucle de jeu en temps réel
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

    public static void updateScore(int value) {
        score += value;
        highscore = Math.max(score, highscore);
        InterfaceManager.setHighscore(highscore);
        InterfaceManager.setScore(score);
    }

    public static void resetScore() {
        score = 0;
    }

    /**
     * Condition d'arrêt du jeu
     * 
     * @return true car on n'as pas encore de conidtions d'arrêt
     */
    private boolean isGameRunning() {
        return true;
    }

    private Level getLevel() {
        return LevelManager.getCurrentLevel();
    }

    /**
     * Dessin tous les éléments du jeu
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
     * Met a jour les attributs de tous les éléments du jeu
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
