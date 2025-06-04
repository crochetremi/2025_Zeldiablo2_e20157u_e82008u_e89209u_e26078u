package gameLaby.laby;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AmuletteTest {
    private Amulette amulette;
    private Labyrinthe labyrinthe;

    @BeforeEach
    void setUp() throws Exception {
        // Initialisation de l'amulette avec des coordonnées de test
        amulette = new Amulette(5, 5);
        labyrinthe = new Labyrinthe("zeldiablo/labySimple/labybig");
    }

    @Test
    void testConstructeur() {
        // Vérifie que l'amulette est créée avec les bonnes coordonnées
        assertEquals(5, amulette.getX());
        assertEquals(5, amulette.getY());
    }

    @Test
    void testPresenceAmulette() {
        // Vérifie que l'amulette est présente à ses coordonnées
        assertTrue(amulette.etrePresent(5, 5));
        assertFalse(amulette.etrePresent(6, 6));
    }

    @Test
    void testAmuletteDansLabyrinthe() {
        // Vérifie que l'amulette est bien présente dans le labyrinthe
        assertNotNull(labyrinthe.getAmu());
    }

    @Test
    void testPositionAmuletteSurCaseVide() {
        Amulette amuLaby = labyrinthe.getAmu();
        // Vérifie que l'amulette est placée sur une case non-mur
        assertFalse(labyrinthe.getMur(amuLaby.getX(), amuLaby.getY()));
    }

    @Test
    void testHerosPossedeAmulette() {
        // Vérifie la possession entre le héros et l'amulette
        Heros heros = labyrinthe.getPj();
        assertFalse(heros.getAmulette()); // Le héros n'a pas l'amulette au début
    }

    @Test
    void testSetLaby() {
        amulette.setLaby(labyrinthe);
        // Vérifie que le labyrinthe est bien associé à l'amulette
        // Cette vérification est indirecte car le labyrinthe est un champ privé
        assertTrue(amulette.etrePresent(amulette.getX(), amulette.getY()));
    }
}