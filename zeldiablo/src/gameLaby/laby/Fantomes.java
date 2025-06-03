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

    @Override
    public void deplacerAleatoire() {
        int probabilite = (int) (Math.random() * 100);
        if (probabilite < 70) {
            String action = deplacementAleatoire();
            this.deplacer(action);
        }
    }

    @Override
    public void deplacerVersHeros() {
        // Les fantômes se déplacent vers le héros en ignorant les murs
        String choix = choisirDeplacement();
        this.deplacer(choix);
    }

    @Override
    public boolean attaquer(int degat) {
        // Même logique d'attaque que les monstres normaux
        int heroX = this.laby.pj.x;
        int heroY = this.laby.pj.y;

        int deltaX = Math.abs(this.x - heroX);
        int deltaY = Math.abs(this.y - heroY);

        boolean heroAPortee = (deltaX <= 1 && deltaY <= 1) && !(deltaX == 0 && deltaY == 0);

        if (heroAPortee) {
            this.laby.pj.subirDegats(degat);
            return true;
        }

        return false;
    }
}