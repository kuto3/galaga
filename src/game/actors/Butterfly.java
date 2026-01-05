package game.actors;

import utils.Vector2;

public class Butterfly extends Ennemy {
    public Butterfly(Vector2 targetPosition, double speed, double size, int points) {
        super(targetPosition, 1, speed, "butterfly", size, 0.1, points);
    }
}
