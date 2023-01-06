package src.ihm;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SceneManager extends Application {

    SceneManager() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ConnectWindow connectWindow = new ConnectWindow(this);
        primaryStage.setTitle(ConnectWindow.TITRE_APPLICATION);
        primaryStage.setScene(connectWindow.initScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private boolean pressConnectButton(String email, String password) {
        // TODO : Check with database
        return true;
    }

}
