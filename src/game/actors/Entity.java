package game.actors;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import engine.StdDraw;
import utils.ColorUtils;
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
            loadSpriteInfo();
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
                var x = getPosition().x() / 2;
                var y = getPosition().y() / 2;

                var pixelSize = size / Game.SCREEN_WIDTH * 100;
                StdDraw.setPenColor(line[j]);
                StdDraw.filledSquare(x + j * pixelSize, y - i * pixelSize, pixelSize);
            }
        }
    };

    private void loadSpriteInfo() {
        File file = new File("ressources/sprites/" + sprite + ".spr");
        if (!file.exists())
            return;

        try (BufferedReader reader = new BufferedReader(
                new FileReader(file))) {
            var lines = reader.lines().toArray(String[]::new);
            var linesLength = lines.length;
            spriteInfo = new Color[linesLength][];
            for (int j = 0; j < linesLength; j++) {
                var line = lines[j];
                System.out.println(line);
                var lineLength = line.length();
                var colorLine = new Color[lineLength];

                for (int i = 0; i < lineLength; i++)
                    colorLine[i] = ColorUtils.toColor(line.charAt(i));

                spriteInfo[j] = colorLine;
            }
        } catch (IOException e) {
            System.out.println("On a pas pu trouver le sprite " + sprite + ": " + e);
        }
    }

    public void update() {
        position = position.lerp(targetPosition, lerpSpeed);
    }

    public void shoot() {

        if (isAlive() && canAttack && Game.time % 20 == 0) {
            Missile missile = new Missile(new Vector2(position.x(), position.y()), 0.01, 0.2);
            Game.missiles.add(missile);

            System.out.println(
                    "Missile créé aux coordonnées : " + missile.getPosition().x() + ", " + missile.getPosition().y());

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
