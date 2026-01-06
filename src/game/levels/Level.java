package game.levels;

import game.InterfaceManager;
import game.actors.Enemy;
import game.actors.Life;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
 
import engine.StdDraw;
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
    private int score;

    public Level(String name) {
        this.name = name;
        enemies = new ArrayList<>();
        missiles = new ArrayList<>();
        enemyMissiles = new ArrayList<>();
        player = new Player(new Vector2(0.5, 0.15), 0.04);
        score = 0;
        
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
        player.update();
        checkCollisions();
    }

    public void draw() {
        missiles.forEach(Missile::draw);
        enemies.forEach(Enemy::draw);
        player.draw();
 
        for (Life elem : lives) {
            elem.draw();
       }
    }

    public void addMissile(Missile missile) {
        if (!missiles.contains(missile))
            missiles.add(missile);
    }

    public List<Enemy> getEnemy() {
        return enemies;
    }

    public int getScore() {
        return score;
    }

    public void updateScore(int value) {
        score = value;
        InterfaceManager.setScore(value);
    }

    public boolean levelCleared() {
        return enemies.isEmpty() || !player.isAlive();
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
                updateScore(score + enemy.getPoints());
            }
        });
        missilesToRemove.forEach(missile -> missiles.remove(missile));
    }
}
