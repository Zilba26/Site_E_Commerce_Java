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
import src.contenu.Avis;

public class PanneauAvis extends JPanel {

    private PanneauMenu panneauMenu;
    private App app;
    public static final int MARGE_ENTRE_PANEL = 30;
    public static final double RAPPORT_ARTICLE_PAGE = 0.9;
    public static final int LARGEUR_PAGE = 600;
    public static final int HAUTEUR_PAGE = 800;

    public PanneauAvis(PanneauMenu panneauMenu, App app) {
        this.panneauMenu = panneauMenu;
        this.app = app;
        ArrayList<Article> listeArticle = getListeArticle();
        for (int k = 0; k < listeArticle.size(); k++)
            for (int j = 0; j < listeArticle.get(k).getListeAvis().size(); j++)
                this.add(creePanelAvis(listeArticle.get(k).getListeAvis().get(j), app));

    }

    private ArrayList<Article> getListeArticle() {
        ArrayList<Article> listArticle = new ArrayList<Article>();
        for (int k = 0; k < app.getStock().getArrayCategorie().size(); k++)
            for (int i = 0; i < app.getStock().getArrayCategorie().get(k).getArticles().size(); i++)
                listArticle.add(app.getStock().getArrayCategorie().get(k).getArticles().get(i)); // Liste de tous les
                                                                                                 // articles
        return listArticle;
    }

    private JPanel creePanelAvis(Avis avis, App app) {
        JPanel panelAvis = new JPanel();
        panelAvis.setPreferredSize(new Dimension((int) (LARGEUR_PAGE * RAPPORT_ARTICLE_PAGE), 50));
        panelAvis.setBorder(BorderFactory.createEmptyBorder(MARGE_ENTRE_PANEL / 2, 0, MARGE_ENTRE_PANEL / 2, 0));
        panelAvis.setBackground(Color.RED);

        JLabel nom = new JLabel("Article : " + avis.getArticleAssocie().getNom());
        panelAvis.add(nom, BorderLayout.WEST);

        JLabel note = new JLabel("Note : " + avis.getNote());
        panelAvis.add(note);

        JLabel contenu = new JLabel(avis.getContenu());
        panelAvis.add(contenu);

        JButton boutonRetirer = new JButton("Retirer");
        panelAvis.add(boutonRetirer, BorderLayout.EAST);
        boutonRetirer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.supprimeAvis(avis);
                // TODO : Trouver le bon article à entrer en paramètre juste au dessus
            }
        });

        return panelAvis;

    }
}
