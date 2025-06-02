package gameLaby.laby;

public class Monstre extends Personnage {
    public Monstre(int dx, int dy) {
        super(dx, dy);
    }

    public String deplacementAleatoire(){
        String[] actions = {Labyrinthe.HAUT, Labyrinthe.BAS, Labyrinthe.GAUCHE, Labyrinthe.DROITE};
        return actions[(int) (Math.random() * actions.length)];
    }

    public void deplacer(String action) {
        deplacerAleatoire();
    }

    public void deplacerAleatoire() {

        String action = deplacementAleatoire();

        // case courante
        int[] courante = {this.x, this.y};

        // calcule case suivante
        int[] suivante = Labyrinthe.getSuivant(courante[0], courante[1], action);

        int proba = (int) (Math.random() * 100);
        // vérifie si le déplacement est possible
        if(proba < 20){
            if(!this.laby.getMur(suivante[0], suivante[1]) && !(suivante[0] == this.laby.pj.x && suivante[1] == this.laby.pj.y)){
                this.x = suivante[0];
                this.y = suivante[1];
            }
        }
    }
}