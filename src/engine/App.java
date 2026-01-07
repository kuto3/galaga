package engine;

import game.Game;

/**
 * Classe de lancement du projet Galaga.
 * 
 * Crée une instance du jeu et le lance.
 * C'est le point d'entrée principal de l'application.
 * 
 * @author nom prenom binôme 1
 * @author nom prenom binôme 2
 * @version 1.0
 */
public class App {

    /**
     * Méthode principale qui lance le jeu Galaga.
     * 
     * @param args Les arguments de la ligne de commande (non utilisés)
     * @throws Exception En cas d'erreur lors du lancement du jeu
     */
    public static void main(String[] args) throws Exception {
        // Création d'un nouveau jeu et lancement de celui-ci
        Game g = new Game();
        g.launch();
    }
}
