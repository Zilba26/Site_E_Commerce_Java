package src.ihm;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauBarreHeader extends JPanel {
    
    public PanneauBarreHeader(SceneManager sceneManager) {

        JButton boutonMenu = new JButton("Menu");
        this.add(boutonMenu);
        boutonMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sceneManager.showPanneau(boutonMenu.getText());
            }
        });

        JButton boutonDeconnexion = new JButton("Deconnexion");
        this.add(boutonDeconnexion);
        boutonDeconnexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sceneManager.deconnectAdmin();
            }
        });

    }

    

}
