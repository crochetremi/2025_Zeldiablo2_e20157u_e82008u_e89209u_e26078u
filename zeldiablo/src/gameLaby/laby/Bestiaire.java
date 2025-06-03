package gameLaby.laby;

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

}
