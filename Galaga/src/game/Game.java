package game;

import engine.StdDraw;
import game.actors.Player;

/**
 * Classe du jeu principal.
 * Gère la création de l'espace de jeu et la boucle de jeu en temps réel.
 */
public class Game {
    public Player player; // Jouer, seul éléments actuellement dans notre jeu

    /**
     * Créé un jeu avec tous les éléments qui le composent
     */
    public Game() {
        player = new Player(0.5, 0.5, 0.05);
    }

    /**
     * Initialise l'espace de jeu
     */
    private void init() {
        StdDraw.setCanvasSize(700, 700);
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
            StdDraw.pause(30); // on attend 30 milisecondes avant de recommencer
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
        player.draw();
    }

    /**
     * Met a jour les attributs de tous les éléments du jeu
     */
    private void update() {
        player.update();
    }
}
