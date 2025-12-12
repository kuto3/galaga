package game.actors;

import game.Game;
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
        this.canAttack = true;
        this.sprite = sprite;
        this.lerpSpeed = lerpSpeed;
    }

    public abstract void draw();

    public void update() {
        position = position.lerp(targetPosition, lerpSpeed);
    }

    public void shoot() {   

        if(isAlive() && canAttack && Game.time % 20 == 0) {
            Missile x = new Missile(new Vector2(position.x(), position.y()), 0.01, null, 0.2);
            Game.missiles.add(x);
            
            System.out.println("Missile créé aux coordonnées : " + x.getPosition().x() + ", " + x.getPosition().y());

        }
 
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
