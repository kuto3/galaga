package game.actors;

import utils.Vector2;

public class Bee extends Ennemy {
    public Bee(Vector2 targetPosition, double speed, double size, int points) {
        super(targetPosition, 1, speed, "bee", size, 0.1, points);
    }
}
