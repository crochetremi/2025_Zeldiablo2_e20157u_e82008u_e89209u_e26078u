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

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        // dessin fond
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Labyrinthe laby = lj.getLaby();

        for (int x = 0; x <
                laby.getLength(); x++) {
            for(int y = 0; y < laby.getLengthY(); y++){
                if(laby.getMur(x,y)){
                    gc.setFill(Color.BLACK);
                    gc.fillRect(x*20, y*20, 20, 20);
                }
            }
        }

        gc.setFill(Color.BROWN);
        gc.fillRect(laby.caseDepart[0]*20, laby.caseDepart[1]*20, 20, 20);

        int x1 = laby.pj.getX();
        int y = laby.pj.getY();

        if(laby.pj.getAmulette() == true){
            gc.setFill(Color.RED);
            gc.fillOval(x1 *20, y *20, 20, 20);
            gc.setFill(Color.YELLOW);
            gc.fillOval(x1 *20, y *20, 10, 10);
        }
        else{
            gc.setFill(Color.YELLOW);
            Amulette amu = laby.amu;
            gc.fillOval(amu.getX()*20, amu.getY()*20, 20, 20);

            gc.setFill(Color.RED);
            gc.fillOval(x1 *20, y *20, 20, 20);
        }

        // afficher les monstres
        gc.setFill(Color.PURPLE);
        ArrayList<Monstre> monstres = laby.monstres.monstres;
        int size = monstres.size();

        for(int i = 0; i < size; i++){
            Monstre monstrei = monstres.get(i);
            gc.fillOval(monstrei.getX()*20, monstrei.getY()*20, 20, 20);
        }


        if(laby.pj.remplirConditionVictoire()){

            this.victoire(canvas);

    }}

    /**
     * Affiche l'écran de victoire lorsque le joueur remplit les conditions de victoire.
     * Crée une nouvelle fenêtre avec un message de félicitations et un bouton pour quitter le jeu.
     * 
     * @param canvas Le canvas sur lequel le jeu était affiché
     */
    public void victoire(Canvas canvas){

        //Crée le canva
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        // dessin fond
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

            // Créer une nouvelle fenêtre pour afficher le message de victoire
            Stage victoryStage = new Stage();
            victoryStage.setTitle("Félicitations!");

            // Créer le contenu de la fenêtre
            VBox content = new VBox(10);
            content.setAlignment(Pos.CENTER);
            content.setPadding(new Insets(20));

            Label messageLabel = new Label("Vous avez gagné!");
            messageLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

            Button quitterButton = new Button("Quitter");
            quitterButton.setOnAction(e -> {
                victoryStage.close();
                Platform.exit();
            });

            content.getChildren().addAll(messageLabel, quitterButton);

            // Configurer et afficher la fenêtre
            Scene scene = new Scene(content, 300, 150);
            victoryStage.setScene(scene);

            // Fermer la fenêtre du labyrinthe
            Stage primaryStage = (Stage) gc.getCanvas().getScene().getWindow();
            primaryStage.close();

            // Afficher la fenêtre de victoire
            victoryStage.show();
        }
    }


