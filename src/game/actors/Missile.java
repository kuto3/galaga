package game.actors;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import engine.StdDraw;
import utils.ColorUtils;
import utils.Vector2;
 
public class Missile {
    private Vector2 position;
    protected int lives;
    protected double speed;
    protected String sprite;
    protected double lerpSpeed;

    public Missile(Vector2 pos, double speed, String sprite, double lerpSpeed) {
        this.position = pos;
        this.speed = speed;
        this.sprite = sprite;
        this.lerpSpeed = lerpSpeed;

    }
   
    public void draw() {
        if (sprite == null) {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledCircle(getPosition().x(), getPosition().y(),  0.01);
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


    public void update() {
 
        double newX = position.x();
        double newY = position.y();
        var newTargetPos = new Vector2(newX, newY + speed);
        position = newTargetPos;
        



    }

    
 
    public Vector2 getPosition() {
        return position;
    }

    

    
     
   
}
