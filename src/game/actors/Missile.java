package game.actors;

import engine.StdDraw;
import utils.Vector2;

public class Missile {
    private Vector2 position;
    protected int lives;
    protected double speed;
    protected double lerpSpeed;

    public Missile(Vector2 pos, double speed, double lerpSpeed) {
        this.position = pos;
        this.speed = speed;
        this.lerpSpeed = lerpSpeed;

    }

    public void draw() {
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(getPosition().x(), getPosition().y(), 0.01);
    }

    public void update() {

        double newX = position.x();
        double newY = position.y();
        var newTargetPos = new Vector2(newX, newY + speed);
        position = newTargetPos;

    }

    public Vector2 getPosition() {
        return position;
    }

}
