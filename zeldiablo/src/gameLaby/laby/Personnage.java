package gameLaby.laby;

public abstract class Personnage {
    /**
     * position du personnage
     */
    int x, y;
    int vie;

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
    public Personnage(int dx, int dy, int pv) {
        this.x = dx;
        this.y = dy;
        this.vie = pv;
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



    /**deplace le personnage selon l'action
     *
     * @param action action a effectuer
     */
    public abstract void deplacer(String action);

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

    public int getVie() {
        return this.vie;
    }
}
