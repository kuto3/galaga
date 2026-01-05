package game.actors;

import utils.Vector2;

public class Moth extends Ennemy {
    public Moth(Vector2 targetPosition, double speed, double size, int points) {
        super(targetPosition, 1, speed, "moth", size, 0.1, points);
    }
}
