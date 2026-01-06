package game;

import engine.StdDraw;
import game.levels.Level;
import game.levels.LevelManager;
import java.awt.Color;

/**
 * Classe du jeu principal.
 * Gère la création de l'espace de jeu et la boucle de jeu en temps réel.
 */
public class Game {
    public static int SCREEN_WIDTH = 1000;
    public static int SCREEN_HEIGHT = 1000;

    public static int time;
    private boolean gameOver = false;

    /**
     * Créé un jeu avec tous les éléments qui le composent
     */
    public Game() {
    }

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

        while (isGameRunning()) {
            StdDraw.clear(); // On efface tous ce qu'il y a sur l'interface

            update(); // on met a jour les attributs de chaque éléments
            draw(); // on dessine chaques éléments

            StdDraw.show(); // on montre l'interface
            StdDraw.pause(10); // on attend 10 milisecondes avant de recommencer

            time++;
        }
    }

    /**
     * Condition d'arrêt du jeu
     * 
     * @return true car on n'as pas encore de conidtions d'arrêt
     */
    private boolean isGameRunning() {
        return true;
    }

    private Level GetLevel() {
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
        if (GetLevel() != null) {
            if (GetLevel().getPlayer().getLives() == 0) {
                gameOver = true;

            }
        }
        if (StdDraw.isKeyPressed(27) && gameOver) {
            gameOver = false;
            LevelManager.start();
        }
    }
}
