package game.levels;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import game.actors.Bee;
import game.actors.Boss;
import game.actors.Butterfly;
import game.actors.Ennemy;
import game.actors.Moth;
import utils.Vector2;

public class LevelManager {
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

    public static Ennemy getEnemyFromString(String enemyString) {
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
}
