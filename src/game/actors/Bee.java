package game.actors;

import utils.Vector2;

public class Bee extends Ennemy {
    public Bee(Vector2 targetPosition, double size) {
        super(targetPosition, 1, 0.02, "bee", size, 0.1);
    }
}
