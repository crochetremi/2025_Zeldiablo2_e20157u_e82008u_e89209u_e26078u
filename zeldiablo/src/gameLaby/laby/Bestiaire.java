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
        for (int i = 0; i < monstres.size(); i++) {
            this.monstres.get(i).deplacerAleatoire();
        }
    }

    public void deplacerMontresVersHeros() {
        for (int i = 0; i < monstres.size(); i++) {
            this.monstres.get(i).deplacerVersHeros();
        }
    }

    public void deplacerMonstresIntelligent() {
        for (int i = 0; i < monstres.size(); i++) {
            this.monstres.get(i).deplacerIntelligent(this.monstres.get(i).getX(),this.monstres.get(i).getY());
        }
    }

    public boolean occupeParUnMonstre(int x, int y, Monstre m) {
        for (int i=0; i<monstres.size(); i++) {
            if (monstres.get(i) != m && monstres.get(i).getX() == x && monstres.get(i).getY() == y) {
                return true;
            }
        }
        return false;
    }

}