package src.ihm;

import src.app.App;

public class PanneauAvis {
    
    private PanneauMenu panneauMenu;
    private App app;
    public static final int LARGEUR_PAGE = 600;
    public static final int HAUTEUR_PAGE = 800;

    public PanneauAvis(PanneauMenu panneauMenu, App app) {
        this.panneauMenu = panneauMenu;
        this.app = app;
    }

}
