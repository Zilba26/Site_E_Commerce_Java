package src.ihm;

import javax.swing.JFrame;

import javafx.geometry.Dimension2D;

public class SceneManager{
    private PanneauConnexion panneauConnexion;


    public SceneManager(){}

    public static void main(String[] args) {
        JFrame pagePrincipale = new JFrame("Page d'administration du site");
        
        
        SceneManager sceneManager = new SceneManager();
        sceneManager.panneauConnexion = new PanneauConnexion();
        pagePrincipale.add(sceneManager.panneauConnexion);
        pagePrincipale.pack();
        pagePrincipale.setSize(600,800);
        

        pagePrincipale.setVisible(true);
    }
}
