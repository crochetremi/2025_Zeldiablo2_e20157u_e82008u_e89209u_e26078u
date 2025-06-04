package gameLaby.laby;

import java.io.IOException;

/**
 * Classe principale qui gère le chargement et l'affichage d'un labyrinthe.
 * Cette classe permet de charger un labyrinthe à partir d'un fichier texte
 * et de l'afficher dans la console.
 */
public class Main {
    /**
     * Point d'entrée principal du programme.
     * Charge un labyrinthe à partir d'un fichier texte et l'affiche dans la console.
     * Le labyrinthe est représenté par des 'X' pour les murs et des '.' pour les espaces vides.
   */
    public static void main(String[] args) throws IOException {

        // charge le labyrinthe
        Labyrinthe laby = new Labyrinthe("zeldiablo/labySimple/laby0.txt");

        //affiche le labyrinthe charge
        for (int y = 0; y < laby.getLengthY(); y++) {
            // affiche la ligne
            for (int x = 0; x < laby.getLength(); x++) {
                if (laby.getMur(x, y))
                    System.out.print('X');
                else
                    System.out.print('.');
            }
            // saut de ligne
            System.out.println();
        }
    }
}
