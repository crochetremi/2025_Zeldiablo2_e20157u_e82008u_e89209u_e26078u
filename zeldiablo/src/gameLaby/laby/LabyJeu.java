package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;

public class LabyJeu implements Jeu {

    public Labyrinthe laby;
    public Perso perso;
    public Monstre monstre;

    public LabyJeu() {
        init();
    }

    public LabyJeu(String fichier) {
        try{
            this.laby = new Labyrinthe(fichier);
            this.perso = laby.pj;
            this.monstre = laby.monstre;
        }
        catch (Exception e){
            System.out.println("erreur chargement labyrinthe");
        }
    }

    @Override
    public void update(double secondes, Clavier clavier) {
        if(clavier.bas){
            laby.deplacerPerso(Labyrinthe.BAS);
        }
        if(clavier.haut){
            laby.deplacerPerso(Labyrinthe.HAUT);
        }
        if(clavier.gauche){
            laby.deplacerPerso(Labyrinthe.GAUCHE);
        }
        if(clavier.droite){
            laby.deplacerPerso(Labyrinthe.DROITE);
        }
    }

    public void init() {
        try{
            this.laby = new Labyrinthe("zeldiablo/labySimple/laby1.txt");
            this.perso = laby.pj;
            this.monstre = laby.monstre;
        }
        catch (Exception e){
            System.out.println("erreur chargement labyrinthe");
        }
    }

    public boolean etreFini() {
        return false;
    }
}
