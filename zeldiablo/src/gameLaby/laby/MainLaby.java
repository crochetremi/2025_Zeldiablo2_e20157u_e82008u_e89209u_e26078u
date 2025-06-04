package gameLaby.laby;
import moteurJeu.MoteurJeu;

/**
 * Méthode principale qui démarre l'application.
 * Elle initialise les dimensions de la fenêtre, crée les objets nécessaires
 * et lance le moteur de jeu avec les paramètres choisis.
 *dddd
 */

public class MainLaby {
    public static void main(String[] args) {
        int width = 780;
        int height = 800;
        int pFPS = 10;

        // creation des objets
        LabyJeu labyJeu = new LabyJeu("zeldiablo/labySimple/labybig");
        LabyDessin labyDessin = new LabyDessin();

        // parametrage du moteur de jeu
        MoteurJeu.setTaille(width,height);
        MoteurJeu.setFPS(pFPS);

        // lancement du jeu
        MoteurJeu.launch(labyJeu,labyDessin);
    }
}