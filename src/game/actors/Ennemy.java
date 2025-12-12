package game.actors;

import utils.Vector2;

public class Ennemy extends Entity {
    
    public Ennemy(Vector2 pos, int lives, double speed, String sprite, double lerpSpeed) {
        super(pos, lives, speed, sprite, lerpSpeed);
    }

    @Override
    public void draw() {
        // Implémentation du dessin de l'ennemi
    }
}
