package game.actors;

import java.util.Random;

import game.Game;
import utils.Vector2;

/**
 * Classe représentant une abeille ennemie.
 * 
 * Une abeille est un type d'ennemi qui tire sur le joueur avec un intervalle
 * aléatoire.
 * 
 * @version 1.0
 */
public class Bee extends Enemy {

    /**
     * Crée une abeille ennemie avec les paramètres spécifiés.
     * 
     * @param targetPosition Position initiale de l'abeille
     * @param speed          Vitesse de déplacement
     * @param size           Taille de l'abeille
     * @param points         Nombre de points accordés à sa destruction
     * @param attackCooldown Temps minimum entre deux attaques en millisecondes
     */
    public Bee(Vector2 targetPosition, double speed, double size, int points, int attackCooldown) {
        super(targetPosition, 1, speed, "bee", size, 0.1, points, attackCooldown);
    }

    /**
     * L'abeille tire un missile avec un intervalle aléatoire.
     */
    @Override
    public void attack() {
        shoot();
    }
}
