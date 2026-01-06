package game.actors;

import utils.Vector2;

/**
 * Classe Bee
 */
public class Bee extends Ennemy {
    /**
     * Créer un Bee
     * 
     * @param targetPosition
     * @param speed
     * @param size
     * @param points
     */
    public Bee(Vector2 targetPosition, double speed, double size, int points) {
        super(targetPosition, 1, speed, "bee", size, 0.1, points);
    }
}
