package game.actors;

import engine.StdDraw;

/**
 * Classe représentant le jouuer.
 * A ce stade cen'est qu'un point rouge qui se déplace avec les flèches du
 * clavier.
 */
public class Player {
    private double x; // postion du joueur sur l'axe des abscisses
    private double y; // position du joueur sur l'axe des ordonnées
    private double length; // largeur du joueur

    /**
     * Créé un joueur.
     * 
     * @param x      postion du joueur sur l'axe des abscisses
     * @param y      position du joueur sur l'axe des ordonnées
     * @param length largeur du joueur
     */
    public Player(double x, double y, double length) {
        this.x = x;
        this.y = y;
        this.length = length;
    }

    /**
     * Dessine le joueur, ici c'est un rond rouge
     */
    public void draw() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(x, y, length / 2);
    }

    /**
     * Met à jour la position du joueur en fonction des touches préssé.
     */
    public void update() {
        double speed = 0.01; // vitesse de déplacement du joueur
        // Si la flèche gauche est préssé
        if (StdDraw.isKeyPressed(37)) { 
            x -= speed;
        }
        // Si la flèche haut est préssé
        if (StdDraw.isKeyPressed(38)) {
            y += speed;
        }
        // Si la flèche droite est préssé
        if (StdDraw.isKeyPressed(39)) {
            x += speed;
        }
        // Si la flèche bas est préssé
        if (StdDraw.isKeyPressed(40)) {
            y -= speed;
        }
    }
}
