package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;

/**
 * Classe représentant le jeu de labyrinthe.
 * Cette classe gère la logique du jeu, les déplacements du héros et des monstres,
 * ainsi que la détection de fin de partie.
 */
public class LabyJeu implements Jeu {

    /** Le labyrinthe du jeu */
    private Labyrinthe pj;

    public Labyrinthe getLaby() {
        return pj;
    }

    /**
     * Constructeur par défaut qui initialise le jeu avec le labyrinthe par défaut.
     */
    public LabyJeu() {
        init();
    }

    /**
     * Constructeur qui initialise le jeu avec un labyrinthe spécifique.
     * @param fichier Le chemin vers le fichier contenant la définition du labyrinthe
     */
    public LabyJeu(String fichier) {
        try{
            this.pj = new Labyrinthe(fichier);
        }
        catch (Exception e){
            System.out.println("erreur chargement labyrinthe");
        }
    }

    /**
     * Met à jour l'état du jeu en fonction des entrées du joueur et du temps écoulé.
     * Gère les déplacements du héros et des monstres.
     * @param secondes Le temps écoulé depuis la dernière mise à jour
     * @param clavier L'état du clavier pour les entrées du joueur
     */
    @Override
    public void update(double secondes, Clavier clavier) {
    
        Heros pj = this.getLaby().getPj();
        
        if(this.getLaby().getJeuEnCours() == true){
            /*int prob = (int) (Math.random() * 100);
            if(prob < 75) {
                laby.monstres.deplacerMontresVersHeros();
            } else if(prob < 90) {
                laby.monstres.deplacerMontresAleatoire();
            }*/

            this.getLaby().getMonstres().deplacerMonstresIntelligent();

            if(clavier.bas){
                pj.deplacer(Labyrinthe.BAS);

            }
            if(clavier.haut){
                pj.deplacer(Labyrinthe.HAUT);

            }
            if(clavier.gauche){
                pj.deplacer(Labyrinthe.GAUCHE);

            }
            if(clavier.droite){
                pj.deplacer(Labyrinthe.DROITE);

            }
        }

        this.etreFini();
    }

    /**
     * Initialise le jeu avec le labyrinthe par défaut.
     */
    public void init() {
        try{
            this.pj = new Labyrinthe("zeldiablo/labySimple/labybig.txt");
        }
        catch (Exception e){
            System.out.println("erreur chargement labyrinthe");
        }
    }

    /**
     * Vérifie si le jeu est terminé.
     * Le jeu se termine si le héros n'est plus en vie.
     * @return true si le jeu est terminé, false sinon
     */
    public boolean etreFini() {
        return this.getLaby().etreFini();
    }
}
