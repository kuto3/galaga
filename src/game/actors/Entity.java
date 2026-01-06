package game.actors;

import engine.StdDraw;
import game.Game;
import java.awt.Color;
import utils.EntityUtils;
import utils.Vector2;

public abstract class Entity {
    protected Vector2 startingPos;
    protected Vector2 position;
    protected Vector2 targetPosition;
    protected int health;
    protected int lives;
    protected double speed;
    protected boolean canAttack;
    protected String sprite;
    protected double size;
    protected double lerpSpeed;
    protected boolean incivible;
    protected int attackSpeed;

    protected Color[][] spriteInfo;
    protected int timeLastShot;

    public Entity(Vector2 pos, int health, int lives, double speed, String sprite, double size, double lerpSpeed,
            boolean incivible, int attackCooldown) {
        this.startingPos = pos;
        this.position = pos;
        this.targetPosition = pos;
        this.health = health;
        this.lives = lives;
        this.speed = speed;
        this.canAttack = true;
        this.sprite = sprite;
        this.size = size;
        this.lerpSpeed = lerpSpeed;
        this.incivible = incivible;
        this.attackSpeed = attackCooldown;

        if (sprite != null)
            spriteInfo = EntityUtils.loadSpriteInfo(sprite);
    }

    public void draw() {
        if (sprite == null || spriteInfo == null) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.filledCircle(position.x(), position.y(), size / 2);
            return;
        }

        for (int i = 0; i < spriteInfo.length; i++) {
            var line = spriteInfo[i];
            for (int j = 0; j < line.length; j++) {
                var pixelSize = size * 0.3 / Game.SCREEN_WIDTH * 100;

                var x = position.x() - pixelSize * line.length / 2;
                var y = position.y() - pixelSize * spriteInfo.length / 2;

                StdDraw.setPenColor(line[j]);
                StdDraw.filledSquare(x + j * pixelSize * 2, y - i * pixelSize * 2, pixelSize);
            }
        }

    };

    public void update() {
        position = position.lerp(targetPosition, lerpSpeed);
    }

    public void takeDamage(int amount) {
        health = Math.clamp(health - amount, 0, health);
        if (health == 0)
            lives--;
    }

    public boolean isAlive() {
        return lives > 0;
    }

    public int getLives() {
        return lives;
    }

    public Vector2 getPosition() {
        return new Vector2(position.x(), position.y());
    }

    public double getSize() {
        return size;
    }

    public Vector2 getTargetPosition() {
        return new Vector2(targetPosition.x(), targetPosition.y());
    }

    public void setTargetPosition(Vector2 pos) {
        targetPosition = pos;
    }

    public void resetPosition() {
        targetPosition = startingPos;
    }
}
