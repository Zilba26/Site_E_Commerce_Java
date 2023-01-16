package src.ihm;

import javax.swing.JFrame;

import src.app.App;

public class SceneManager {
    private JFrame pageMenu;
    private JFrame pageArticle;
    private JFrame pageAvis;
    private JFrame pageCategorie;


    public SceneManager(){}

    public static void main(String[] args) {
        
        SceneManager sceneManager = new SceneManager();
        sceneManager.pageMenu = new JFrame("Page d'administration du site");
        App app = new App();
        
        new PanneauConnexion(app);

        while(!app.adminEstConnecte()){
            new PanneauConnexion(app);
        }
        sceneManager.initPages(app);
        
    }

    private void initPages(App app){
        this.pageMenu.add(new PanneauMenu(app, this));
        this.pageMenu.setSize(PanneauMenu.LARGEUR_PAGE,PanneauMenu.HAUTEUR_PAGE);
        this.pageMenu.setVisible(true);

        this.pageArticle.add(new PanneauArticle(app, this));
        this.pageArticle.setSize(PanneauArticle.LARGEUR_PAGE,PanneauArticle.HAUTEUR_PAGE);
        this.pageArticle.setVisible(false);

        this.pageAvis.add(new PanneauAvis(app, this));
        this.pageAvis.setSize(PanneauAvis.LARGEUR_PAGE,PanneauAvis.HAUTEUR_PAGE);
        this.pageAvis.setVisible(false);

        this.pageCategorie.add(new PanneauCategorie(app, this));
        this.pageCategorie.setSize(PanneauCategorie.LARGEUR_PAGE,PanneauCategorie.HAUTEUR_PAGE);
        this.pageCategorie.setVisible(false);

    }

    public void showPanneauArticle(){

    }
}
