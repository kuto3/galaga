package game.levels;

import game.actors.Bee;
import game.actors.Boss;
import game.actors.Butterfly;
import game.actors.Enemy;
import game.actors.Missile;
import game.actors.Moth;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

import utils.Vector2;

/**
 * Gestionnaire des niveaux du jeu Galaga.
 * 
 * Gère le chargement, la mise à jour et la gestion des niveaux.
 * Maintient le niveau actuel et contrôle la progression du jeu.
 * 
 * @version 1.0
 */
public class LevelManager {

    /**
     * Le niveau actuellement en cours.
     */
    private static Level currentLevel = null;

    /**
     * Le numéro du niveau actuel.
     */
    private static int currentLevelValue = 1;

    /**
     * Le nombre maximum de niveaux disponibles.
     */
    private static int maxLevel = getAmountOfLevels();

    /**
     * Charge un niveau depuis un fichier .lvl.
     * 
     * Le fichier contient les informations du niveau et la liste des ennemis.
     * Format: première ligne = métadonnées, lignes suivantes = données d'ennemis.
     * 
     * @param level Le nom du niveau à charger (sans extension)
     * @param lives Nombre de vies du joueur pour ce niveau
     * @return Le niveau chargé, ou null s'il n'existe pas
     */
    public static Level loadLevel(String level, int lives) {
        File file = new File("ressources/levels/" + level + ".lvl");
        if (!file.exists())
            return null;

        try (BufferedReader reader = new BufferedReader(
                new FileReader(file))) {
            var lines = reader.lines().toArray(String[]::new);
            var levelInfos = lines[0].split(" ");
            String name = levelInfos[0];
            double formationSpeed = Double.parseDouble(levelInfos[1]);
            int attackSpeed = Integer.parseInt(levelInfos[2]);
            int attackIntermission = Integer.parseInt(levelInfos[3]);

            var levelObj = new Level(name, lives, formationSpeed);
            for (int i = 1; i < lines.length; i++)
                levelObj.addEnemy(getEnemyFromString(lines[i], formationSpeed, attackIntermission));

            return levelObj;
        } catch (IOException e) {
            System.out.println("On a pas pu trouver le niveau " + level + ": " + e);
        }

        return null;
    }

    /**
     * Compte le nombre de fichiers de niveaux disponibles.
     * 
     * @return Le nombre de niveaux disponibles dans le répertoire ressources/levels
     */
    private static int getAmountOfLevels() {
        try {
            var amount = Files.list(new File("ressources/levels").toPath()).toArray().length;
            System.out.println(amount);
            return amount;
        } catch (Exception e) {
            System.out.println("Couldn't find files in directory: " + System.getProperty("user.dir"));
            return 0;
        }
    }

    /**
     * Crée un ennemi à partir d'une chaîne de données.
     * 
     * Format: "TYPE x y size points speed"
     * Types valides: Moth, Butterfly, Bee, Boss
     * 
     * @param enemyString    La chaîne contenant les données de l'ennemi
     * @param moveSpeed      La vitesse de déplacement de la formation (non
     *                       utilisée)
     * @param attackCooldown Le temps d'attente entre les attaques de l'ennemi
     * @return L'ennemi créé, ou null si le type est invalide
     */
    public static Enemy getEnemyFromString(String enemyString, double moveSpeed, int attackCooldown) {
        String[] data = enemyString.split(" ");
        var position = new Vector2(Double.parseDouble(data[1]), Double.parseDouble(data[2]));
        var size = Double.parseDouble(data[3]);
        var points = Integer.parseInt(data[4]);
        var speed = Double.parseDouble(data[5]);

        switch (data[0]) {
            case "Moth":
                return new Moth(position, speed, size, points, attackCooldown);
            case "Butterfly":
                return new Butterfly(position, speed, size, points, attackCooldown);
            case "Bee":
                return new Bee(position, speed, size, points, attackCooldown);
            case "Boss":
                return new Boss(position, speed, size, points, attackCooldown);
            default:
                return null;
        }
    }

    /**
     * Met à jour le niveau actuel et gère la progression vers le prochain niveau.
     * 
     * Si le niveau actuel est terminé, charge le niveau suivant.
     */
    public static void update() {
        if (currentLevel == null)
            return;

        currentLevel.update();

        if (currentLevel.levelCleared()) {
            currentLevelValue++;
            if (currentLevelValue > maxLevel) {
                return;
            }
            currentLevel = loadLevel("level" + currentLevelValue, 3);
        }
    }

    /**
     * Obtient le niveau actuellement en cours.
     * 
     * @return Le niveau actuel, ou null s'il n'y a pas de niveau chargé
     */
    public static Level getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Obtient la vitesse de formation des ennemis du niveau actuel.
     * 
     * @return La vitesse de formation
     */
    public static double getFormationSpeed() {
        return currentLevel.getFormationSpeed();
    }

    /**
     * Dessine le niveau actuellement en cours.
     */
    public static void draw() {
        if (currentLevel == null)
            return;
        currentLevel.draw();
    }

    /**
     * Démarre le jeu en chargeant le premier niveau.
     */
    public static void start() {
        currentLevelValue = 1;
        currentLevel = loadLevel("level" + currentLevelValue, 3);
    }

    /**
     * Redémarre le jeu en réinitialisant les niveaux.
     */
    public static void restart() {
        currentLevelValue = 1;
        currentLevel = loadLevel("level" + currentLevelValue, 3);
    }

    /**
     * Ajoute un missile du joueur au niveau actuel.
     * 
     * @param missile Le missile à ajouter
     */
    public static void addPlayerMissile(Missile missile) {
        currentLevel.addPlayerMissile(missile);
    }

    /**
     * Ajoute un missile d'ennemi au niveau actuel.
     * 
     * @param missile Le missile à ajouter
     */
    public static void addEnemyMissile(Missile missile) {
        currentLevel.addEnemyMissile(missile);
    }

    /**
     * Vérifie si un ennemi a un allié en dessous de lui (même colonne).
     * 
     * Utilisé pour empêcher les ennemis de tirer s'il y a un allié devant eux.
     * 
     * @param enemy L'ennemi à vérifier
     * @return true s'il y a au moins un allié directement en dessous
     */
    public static boolean enemyHasAllyBelow(Enemy enemy) {
        var pos = enemy.getPosition();
        for (var ally : currentLevel.getEnemies()) {
            var allyPos = ally.getPosition();
            if (Math.abs(allyPos.x() - pos.x()) < 0.05 && allyPos.y() < pos.y())
                return true;
        }
        return false;
    }
}
