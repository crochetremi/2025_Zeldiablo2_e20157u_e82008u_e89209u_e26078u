package gameLaby.laby;

import java.util.ArrayList;

/**
 * Cette classe représente un bestiaire contenant une liste de monstres.
 * Elle fournit des méthodes pour manipuler les monstres, comme les ajouter,
 * les déplacer et vérifier des conditions liées à leurs positions.
 */

public class Bestiaire {
    /** Liste contenant tous les monstres du jeu */
    private ArrayList<Monstre> listeMonstres;

    /**
     * Constructeur de la classe Bestiaire.
     * Initialise la liste des monstres en tant que liste vide.
     */
    public Bestiaire() {
        listeMonstres = new ArrayList<>();
    }

    /**
     * Ajoute un monstre dans la liste des monstres du bestiaire.
     *
     * @param m Le monstre à ajouter à la liste des monstres.
     */
    public void ajouterMonstre(Monstre m) {
        listeMonstres.add(m);
    }

    /**
     * Déplace tous les monstres du bestiaire de manière aléatoire.
    */
    public void deplacerMonstresAleatoire() {
        for (int i = 0; i < listeMonstres.size(); i++) {
            this.listeMonstres.get(i).deplacerAleatoire();
        }
    }

    /**
     * Déplace tous les monstres de la liste de monstres vers le héros.
     * Chaque monstre utilise sa méthode interne pour ajuster sa position en direction du héros.
     */
    public void deplacerMonstresVersHeros() {
        for (int i = 0; i < listeMonstres.size(); i++) {
            this.listeMonstres.get(i).deplacerVersHeros();
        }
    }

    //Déplace tout les monstres de manière à ce qu'ils aillent vers le héros de manière intelligente
    public void deplacerMonstresIntelligent() {
        for (int i = 0; i < listeMonstres.size(); i++) {
            this.listeMonstres.get(i).deplacerIntelligent(this.listeMonstres.get(i).getX(),this.listeMonstres.get(i).getY());
        }
    }

    //Renvoie vrai si la case(x,y) est occupé par un monstre
    public boolean occupeParUnMonstre(int x, int y, Monstre m) {
        for (int i=0; i<listeMonstres.size(); i++) {
            if (listeMonstres.get(i) != m && listeMonstres.get(i).getX() == x && listeMonstres.get(i).getY() == y) {
                return true;
            }
        }
        return false;
    }

    //getter
    public ArrayList<Monstre> getListeMonstres() {
        return listeMonstres;
    }
}