package game;

import engine.StdDraw;
import java.awt.Font;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InterfaceManager {
    private static int score = 0;
    private static int highscore = 0;

    public static void draw() {

        highscore = ReadHighScore();
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
        StdDraw.text(0.5, 0.4, "Appuyez sur Esc pour recommencer");
    }

    public static void update() {

    }

    public static void setScore(int value) {
        score = value;
    }

    public static int ReadHighScore() {
        try {
            String content = Files.readString(Path.of("ressources/highscore/highscore.sc"));
            return Integer.parseInt(content.trim()); // convertit le texte en nombre
        } catch (IOException e) {
            System.out.println("Aucun highscore trouvé, valeur par défaut = 0");
            return 0; // si le fichier n'existe pas encore
        } catch (NumberFormatException e) {
            System.out.println("Highscore corrompu, reset = 0");
            return 0;
        }
    }

}
