package gameLaby.laby;

/**
 * Représente une amulette dans le jeu.
 * Une amulette est un objet spécial qui peut être collecté par le joueur.
 */
public class Amulette extends Objet{

    /**
     * Constructeur de l'amulette.
     * 
     * @param dx Position x de l'amulette dans le labyrinthe
     * @param dy Position y de l'amulette dans le labyrinthe
     */
    Amulette(int dx, int dy){
        super(dx, dy);
    }

}
