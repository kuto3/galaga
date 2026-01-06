package game;

import engine.StdDraw;
import game.actors.Ennemy;
import game.actors.Missile;
import game.actors.Player;
import game.levels.Level;
import game.levels.LevelManager;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
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
            StdDraw.pause(5); // on attend 10 milisecondes avant de recommencer
            if (time % 60 == 0) {
                isEnnemyDead();
            }

            time++;
        }
    }

    public void LaunchLevel(Level level) {
        enemies = level.getEnemy();

    }

    public void isEnnemyDead() {
        missiles.forEach(

                missile -> {
                    enemies.forEach(
                            ennemy -> {
                                if (distanceOf(missile.getPosition(), ennemy.getPosition()) < 0.4) {
                                    enemies.remove(ennemy);
                                    missiles.remove(missile);
                                }
                            });
                });
    }

    public double distanceOf(Vector2 a, Vector2 b) {
        return (double) Math.sqrt(Math.pow(b.x() - a.x(), 2) + Math.pow(b.y() - a.y(), 2));
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
        enemies.forEach(Ennemy::draw);

    }

    /**
     * Met a jour les attributs de tous les éléments du jeu
     */

    private void update() {
        LevelManager.update();
        player.update();
    }
}
