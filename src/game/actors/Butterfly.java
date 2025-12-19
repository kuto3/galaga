package game.actors;

import utils.Vector2;

public class Butterfly extends Ennemy {
    public Butterfly(Vector2 targetPosition, double size) {
        super(targetPosition, 1, 0.02, "butterfly", size, 0.1);
    }
}
