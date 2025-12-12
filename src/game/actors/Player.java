package game.actors;

import java.io.BufferedReader;
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
        super(startingPosition, 3, 0.01, null, 1);
        this.length = length;
    }

    /**
     * Dessine le joueur, ici c'est un rond rouge
     */
    @Override
    public void draw() {
        if (sprite == null) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(getPosition().x(), getPosition().y(), length / 2);
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader("ressources/sprites/" + sprite))) {
                var line = reader.readLine();

                var j = 0;
                while (line != null) {
                    var x = getPosition().x();
                    var y = getPosition().y();
                    for (int i = 0; i < line.length(); i++) {
                        StdDraw.setPenColor(ColorUtils.toColor(line.charAt(i)));
                        StdDraw.point(x + i, y + j);
                    }
                    j++;
                }
            } catch (IOException e) {
                System.out.println("On a pas pu trouver le sprite " + sprite + ": " + e);
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
