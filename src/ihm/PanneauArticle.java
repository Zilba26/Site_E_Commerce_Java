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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import src.app.App;
import src.contenu.Article;
import src.contenu.Categorie;

public class PanneauArticle extends JPanel {

    private PanneauMenu panneauMenu;
    private App app;
    public static final int MARGE_ENTRE_PANEL = 10;
    public static final int LARGEUR_PAGE = 900;
    public static final int HAUTEUR_PAGE = 800;
    public static final double RAPPORT_ARTICLE_PAGE = 0.95;

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
        this.setBackground(Color.PINK);
        JButton boutonAjouter = new JButton("Ajouter");
        this.add(boutonAjouter);
        boutonAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel nomLabel = new JLabel("Nom : ");
                JTextField nomField = new JTextField();

                JLabel quantiteLabel = new JLabel("Quantité : ");
                JTextField quantiteField = new JTextField();

                JLabel prixLabel = new JLabel("Prix : ");
                JTextField prixField = new JTextField();

                JLabel descLabel = new JLabel("Description : ");
                JTextField descField = new JTextField();

                JLabel categorieLabel = new JLabel("Categorie : ");
                JComboBox<String> categorieComboBox = new JComboBox<String>();
                for (Categorie c : app.getStock().getArrayCategorie()) {
                    categorieComboBox.addItem(c.getNom());
                }

                Object[] message = {
                        nomLabel, nomField,
                        quantiteLabel, quantiteField,
                        prixLabel, prixField,
                        descLabel, descField,
                        categorieLabel, categorieComboBox
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Ajouter article",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    Categorie c = app.stringToCategorie(categorieComboBox.getSelectedItem().toString());
                    c.ajouteArticle(new Article(nomField.getText(), Double.parseDouble(prixField.getText()),
                            Integer.parseInt(quantiteField.getText()), "", descField.getText(), c));

                    panneauMenu.getSceneManager().getPage("Article").setVisible(false);
                    panneauMenu.getSceneManager().creePage("Article", true);
                    panneauMenu.getSceneManager().creePage("Avis", false);
                }
            }
        });

    }

    private JPanel creePanelArticle(Article article, App app) {
        JPanel panelArticle = new JPanel();
        panelArticle.setPreferredSize(new Dimension((int) (LARGEUR_PAGE * RAPPORT_ARTICLE_PAGE), 78));
        panelArticle.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelArticle.setBackground(Color.LIGHT_GRAY);

        JLabel labelInfo = new JLabel("Nom : " + article.getNom() + " | Prix : " + article.getPrix().toString() + "€"
                + " | Qté : " + article.getQuantite() + " | Catégorie : " + article.getNomCategorie());
        JPanel panelInfo = new JPanel();
        panelInfo.add(labelInfo, BorderLayout.CENTER);
        panelInfo.setBackground(Color.LIGHT_GRAY);
        panelInfo.setBorder(new LineBorder(Color.GRAY));

        JLabel labelDescription = new JLabel("Description : " + '"' + article.getDescription() + '"');
        labelDescription.setVerticalAlignment(JLabel.CENTER);
        JPanel panelDescription = new JPanel();
        panelDescription.add(labelDescription, BorderLayout.CENTER);
        panelDescription.setBackground(Color.LIGHT_GRAY);
        panelDescription.setBorder(new LineBorder(Color.GRAY));

        JButton boutonModifier = new JButton("Modifier");
        JPanel panelModifier = new JPanel();
        panelModifier.add(boutonModifier, BorderLayout.CENTER);
        panelModifier.setBackground(Color.LIGHT_GRAY);
        panelModifier.setBorder(new LineBorder(Color.GRAY));
        boutonModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.modifierArticle(article);
                panneauMenu.getSceneManager().getPage("Article").setVisible(false);
                panneauMenu.getSceneManager().creePage("Article", true);
                panneauMenu.getSceneManager().creePage("Avis", false);
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
                int result = JOptionPane.showConfirmDialog(null,
                        "Etes-vous sûr de vouloir supprimer l'article : " + article.getNom(),
                        "Supprimer l'article ?", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    app.supprimerArticle(article);
                    panneauMenu.getSceneManager().getPage("Article").setVisible(false);
                    panneauMenu.getSceneManager().creePage("Article", true);
                    panneauMenu.getSceneManager().creePage("Avis", false);
                }
            }
        });

        GridBagLayout gbl = new GridBagLayout();
        panelArticle.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.9;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelArticle.add(panelInfo, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelArticle.add(panelModifier, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.9;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelArticle.add(panelDescription, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelArticle.add(panelRetirer, gbc);

        return panelArticle;

    }

}
