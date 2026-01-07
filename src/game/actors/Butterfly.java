package game.actors;

import java.util.Random;

import game.Game;
import utils.Vector2;

/**
 * Classe représentant un papillon ennemi.
 * 
 * Un papillon est un type d'ennemi qui tire sur le joueur avec un intervalle
 * aléatoire.
 * 
 * @version 1.0
 */
public class Butterfly extends Enemy {

    /**
     * Crée un papillon ennemi avec les paramètres spécifiés.
     * 
     * @param targetPosition Position initiale du papillon
     * @param speed          Vitesse de déplacement
     * @param size           Taille du papillon
     * @param points         Nombre de points accordés à sa destruction
     * @param attackCooldown Temps minimum entre deux attaques en millisecondes
     */
    public Butterfly(Vector2 targetPosition, double speed, double size, int points, int attackCooldown) {
        super(targetPosition, 1, speed, "butterfly", size, 0.1, points, attackCooldown);
    }

    /**
     * Le papillon tire un missile avec un intervalle aléatoire.
     */
    @Override
    public void attack() {
        shoot();
        nextAttackTime = Game.time + new Random().nextDouble(attackSpeed, attackSpeed + 5);
    }
}
