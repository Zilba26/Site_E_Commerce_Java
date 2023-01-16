package src.ihm;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.app.App;

public class PanneauMenu extends JPanel {

    private App app;
    public SceneManager sceneManager;
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
                sceneManager.showPanneau(boutonArticle.getText());
            }
        });

        JButton boutonAvis = new JButton("Avis");
        this.add(boutonAvis);
        boutonAvis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sceneManager.showPanneau(boutonAvis.getText());
            }
        });

        JButton boutonCategorie = new JButton("Categorie");
        this.add(boutonCategorie);
        boutonCategorie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sceneManager.showPanneau(boutonCategorie.getText());
            }
        });

        
    }
    
}
