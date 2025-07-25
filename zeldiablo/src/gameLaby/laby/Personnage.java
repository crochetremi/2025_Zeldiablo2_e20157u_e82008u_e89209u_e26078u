package gameLaby.laby;

public abstract class Personnage {
    /**
     * position du personnage
     */
    private int x, y;
    private int vie;

    /**
     * labyrinthe dans lequel se trouve le personnage
     */
    private Labyrinthe laby;

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

    public void setX(int dx) {
        this.x = dx;
    }

    public void setY(int dy) {
        this.y = dy;
    }

    public int getVie() {
        return this.vie;
    }

    public void setVie(int pv) {
        if(pv < 0){
            this.vie = 0;
        }
        else{
            this.vie = pv;
        }
    }

    public Labyrinthe getLaby() {
        return laby;
    }

}
