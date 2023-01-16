package src.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.app.App;
import src.contenu.Article;

public class PanneauArticle extends JPanel{
    
    private PanneauMenu panneauMenu;
    private App app;
    public static final int MARGE_ENTRE_PANEL = 30;
    public static final int LARGEUR_PAGE = 600;
    public static final int HAUTEUR_PAGE = 800;

    public PanneauArticle(PanneauMenu panneauMenu, App app) {
        this.panneauMenu = panneauMenu;
        this.app = app;

        ArrayList<Article> listArticle = new ArrayList<Article>();
        for(int k = 0 ; k <  app.getStock().getArrayCategorie().size() ; k++)
            for(int i = 0 ; i < app.getStock().getArrayCategorie().get(k).getArticles().size() ; i++)
                listArticle.add(app.getStock().getArrayCategorie().get(k).getArticles().get(i)); // Liste de tous les articles
        for(int k = 0 ; k < listArticle.size() ; k++){
            this.add(creePanelArticle(listArticle.get(k)));
        }
        
    }

    private JPanel creePanelArticle(Article article){
        JPanel panelArticle = new JPanel(new GridLayout(2,4));
        panelArticle.setPreferredSize(new Dimension((int)(LARGEUR_PAGE*0.8), 50));
        panelArticle.setBorder(BorderFactory.createEmptyBorder(MARGE_ENTRE_PANEL/2, 0, MARGE_ENTRE_PANEL/2, 0));
        panelArticle.setBackground(Color.RED);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1; // colonne 1
        constraints.gridy = 1; // ligne 1
        JLabel nom = new JLabel(article.getNom());
        panelArticle.add(nom,constraints);

        constraints.gridx = 1; // colonne 1
        constraints.gridy = 2; // ligne 1
        JLabel prix = new JLabel(article.getPrix().toString() + " €");
        panelArticle.add(prix,constraints);

        constraints.gridx = 4; // colonne 1
        constraints.gridy = 1; // ligne 1
        JButton boutonRetirer = new JButton("Retirer");
        panelArticle.add(boutonRetirer,constraints);

        constraints.gridx = 3; // colonne 1
        constraints.gridy = 2; // ligne 1
        JLabel description = new JLabel(article.getDescription());
        panelArticle.add(description,constraints);


        return panelArticle;
    }

}
