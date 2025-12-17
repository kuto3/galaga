package game;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

import engine.StdDraw;
import game.actors.Missile;
import game.actors.Player;
import utils.Vector2;

/**
 * Classe du jeu principal.
 * Gère la création de l'espace de jeu et la boucle de jeu en temps réel.
 */
public class Game {
    public static int SCREEN_WIDTH = 800;
    public static int SCREEN_HEIGHT = 800;
    public Player player; // Jouer, seul éléments actuellement dans notre jeu
    public static List<Missile> missiles = new ArrayList<>();
    public static List<Vector2> enemies = new ArrayList<>();
    public static List<Vector2> enemyMissiles = new ArrayList<>();
    public static int time;

    /**
     * Créé un jeu avec tous les éléments qui le composent
     */
    public Game() {
        player = new Player(new Vector2(0.5, 0.3), 0.05);
    }

    /**
     * Initialise l'espace de jeu
     */
    private void init() {
        StdDraw.setCanvasSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        StdDraw.enableDoubleBuffering();
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
            StdDraw.pause(20); // on attend 10 milisecondes avant de recommencer
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
        missiles.forEach(Missile::draw);
        player.draw();
    }

    /**
     * Met a jour les attributs de tous les éléments du jeu
     */

    private void update() {
        player.update();
        missiles.forEach(Missile::update);
    }
}
