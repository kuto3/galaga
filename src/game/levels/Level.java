package game.levels;

import game.actors.Ennemy;
import java.util.ArrayList;
import java.util.List;
import game.actors.Missile;

public class Level {
    private String name;
    private List<Ennemy> enemies;
    private List<Missile> missiles = new ArrayList<>();
    private List<Missile> enemyMissiles = new ArrayList<>();

    public Level(String name) {
        this.name = name;
        this.enemies = new ArrayList<>();
    }

    public void addEnemy(Ennemy enemy) {
        if (!enemies.contains(enemy))
            enemies.add(enemy);
    }

    public void removeEnemy(Ennemy enemy) {
        if (enemies.contains(enemy))
            enemies.remove(enemy);
    }

    public void update() {
        enemies.forEach(Ennemy::update);
        missiles.forEach(Missile::update);
        enemyMissiles.forEach(Missile::update);
    }

    public void draw() {
        missiles.forEach(Missile::draw);
    }

    public void addMissile(Missile missile) {
        if (!missiles.contains(missile))
            missiles.add(missile);
    }

    public List<Ennemy> getEnemy() {
        return enemies;
    }

    public void isEnnemyDead() {
        missiles.forEach(
                missile -> {
                    enemies.forEach(
                            ennemy -> {
                                if (missile.getPosition().distanceOf(ennemy.getPosition()) < 0.4) {
                                    enemies.remove(ennemy);
                                    missiles.remove(missile);
                                }
                            });
                });
    }
}
