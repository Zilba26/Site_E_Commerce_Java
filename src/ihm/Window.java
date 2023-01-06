package src.ihm;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class Window extends Application {

    public static final int LARGEUR_FENETRE = 400;
    public static final int HAUTEUR_FENETRE = 600;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        Button btn1 = new Button("Say, Hello World");
        Label labelConnexion = new Label("Connexion");
        TextField shownTF = new TextField();
        shownTF.setMaxWidth(150);
        shownTF.setTranslateX(10);
        shownTF.setTranslateY(100);

        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                System.out.println("hello world");
            }
        });
        Pane root = new Pane();
        root.getChildren().add(btn1);
        root.getChildren().add(shownTF);
        root.getChildren().add(labelConnexion);
        Scene scene = new Scene(root, HAUTEUR_FENETRE, LARGEUR_FENETRE);
        primaryStage.setTitle("First JavaFX Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}