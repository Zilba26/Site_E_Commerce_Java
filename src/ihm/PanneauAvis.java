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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

        // Infos à mettre : note contenu nomClient date articleAssocie

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

        JButton boutonModifier = new JButton("Modifier");
        panelAvis.add(boutonModifier);
        boutonModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JLabel noteLabel = new JLabel("Note : ");
                JTextField noteField = new JTextField();
                noteField.setText("" + avis.getNote());

                JLabel contenuLabel = new JLabel("Contenu : ");
                JTextField contenuField = new JTextField();
                contenuField.setText(avis.getContenu());

                JLabel nomClientLabel = new JLabel("Nom Client : ");
                JLabel nomClientField = new JLabel(avis.getNomClient());

                JLabel dateLabel = new JLabel("Date : ");
                JTextField dateField = new JTextField();
                dateField.setText(avis.getDate());

                JLabel articleLabel = new JLabel("Article : ");
                JLabel articleField = new JLabel(avis.getArticleAssocie().getNom()); // TODO : Mettre un menu deroulant

                Object[] message = {
                        noteLabel, noteField,
                        contenuLabel, contenuField,
                        nomClientLabel, nomClientField,
                        dateLabel, dateField,
                        articleLabel, articleField
                };
                int option = JOptionPane.showConfirmDialog(null, message, "Modifier article",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    avis.modifierAvis(Double.parseDouble(noteField.getText()), contenuField.getText(),
                            dateField.getText());// TODO : Rajouter l'article modifié
                }
                getPanneauMenu().getSceneManager().getPage("Avis").setVisible(false);
                getPanneauMenu().getSceneManager().creePage("Avis", true);
                // TODO : Refresh la page
            }
        });

        return panelAvis;

    }

    public PanneauMenu getPanneauMenu() {
        return this.panneauMenu;
    }

    public App getApp() {
        return this.app;
    }
}
