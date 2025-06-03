package gameLaby.laby;

public class Monstre extends Personnage {
    public Monstre(int dx, int dy) {
        super(dx, dy);
    }

    public String deplacementAleatoire(){
        String[] actions = {Labyrinthe.HAUT, Labyrinthe.BAS, Labyrinthe.GAUCHE, Labyrinthe.DROITE};
        return actions[(int) (Math.random() * actions.length)];
    }

    public void deplacerAleatoire(){
        int probabilite = (int) (Math.random() * 100);
        if(probabilite < 50) {
            String action = deplacementAleatoire();
            this.deplacer(action);
        }
    }

    public boolean attaquer(int degat) {

        int heroX = this.laby.pj.x;
        int heroY = this.laby.pj.y;

        int deltaX = Math.abs(this.x - heroX);
        int deltaY = Math.abs(this.y - heroY);

        boolean heroAPortee = (deltaX <= 1 && deltaY <= 1) && !(deltaX == 0 && deltaY == 0);

        if (heroAPortee) {
            this.laby.pj.subirDegats(10);
            return true;
        }

        return false;

    }

    @Override
    public void deplacer(String action) {

        if (attaquer(1)){
            return;
        }

        boolean b = this.attaquer(5);


        // case courante
        int[] courante = {this.x, this.y};

        // calcule case suivante
        int[] suivante = Labyrinthe.getSuivant(courante[0], courante[1], action);

            if(!this.laby.getMur(suivante[0], suivante[1]) &&
                    !(suivante[0] == this.laby.pj.x && suivante[1] == this.laby.pj.y)){
                this.x = suivante[0];
                this.y = suivante[1];
            }
        }



    public void deplacerVersHeros() {
        String choix = choisirDeplacement();
        this.deplacer(choix);
    }

    public String choisirDeplacement() {
        int[] calcul = calculerDistance();

        if(calcul[0] == 0) {
            if(calcul[1] > 0) {
                return Labyrinthe.DROITE;
            } else {
                return Labyrinthe.GAUCHE;
            }
        } else {
            if(calcul[1] > 0) {
                return Labyrinthe.BAS;
            } else {
                return Labyrinthe.HAUT;
            }
        }
    }


    public int[] calculerDistance() {

        // position du personnage et du monstre
        int Px = this.laby.pj.getX();
        int Py = this.laby.pj.getY();

        int Mx = this.getX();
        int My = this.getY();

        int diffx = Px - Mx;
        int diffy = Py - My;


        if(Math.pow(diffy,2) > Math.pow(diffx,2)) {
            return new int[]{1, diffy};
        } else {
            return new int[]{0, diffx};
        }
    }
}
