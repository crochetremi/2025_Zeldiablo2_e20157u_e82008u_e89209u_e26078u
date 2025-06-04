package gameLaby.laby;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

import java.util.ArrayList;

/**
 * Classe responsable du rendu graphique du jeu de labyrinthe.
 * Cette classe implémente l'interface DessinJeu pour gérer l'affichage
 * du labyrinthe, du joueur, des monstres et de l'amulette.
 */
public class LabyDessin implements DessinJeu {
    /**
     * 
     * @param jeu Le jeu à dessiner
     * @param canvas Le canvas sur lequel dessiner
     */
    @Override
    public void dessinerJeu(Jeu jeu, Canvas canvas) {

        // on récupère le jeu
        LabyJeu lj = (LabyJeu) jeu;

        //Création crayon dessin
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        // dessin fond
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Labyrinthe laby = lj.getLaby();
        Color c1 = Color.BLACK;
        dessinerMurs(laby, gc,c1);

        gc.setFill(Color.BROWN);
        gc.fillRect(laby.getCaseDepart()[0]*20, laby.getCaseDepart()[1]*20, 20, 20);

        int x1 = laby.getPj().getX();
        int y = laby.getPj().getY();

        dessinerAmulette(laby, gc, x1, y);

        // afficher les monstres
        afficherMonstre(gc, laby);


    }

    private static void dessinerMurs(Labyrinthe laby, GraphicsContext gc,Color c) {
        for (int x = 0; x <
                laby.getLength(); x++) {
            for(int y = 0; y < laby.getLengthY(); y++){
                if(laby.getMur(x,y)){
                    gc.setFill(Color.BLACK);
                    gc.fillRect(x*20, y*20, 20, 20);
                }
            }
        }
    }

    private static void afficherMonstre(GraphicsContext gc, Labyrinthe laby) {
        gc.setFill(Color.PURPLE);
        ArrayList<Monstre> monstres = laby.getMonstres().getListeMonstres();
        int size = monstres.size();

        for(int i = 0; i < size; i++){
            Monstre monstrei = monstres.get(i);
            gc.fillOval(monstrei.getX()*20, monstrei.getY()*20, 20, 20);
        }
    }

    private static void dessinerAmulette(Labyrinthe laby, GraphicsContext gc, int x1, int y) {
        if(laby.getPj().getAmulette() == true){
            gc.setFill(Color.RED);
            gc.fillOval(x1 *20, y *20, 20, 20);
            gc.setFill(Color.YELLOW);
            gc.fillOval(x1 *20, y *20, 10, 10);
        }
        else{
            gc.setFill(Color.YELLOW);
            Amulette amu = laby.getAmu();
            gc.fillOval(amu.getX()*20, amu.getY()*20, 20, 20);

            gc.setFill(Color.RED);
            gc.fillOval(x1 *20, y *20, 20, 20);
        }
    }

}


