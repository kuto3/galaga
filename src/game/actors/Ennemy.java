package game.actors;

import engine.StdDraw;
import utils.Vector2;

public class Ennemy extends Entity {

    private double length; // largeur du joueur

    public Ennemy(Vector2 pos, int lives, double speed, String sprite, double size, double lerpSpeed) {
        super(pos, lives, speed, sprite, size, lerpSpeed);
    }

    public void update() {
        double newX = targetPosition.x();
        double newY = targetPosition.y();

        // Si la flèche gauche est préssé

        // On plafone la nouvelle position dans les limites de l'écran
        var newTargetPos = new Vector2(newX, newY);
        newTargetPos.clampToBoundBox(
                new Vector2(length / 2, length / 2),
                new Vector2(1 - length / 2, 1 - length / 2));

        setTargetPosition(newTargetPos);
        super.update();
    }
}
