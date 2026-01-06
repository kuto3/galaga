package game.levels;

import game.actors.Enemy;
import java.util.ArrayList;
import java.util.List;
import game.actors.Missile;

public class Level {
    private String name;
    private List<Enemy> enemies;
    private List<Missile> missiles = new ArrayList<>();
    private List<Missile> enemyMissiles = new ArrayList<>();

    public Level(String name) {
        this.name = name;
        this.enemies = new ArrayList<>();
    }

    public void addEnemy(Enemy enemy) {
        if (!enemies.contains(enemy))
            enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        if (enemies.contains(enemy))
            enemies.remove(enemy);
    }

    public void update() {
        enemies.forEach(Enemy::update);
        missiles.forEach(Missile::update);
        enemyMissiles.forEach(Missile::update);
    }

    public void draw() {
        missiles.forEach(Missile::draw);
        enemies.forEach(Enemy::draw);
    }

    public void addMissile(Missile missile) {
        if (!missiles.contains(missile))
            missiles.add(missile);
    }

    public List<Enemy> getEnemy() {
        return enemies;
    }

    public void isEnnemyDead() {
        var enemiesToRemove = new ArrayList<Enemy>();

        missiles.forEach(
                missile -> {
                    enemies.forEach(
                            ennemy -> {
                                if (missile.getPosition().distanceOf(ennemy.getPosition()) < 0.1) {
                                    enemies.remove(ennemy);
                                    missiles.remove(missile);
                                }
                            });
                });
    }
}
