package gameLaby.laby;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestPerso {

    @Test
    void testConstructeur() {
        Perso p = new Perso(5, 3);
        assertEquals(5, p.getX());
        assertEquals(3, p.getY());
    }

    @Test
    void testEtrePresent() {
        Perso p = new Perso(5, 3);
        assertTrue(p.etrePresent(5, 3));
        assertFalse(p.etrePresent(5, 4));
        assertFalse(p.etrePresent(4, 3));
        assertFalse(p.etrePresent(4, 4));
    }
}