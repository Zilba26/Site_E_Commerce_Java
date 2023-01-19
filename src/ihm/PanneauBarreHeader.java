package src.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanneauBarreHeader extends JPanel {
    
    public PanneauBarreHeader(SceneManager sceneManager, JFrame jFrame) {

        JButton boutonMenu = new JButton("Menu");
        this.add(boutonMenu);
        boutonMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sceneManager.setSizeFenetre(jFrame.getSize());
                sceneManager.setLocationFenetre(jFrame.getLocation());
                // sceneManager.getPage("Menu").setVisible(false);
                // sceneManager.creePage("Menu", true);
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
