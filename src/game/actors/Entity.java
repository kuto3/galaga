package game.actors;

import java.awt.Color;

import engine.StdDraw;
import utils.EntityUtils;
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
    protected double size;
    protected double lerpSpeed;
    protected Color[][] spriteInfo;

    public Entity(Vector2 pos, int lives, double speed, String sprite, double size, double lerpSpeed) {
        this.startingPos = pos;
        this.position = pos;
        this.targetPosition = pos;
        this.lives = lives;
        this.speed = speed;
        this.canAttack = true;
        this.sprite = sprite;
        this.size = size;
        this.lerpSpeed = lerpSpeed;
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
                var pixelSize = size / 2 / Game.SCREEN_WIDTH * 100;

                var x = getPosition().x() - pixelSize * line.length / 2;
                var y = getPosition().y() - pixelSize * spriteInfo.length / 2;

                StdDraw.setPenColor(line[j]);
                StdDraw.filledSquare(x + j * pixelSize * 2, y - i * pixelSize * 2, pixelSize);
            }
        }
    };

    public void update() {
        position = position.lerp(targetPosition, lerpSpeed);
    }

    public void shoot() {

        if (isAlive() && canAttack && Game.time % 20 == 0) {
            Missile missile = new Missile(new Vector2(position.x(), position.y()), 0.01, 0.2);
            Game.missiles.add(missile);

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
