package game.levels;

import game.Game;
import game.actors.Enemy;
import game.actors.Life;

import java.util.ArrayList;
import java.util.List;
import game.actors.Missile;
import game.actors.Player;
import utils.Vector2;

/**
 * Classe représentant un niveau du jeu Galaga.
 * 
 * Un niveau contient le joueur, les ennemis, les missiles et gère les
 * collisions.
 * Elle gère également la détection de la fin du niveau (victoire ou défaite).
 * 
 * @version 1.0
 */
public class Level {

    /**
     * Nom du niveau.
     */
    private String name;

    /**
     * Liste des ennemis du niveau.
     */
    private List<Enemy> enemies;

    /**
     * Liste des missiles du joueur.
     */
    private List<Missile> missiles;

    /**
     * Liste des missiles des ennemis.
     */
    private List<Missile> enemyMissiles;

    /**
     * Liste des affichages de vies du joueur.
     */
    private List<Life> playerLives = new ArrayList<>();

    /**
     * Le joueur du niveau.
     */
    private Player player;

    /**
     * Vitesse de mouvement de la formation d'ennemis.
     */
    private double formationSpeed;

    /**
     * Crée un nouveau niveau avec les paramètres spécifiés.
     * 
     * @param name  Nom du niveau
     * @param lives Nombre de vies initiales du joueur
     * @param speed Vitesse de déplacement de la formation d'ennemis
     */
    public Level(String name, int lives, double speed) {
        this.name = name;
        enemies = new ArrayList<>();
        missiles = new ArrayList<>();
        enemyMissiles = new ArrayList<>();
        player = new Player(new Vector2(0.5, 0.15), 0.04, lives, 300);
        formationSpeed = speed;
        playerLives.add(new Life(new Vector2(0.05, 0.1), 0.04));
        playerLives.add(new Life(new Vector2(0.1, 0.1), 0.04));
        playerLives.add(new Life(new Vector2(0.15, 0.1), 0.04));
    }

    /**
     * Ajoute un ennemi au niveau.
     * 
     * @param enemy L'ennemi à ajouter
     */
    public void addEnemy(Enemy enemy) {
        if (!enemies.contains(enemy))
            enemies.add(enemy);
    }

    /**
     * Supprime un ennemi du niveau.
     * 
     * @param enemy L'ennemi à supprimer
     */
    public void removeEnemy(Enemy enemy) {
        if (enemies.contains(enemy))
            enemies.remove(enemy);
    }

    /**
     * Obtient la vitesse de formation des ennemis.
     * 
     * @return La vitesse de formation
     */
    public double getFormationSpeed() {
        return formationSpeed;
    }

    /**
     * Met à jour tous les éléments du niveau et vérifie les collisions.
     */
    public void update() {
        enemies.forEach(Enemy::update);
        missiles.forEach(Missile::update);
        enemyMissiles.forEach(Missile::update);
        player.update();
        checkCollisions();
    }

    /**
     * Obtient le joueur du niveau.
     * 
     * @return Le joueur
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Dessine tous les éléments du niveau.
     */
    public void draw() {
        enemies.forEach(Enemy::draw);
        missiles.forEach(Missile::draw);
        player.draw();
        enemyMissiles.forEach(Missile::draw);

        for (Life elem : playerLives) {
            elem.draw();
        }
    }

    /**
     * Ajoute un missile du joueur au niveau.
     * 
     * @param missile Le missile à ajouter
     */
    public void addPlayerMissile(Missile missile) {
        if (!missiles.contains(missile))
            missiles.add(missile);
    }

    /**
     * Ajoute un missile d'ennemi au niveau.
     * 
     * @param missile Le missile à ajouter
     */
    public void addEnemyMissile(Missile missile) {
        if (!enemyMissiles.contains(missile))
            enemyMissiles.add(missile);
    }

    /**
     * Obtient la liste des ennemis du niveau.
     * 
     * @return La liste des ennemis
     */
    public List<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Vérifie si le niveau est terminé (victoire ou défaite).
     * 
     * @return true si tous les ennemis sont vaincus ou le joueur est mort
     */
    public boolean levelCleared() {
        return enemies.isEmpty();
    }

    /**
     * Vérifie et gère les collisions entre les missiles et les ennemis.
     * 
     * Inflige des dégâts aux ennemis touchés, les détruit s'ils n'ont plus de vie,
     * et ajoute les points à la variable score du jeu.
     * Gère également les collisions entre les missiles des ennemis et le joueur.
     */
    public void checkCollisions() {
        var enemiesToRemove = new ArrayList<Enemy>();
        var missilesToRemove = new ArrayList<Missile>();
        var enemyMissilesToRemove = new ArrayList<Missile>();

        // On check et stock les missiles et enemies qui sont en contact
        enemies.forEach(ennemy -> {
            missiles.forEach(missile -> {
                if (missile.getPosition().distanceOf(ennemy.getPosition()) < 0.05) {
                    enemiesToRemove.add(ennemy);
                    missilesToRemove.add(missile);
                }
            });
            if (ennemy.getPosition().distanceOf(player.getPosition()) < 0.05) {
                player.takeDamage(1);
                if (!enemiesToRemove.contains(ennemy))
                    enemiesToRemove.add(ennemy);
                if (!playerLives.isEmpty())
                    playerLives.removeLast();
            }
        });

        enemyMissiles.forEach(missile -> {
            if (missile.getPosition().distanceOf(player.getPosition()) < 0.03) {
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

    public String getName() {
        return name;
    }

}
