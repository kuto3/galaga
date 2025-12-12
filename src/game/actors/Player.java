package game.actors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import engine.StdDraw;
import utils.ColorUtils;
import utils.Vector2;

/**
 * Classe représentant le jouuer.
 * A ce stade ce n'est qu'un point rouge qui se déplace avec les flèches du
 * clavier.
 */
public class Player extends Entity {
    private double length; // largeur du joueur

    /**
     * Créé un joueur.
     * 
     * @param startingPosition postion initiale du joueur
     * @param length           largeur du joueur
     */
    public Player(Vector2 startingPosition, double length) {
        super(startingPosition, 3, 0.01, "ship", 1);
        this.length = length;
    }

    /**
     * Dessine le joueur
     */
    @Override
    public void draw() {
        if (sprite == null) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(getPosition().x(), getPosition().y(), length / 2);
            return;
        }

        for (int i = 0; i < spriteInfo.length; i++) {
            var line = spriteInfo[i];
            for (int j = 0; j < line.length; j++) {
                var x = getPosition().x();
                var y = getPosition().y();
                var pixelSize = length / line.length;

                StdDraw.setPenColor(line[i]);
                StdDraw.setPenRadius(pixelSize);
                StdDraw.point(x + i * pixelSize, y - j * pixelSize);
            }
        }

    }

    /**
     * Met à jour la position du joueur en fonction des touches préssé.
     */
    @Override
    public void update() {
        double newX = targetPosition.x();
        double newY = targetPosition.y();

        // Si la flèche gauche est préssé
        if (StdDraw.isKeyPressed(37)) {
            newX -= speed;
        }
        // Si la flèche droite est préssé
        if (StdDraw.isKeyPressed(39)) {
            newX += speed;
        }

        // On plafone la nouvelle position dans les limites de l'écran
        var newTargetPos = new Vector2(newX, newY);
        newTargetPos.clampToBoundBox(
                new Vector2(length / 2, length / 2),
                new Vector2(1 - length / 2, 1 - length / 2));

        setTargetPosition(newTargetPos);
        super.update();
    }
}
