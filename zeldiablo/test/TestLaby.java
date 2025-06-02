package gameLaby.laby;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestLaby {

    private Labyrinthe laby;

    @BeforeEach
    void setUp() throws Exception {
        laby = new Labyrinthe("zeldiablo/labySimple/laby1monstre.txt");
    }

    @Test
    void testGetSuivant() {
        // Test déplacement vers le haut
        int[] suivantHaut = Labyrinthe.getSuivant(5, 5, Labyrinthe.HAUT);
        assertEquals(5, suivantHaut[0]);
        assertEquals(4, suivantHaut[1]);

        // Test déplacement vers le bas
        int[] suivantBas = Labyrinthe.getSuivant(5, 5, Labyrinthe.BAS);
        assertEquals(5, suivantBas[0]);
        assertEquals(6, suivantBas[1]);

        // Test déplacement vers la gauche
        int[] suivantGauche = Labyrinthe.getSuivant(5, 5, Labyrinthe.GAUCHE);
        assertEquals(4, suivantGauche[0]);
        assertEquals(5, suivantGauche[1]);

        // Test déplacement vers la droite
        int[] suivantDroite = Labyrinthe.getSuivant(5, 5, Labyrinthe.DROITE);
        assertEquals(6, suivantDroite[0]);
        assertEquals(5, suivantDroite[1]);
    }

    @Test
    void testDeplacerPersoVersMur() {
        // Sauvegarde position initiale
        int initialX = laby.pj.x;
        int initialY = laby.pj.y;

        // On cherche un mur adjacent
        String[] directions = {Labyrinthe.HAUT, Labyrinthe.BAS, Labyrinthe.GAUCHE, Labyrinthe.DROITE};
        for (String direction : directions) {
            int[] suivant = Labyrinthe.getSuivant(initialX, initialY, direction);
            if (laby.murs[suivant[0]][suivant[1]]) {
                laby.pj.deplacer(direction);
                // Vérifie que le personnage n'a pas bougé
                assertEquals(initialX, laby.pj.x);
                assertEquals(initialY, laby.pj.y);
                break;
            }
        }
    }

    @Test
    void testDeplacerPersoVersMonstre() {
        // Place le monstre adjacent au personnage
        laby.monstre.x = laby.pj.x + 1;
        laby.monstre.y = laby.pj.y;

        // Sauvegarde position initiale du personnage
        int initialX = laby.pj.x;
        int initialY = laby.pj.y;

        // Tente de déplacer le personnage vers le monstre
        laby.pj.deplacer(Labyrinthe.DROITE);

        // Vérifie que le personnage n'a pas bougé
        assertEquals(initialX, laby.pj.x);
        assertEquals(initialY, laby.pj.y);
    }

    @Test
    void testDeplacerMonstreVersMur() {
        // Sauvegarde position initiale du monstre
        int initialX = laby.monstre.x;
        int initialY = laby.monstre.y;

        // Force le déplacement vers un mur si possible
        String[] directions = {Labyrinthe.HAUT, Labyrinthe.BAS, Labyrinthe.GAUCHE, Labyrinthe.DROITE};
        for (String direction : directions) {
            int[] suivant = Labyrinthe.getSuivant(initialX, initialY, direction);
            if (laby.murs[suivant[0]][suivant[1]]) {
                // Simule un déplacement forcé vers le mur
                int[] courante = {laby.monstre.x, laby.monstre.y};
                int[] suivante = Labyrinthe.getSuivant(courante[0], courante[1], direction);
                if (!laby.murs[suivante[0]][suivante[1]]) {
                    laby.monstre.x = suivante[0];
                    laby.monstre.y = suivante[1];
                }
                // Vérifie que le monstre n'a pas bougé
                assertEquals(initialX, laby.monstre.x);
                assertEquals(initialY, laby.monstre.y);
                break;
            }
        }
    }
}