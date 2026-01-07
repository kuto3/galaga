package game.actors;

import java.awt.Color;
import engine.StdDraw;
import utils.Vector2;

import game.Game;

import utils.EntityUtils;

/**
 * Classe représentant un missile dans le jeu.
 * 
 * Les missiles peuvent être tirés par le joueur ou par les ennemis.
 * Un missile se déplace verticalement et est détruit s'il quitte l'écran.
 * 
 * @version 1.0
 */
public class Missile {

    /**
     * Position actuelle du missile.
     */
    private Vector2 position;

    /**
     * Vitesse de déplacement du missile.
     */
    private double speed;

    /**
     * Chemin du fichier sprite du missile.
     */
    private String sprite;

    /**
     * Taille du missile.
     */
    private double size;

    /**
     * Informations du sprite chargées depuis un fichier.
     */
    private Color[][] spriteInfo;

    /**
     * Indique si le missile se déplace vers le haut (joueur) ou vers le bas
     * (ennemi).
     */
    private boolean goingUp;

    /**
     * Crée un missile avec les paramètres spécifiés.
     * 
     * @param pos     Position initiale du missile
     * @param speed   Vitesse de déplacement du missile
     * @param goingUp true si le missile va vers le haut (tir du joueur), false vers
     *                le bas (tir ennemi)
     */
    public Missile(Vector2 pos, double speed, boolean goingUp) {
        this.position = pos;
        this.speed = speed;
        this.sprite = "missile";
        this.size = 0.009;
        this.goingUp = goingUp;
        this.spriteInfo = EntityUtils.loadSpriteInfo(sprite);
    }

    /**
     * Dessine le missile.
     * 
     * Si un sprite est défini, utilise les informations du sprite pour dessiner.
     * Sinon, dessine un cercle rouge par défaut.
     */
    public void draw() {
        if (sprite == null || spriteInfo == null) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.filledCircle(position.x(), position.y(), 0.01);
            return;
        }

        for (int i = 0; i < spriteInfo.length; i++) {
            var line = spriteInfo[i];
            for (int j = 0; j < line.length; j++) {
                var pixelSize = size / 2 / Game.SCREEN_WIDTH * 100;

                var x = getPosition().x() - pixelSize * line.length / 2;
                var y = getPosition().y() - pixelSize * spriteInfo.length / 2;

                StdDraw.setPenColor(line[j]);
                StdDraw.filledSquare(x + j * pixelSize * 2, y - i * pixelSize * 2, pixelSize);
            }
        }
    };

    /**
     * Met à jour la position du missile.
     * 
     * Déplace le missile vers le haut ou vers le bas selon sa direction.
     */
    public void update() {
        double newX = position.x();
        double newY = position.y();
        var newTargetPos = new Vector2(newX, newY + (goingUp ? speed : -speed));
        position = newTargetPos;
    }

    /**
     * Obtient la position actuelle du missile.
     * 
     * @return La position du missile
     */
    public Vector2 getPosition() {
        return position;
    }

}
