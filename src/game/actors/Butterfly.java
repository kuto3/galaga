package game.actors;

import java.util.Random;

import game.Game;
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
    public Butterfly(Vector2 targetPosition, double speed, double size, int points, int attackCooldown) {
        super(targetPosition, 1, speed, "butterfly", size, 0.1, points, attackCooldown);
    }

    @Override
    public void attack() {
        shoot();
        nextAttackTime = Game.time + new Random().nextDouble(attackSpeed, attackSpeed + 5);
    }
}
