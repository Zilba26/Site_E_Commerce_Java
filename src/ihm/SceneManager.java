package src.ihm;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SceneManager extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static final int LARGEUR_FENETRE = 400;
    public static final int HAUTEUR_FENETRE = 400;
    public static final int INPUT_WIDTH = 150;
    public static final String TITRE_APPLICATION = "Gestion du site";
    private static Scene connectScene;
    private static Scene menuScene;

    private static TextField mailInput;
    private static TextField mdpInput;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Création des scènes
        initScenes();

        Node connectButtonNode = connectScene.getRoot().lookup("#ConnectButton");
        if (connectButtonNode instanceof Button) {
            Button connectButton = (Button) connectButtonNode;
            connectButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (checkConnectID())
                        primaryStage.setScene(menuScene);
                }
            });

        }

        // Affichage de la scène 1
        // primaryStage.setScene(scene1);
        primaryStage.setScene(connectScene);
        primaryStage.show();
    }

    private static boolean checkConnectID() {
        // TODO : Check with database
        return true;
    }

    private static void initScenes() {
        initConnectScene();
        initMenuScene();
    }

    private static Scene initMenuScene() {
        StackPane sp = new StackPane();

        Label textAccueil = new Label("Bienvenue sur la page d'administration");
        textAccueil.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        sp.getChildren().add(textAccueil);

        menuScene = new Scene(sp, 500, 500);
        return menuScene;
    }

    private static Scene initConnectScene() {
        Button btnConnect = new Button("Connect");
        btnConnect.setTranslateY(70);
        btnConnect.setId("ConnectButton");

        Label labelConnexion = new Label("Connect to the admin account");
        labelConnexion.setTranslateY(-60);
        labelConnexion.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        mailInput = new TextField();
        mailInput.setPromptText("Email");
        mailInput.setMaxWidth(INPUT_WIDTH);
        mailInput.setTranslateY(-15);

        mdpInput = new TextField();
        mdpInput.setPromptText("Password");
        mdpInput.setMaxWidth(INPUT_WIDTH);
        mdpInput.setTranslateY(15);

        StackPane sp = new StackPane();
        sp.getChildren().add(labelConnexion);
        sp.getChildren().add(mailInput);
        sp.getChildren().add(mdpInput);
        sp.getChildren().add(btnConnect);

        connectScene = new Scene(sp, LARGEUR_FENETRE, HAUTEUR_FENETRE);
        return connectScene;
    }
}
