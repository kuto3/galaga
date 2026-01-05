package game.actors;

import utils.Vector2;

public class Boss extends Ennemy {
    public Boss(Vector2 targetPosition, double speed, double size, int points) {
        super(targetPosition, 10, speed, "boss", size, 0.1, points);
    }
}
