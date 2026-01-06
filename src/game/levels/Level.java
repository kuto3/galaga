package game.levels;

import engine.StdDraw;
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
    private List<Life> playerLives = new ArrayList<>();
    private Player player;
    private double formationSpeed;

    public Level(String name, int lives, double speed, int attackSpeed) {
        this.name = name;
        enemies = new ArrayList<>();
        missiles = new ArrayList<>();
        enemyMissiles = new ArrayList<>();
        player = new Player(new Vector2(0.5, 0.15), 0.04, lives, attackSpeed);
        formationSpeed = speed;
        playerLives.add(new Life(new Vector2(0.05, 0.1), 0.04));
        playerLives.add(new Life(new Vector2(0.1, 0.1), 0.04));
        playerLives.add(new Life(new Vector2(0.15, 0.1), 0.04));
      
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
        enemyMissiles.forEach(Missile::draw);
        enemies.forEach(Enemy::draw);
        player.draw();
          
      setTargetPositionx();
     
        for (Life elem : playerLives) {
            elem.draw();
        }
    }

    public void  setTargetPositionx() {
          boolean goright = true;
          for(int i = 0; i < enemies.size(); i++) {
            double x = enemies.get(i).getTargetPosition().x();


            if(goright) {
                  enemies.get(i).setTargetPosition(new Vector2(x += 0.001,  enemies.get(i).getTargetPosition().y()));
                    if(x >= 0.9) {
                        goright = false;
                    }
            } else
            {
                  enemies.get(i).setTargetPosition(new Vector2(x -= 0.001,  enemies.get(i).getTargetPosition().y()));
                  if(x <= 0.1) {
                        goright = false;
                    }
            }
           
        
             
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
            var pos = enemy.getPosition();
            var lineBelow = new Vector2(pos.x(), pos.y() - enemy.getSize());
            var offset = new Vector2(0.01, 0);

            var min = new Vector2(lineBelow.x(), lineBelow.y()).sub(offset);
            var max = new Vector2(lineBelow.x(), lineBelow.y()).add(offset);

            if (ally != enemy && ally.getPosition().isInBoundBox(min, max)) {
                return true;
            }
        }
        return false;
    }

    public void checkCollisions() {
        var enemiesToRemove = new ArrayList<Enemy>();
        var missilesToRemove = new ArrayList<Missile>();
        var enemyMissilesToRemove = new ArrayList<Missile>();

        // On check et stock les missiles et enemies qui sont en contact
        missiles.forEach(missile -> {
            enemies.forEach(ennemy -> {
                if (missile.getPosition().distanceOf(ennemy.getPosition()) < 0.05) {
                    enemiesToRemove.add(ennemy);
                    missilesToRemove.add(missile);
                }
            });
        });

        enemyMissiles.forEach(missile -> {
            if (missile.getPosition().distanceOf(player.getPosition()) < 0.05) {
                enemyMissilesToRemove.add(missile);
                player.takeDamage(1);
                if (!playerLives.isEmpty())
                    playerLives.removeLast();
            }
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
        enemyMissilesToRemove.forEach(missile -> enemyMissiles.remove(missile));
    }

}
