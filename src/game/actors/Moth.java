package game.actors;

import utils.Vector2;

/**
 * Classe Moth
 */
public class Moth extends Enemy {
    /**
     * Créer un Moth
     * 
     * @param targetPosition
     * @param speed
     * @param size
     * @param points
     */
    public Moth(Vector2 targetPosition, double speed, double size, int points) {
        super(targetPosition, 1, speed, "moth", size, 0.1, points);
    }
}
