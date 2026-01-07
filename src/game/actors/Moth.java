package game.actors;

import java.util.Random;

import game.Game;
import utils.Vector2;

/**
 * Classe représentant une mite ennemie.
 * 
 * Une mite est un type d'ennemi avec un comportement d'attaque spécifique.
 * 
 * @version 1.0
 */
public class Moth extends Enemy {

    /**
     * Crée une mite ennemie avec les paramètres spécifiés.
     * 
     * @param targetPosition Position initiale de la mite
     * @param speed          Vitesse de déplacement
     * @param size           Taille de la mite
     * @param points         Nombre de points accordés à sa destruction
     * @param attackCooldown Temps minimum entre deux attaques en millisecondes
     */
    public Moth(Vector2 targetPosition, double speed, double size, int points, int attackCooldown) {
        super(targetPosition, 1, speed, "moth", size, 0.1, points, attackCooldown);
    }

    /**
     * La mite exécute son attaque spécifique.
     */
    @Override
    public void attack() {
        System.out.println("ATTAQUE DU MOTH");
        nextAttackTime = Game.time + new Random().nextDouble(attackSpeed, attackSpeed + 5);
    }
}
