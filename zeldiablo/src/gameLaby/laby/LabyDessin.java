package gameLaby.laby;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class LabyDessin implements DessinJeu {
    @Override
    public void dessinerJeu(Jeu jeu, Canvas canvas) {

        //Crée le jeu
        LabyJeu lj = (LabyJeu) jeu;

        //Crée le canva
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        // dessin fond
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int x = 0; x < lj.laby.getLength(); x++) {
            for (int y = 0; y < lj.laby.getLengthY(); y++) {
                if (lj.laby.getMur(x, y)) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(x * 20, y * 20, 20, 20);
                }
            }
        }

        gc.setFill(Color.BROWN);
        gc.fillRect(lj.laby.caseDepart[0] * 20, lj.laby.caseDepart[1] * 20, 20, 20);

        if (lj.laby.pj.getAmulette() == true) {
            gc.setFill(Color.RED);
            gc.fillOval(lj.laby.pj.getX() * 20, lj.laby.pj.getY() * 20, 20, 20);
            gc.setFill(Color.YELLOW);
            gc.fillOval(lj.laby.pj.getX() * 20, lj.laby.pj.getY() * 20, 10, 10);
        } else {
            gc.setFill(Color.YELLOW);
            gc.fillOval(lj.laby.amu.getX() * 20, lj.laby.amu.getY() * 20, 20, 20);

            gc.setFill(Color.RED);
            gc.fillOval(lj.laby.pj.getX() * 20, lj.laby.pj.getY() * 20, 20, 20);
        }

        gc.setFill(Color.PURPLE);
        for (int x = 0; x < lj.laby.monstres.monstres.size(); x++) {
            gc.fillOval(lj.laby.monstres.monstres.get(x).getX() * 20, lj.laby.monstres.monstres.get(x).getY() * 20, 20, 20);
        }

        if(lj.etreFini() && lj.laby.pj.remplirConditionVictoire()){
            for (int x = 0; x < lj.laby.getLength(); x++) {
                for (int y = 0; y < lj.laby.getLengthY(); y++) {
                    if (lj.laby.getMur(x, y)) {
                        gc.setFill(Color.YELLOW);
                        gc.fillRect(x * 20, y * 20, 20, 20);
                    }
                    else{
                        gc.setFill(Color.LIGHTYELLOW);
                        gc.fillRect(x * 20, y * 20, 20, 20);
                    }
                }
            }
            gc.setFill(Color.RED);
            Font customFont = new Font("Arial",60);
            gc.setFont(customFont);
            gc.fillText("VICTOIRE", 250, 400);

        }

    }

}


