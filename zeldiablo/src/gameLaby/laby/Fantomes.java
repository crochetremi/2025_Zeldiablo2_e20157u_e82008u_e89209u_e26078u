package gameLaby.laby;

public class Fantomes extends Monstre {

    public Fantomes(int dx, int dy) {
        super(dx, dy);
    }

    @Override
    public void deplacer(String action) {
        // Vérifie d'abord si le héros est à portée d'attaque
        if (attaquer(1)) {
            return;
        }

        // case courante
        int[] courante = {this.x, this.y};

        // calcule case suivante
        int[] suivante = Labyrinthe.getSuivant(courante[0], courante[1], action);

        // Les fantômes peuvent traverser les murs mais pas les autres entités
        if (!(suivante[0] == this.laby.pj.x && suivante[1] == this.laby.pj.y)) {
            this.x = suivante[0];
            this.y = suivante[1];
        }
    }
}