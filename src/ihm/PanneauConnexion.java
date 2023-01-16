package src.ihm;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.image.BufferedImage;

import src.app.App;

public class PanneauConnexion extends JPanel {
    private JLabel emailLabel, passwordLabel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private App app;

    public PanneauConnexion(App app) {
        // Initialize components
        this.app = app;
        emailLabel = new JLabel("Adresse e-mail:");
        passwordLabel = new JLabel("Mot de passe:");
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);


        Object[] message = {
            emailLabel, emailField,
            passwordLabel, passwordField
        };
        int option = 0;
        try{
            BufferedImage originalImage = ImageIO.read(new File("image/eseo.jpg"));
            int width = 30;
            int height = 30;
            BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            option = JOptionPane.showConfirmDialog(null, message, "Connexion", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, resizedIcon);
        }
        catch(Exception e){
            option = JOptionPane.showConfirmDialog(null, message, "Connexion", JOptionPane.OK_CANCEL_OPTION);
        }
        if(option == JOptionPane.OK_OPTION){
            this.app.connexionAdmin(emailField.getText(),passwordField.getPassword().toString());
        }

    }
}
