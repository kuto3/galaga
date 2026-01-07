package game.actors;

import game.Game;
import game.levels.LevelManager;
import utils.Vector2;

/**
 * Classe abstraite représentant un ennemi du jeu.
 * 
 * Un ennemi est une entité qui attaque le joueur et donne des points lorsqu'il
 * est détruit.
 * Les ennemis se déplacent en formation et peuvent tirer des missiles.
 * 
 * @version 1.0
 */
public abstract class Enemy extends Entity {

    /**
     * Nombre de points accordés quand cet ennemi est détruit.
     */
    protected int points;

    /**
     * Moment de la prochaine attaque (en millisecondes).
     */
    protected double nextAttackTime;

    /**
     * Indique si l'ennemi se déplace vers la droite.
     */
    protected boolean movingRight;

    /**
     * Position de départ de l'ennemi.
     */
    protected Vector2 startingPosition;

    /**
     * Vitesse de départ
     */
    protected double startingSpeed;

    /**
     * Crée un ennemi avec les paramètres spécifiés.
     * 
     * @param pos            Position initiale de l'ennemi
     * @param health         Points de santé initiaux
     * @param speed          Vitesse de déplacement
     * @param sprite         Chemin du fichier sprite
     * @param size           Taille de l'ennemi
     * @param lerpSpeed      Vitesse d'interpolation vers la cible
     * @param points         Nombre de points accordés à la destruction
     * @param attackCooldown Temps d'attente entre deux attaques
     */
    public Enemy(Vector2 pos, int health, double speed, String sprite, double size, double lerpSpeed, int points,
            int attackCooldown) {
        super(pos, health, 1, speed, sprite, size, lerpSpeed, false, attackCooldown);
        this.points = points;
        this.startingPosition = pos;
        this.movingRight = true;
        this.startingSpeed = speed;
    }

    /**
     * Obtient le nombre de points accordés à la destruction de cet ennemi.
     * 
     * @return Le nombre de points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Effectue une attaque spécifique à cet ennemi.
     * 
     * Cette méthode abstraite doit être implémentée par les sous-classes.
     */
    public abstract void attack();

    /**
     * Vérifie si cet ennemi a un allié en dessous (même colonne, plus bas).
     * 
     * @return true s'il y a un allié en dessous, false sinon
     */
    public boolean hasAllyBelow() {
        return LevelManager.enemyHasAllyBelow(this);
    }

    /**
     * Tire un missile vers le bas depuis la position de l'ennemi.
     */
    public void shoot() {
        if (isAlive() && canAttack) {
            Missile missile = new Missile(position.sub(new Vector2(0.005, size)), 0.01, false);
            LevelManager.addEnemyMissile(missile);
            timeLastShot = Game.time;
        }
    }

    /**
     * Tire un missile vers le bas depuis une position décalée.
     * 
     * @param offset Le décalage par rapport à la position de l'ennemi
     */
    public void shoot(Vector2 offset) {
        if (isAlive() && canAttack) {
            Missile missile = new Missile(position.add(offset).sub(new Vector2(0.005, size)), 0.01, false);
            LevelManager.addEnemyMissile(missile);
            timeLastShot = Game.time;
        }
    }

    @Override
    public void update() {
        // L'enemie peut pas attaquer s'il y a un allié en dessous
        canAttack = !hasAllyBelow();

        if (Game.time % 500 == 0)
            movingRight = !movingRight;

        if (canAttack && Game.time > nextAttackTime)
            attack();

        Vector2 newTargetPos = new Vector2(
                startingPos.x() + (movingRight ? 0.04 : -0.04),
                targetPosition.y());

        // On plafone la nouvelle position dans les limites de l'écran
        newTargetPos.clampToBoundBox(
                new Vector2(size / 2, size / 2),
                new Vector2(1 - size / 2, 1 - size / 2));

        setTargetPosition(newTargetPos);

        super.update();
    }
}
