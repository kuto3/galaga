package game.actors;

import java.util.Random;

import game.Game;
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
    public Moth(Vector2 targetPosition, double speed, double size, int points, int attackCooldown) {
        super(targetPosition, 1, speed, "moth", size, 0.1, points, attackCooldown);
    }

    @Override
    public void attack() {
        System.out.println("ATTAQUE DU MOTH");
        nextAttackTime = Game.time + new Random().nextDouble(attackSpeed, attackSpeed + 5);
    }
}
