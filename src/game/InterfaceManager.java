package game;

import engine.StdDraw;
import java.awt.Font;

public class InterfaceManager {
    private static int score = 0;
    private static int highscore = 0;

    public static void draw() {
  
      
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setFont(new Font("Arial", Font.BOLD, 24));
        StdDraw.text(0.1, 0.95, "SCORE: " + score); // Coordonnées normalisées
        StdDraw.text(0.85, 0.95, "HIGH SCORE: " + highscore); // Coordonnées normalisées
        StdDraw.picture(0.5, 0.93, "ressources/logo/logo.png"); // centre de l'écran
    }

    public static void over() {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setFont(new Font("Arial", Font.BOLD, 48));
            StdDraw.text(0.5, 0.5, "GAME OVER");
            StdDraw.text(0.5, 0.4, "appuyez sur Esc pour recommencer");
        
        
        
               
    }

    public static void update() {

    }

    public static void setScore(int value) {
        score = value;
    }

      public static void setHighScore(int value) {
        highscore = value;
    }
}
