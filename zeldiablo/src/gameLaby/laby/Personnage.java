package gameLaby.laby;

public abstract class Personnage {
    /**
     * position du personnage
     */
    protected int x, y;

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

    /**
     * permet de savoir si le personnage est en x,y
     */
    public boolean etrePresent(int dx, int dy) {
        return (this.x == dx && this.y == dy);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
