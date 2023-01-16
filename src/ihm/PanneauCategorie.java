package src.ihm;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.app.App;

public class PanneauCategorie extends JPanel{
    
    private PanneauMenu panneauMenu;
    private App app;
    public static final int LARGEUR_PAGE = 600;
    public static final int HAUTEUR_PAGE = 800;

    public PanneauCategorie(PanneauMenu panneauMenu, App app) {
        this.panneauMenu = panneauMenu;
        this.app = app;

        JButton boutonReturn = new JButton("Menu");
        this.add(boutonReturn);
        boutonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panneauMenu.sceneManager.showPanneau(boutonReturn.getText());
            }
        });
    }
}
