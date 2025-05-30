package gameLaby.laby;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestMonstre {

    @Test
    void testConstructeur() {
        Monstre m = new Monstre(5, 3);
        assertEquals(5, m.getX());
        assertEquals(3, m.getY());
    }

    @Test
    void testEtrePresent() {
        Monstre m = new Monstre(5, 3);
        assertTrue(m.etrePresent(5, 3));
        assertFalse(m.etrePresent(5, 4));
        assertFalse(m.etrePresent(4, 3));
        assertFalse(m.etrePresent(4, 4));
    }
}