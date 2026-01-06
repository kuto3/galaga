package game.actors;

import game.Game;
import game.levels.LevelManager;
import utils.Vector2;

/**
 * Classe représentant le jouuer.
 * A ce stade ce n'est qu'un point rouge qui se déplace avec les flèches du
 * clavier.
 */
public abstract class Enemy extends Entity {
    protected int points;
    protected double nextAttackTime;
    protected boolean movingRight = true;
    protected Vector2 startingPosition;

    /**
     * Créer un enemie
     * 
     * @param pos
     * @param health
     * @param speed
     * @param sprite
     * @param size
     * @param lerpSpeed
     * @param points
     */
    public Enemy(Vector2 pos, int health, double speed, String sprite, double size, double lerpSpeed, int points,
            int attackCooldown) {
        super(pos, health, 1, speed, sprite, size, lerpSpeed, false, attackCooldown);
        this.points = points;
        this.startingPosition = pos;
    }

    public int getPoints() {
        return points;
    }

    public abstract void attack();

    public boolean hasAllyBelow() {
        return LevelManager.enemyHasAllyBelow(this);
    }

    public void shoot() {
        if (isAlive() && canAttack) {
            Missile missile = new Missile(position.sub(new Vector2(0.005, size)), 0.01, false);
            LevelManager.addEnemyMissile(missile);
            timeLastShot = Game.time;
        }
    }

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
