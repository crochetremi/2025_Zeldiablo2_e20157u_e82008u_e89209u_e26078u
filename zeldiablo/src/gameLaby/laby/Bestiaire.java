package gameLaby.laby;

import java.util.ArrayList;

/**
 * Cette classe représente un bestiaire contenant une liste de monstres.
 * Elle fournit des méthodes pour manipuler les monstres, comme les ajouter,
 * les déplacer et vérifier des conditions liées à leurs positions.
 */

public class Bestiaire {
    /** Liste contenant tous les monstres du jeu */
    ArrayList<Monstre> monstres;

    /**
     * Constructeur de la classe Bestiaire.
     * Initialise la liste des monstres en tant que liste vide.
     */
    public Bestiaire() {
        monstres = new ArrayList<>();
    }

    /**
     * Ajoute un monstre dans la liste des monstres du bestiaire.
     *
     * @param m Le monstre à ajouter à la liste des monstres.
     */
    public void ajouterMonstre(Monstre m) {
        monstres.add(m);
    }

    /**
     * Déplace tous les monstres du bestiaire de manière aléatoire.
    */
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

    public boolean occupeParUnMonstre(int x, int y, Monstre m) {
        for (int i=0; i<monstres.size(); i++) {
            if (monstres.get(i) != m && monstres.get(i).getX() == x && monstres.get(i).getY() == y) {
                return true;
            }
        }
        return false;
    }

}