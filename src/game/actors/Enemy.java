package game.actors;

import utils.Vector2;

/**
 * Classe représentant le jouuer.
 * A ce stade ce n'est qu'un point rouge qui se déplace avec les flèches du
 * clavier.
 */
public abstract class Enemy extends Entity {
    private int points;

    /**
     * Créer un enemie
     * 
     * @param pos
     * @param health
     * @param speed
     * @param sprite
     * @param size
     * @param lerpSpeed
     * @param points
     */
    public Enemy(Vector2 pos, int health, double speed, String sprite, double size, double lerpSpeed, int points) {
        super(pos, health, 1, speed, sprite, size, lerpSpeed);
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public void update() {
        double newX = targetPosition.x();
        double newY = targetPosition.y();

        // On plafone la nouvelle position dans les limites de l'écran
        var newTargetPos = new Vector2(newX, newY);
        newTargetPos.clampToBoundBox(
                new Vector2(size / 2, size / 2),
                new Vector2(1 - size / 2, 1 - size / 2));

        setTargetPosition(newTargetPos);
        super.update();
    }
}
