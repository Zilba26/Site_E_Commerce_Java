package src.ihm;

import javax.swing.JFrame;

import src.app.App;

public class SceneManager {
    private JFrame pageMenu;
    private JFrame pageArticle;
    private JFrame pageAvis;
    private JFrame pageCategorie;
    private JFrame activePage;


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
        PanneauMenu panneauMenu = new PanneauMenu(app, this);
        this.pageMenu.add(panneauMenu);
        this.pageMenu.setSize(PanneauMenu.LARGEUR_PAGE,PanneauMenu.HAUTEUR_PAGE);
        this.pageMenu.setVisible(true);

        this.pageArticle = new JFrame("Page de gestion des articles");
        this.pageArticle.add(new PanneauArticle(panneauMenu, app));
        this.pageArticle.setSize(PanneauArticle.LARGEUR_PAGE,PanneauArticle.HAUTEUR_PAGE);
        this.pageArticle.setVisible(false);

        this.pageAvis = new JFrame("Page de gestion des avis");
        this.pageAvis.add(new PanneauAvis(panneauMenu, app));
        this.pageAvis.setSize(PanneauAvis.LARGEUR_PAGE,PanneauAvis.HAUTEUR_PAGE);
        this.pageAvis.setVisible(false);

        this.pageCategorie = new JFrame("Page de gestion des categories");
        this.pageCategorie.add(new PanneauCategorie(panneauMenu, app));
        this.pageCategorie.setSize(PanneauCategorie.LARGEUR_PAGE,PanneauCategorie.HAUTEUR_PAGE);
        this.pageCategorie.setVisible(false);

    }

    public void showPanneau(String nomPanneau){
        this.pageArticle.setVisible(false);
        this.pageAvis.setVisible(false);
        this.pageCategorie.setVisible(false);
        this.pageMenu.setVisible(false);

        switch(nomPanneau){
            case "Article":
                this.pageArticle.setVisible(true);
                break;
            case "Avis":
                this.pageAvis.setVisible(true);
                break;
            case "Categorie":
                this.pageCategorie.setVisible(true);
                break;
            case "Menu":
                this.pageMenu.setVisible(true);
                break;
            default:
                this.pageMenu.setVisible(true);
                break;
            

        }

    }
}
