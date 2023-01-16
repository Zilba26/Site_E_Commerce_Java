package src.ihm;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.app.App;

public class PanneauMenu extends JPanel {

    private App app;
    private SceneManager sceneManager;
    public static final int LARGEUR_PAGE = 600;
    public static final int HAUTEUR_PAGE = 800;

    public PanneauMenu(App app, SceneManager sceneManager){

        this.app = app;
        this.sceneManager = sceneManager;

        JButton boutonArticle = new JButton("Article");
        this.add(boutonArticle);
        boutonArticle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sceneManager.showPanneau("Article");
            }
        });

        
    }
    
}
