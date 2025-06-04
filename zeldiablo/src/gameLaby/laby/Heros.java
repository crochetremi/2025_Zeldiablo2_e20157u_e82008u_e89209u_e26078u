package gameLaby.laby;

public class Heros extends Personnage {

    private boolean possedeeAmulette;
    /**
     * Crée un nouveau héros avec une position initiale spécifiée.
     * Le héros est initialisé avec 5 points de vie et sans amulette.
     *
     * @param dx la coordonnée x initiale du héros dans le labyrinthe
     * @param dy la coordonnée y initiale du héros dans le labyrinthe
     */
    public Heros(int dx, int dy) {
        super(dx, dy, 5);
        this.possedeeAmulette = false;
    }

    /**
     * Déplace le héros vers une nouvelle position dans le labyrinthe en fonction
     * de l'action spécifiée. Le déplacement ne s'effectue que si la case suivante n'est pas un mur et ne contient pas de monstre.
     * Si un déplacement a lieu et que la nouvelle position contient une amulette, celle-ci
     * est récupérée.
     *
     * @param action une chaîne décrivant la direction du déplacement ("haut", "bas", "gauche", "droite")
     */
    @Override
    public void deplacer(String action) {
        // case courante
        int[] courante = {this.getX(), this.getY()};

        // calcule case suivante
        int[] suivante = Labyrinthe.getSuivant(courante[0], courante[1], action);

        // si c'est pas un mur, on effectue le deplacement
        boolean cbon = true;
        Labyrinthe laby = this.getLaby();
        if (!laby.getMur(suivante[0], suivante[1])) {
            //vérifie la position du monstre
            for(int i = 0; i< laby.getMonstres().getListeMonstres().size(); i++) {
                Monstre m = this.getLaby().getMonstres().getListeMonstres().get(i);
                if((suivante[0] == m.getX() && suivante[1] == m.getY())){
                    cbon = false;
                }
            }
            if(cbon) {
                this.setX(suivante[0]);
                this.setY(suivante[1]);
            }
        }
        recupererAmulette();
    }

    public int subirDegats(int degats) {
        this.setVie(this.getVie()-degats);
        int subis = this.getVie();
        return subis;

    }

    public boolean etreVivant() {
        return this.getVie() > 0;
    }

    public void recupererAmulette(){

        if(this.etreVivant()){
            if(this.getLaby().getPj().etrePresent(this.getLaby().getAmu().getX(), this.getLaby().getAmu().getY())){
                this.possedeeAmulette = true;
            }
        }

    }

    /**
     * Vérifie si les conditions de victoire du jeu sont remplies.
     * Un héros remplit les conditions de victoire s'il possède l'amulette
     * et s'il se trouve sur la case de départ du labyrinthe.
     *
     * @return true si les conditions de victoire sont satisfaites, false sinon
     */
    public boolean remplirConditionVictoire(){

        boolean remplir = false;

        if(this.possedeeAmulette){
            if(this.getLaby().getPj().etrePresent(this.getLaby().getCaseDepart()[0], this.getLaby().getCaseDepart()[1])){
                remplir = true;
            }
        }
        return remplir;
    }

    /**
     * Retourne l'état de possession de l'amulette par le héros.
     *
     * @return true si le héros possède l'amulette, false sinon
     */
    public boolean getAmulette() {
        return this.possedeeAmulette;
    }
}
