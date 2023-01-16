package src.ihm;

import javax.swing.JFrame;
import java.awt.BorderLayout;

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
        PanneauMenu panneauMenu = new PanneauMenu(app, this);
        this.pageMenu.add(panneauMenu);
        this.pageMenu.setSize(PanneauMenu.LARGEUR_PAGE,PanneauMenu.HAUTEUR_PAGE);
        this.pageMenu.add(new PanneauBarreHeader(this), BorderLayout.NORTH);
        this.pageMenu.setVisible(true);
        this.pageMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.pageArticle = new JFrame("Page de gestion des articles");
        this.pageArticle.add(new PanneauArticle(panneauMenu, app));
        this.pageArticle.setSize(PanneauArticle.LARGEUR_PAGE,PanneauArticle.HAUTEUR_PAGE);
        this.pageArticle.setVisible(false);
        this.pageArticle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.pageAvis = new JFrame("Page de gestion des avis");
        this.pageAvis.add(new PanneauAvis(panneauMenu, app));
        this.pageAvis.setSize(PanneauAvis.LARGEUR_PAGE,PanneauAvis.HAUTEUR_PAGE);
        this.pageAvis.setVisible(false);
        this.pageAvis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.pageCategorie = new JFrame("Page de gestion des categories");
        this.pageCategorie.add(new PanneauCategorie(panneauMenu, app));
        this.pageCategorie.setSize(PanneauCategorie.LARGEUR_PAGE,PanneauCategorie.HAUTEUR_PAGE);
        this.pageCategorie.setVisible(false);
        this.pageCategorie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.pageArticle.add(new PanneauBarreHeader(this), BorderLayout.NORTH);
        this.pageAvis.add(new PanneauBarreHeader(this), BorderLayout.NORTH);
        this.pageCategorie.add(new PanneauBarreHeader(this), BorderLayout.NORTH);

    }

    public void showPanneau(String nomPanneau){
        this.hidePanneau();
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

    private void hidePanneau(){
        this.pageArticle.setVisible(false);
        this.pageAvis.setVisible(false);
        this.pageCategorie.setVisible(false);
        this.pageMenu.setVisible(false);
    }

    public void deconnectAdmin() {

        this.pageMenu.dispose();
        this.pageArticle.dispose();
        this.pageAvis.dispose();
        this.pageCategorie.dispose();
        System.gc();

        SceneManager.main(null);
    }

    //TODO : Garder en mémoire la taille des fenetres pour les appliquer à toutes lors d'un changement de fenetre

}
