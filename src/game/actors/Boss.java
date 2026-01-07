package game.actors;

import java.util.Random;

import game.Game;
import utils.Vector2;

/**
 * Classe représentant un boss ennemi.
 * 
 * Un boss est un ennemi plus puissant avec plus de santé et un attaque double
 * qui tire deux missiles à la fois (un à gauche, un à droite).
 * 
 * @version 1.0
 */
public class Boss extends Enemy {

    /**
     * Crée un boss ennemi avec les paramètres spécifiés.
     * 
     * @param targetPosition Position initiale du boss
     * @param speed          Vitesse de déplacement
     * @param size           Taille du boss
     * @param points         Nombre de points accordés à sa destruction
     * @param attackCooldown Temps minimum entre deux attaques en millisecondes
     */
    public Boss(Vector2 targetPosition, double speed, double size, int points, int attackCooldown) {
        super(targetPosition, 10, speed, "boss", size, 0.1, points, attackCooldown);
    }

    /**
     * Le boss tire deux missiles simultanément (un à gauche, un à droite).
     */
    @Override
    public void attack() {
        shoot(new Vector2(-0.01, 0));
        shoot(new Vector2(0.01, 0));
        nextAttackTime = Game.time + new Random().nextDouble(attackSpeed, attackSpeed + 5);
    }
}
