package gameLaby.laby;

import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe représentant un labyrinthe de jeu.
 */
public class Labyrinthe {

    private boolean jeuEnCours = true;

    /**
     * Caractère représentant un mur dans le fichier de labyrinthe
     */
    public static final char MUR = 'X';
    /**
     * Caractère représentant le personnage joueur dans le fichier de labyrinthe
     */
    public static final char PJ = 'P';
    /**
     * Caractère représentant une case vide dans le fichier de labyrinthe
     */
    public static final char VIDE = '.';
    /**
     * Caractère représentant un monstre dans le fichier de labyrinthe
     */
    public static final char M = 'M';
    /**
     * Caractère représentant l'amulette dans le fichier de labyrinthe
     */
    public static final char A = 'A';

    /**
     * Constante pour le mouvement vers le haut
     */
    public static final String HAUT = "Haut";
    /**
     * Constante pour le mouvement vers le bas
     */
    public static final String BAS = "Bas";
    /**
     * Constante pour le mouvement vers la gauche
     */
    public static final String GAUCHE = "Gauche";
    /**
     * Constante pour le mouvement vers la droite
     */
    public static final String DROITE = "Droite";

    /**
     * Le héros du jeu
     */
    private Heros pj;

    /**
     * La collection de monstres présents dans le labyrinthe
     */
    private Bestiaire monstres = new Bestiaire();

    /**
     * L'amulette à collecter dans le labyrinthe
     */
    private Amulette amu;

    /**
     * Tableau 2D représentant les murs du labyrinthe
     */
    private boolean[][] murs;

    /**
     * Position de départ du héros dans le labyrinthe
     */
    private final int[] caseDepart;

    /**
     * Calcule la case suivante en fonction d'une action de déplacement.
     *
     * @param x      Coordonnée x de la case de départ
     * @param y      Coordonnée y de la case de départ
     * @param action Action de déplacement (HAUT, BAS, GAUCHE, DROITE)
     * @return Tableau contenant les nouvelles coordonnées [x, y]
     */
    static int[] getSuivant(int x, int y, String action) {
        switch (action) {
            case HAUT:
                // on monte une ligne
                y--;
                break;
            case BAS:
                // on descend une ligne
                y++;
                break;
            case DROITE:
                // on augmente colonne
                x++;
                break;
            case GAUCHE:
                // on augmente colonne
                x--;
                break;
            default:
                throw new Error("action inconnue");
        }
        int[] res = {x, y};
        return res;
    }

    /**
     * Constructeur du labyrinthe.
     * Charge et initialise le labyrinthe à partir d'un fichier texte.
     * @param nom Chemin vers le fichier contenant la définition du labyrinthe
     */
    public Labyrinthe(String nom) throws IOException {
        // ouvrir fichier
        FileReader fichier = new FileReader(nom);
        BufferedReader bfRead = new BufferedReader(fichier);

        int nbLignes, nbColonnes;
        // lecture nblignes
        nbLignes = Integer.parseInt(bfRead.readLine());
        // lecture nbcolonnes
        nbColonnes = Integer.parseInt(bfRead.readLine());

        // creation labyrinthe vide
        this.murs = new boolean[nbColonnes][nbLignes];
        this.pj = null;
        this.caseDepart = new int[2];

        // lecture des cases
        String ligne = bfRead.readLine();

        // stocke les indices courants
        int numeroLigne = 0;


        // parcours le fichier
        while (ligne != null) {

            // parcours de la ligne
            for (int colonne = 0; colonne < ligne.length(); colonne++) {
                char c = ligne.charAt(colonne);
                switch (c) {
                    case MUR:
                        this.murs[colonne][numeroLigne] = true;
                        break;
                    case VIDE:
                        this.murs[colonne][numeroLigne] = false;
                        break;
                    case PJ:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        this.pj = new Heros(colonne, numeroLigne);
                        this.caseDepart[0] = colonne;
                        this.caseDepart[1] = numeroLigne;
                        break;
                    case M :
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        monstres.ajouterMonstre(new Monstre(colonne, numeroLigne));
                        break;
                    case A:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        this.amu = new Amulette(colonne, numeroLigne);
                        break;


                    default:
                        throw new Error("caractere inconnu " + c);
                }
            }

            // lecture
            ligne = bfRead.readLine();
            numeroLigne++;
        }

        // ferme fichier
        bfRead.close();

        if (this.pj != null) this.pj.setLaby(this);
        for(int i = 0; i < this.getMonstres().getListeMonstres().size(); i ++) {
            Monstre monstre = this.getMonstres().getListeMonstres().get(i);
            if (monstre != null) monstre.setLaby(this);
        }


    }


    /**
     * Vérifie si le jeu est terminé.
     * Cette méthode est surchargée dans LabyJeu pour une implémentation spécifique.
     *
     * @return false par défaut
     */
    public boolean etreFini() {
        boolean b = false;
        if( ! (this.pj.etreVivant()) ){
            b = true;
            Platform.exit();
        }
        if(this.pj.etreVivant() && this.pj.remplirConditionVictoire()){
            b = true;

        }
        if(b == true){
            jeuEnCours = false;
        }
        return b;
    }

    // ##################################
    // GETTER
    // ##################################

    /**
     * Retourne la hauteur du labyrinthe (nombre de lignes).
     *
     * @return Nombre de lignes du labyrinthe
     */
    public int getLengthY() {
        return murs[0].length;
    }

    /**
     * Retourne la largeur du labyrinthe (nombre de colonnes).
     *
     * @return Nombre de colonnes du labyrinthe
     */
    public int getLength() {
        return murs.length;
    }

    /**
     * Vérifie si une case donnée contient un mur.
     *
     * @param x Coordonnée x de la case à vérifier
     * @param y Coordonnée y de la case à vérifier
     * @return true si la case contient un mur, false sinon
     */
    public boolean getMur(int x, int y) {
        // utilise le tableau de boolean
        return this.murs[x][y];
    }

    public Heros getPj() {
        return pj;
    }

    public Bestiaire getMonstres() {
        return monstres;
    }

    public Amulette getAmu() {
        return amu;
    }

    public int[] getCaseDepart() {
        return caseDepart;
    }

    public boolean[][] getMurs() {
        return murs;
    }

    public boolean getJeuEnCours() {
        return jeuEnCours;
    }
}
