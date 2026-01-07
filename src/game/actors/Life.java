package game.actors;

import utils.Vector2;

/**
 * Classe représentant une vie du joueur affichée à l'écran.
 * 
 * Les vies sont des entités invincibles affichées en bas de l'écran
 * pour montrer le nombre de vies restantes au joueur.
 * 
 * @version 1.0
 */
public class Life extends Entity {

    /**
     * Largeur de la vie affichée.
     */
    private double length;

    /**
     * Crée une vie avec les paramètres spécifiés.
     * 
     * @param startingPosition Position initiale de la vie
     * @param length           Largeur de la vie
     */
    public Life(Vector2 startingPosition, double length) {
        super(startingPosition, 1, 1, 0.02, "ship", length, 1, true, -1);
        this.length = length;
    }

    /**
     * Met à jour la position de la vie.
     * 
     * Restreint la position dans les limites de l'écran.
     */
    @Override
    public void update() {
        double newX = targetPosition.x();
        double newY = targetPosition.y();

        // On plafone la nouvelle position dans les limites de l'écran
        var newTargetPos = new Vector2(newX, newY);
        newTargetPos.clampToBoundBox(
                new Vector2(length / 2, length / 2),
                new Vector2(1 - length / 2, 1 - length / 2));

        setTargetPosition(newTargetPos);
        super.update();
    }
}
