package game.actors;

import utils.Vector2;

/**
 * Classe Boss
 */
public class Boss extends Ennemy {
    /**
     * Créer un Boss
     * 
     * @param targetPosition
     * @param speed
     * @param size
     * @param points
     */
    public Boss(Vector2 targetPosition, double speed, double size, int points) {
        super(targetPosition, 10, speed, "boss", size, 0.1, points);
    }
}
