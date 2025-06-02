package gameLaby.laby;

public class Personnage {
    /**
     * position du personnage
     */
    int x, y;

    /**
     * labyrinthe dans lequel se trouve le personnage
     */
    Labyrinthe laby;

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Personnage(int dx, int dy) {
        this.x = dx;
        this.y = dy;
    }

    public void setLaby(Labyrinthe laby) {
        this.laby = laby;
    }

    /**
     * permet de savoir si le personnage est en x,y
     */
    public boolean etrePresent(int dx, int dy) {
        return (this.x == dx && this.y == dy);
    }


    public void deplacer(String action) {
        // case courante
        int[] courante = {this.x, this.y};

        // calcule case suivante
        int[] suivante = Labyrinthe.getSuivant(courante[0], courante[1], action);

        // si c'est pas un mur, on effectue le deplacement
        if (!this.laby.getMur(suivante[0], suivante[1])) {
            //vérifie la position du monstre
            if(!(suivante[0] == this.laby.monstre.x && suivante[1] == this.laby.monstre.y)){
                this.x = suivante[0];
                this.y = suivante[1];
            }
        }
    }

    /**
     * retourne la position du personnage
     *
     * @return tableau de deux entiers, x et y
     */
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
