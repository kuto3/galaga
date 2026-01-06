package game.levels;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

import game.actors.Bee;
import game.actors.Boss;
import game.actors.Butterfly;
import game.actors.Enemy;
import game.actors.Missile;
import game.actors.Moth;
import utils.Vector2;

public class LevelManager {
    private static Level currentLevel = null;
    private static int currentLevelValue = 1;
    private static int maxLevel = getAmountOfLevels();

    public static Level loadLevel(String level) {
        File file = new File("ressources/levels/" + level + ".lvl");
        if (!file.exists())
            return null;

        try (BufferedReader reader = new BufferedReader(
                new FileReader(file))) {
            var lines = reader.lines().toArray(String[]::new);
            var levelInfos = lines[0].split(" ");
            String name = levelInfos[0];

            var levelObj = new Level(name);
            for (int i = 1; i < lines.length; i++)
                levelObj.addEnemy(getEnemyFromString(lines[i]));

            return levelObj;
        } catch (IOException e) {
            System.out.println("On a pas pu trouver le niveau " + level + ": " + e);
        }

        return null;
    }

    private static int getAmountOfLevels() {
        try {
            return Files.list(new File("ressources/levels").toPath()).toArray().length;
        } catch (Exception e) {
            System.out.println("Couldn't find files in directory: " + System.getProperty("user.dir"));
            return 0;
        }
    }

    public static Enemy getEnemyFromString(String enemyString) {
        String[] data = enemyString.split(" ");
        var position = new Vector2(Double.parseDouble(data[1]), Double.parseDouble(data[2]));
        var size = Double.parseDouble(data[3]);
        var points = Integer.parseInt(data[4]);
        var speed = Double.parseDouble(data[5]);

        switch (data[0]) {
            case "Moth":
                return new Moth(position, speed, size, points);
            case "Butterfly":
                return new Butterfly(position, speed, size, points);
            case "Bee":
                return new Bee(position, speed, size, points);
            case "Boss":
                return new Boss(position, speed, size, points);
            default:
                return null;
        }
    }

    public static void update() {
        currentLevel.update();
        if (currentLevel.levelCleared()) {
            currentLevelValue++;
            if (currentLevelValue >= maxLevel) {
                System.out.println("Vous avez gagné!");
                return;
            }
            currentLevel = loadLevel("level" + currentLevelValue);
        }
    }

    public static boolean enemyHasAllyBelow(Enemy enemy) {
        return currentLevel.enemyHasAllyBelow(enemy);
    }

    public static void draw() {
        currentLevel.draw();
    }

    public static void start() {
        currentLevel = loadLevel("level" + currentLevelValue);
    }

    public static void addPlayerMissile(Missile missile) {
        currentLevel.addPlayerMissile(missile);
    }

    public static void addEnemyMissile(Missile missile) {
        currentLevel.addEnemyMissile(missile);
    }
}
