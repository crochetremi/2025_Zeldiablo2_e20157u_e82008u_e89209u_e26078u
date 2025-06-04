package gameLaby.laby;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MonstreTest {
    private Monstre monstre;
    private Labyrinthe laby;

    @BeforeEach
    void setUp() throws Exception {
        // Initialisation avant chaque test
        monstre = new Monstre(5, 5);
        laby = new Labyrinthe("zeldiablo/labySimple/labybig");
        monstre.setLaby(laby);
    }

    @Test
    void testConstructeur() {
        assertEquals(5, monstre.getX());
        assertEquals(5, monstre.getY());
        assertEquals(2, monstre.getVie());
    }

    @Test
    void testDeplacementAleatoire() {
        String direction = monstre.deplacementAleatoire();
        assertTrue(direction.equals(Labyrinthe.HAUT) ||
                direction.equals(Labyrinthe.BAS) ||
                direction.equals(Labyrinthe.GAUCHE) ||
                direction.equals(Labyrinthe.DROITE));
    }

    @Test
    void testAttaquerHorsPortee() {
        // Le héros est trop loin pour être attaqué
        assertFalse(monstre.attaquer());
    }

    @Test
    void testCalculerDistance() {
        int[] distance = monstre.calculerDistance();
        assertNotNull(distance);
        assertEquals(2, distance.length);
    }
}