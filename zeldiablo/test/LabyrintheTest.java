package gameLaby.laby;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LabyrintheTest {
    private Labyrinthe laby;

    @BeforeEach
    void setUp() throws Exception {
        laby = new Labyrinthe("zeldiablo/labySimple/labybig");
    }

    @Test
    void testGetSuivant() {
        int[] pos = Labyrinthe.getSuivant(5, 5, Labyrinthe.HAUT);
        assertEquals(5, pos[0]);
        assertEquals(4, pos[1]);

        pos = Labyrinthe.getSuivant(5, 5, Labyrinthe.BAS);
        assertEquals(5, pos[0]);
        assertEquals(6, pos[1]);
    }

    @Test
    void testDimensions() {
        assertTrue(laby.getLength() > 0);
        assertTrue(laby.getLengthY() > 0);
    }

    @Test
    void testHerosPresent() {
        assertNotNull(laby.getPj());
    }

    @Test
    void testMonstresPresents() {
        assertNotNull(laby.getMonstres());
    }

    @Test
    void testEtreFini() {
        assertFalse(laby.etreFini());
    }
}