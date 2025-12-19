package game.actors;

import utils.Vector2;

public class Moth extends Ennemy {
    public Moth(Vector2 targetPosition, double size) {
        super(targetPosition, 1, 0.02, "moth", size, 0.1);
    }
}
