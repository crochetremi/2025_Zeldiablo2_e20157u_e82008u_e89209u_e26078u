package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;

public class LabyJeu implements Jeu {

    public Labyrinthe laby;

    public LabyJeu() {
        init();
    }

    public LabyJeu(String fichier) {
        try{
            this.laby = new Labyrinthe(fichier);
        }
        catch (Exception e){
            System.out.println("erreur chargement labyrinthe");
        }
    }

    @Override
    public void update(double secondes, Clavier clavier) {
        if(clavier.bas){
            laby.pj.deplacer(Labyrinthe.BAS);
            laby.monstre.deplacerVersHeros();
        }
        if(clavier.haut){
            laby.pj.deplacer(Labyrinthe.HAUT);
            laby.monstre.deplacerVersHeros();
        }
        if(clavier.gauche){
            laby.pj.deplacer(Labyrinthe.GAUCHE);
            laby.monstre.deplacerVersHeros();
        }
        if(clavier.droite){
            laby.pj.deplacer(Labyrinthe.DROITE);
            laby.monstre.deplacerVersHeros();
        }
    }

    public void init() {
        try{
            this.laby = new Labyrinthe("zeldiablo/labySimple/laby1.txt");
        }
        catch (Exception e){
            System.out.println("erreur chargement labyrinthe");
        }
    }

    public boolean etreFini() {
        return false;
    }
}
