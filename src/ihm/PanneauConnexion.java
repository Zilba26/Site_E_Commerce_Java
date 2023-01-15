package src.ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanneauConnexion extends JPanel {
    private JLabel emailLabel, passwordLabel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public PanneauConnexion() {
        // Initialize components
        emailLabel = new JLabel("Adresse email:");
        passwordLabel = new JLabel("Mot de passe:");
        emailField = new JTextField(20);
        emailField.setForeground(Color.GRAY);
        emailField.setText("Email");

        emailField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (emailField.getText().equals("Email")) {
                    emailField.setText("");
                    emailField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (emailField.getText().isEmpty()) {
                    emailField.setForeground(Color.GRAY);
                    emailField.setText("Email");
                }
            }
        });
        passwordField = new JPasswordField(20);
        
        loginButton = new JButton("Connexion");

        // Add action listener to login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                // Verify email and password
                if (true) {
                    //Ajoutez ici l'appel à la nouvelle scene
                    JOptionPane.showMessageDialog(null, "Connexion réussie!");
                } else {
                    JOptionPane.showMessageDialog(null, "Adresse email ou mot de passe invalide!");
                }
            }
        });

        // Set layout
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        // Add components to panel
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(5, 5, 1, 0);
        //gc.fill = GridBagConstraints.RELATIVE;
        add(emailLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(5, 0, 1, 5);
        //gc.fill = GridBagConstraints.RELATIVE;
        add(emailField, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(5, 5, 5, 5);
        //gc.fill = GridBagConstraints.RELATIVE;
        add(passwordLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(5, 5, 5, 5);
        //gc.fill = GridBagConstraints.RELATIVE;
        add(passwordField, gc);
    }
}
