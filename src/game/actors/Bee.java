package game.actors;

import java.util.Random;

import game.Game;
import utils.Vector2;

/**
 * Classe Bee
 */
public class Bee extends Enemy {
    /**
     * Créer un Bee
     * 
     * @param targetPosition
     * @param speed
     * @param size
     * @param points
     */
    public Bee(Vector2 targetPosition, double speed, double size, int points, int attackCooldown) {
        super(targetPosition, 1, speed, "bee", size, 0.1, points, attackCooldown);
    }

    @Override
    public void attack() {
        shoot();
        nextAttackTime = Game.time + new Random().nextDouble(attackSpeed, attackSpeed + 5);
    }
}
