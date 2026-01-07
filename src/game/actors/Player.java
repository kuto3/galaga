package game.actors;

import engine.StdDraw;
import game.Game;
import game.levels.LevelManager;
import utils.Vector2;

/**
 * Classe représentant le joueur.
 * 
 * Le joueur peut se déplacer horizontalement avec les flèches du clavier
 * et tirer des missiles vers le haut avec la barre d'espace.
 * 
 * @version 1.0
 */
public class Player extends Entity {

    /**
     * Crée un joueur avec les paramètres spécifiés.
     * 
     * @param startingPosition Position initiale du joueur
     * @param size             Taille du joueur
     * @param lives            Nombre de vies initiales
     * @param attackSpeed      Délai entre deux tirs en millisecondes
     */
    public Player(Vector2 startingPosition, double size, int lives, int attackSpeed) {
        super(startingPosition, 1, lives, 0.02, "ship", size, 1, false, attackSpeed);
    }

    /**
     * Tire un missile vers le haut depuis la position du joueur.
     */
    public void shoot() {
        if (isAlive() && canAttack) {
            Missile missile = new Missile(position.sub(new Vector2(0.005, 0)), 0.01, true);
            LevelManager.addPlayerMissile(missile);
            timeLastShot = Game.time;
        }
    }

    /**
     * Tire un missile vers le haut depuis une position décalée.
     * 
     * @param offset Le décalage par rapport à la position du joueur
     */
    public void shoot(Vector2 offset) {
        if (isAlive() && canAttack) {
            Missile missile = new Missile(position.add(offset).sub(new Vector2(0.005, 0)), 0.01, true);
            LevelManager.addPlayerMissile(missile);
            timeLastShot = Game.time;
        }
    }

    /**
     * Joue une animation de dégâts au joueur.
     */
    public void onTakeDamageAnimation() {

    }

    /**
     * Met à jour la position du joueur en fonction des touches pressées.
     * 
     * - Flèche gauche (37) : déplacement à gauche
     * - Flèche droite (39) : déplacement à droite
     * - Barre espace (32) : tir d'un missile
     * 
     * La position est restreinte dans les limites de l'écran.
     */
    @Override
    public void update() {
        double newX = targetPosition.x();
        double newY = targetPosition.y();

        // Si la flèche gauche est préssé
        if (StdDraw.isKeyPressed(37)) {
            newX -= speed;
        }
        // Si la flèche droite est préssé
        if (StdDraw.isKeyPressed(39)) {
            newX += speed;
        }

        if (StdDraw.isKeyPressed(32)) {
            if (Game.time - timeLastShot > attackSpeed)
                shoot();
        }

        // On plafone la nouvelle position dans les limites de l'écran
        var newTargetPos = new Vector2(newX, newY);
        newTargetPos.clampToBoundBox(
                new Vector2(size / 2, size / 2),
                new Vector2(1 - size / 2, 1 - size / 2));

        setTargetPosition(newTargetPos);
        super.update();
    }
}
