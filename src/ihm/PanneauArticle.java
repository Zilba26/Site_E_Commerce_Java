package src.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.app.App;
import src.contenu.Article;

public class PanneauArticle extends JPanel {

    private PanneauMenu panneauMenu;
    private App app;
    public static final int MARGE_ENTRE_PANEL = 30;
    public static final int LARGEUR_PAGE = 600;
    public static final int HAUTEUR_PAGE = 800;
    public static final double RAPPORT_ARTICLE_PAGE = 0.9;

    public PanneauArticle(PanneauMenu panneauMenu, App app) {
        this.panneauMenu = panneauMenu;
        this.app = app;

        ArrayList<Article> listArticle = new ArrayList<Article>();
        for (int k = 0; k < app.getStock().getArrayCategorie().size(); k++)
            for (int i = 0; i < app.getStock().getArrayCategorie().get(k).getArticles().size(); i++)
                listArticle.add(app.getStock().getArrayCategorie().get(k).getArticles().get(i)); // Liste de tous les
                                                                                                 // articles
        for (int k = 0; k < listArticle.size(); k++) {
            this.add(creePanelArticle(listArticle.get(k), app));
        }

    }

    private JPanel creePanelArticle(Article article, App app) {
        JPanel panelArticle = new JPanel();
        panelArticle.setPreferredSize(new Dimension((int) (LARGEUR_PAGE * RAPPORT_ARTICLE_PAGE), 50));
        panelArticle.setBorder(BorderFactory.createEmptyBorder(MARGE_ENTRE_PANEL / 2, 0, MARGE_ENTRE_PANEL / 2, 0));
        panelArticle.setBackground(Color.RED);

        JLabel nom = new JLabel(article.getNom());
        panelArticle.add(nom, BorderLayout.WEST);

        JLabel prix = new JLabel(article.getPrix().toString() + " €");
        panelArticle.add(prix);

        JLabel description = new JLabel(article.getDescription());
        panelArticle.add(description);

        JButton boutonRetirer = new JButton("Retirer");
        panelArticle.add(boutonRetirer, BorderLayout.EAST);
        boutonRetirer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.supprimerArticle(article);
                // TODO : Trouver le bon article à entrer en paramètre juste au dessus
            }
        });

        return panelArticle;
    }

}
