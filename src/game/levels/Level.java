package game.levels;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import game.actors.Ennemy;

public class Level {
    private String name;
    private List<Ennemy> enemies;

    public Level(String name) {
        this.name = name;
        this.enemies = load();
    }

    public List<Ennemy> load() {
        File file = new File("ressources/levels/" + name + ".lvl");
        if (!file.exists())
            return null;

        try (BufferedReader reader = new BufferedReader(
                new FileReader(file))) {
            var levelInfo = reader.readLine();

            var lines = reader.lines().toArray(String[]::new);
            var linesLength = lines.length;

            for (var line : lines) {

            }

            var enemy = new ArrayList<Ennemy>();

            return enemy;
        } catch (IOException e) {
            System.out.println("On a pas pu trouver le niveau " + name + ": " + e);
        }

        return null;
    }
}
