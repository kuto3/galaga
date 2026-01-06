package game.actors;

import engine.StdDraw;
import game.Game;
import game.levels.LevelManager;
import utils.Vector2;

/**
 * Classe représentant le jouuer.
 * A ce stade ce n'est qu'un point rouge qui se déplace avec les flèches du
 * clavier.
 */
public class Player extends Entity {
    /**
     * Créé un joueur.
     * 
     * @param startingPosition postion initiale du joueur
     * @param size             largeur du joueur
     */
    public Player(Vector2 startingPosition, double size) {
        super(startingPosition, 1, 0, 0.02, "ship", size, 1, false);
    }

    public void shoot() {
        if (isAlive() && canAttack) {
            Missile missile = new Missile(position, 0.01, true);
            LevelManager.addPlayerMissile(missile);
            timeLastShot = Game.time;
        }
    }

    public void shoot(Vector2 offset) {
        if (isAlive() && canAttack) {
            Missile missile = new Missile(position.add(offset), 0.01, true);
            LevelManager.addPlayerMissile(missile);
            timeLastShot = Game.time;
        }
    }

    /**
     *
     * Met à jour la position du joueur en fonction des touches préssé.
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
            if (Game.time - timeLastShot > 20)
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
