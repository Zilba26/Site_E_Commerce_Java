package src.ihm;

import javax.swing.JPanel;

import src.app.App;

public class PanneauArticle extends JPanel{
    
    private PanneauMenu panneauMenu;
    private App app;
    public static final int LARGEUR_PAGE = 600;
    public static final int HAUTEUR_PAGE = 800;

    public PanneauArticle(PanneauMenu panneauMenu, App app) {
        this.panneauMenu = panneauMenu;
        this.app = app;
    }

}
