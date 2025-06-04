package gameLaby.laby;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BestiaireTest {
    private Bestiaire bestiaire;
    private Monstre monstre;

    @BeforeEach
    void setUp() {
        bestiaire = new Bestiaire();
        monstre = new Monstre(5, 5);
    }

    @Test
    void testAjouterMonstre() {
        bestiaire.ajouterMonstre(monstre);
        assertFalse(bestiaire.getListeMonstres().isEmpty());
        assertEquals(1, bestiaire.getListeMonstres().size());
    }

    @Test
    void testOccupeParUnMonstre() {
        bestiaire.ajouterMonstre(monstre);
        assertTrue(bestiaire.occupeParUnMonstre(5, 5, null));
        assertFalse(bestiaire.occupeParUnMonstre(6, 6, null));
    }
}