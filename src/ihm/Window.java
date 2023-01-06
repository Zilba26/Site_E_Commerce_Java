package src.ihm;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Window extends Application {

    public static final int LARGEUR_FENETRE = 400;
    public static final int HAUTEUR_FENETRE = 400;
    public static final int INPUT_WIDTH = 150;
    public static final String TITRE_APPLICATION = "Gestion du site";

    @Override
    public void start(Stage primaryStage) throws Exception {

        Button btnConnect = new Button("Connect");
        btnConnect.setTranslateY(70);

        Label labelConnexion = new Label("Connect to the admin account");
        labelConnexion.setTranslateY(-60);
        labelConnexion.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        TextField mailInput = new TextField();
        mailInput.setPromptText("Email");
        mailInput.setMaxWidth(INPUT_WIDTH);
        mailInput.setTranslateY(-15);

        TextField mdpInput = new TextField();
        mdpInput.setPromptText("Password");
        mdpInput.setMaxWidth(INPUT_WIDTH);
        mdpInput.setTranslateY(15);

        btnConnect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("Trying to connect...");
                // TODO : Check if email and password are right, then switch scene
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btnConnect);
        root.getChildren().add(mailInput);
        root.getChildren().add(mdpInput);
        root.getChildren().add(labelConnexion);
        Scene scene = new Scene(root, HAUTEUR_FENETRE, LARGEUR_FENETRE);
        primaryStage.setTitle(TITRE_APPLICATION);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}