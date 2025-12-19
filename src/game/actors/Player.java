package game.actors;

import engine.StdDraw;
import utils.Vector2;

/**
 * Classe représentant le jouuer.
 * A ce stade ce n'est qu'un point rouge qui se déplace avec les flèches du
 * clavier.
 */
public class Player extends Entity {
    private double length; // largeur du joueur

    /**
     * Créé un joueur.
     * 
     * @param startingPosition postion initiale du joueur
     * @param length           largeur du joueur
     */
    public Player(Vector2 startingPosition, double length) {
        super(startingPosition, 3, 0.05, "ship", length, 1);
        this.length = length;
    }

    /**
     * Met à jour la position du joueur en fonction des touches préssé.
     */
    @Override
    public void update() {
        double newX = targetPosition.x();
        double newY = targetPosition.y();

        // Si la flèche gauche est préssé
        if (StdDraw.isKeyPressed(37)) {
            newX -= speed;
        }
        // Si la flèche droite est préssé
        if (StdDraw.isKeyPressed(39)) {
            newX += speed;
        }

        if (StdDraw.isKeyPressed(69)) {
            shoot();
        }

        // On plafone la nouvelle position dans les limites de l'écran
        var newTargetPos = new Vector2(newX, newY);
        newTargetPos.clampToBoundBox(
                new Vector2(length / 2, length / 2),
                new Vector2(1 - length / 2, 1 - length / 2));

        System.out.println(newTargetPos);
        setTargetPosition(newTargetPos);
        super.update();
    }
}
