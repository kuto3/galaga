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
        this.enemies = new ArrayList<>();
    }

    public void addEnemy(Ennemy enemy) {
        if (enemies.contains(enemy))
            return;
        enemies.add(enemy);
    }
}
