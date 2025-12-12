package game.actors;

import utils.Vector2;

public abstract class Entity {
    private Vector2 startingPos;
    private Vector2 position;
    protected Vector2 targetPosition;
    protected int lives;
    protected double speed;
    protected boolean canAttack;
    protected String sprite;
    protected double lerpSpeed;

    public Entity(Vector2 pos, int lives, double speed, String sprite, double lerpSpeed) {
        this.startingPos = pos;
        this.position = pos;
        this.targetPosition = pos;
        this.lives = lives;
        this.speed = speed;
        this.canAttack = false;
        this.sprite = sprite;
        this.lerpSpeed = lerpSpeed;
    }

    public abstract void draw();

    public void update() {
        position = position.lerp(targetPosition, lerpSpeed);
    }

    public void shoot() {

    }

    public boolean isAlive() {
        return lives > 0;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(Vector2 pos) {
        targetPosition = pos;
    }

    public void resetPosition() {
        targetPosition = startingPos;
    }
}
