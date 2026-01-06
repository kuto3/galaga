package game;

import engine.StdDraw;
<<<<<<< HEAD
=======
import game.actors.Enemy;
import game.actors.Missile;
>>>>>>> 5938097da30b29883047686832b58fe8b2253cd1
import game.actors.Player;
import game.levels.LevelManager;
import java.awt.Color;
import java.awt.Font;
import utils.Vector2;

/**
 * Classe du jeu principal.
 * Gère la création de l'espace de jeu et la boucle de jeu en temps réel.
 */
public class Game {
    public static int SCREEN_WIDTH = 900;
    public static int SCREEN_HEIGHT = 900;
    public Player player;
    public static int time;

    /**
     * Créé un jeu avec tous les éléments qui le composent
     */
    public Game() {
        player = new Player(new Vector2(0.5, 0.15), 0.04);
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

            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setFont(new Font("Arial", Font.BOLD, 24));
            StdDraw.text(0.5, 0.95, "GALAGA ");
            StdDraw.text(0.1, 0.95, "SCORE: "); // Coordonnées normalisées
            StdDraw.show(); // on montre l'interface
            StdDraw.pause(10); // on attend 10 milisecondes avant de recommencer
            LevelManager.isEnemyDead();
  
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

    /**
     * Dessin tous les éléments du jeu
     */
    public void draw() {
        StdDraw.setPenColor(Color.BLACK);

        StdDraw.filledRectangle(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
        LevelManager.draw();
        player.draw();

        for (int i = 0; i < player.getLives(); i++) {
            double x = 0.05 + i * 0.03;
            double y = 0.05;
            double size = 0.02;

            StdDraw.setPenColor(Color.RED);
            StdDraw.filledCircle(x, y, size / 2);
        }
      
             
          
    }

    /**
     * Met a jour les attributs de tous les éléments du jeu
     */

    private void update() {
        LevelManager.update();
        player.update();
    }
}
