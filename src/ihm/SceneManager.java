package src.ihm;

import javax.swing.JFrame;

import src.app.App;

public class SceneManager{
    private PanneauConnexion panneauConnexion;


    public SceneManager(){}

    public static void main(String[] args) {
        JFrame pagePrincipale = new JFrame("Page d'administration du site");
        App app = new App();
        
        SceneManager sceneManager = new SceneManager();
        sceneManager.panneauConnexion = new PanneauConnexion(app);
        if(app.adminEstConnecte()){
            pagePrincipale.setSize(600,800);
            pagePrincipale.setVisible(true);
        }
        // pagePrincipale.add(sceneManager.panneauConnexion);
        // pagePrincipale.pack();
        // pagePrincipale.setSize(600,800);
        

        // pagePrincipale.setVisible(true);
    }
}
