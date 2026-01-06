package game.actors;

import utils.Vector2;

/**
 * Classe Butterfly
 */
public class Butterfly extends Enemy {
    /**
     * Créer un Butterfly
     * 
     * @param targetPosition
     * @param speed
     * @param size
     * @param points
     */
    public Butterfly(Vector2 targetPosition, double speed, double size, int points) {
        super(targetPosition, 1, speed, "butterfly", size, 0.1, points);
    }
}
