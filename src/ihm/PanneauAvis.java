package src.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

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
        panelAvis.setBorder(new EmptyBorder(0,0,0,0));
        panelAvis.setBackground(Color.LIGHT_GRAY);

        JLabel labelInfo = new JLabel("Article : " + avis.getArticleAssocie().getNom() + " | Note : " + avis.getNote() + " | Contenu : " + avis.getContenu());
        JPanel panelInfo = new JPanel();
        panelInfo.add(labelInfo, BorderLayout.WEST);
        panelInfo.setBackground(Color.LIGHT_GRAY);
        panelInfo.setBorder(new LineBorder(Color.GRAY));

        JButton boutonModifier = new JButton("Modifier");
        JPanel panelModifier = new JPanel();
        panelModifier.add(boutonModifier, BorderLayout.CENTER);
        panelModifier.setBackground(Color.LIGHT_GRAY);
        panelModifier.setBorder(new LineBorder(Color.GRAY));
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
                    if (Float.parseFloat(noteField.getText()) <= 5 && Float.parseFloat(noteField.getText()) >= 0 && (Float.parseFloat(noteField.getText()) % 0.25) == 0) {
                        avis.modifierAvis(Double.parseDouble(noteField.getText()), contenuField.getText(), dateField.getText());
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "La note de l'article doit être comprise entre 0 et 5 modulo 0.25");
                    }
                }
                getPanneauMenu().getSceneManager().getPage("Avis").setVisible(false);
                getPanneauMenu().getSceneManager().creePage("Avis", true);
                // TODO : Refresh la page
            }
        });

        JButton boutonRetirer = new JButton("Retirer");
        JPanel panelRetirer = new JPanel();
        panelRetirer.add(boutonRetirer, BorderLayout.CENTER);
        panelRetirer.setBackground(Color.LIGHT_GRAY);
        panelRetirer.setBorder(new LineBorder(Color.GRAY));
        boutonRetirer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.supprimeAvis(avis);
                // TODO : Trouver le bon article à entrer en paramètre juste au dessus
            }
        });

        GridBagLayout gbl = new GridBagLayout();
        panelAvis.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.8;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelAvis.add(panelInfo, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelAvis.add(panelModifier, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.1;
        gbc.gridx = 2;
        gbc.gridy = 0;
        panelAvis.add(panelRetirer, gbc);

        return panelAvis;

    }

    public PanneauMenu getPanneauMenu() {
        return this.panneauMenu;
    }

    public App getApp() {
        return this.app;
    }
}
