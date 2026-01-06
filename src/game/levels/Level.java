package game.levels;

import game.actors.Ennemy;
import java.util.ArrayList;
import java.util.List;

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

    public List<Ennemy> getEnemy() {
        return enemies;
}

}
