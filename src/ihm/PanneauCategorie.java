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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import src.app.App;
import src.contenu.Categorie;

public class PanneauCategorie extends JPanel {

    private PanneauMenu panneauMenu;
    private App app;
    public static final int MARGE_ENTRE_PANEL = 30;
    public static final int LARGEUR_PAGE = 600;
    public static final int HAUTEUR_PAGE = 800;
    public static final double RAPPORT_ARTICLE_PAGE = 0.9;

    public PanneauCategorie(PanneauMenu panneauMenu, App app) {
        this.panneauMenu = panneauMenu;
        this.app = app;

        ArrayList<Categorie> listCategorie = app.getStock().getArrayCategorie();

        for (int k = 0; k < listCategorie.size(); k++) {
            this.add(creePanelCategorie(listCategorie.get(k), app));
        }

        JButton boutonAjouter = new JButton("Ajouter");
        this.add(boutonAjouter);
        boutonAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel nomLabel = new JLabel("Nom : ");
                JTextField nomField = new JTextField();

                Object[] message = {
                        nomLabel, nomField
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Ajouter catégorie",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    app.getStock().ajouteCategorie(new Categorie(nomField.getText()));
                    panneauMenu.getSceneManager().getPage("Categorie").setVisible(false);
                    panneauMenu.getSceneManager().creePage("Categorie", true);
                    panneauMenu.getSceneManager().creePage("Article", false);
                    panneauMenu.getSceneManager().creePage("Avis", false);
                }
            }
        });
    }

    private JPanel creePanelCategorie(Categorie categorie, App app) {
        JPanel panelCategorie = new JPanel();
        panelCategorie.setPreferredSize(new Dimension((int) (LARGEUR_PAGE * RAPPORT_ARTICLE_PAGE), 78));
        panelCategorie.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelCategorie.setBackground(Color.LIGHT_GRAY);

        JLabel labelInfo = new JLabel("Catégorie : " + categorie.getNom());
        JPanel panelInfo = new JPanel();
        panelInfo.add(labelInfo, BorderLayout.CENTER);
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
                app.modifierCategorie(categorie);
                panneauMenu.getSceneManager().getPage("Categorie").setVisible(false);
                panneauMenu.getSceneManager().creePage("Categorie", true);
                panneauMenu.getSceneManager().creePage("Article", false);
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
                        "Etes-vous sûr de vouloir supprimer la catégorie : " + categorie.getNom(),
                        "Supprimer la catégorie ?", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    app.supprimeCategorie(categorie);
                    panneauMenu.getSceneManager().getPage("Categorie").setVisible(false);
                    panneauMenu.getSceneManager().creePage("Categorie", true);
                    panneauMenu.getSceneManager().creePage("Article", false);
                    panneauMenu.getSceneManager().creePage("Avis", false);
                }
            }
        });

        GridBagLayout gbl = new GridBagLayout();
        panelCategorie.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.8;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCategorie.add(panelInfo, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelCategorie.add(panelModifier, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.1;
        gbc.gridx = 2;
        gbc.gridy = 0;
        panelCategorie.add(panelRetirer, gbc);

        return panelCategorie;
    }

}
