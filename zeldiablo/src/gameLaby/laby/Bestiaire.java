package gameLaby.laby;

<<<<<<< HEAD
import java.util.ArrayList;

public class Bestiaire {

    ArrayList<Monstre> monstres;

    public Bestiaire() {
        monstres = new ArrayList<>();
    }

    public void ajouterMonstre(Monstre m) {
        monstres.add(m);
    }

    public void deplacerMontresAleatoire() {
        for(int i = 0; i < monstres.size(); i++) {
            this.monstres.get(i).deplacerAleatoire();
        }
    }

    public void deplacerMontresVersHeros() {
        for(int i = 0; i < monstres.size(); i++) {
            this.monstres.get(i).deplacerVersHeros();
        }
    }

=======
/**
 * Classe représentant les monstres.
 */
public class Bestiaire {

>>>>>>> 15a8f08 (maj diagramme de classe Bestiaire)
}
