package game.actors;

import utils.Vector2;

public class Catcher extends Ennemy {
    public Catcher(Vector2 targetPosition, double size) {
        super(targetPosition, 1, 0.02, "catcher", size, 0.1);
    }
}
