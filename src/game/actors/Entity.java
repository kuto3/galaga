package game.actors;

import engine.StdDraw;
import game.Game;
import java.awt.Color;
import utils.EntityUtils;
import utils.Vector2;

/**
 * Classe abstraite représentant une entité du jeu.
 * 
 * Une entité est un objet du jeu qui a une position, une taille, de la santé
 * et des vies. Elle peut se déplacer, être dessinée et recevoir des dégâts.
 * Cette classe est la base pour les ennemis, le joueur, les missiles, etc.
 * 
 * @version 1.0
 */
public abstract class Entity {

    /**
     * Position de départ de l'entité.
     */
    protected Vector2 startingPos;

    /**
     * Position actuelle de l'entité.
     */
    protected Vector2 position;

    /**
     * Position cible vers laquelle l'entité se déplace.
     */
    protected Vector2 targetPosition;

    /**
     * Points de santé de l'entité.
     */
    protected int health;

    /**
     * Nombre de vies restantes de l'entité.
     */
    protected int lives;

    /**
     * Vitesse de déplacement de l'entité.
     */
    protected double speed;

    /**
     * Indique si l'entité peut attaquer.
     */
    protected boolean canAttack;

    /**
     * Chemin du fichier sprite de l'entité.
     */
    protected String sprite;

    /**
     * Taille de l'entité (rayon pour un cercle).
     */
    protected double size;

    /**
     * Vitesse d'interpolation linéaire vers la position cible.
     */
    protected double lerpSpeed;

    /**
     * Indique si l'entité est invincible.
     */
    protected boolean incivible;

    /**
     * Temps d'attente (en millisecondes) entre deux attaques.
     */
    protected int attackSpeed;

    /**
     * Informations du sprite chargées depuis un fichier.
     */
    protected Color[][] spriteInfo;

    /**
     * Moment de la dernière attaque (en millisecondes).
     */
    protected int timeLastShot;

    /**
     * Crée une entité avec les paramètres spécifiés.
     * 
     * @param pos            Position initiale de l'entité
     * @param health         Points de santé initiaux
     * @param lives          Nombre de vies initiaux
     * @param speed          Vitesse de déplacement
     * @param sprite         Chemin du fichier sprite (null si pas de sprite)
     * @param size           Taille de l'entité
     * @param lerpSpeed      Vitesse d'interpolation vers la cible
     * @param incivible      Invincibilité de l'entité
     * @param attackCooldown Temps d'attente entre deux attaques
     */
    public Entity(Vector2 pos, int health, int lives, double speed, String sprite, double size, double lerpSpeed,
            boolean incivible, int attackCooldown) {
        this.startingPos = pos;
        this.position = pos;
        this.targetPosition = pos;
        this.health = health;
        this.lives = lives;
        this.speed = speed;
        this.canAttack = true;
        this.sprite = sprite;
        this.size = size;
        this.lerpSpeed = lerpSpeed;
        this.incivible = incivible;
        this.attackSpeed = attackCooldown;

        if (sprite != null)
            spriteInfo = EntityUtils.loadSpriteInfo(sprite);
    }

    /**
     * Dessine l'entité.
     * 
     * Si un sprite est défini, utilise les informations du sprite pour dessiner.
     * Sinon, dessine un cercle rouge par défaut.
     */
    public void draw() {
        if (sprite == null || spriteInfo == null) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.filledCircle(position.x(), position.y(), size / 2);
            return;
        }

        for (int i = 0; i < spriteInfo.length; i++) {
            var line = spriteInfo[i];
            for (int j = 0; j < line.length; j++) {
                var pixelSize = size * 0.3 / Game.SCREEN_WIDTH * 100;

                var x = position.x() - pixelSize * line.length / 2;
                var y = position.y() - pixelSize * spriteInfo.length / 2;

                StdDraw.setPenColor(line[j]);
                StdDraw.filledSquare(x + j * pixelSize * 2, y - i * pixelSize * 2, pixelSize);
            }
        }

    };

    /**
     * Met à jour la position de l'entité par interpolation linéaire.
     */
    public void update() {
        position = position.lerp(targetPosition, lerpSpeed);
    }

    /**
     * Inflige des dégâts à l'entité.
     * 
     * Réduit la santé et, si elle atteint 0, réduit le nombre de vies.
     * 
     * @param amount Le nombre de dégâts à infliger
     */
    public void takeDamage(int amount) {
        health = Math.clamp(health - amount, 0, health);
        if (health == 0)
            lives--;
    }

    /**
     * Vérifie si l'entité est vivante.
     * 
     * @return true si le nombre de vies est supérieur à 0, false sinon
     */
    public boolean isAlive() {
        return lives > 0;
    }

    /**
     * Obtient le nombre de vies restantes.
     * 
     * @return Le nombre de vies
     */
    public int getLives() {
        return lives;
    }

    /**
     * Obtient la position actuelle de l'entité.
     * 
     * @return Une copie de la position actuelle
     */
    public Vector2 getPosition() {
        return new Vector2(position.x(), position.y());
    }

    /**
     * Obtient la taille de l'entité.
     * 
     * @return La taille de l'entité
     */
    public double getSize() {
        return size;
    }

    /**
     * Obtient la position cible vers laquelle se déplace l'entité.
     * 
     * @return Une copie de la position cible
     */
    public Vector2 getTargetPosition() {
        return new Vector2(targetPosition.x(), targetPosition.y());
    }

    /**
     * Définit la position cible vers laquelle se déplacer.
     * 
     * @param pos La nouvelle position cible
     */
    public void setTargetPosition(Vector2 pos) {
        targetPosition = pos;
    }

    public void resetPosition() {
        targetPosition = startingPos;
    }
}
