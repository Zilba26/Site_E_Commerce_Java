package src.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import src.app.App;
import src.contenu.Categorie;

public class PanneauCategorie extends JPanel {

    public static final int MARGE_ENTRE_PANEL = 30;
    public static final int LARGEUR_PAGE = 600;
    public static final int HAUTEUR_PAGE = 800;
    public static final double RAPPORT_ARTICLE_PAGE = 0.9;

    public PanneauCategorie(PanneauMenu panneauMenu, App app) {

        ArrayList<Categorie> listCategorie = app.getStock().getArrayCategorie();

        for (int k = 0; k < listCategorie.size(); k++) {
            this.add(creePanelCategorie(listCategorie.get(k), app, panneauMenu));
        }

        this.setBackground(Color.PINK);

        JPanel panelAjouter = new JPanel();
        panelAjouter.setPreferredSize(new Dimension((int) (LARGEUR_PAGE * RAPPORT_ARTICLE_PAGE), 78));
        panelAjouter.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelAjouter.setBackground(Color.LIGHT_GRAY);
        JButton boutonAjouter = new JButton("Ajouter");
        panelAjouter.add(boutonAjouter, BorderLayout.CENTER);
        this.add(panelAjouter);
        boutonAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel nomLabel = new JLabel("Nom : ");
                JTextField nomField = new JTextField();

                JLabel categorieLabel = new JLabel("Catégorie ID (1 à 8) : ");
                JTextField categorieField = new JTextField();

                Object[] message = {
                        nomLabel, nomField,
                        categorieLabel, categorieField
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Ajouter catégorie",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    
                    try {
                        String queryCountCategory = "SELECT COUNT(Name) FROM subcategory";
                        PreparedStatement statementCountCategory = panneauMenu.getSceneManager().getConnectionBDD().prepareStatement(queryCountCategory);
                        ResultSet resultCountCategory = statementCountCategory.executeQuery();
                        int numSubCategory=0;

                        if (resultCountCategory.next()) 
                            numSubCategory = resultCountCategory.getInt(1) + 1;

                        String queryCategory = "INSERT INTO `subcategory`(`SubCategoryID`, `Name`, `CategoryID`) VALUES ('"+numSubCategory+"','"+nomField.getText()+"','"+categorieField.getText()+"')";
                        PreparedStatement statementCategory = panneauMenu.getSceneManager().getConnectionBDD().prepareStatement(queryCategory);
                        statementCategory.executeUpdate();
                    }
                    catch (Exception exception) {
                        exception.printStackTrace();;
                    }

                    app.getStock().ajouteCategorie(new Categorie(nomField.getText()));

                }
                    panneauMenu.getSceneManager().setSizeFenetre(panneauMenu.getSceneManager().getPage("Categorie").getSize());
                    panneauMenu.getSceneManager().setLocationFenetre(panneauMenu.getSceneManager().getPage("Categorie").getLocation());
                    panneauMenu.getSceneManager().getPage("Categorie").setVisible(false);
                    panneauMenu.getSceneManager().creePage("Categorie", true);
                    panneauMenu.getSceneManager().creePage("Article", false);
                    panneauMenu.getSceneManager().creePage("Avis", false);
            }
        });

    }

    private JPanel creePanelCategorie(Categorie categorie, App app, PanneauMenu panneauMenu) {

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
                try {
                    String querySelectIDSubCategory = "SELECT `SubCategoryID` FROM `subcategory` WHERE Name = '"+categorie.getNom()+"'";
                    String[] newCategorieInfo = app.modifierCategorie(categorie);
                    PreparedStatement statementSelectIDSubCategory = panneauMenu.getSceneManager().getConnectionBDD().prepareStatement(querySelectIDSubCategory);
                    ResultSet resultSelectIDSubCategory = statementSelectIDSubCategory.executeQuery();
                    int numSubCategoryID = 1;
                    if (resultSelectIDSubCategory.next()) 
                        numSubCategoryID=resultSelectIDSubCategory.getInt("SubCategoryID");
                    String queryUpdateCategory = "UPDATE `subcategory` SET `Name`='"+newCategorieInfo[0]+"',`CategoryID`='"+newCategorieInfo[1]+"' WHERE `SubCategoryID`='"+numSubCategoryID+"'";
                    PreparedStatement statementUpdateCategory = panneauMenu.getSceneManager().getConnectionBDD().prepareStatement(queryUpdateCategory);
                    int resultUpdateCategory = statementUpdateCategory.executeUpdate();
                }
                catch (Exception exception2) {
                    exception2.printStackTrace();
                    System.out.println("Exception");
                }
                
                panneauMenu.getSceneManager()
                        .setSizeFenetre(panneauMenu.getSceneManager().getPage("Categorie").getSize());
                panneauMenu.getSceneManager()
                        .setLocationFenetre(panneauMenu.getSceneManager().getPage("Categorie").getLocation());
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

                    try {
                        String querySelectIDSubCategory = "SELECT `SubCategoryID` FROM `subcategory` WHERE Name = '"+categorie.getNom()+"'";
                        app.supprimeCategorie(categorie);
                        PreparedStatement statementSelectIDSubCategory = panneauMenu.getSceneManager().getConnectionBDD().prepareStatement(querySelectIDSubCategory);
                        ResultSet resultSelectIDSubCategory = statementSelectIDSubCategory.executeQuery();
                        int numSubCategoryID = 1;
                        if (resultSelectIDSubCategory.next()) 
                            numSubCategoryID=resultSelectIDSubCategory.getInt("SubCategoryID");

                        String queryDeleteAvis = "DELETE FROM comment WHERE ItemID IN (SELECT ItemID FROM item WHERE SubCategoryID = "+numSubCategoryID+");";
                        PreparedStatement statementDeleteAvis = panneauMenu.getSceneManager().getConnectionBDD().prepareStatement(queryDeleteAvis);
                        int resultDeleteAvis = statementDeleteAvis.executeUpdate();

                        String queryDeleteItems = "DELETE FROM `item` WHERE SubCategoryID = '"+numSubCategoryID+"'";
                        PreparedStatement statementDeleteItems = panneauMenu.getSceneManager().getConnectionBDD().prepareStatement(queryDeleteItems);
                        int resultDeleteItems = statementDeleteItems.executeUpdate();
                        
                        String queryUpdateCategory = "DELETE FROM `subcategory` WHERE SubCategoryID = '"+numSubCategoryID+"'";
                        PreparedStatement statementUpdateCategory = panneauMenu.getSceneManager().getConnectionBDD().prepareStatement(queryUpdateCategory);
                        int resultUpdateCategory = statementUpdateCategory.executeUpdate();
                    }
                    catch (Exception exception2) {
                        exception2.printStackTrace();
                        System.out.println("Exception");
                    }

                    panneauMenu.getSceneManager()
                            .setSizeFenetre(panneauMenu.getSceneManager().getPage("Categorie").getSize());
                    panneauMenu.getSceneManager()
                            .setLocationFenetre(panneauMenu.getSceneManager().getPage("Categorie").getLocation());
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
