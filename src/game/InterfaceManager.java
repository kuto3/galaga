package game;

import java.awt.Font;

import engine.StdDraw;

public class InterfaceManager {
    private static int score = 0;

    public static void draw() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setFont(new Font("Arial", Font.BOLD, 24));
        StdDraw.text(0.5, 0.95, "GALAGA ");
        StdDraw.text(0.1, 0.95, "SCORE: " + score); // Coordonnées normalisées
    }

    public static void update() {

    }

    public static void setScore(int value) {
        score = value;
    }
}
