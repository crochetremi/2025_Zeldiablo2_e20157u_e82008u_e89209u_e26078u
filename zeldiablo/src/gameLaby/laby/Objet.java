package gameLaby.laby;

public class Objet {
    /**
     * position du personnage
     */
    private int x, y;

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
    public Objet(int dx, int dy) {
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
