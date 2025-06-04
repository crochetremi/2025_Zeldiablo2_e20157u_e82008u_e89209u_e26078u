package gameLaby.laby;

import java.util.ArrayList;

/**
 * Classe représentant un monstre dans le jeu.
 * Un monstre est un personnage qui peut se déplacer aléatoirement ou suivre le héros.
 */
public class Monstre extends Personnage {
    /**
     * Constructeur d'un monstre.
     * @param dx Position x initiale du monstre
     * @param dy Position y initiale du monstre
     */
    public Monstre(int dx, int dy) {
        super(dx, dy, 2);
    }

    /**
     * Génère une direction de déplacement aléatoire.
     * @return Une direction aléatoire parmi HAUT, BAS, GAUCHE, DROITE
     */
    public String deplacementAleatoire(){
        String[] actions = {Labyrinthe.HAUT, Labyrinthe.BAS, Labyrinthe.GAUCHE, Labyrinthe.DROITE};
        return actions[(int) (Math.random() * actions.length)];
    }

    /**
     * Déplace le monstre aléatoirement avec une probabilité de 50%.
     */
    public void deplacerAleatoire(){
        int probabilite = (int) (Math.random() * 100);
        if(probabilite < 50) {
            String action = deplacementAleatoire();
            this.deplacer(action);
        }
    }

    /**
     * Tente d'attaquer le héros si celui-ci est à portée.
     * @param degat Les dégâts à infliger au héros
     * @return true si l'attaque a réussi, false sinon
     */
    public boolean attaquer(int degat) {

        int heroX = this.laby.pj.x;
        int heroY = this.laby.pj.y;

        int deltaX = Math.abs(this.x - heroX);
        int deltaY = Math.abs(this.y - heroY);

        boolean heroAPortee = (deltaX == 1 && deltaY == 0) || (deltaX == 0 && deltaY == 1);


        if (heroAPortee) {
            if (this.laby.pj.vie >= 1) {
                this.laby.pj.subirDegats(1);
            } else {
                this.laby.pj.vie = 0;
            }
            System.out.println("Vie du héros après attaque : " + this.laby.pj.vie);
            return true;
        }
        return false;

    }

    /**
     * Déplace le monstre dans la direction spécifiée.
     * Le monstre attaquera le héros s'il est à portée, sinon il se déplacera.
     * @param action La direction de déplacement (HAUT, BAS, GAUCHE, DROITE)
     */
    @Override
    public void deplacer(String action) {

        if (attaquer(1)) {
            return;
        }

        // case courante
        int[] courante = {this.x, this.y};

        // calcule case suivante
        int[] suivante = Labyrinthe.getSuivant(courante[0], courante[1], action);

            if(!this.laby.getMur(suivante[0], suivante[1]) && !(suivante[0] == this.laby.pj.x && suivante[1] == this.laby.pj.y) && !this.laby.monstres.occupeParUnMonstre(suivante[0], suivante[1], this)){
                this.x = suivante[0];
                this.y = suivante[1];
            }


        }



    /**
     * Déplace le monstre vers le héros en utilisant l'algorithme de choix de déplacement.
     */
    public void deplacerVersHeros() {
        String choix = choisirDeplacement();
        this.deplacer(choix);
    }

    /**
     * Choisit la direction de déplacement pour se rapprocher du héros.
     * @return La direction choisie (HAUT, BAS, GAUCHE, DROITE)
     */
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


    /**
     * Calcule la distance entre le monstre et le héros.
     * @return Un tableau contenant [0, diffx] si la distance horizontale est plus grande,
     *         ou [1, diffy] si la distance verticale est plus grande
     */
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


    public void deplacerIntelligent(int x, int y) {

        String action = choixDeplacerIntelligent(x, y);

        this.deplacer(action);

    }

    public String choixDeplacerIntelligent(int x, int y) {
        String[] directions = {Labyrinthe.HAUT, Labyrinthe.BAS, Labyrinthe.GAUCHE, Labyrinthe.DROITE};

        ArrayList<int[]> file = new ArrayList<>();
        boolean[][] visites = new boolean[laby.getLength()][laby.getLengthY()];
        String[][] directionInitiale = new String[laby.getLength()][laby.getLengthY()];

        // on regarde les 4 cases autour du monstre
        for (String d : directions) {
            int[] suivante = Labyrinthe.getSuivant(x, y, d);
            int suivant_x = suivante[0];
            int suivant_y = suivante[1];

            if (estValide(suivant_x, suivant_y) && !laby.monstres.occupeParUnMonstre(suivant_x, suivant_y, this)) {
                file.add(new int[]{suivant_x, suivant_y, 1});
                directionInitiale[suivant_x][suivant_y] = d;
                visites[suivant_x][suivant_y] = true;
            }
        }

        int index = 0;
        int maxProfondeur = 80; // empêche de boucler à l'infini

        while (index < file.size()) {
            int[] courant = file.get(index++);
            int cx = courant[0];
            int cy = courant[1];
            int profondeur = courant[2];

            if (cx == laby.pj.x && cy == laby.pj.y) {
                return directionInitiale[cx][cy];
            }

            if (profondeur >= maxProfondeur) continue;

            for (String dir : directions) {
                int[] voisin = Labyrinthe.getSuivant(cx, cy, dir);
                int vx = voisin[0];
                int vy = voisin[1];

                if (estValide(vx, vy) && !visites[vx][vy] && !laby.monstres.occupeParUnMonstre(vx, vy, this)) {
                    file.add(new int[]{vx, vy, profondeur + 1});
                    directionInitiale[vx][vy] = directionInitiale[cx][cy];
                    visites[vx][vy] = true;
                }
            }
        }

        return Labyrinthe.HAUT;
    }

    private boolean estValide(int x, int y) {
        return x >= 0 && x < laby.getLength() && y >= 0 && y < laby.getLengthY() && !laby.getMur(x, y);
    }

}