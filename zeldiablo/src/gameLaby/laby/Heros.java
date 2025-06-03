package gameLaby.laby;

public class Heros extends Personnage {

    public Heros(int dx, int dy) {
        super(dx, dy, 5);
    }

    @Override
    public void deplacer(String action) {
        // case courante
        int[] courante = {this.x, this.y};

        // calcule case suivante
        int[] suivante = Labyrinthe.getSuivant(courante[0], courante[1], action);

        // si c'est pas un mur, on effectue le deplacement
        boolean cbon = true;
        if (!this.laby.getMur(suivante[0], suivante[1])) {
            //vérifie la position du monstre
            for(int i = 0; i< this.laby.monstres.monstres.size(); i++) {
                if((suivante[0] == this.laby.monstres.monstres.get(i).x && suivante[1] == this.laby.monstres.monstres.get(i).y)){
                    cbon = false;
                }
            }
            if(cbon) {
                this.x = suivante[0];
                this.y = suivante[1];
            }
        }
        /*
        if (!this.laby.getMur(suivante[0], suivante[1])) {
            for (Monstre m : this.laby.monstres.monstres) {
                if (suivante[0] == m.getX() && suivante[1] == m.getY()) {
                    cbon = false;
                    break;
                }
            }
        }

        if (caseLibre) {
            this.x = suivante[0];
            this.y = suivante[1];
        }*/

    }

    public int subirDegats(int degats) {
        return this.vie -= degats;
    }

    public boolean etreVivant() {
        return this.vie > 0;
    }

}
