package game.levels;

import game.Game;
import game.actors.Enemy;
import game.actors.Life;

import java.util.ArrayList;
import java.util.List;
import game.actors.Missile;
import game.actors.Player;
import utils.Vector2;

public class Level {
    private String name;
    private List<Enemy> enemies;
    private List<Missile> missiles;
    private List<Missile> enemyMissiles;
    Life life1 = new Life(new Vector2(0.05, 0.1), 0.04);
    Life life2 = new Life(new Vector2(0.1, 0.1), 0.04);
    Life life3 = new Life(new Vector2(0.15, 0.1), 0.04);
    List<Life> lives = List.of(life1, life2, life3);
    private Player player;
    private double formationSpeed;

    public Level(String name, int lives, double speed, int attackSpeed) {
        this.name = name;
        enemies = new ArrayList<>();
        missiles = new ArrayList<>();
        enemyMissiles = new ArrayList<>();
        player = new Player(new Vector2(0.5, 0.15), 0.04, lives, attackSpeed);
        formationSpeed = speed;
    }

    public void addEnemy(Enemy enemy) {
        if (!enemies.contains(enemy))
            enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        if (enemies.contains(enemy))
            enemies.remove(enemy);
    }

    public double getFormationSpeed() {
        return formationSpeed;
    }

    public void update() {
        enemies.forEach(Enemy::update);
        missiles.forEach(Missile::update);
        enemyMissiles.forEach(Missile::update);
        player.update();
        checkCollisions();
    }

    public Player getPlayer() {
        return player;
    }

    public void draw() {
        missiles.forEach(Missile::draw);
        enemies.forEach(Enemy::draw);
        player.draw();

        for (Life elem : lives) {
            elem.draw();
        }
    }

    public void addPlayerMissile(Missile missile) {
        if (!missiles.contains(missile))
            missiles.add(missile);
    }

    public void addEnemyMissile(Missile missile) {
        if (!enemyMissiles.contains(missile))
            enemyMissiles.add(missile);
    }

    public List<Enemy> getEnemy() {
        return enemies;
    }

    public boolean levelCleared() {
        return enemies.isEmpty() || !player.isAlive();
    }

    public boolean enemyHasAllyBelow(Enemy enemy) {
        for (var ally : enemies) {
            var box = enemy.getPosition().mul(new Vector2(1, 5)).sub(new Vector2(0, enemy.getSize() / 2));
            var offset = new Vector2(0.01, 0);

            if (ally != enemy && ally.getPosition().isInBoundBox(
                    box.sub(offset),
                    box.add(offset)))
                return true;
        }
        return false;
    }

    public void checkCollisions() {
        var enemiesToRemove = new ArrayList<Enemy>();
        var missilesToRemove = new ArrayList<Missile>();

        // On check et stock les missiles et enemies qui sont en contact
        missiles.forEach(missile -> {
            enemies.forEach(ennemy -> {
                if (missile.getPosition().distanceOf(ennemy.getPosition()) < 0.05) {
                    enemiesToRemove.add(ennemy);
                    missilesToRemove.add(missile);
                }
            });
        });

        // Et on leur enlève un pv
        enemiesToRemove.forEach(enemy -> {
            enemy.takeDamage(1);
            if (!enemy.isAlive()) {
                enemies.remove(enemy);
                Game.updateScore(enemy.getPoints());
            }
        });
        missilesToRemove.forEach(missile -> missiles.remove(missile));
    }

}
