package game.actors;

import java.awt.Color;
import engine.StdDraw;
import utils.Vector2;

import game.Game;

import utils.EntityUtils;

public class Missile {
    private Vector2 position;
    protected int lives;
    protected double speed;
    protected double lerpSpeed;

    protected Vector2 targetPosition;

    protected boolean canAttack;
    protected String sprite;
    protected double size;

    protected Color[][] spriteInfo;

    public Missile(Vector2 pos, double speed, double lerpSpeed) {
        this.position = pos;
        this.speed = speed;
        this.lerpSpeed = lerpSpeed;
        this.sprite = "missile";
        this.spriteInfo = EntityUtils.loadSpriteInfo(sprite);
        this.size = 0.009;

    }

    public void draw() {
        if (sprite == null || spriteInfo == null) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.filledCircle(position.x(), position.y(), 0.01);
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

        double newX = position.x();
        double newY = position.y();
        var newTargetPos = new Vector2(newX, newY + speed);
        position = newTargetPos;

    }

    public Vector2 getPosition() {
        return position;
    }

}
