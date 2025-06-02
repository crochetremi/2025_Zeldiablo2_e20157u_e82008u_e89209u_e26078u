package gameLaby.laby;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

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
            for(int y = 0; y < lj.laby.getLengthY(); y++){
                if(lj.laby.getMur(x,y)){
                    gc.setFill(Color.BLACK);
                    gc.fillRect(x*20, y*20, 20, 20);
                }
            }
        }

        gc.setFill(Color.RED);
        gc.fillOval(lj.laby.pj.getX()*20, lj.laby.pj.getY()*20, 20, 20);

        gc.setFill(Color.PURPLE);
        gc.fillOval(lj.laby.monstre.getX()*20, lj.laby.monstre.getY()*20, 20, 20);

    }
}
