package game.actors;

import java.util.Random;

import game.Game;
import utils.Vector2;

/**
 * Classe Boss
 */
public class Boss extends Enemy {
    /**
     * Créer un Boss
     * 
     * @param targetPosition
     * @param speed
     * @param size
     * @param points
     */
    public Boss(Vector2 targetPosition, double speed, double size, int points, int attackCooldown) {
        super(targetPosition, 10, speed, "boss", size, 0.1, points, attackCooldown);
    }

    @Override
    public void attack() {
        shoot(new Vector2(-0.01, 0));
        shoot(new Vector2(0.01, 0));
        nextAttackTime = Game.time + new Random().nextDouble(attackSpeed, attackSpeed + 5);
    }
}
