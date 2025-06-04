package gameLaby.laby;

import javafx.application.Platform;
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

        if(this.laby.jeuEnCours == true){
            int prob = (int) (Math.random() * 100);
            if(prob < 75) {
                laby.monstres.deplacerMontresVersHeros();
            } else if(prob < 90) {
                laby.monstres.deplacerMontresAleatoire();
            }

            if(clavier.bas){
                laby.pj.deplacer(Labyrinthe.BAS);

            }
            if(clavier.haut){
                laby.pj.deplacer(Labyrinthe.HAUT);

            }
            if(clavier.gauche){
                laby.pj.deplacer(Labyrinthe.GAUCHE);

            }
            if(clavier.droite){
                laby.pj.deplacer(Labyrinthe.DROITE);

            }
        }

        this.etreFini();
    }

    public void init() {
        try{
            this.laby = new Labyrinthe("zeldiablo/labySimple/labybig.txt");
        }
        catch (Exception e){
            System.out.println("erreur chargement labyrinthe");
        }
    }

    public boolean etreFini() {
        return this.laby.etreFini();
    }
}
