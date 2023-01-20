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
import javax.swing.JComboBox;
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

        this.setBackground(Color.PINK);

        JButton boutonAjouter = new JButton("Ajouter");
        JPanel panelAjout = new JPanel();
        panelAjout.setPreferredSize(new Dimension((int) (LARGEUR_PAGE * RAPPORT_ARTICLE_PAGE), 50));
        panelAjout.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelAjout.setBackground(Color.LIGHT_GRAY);
        panelAjout.add(boutonAjouter);
        this.add(panelAjout);
        boutonAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel nomClientLabel = new JLabel("Nom du Client : ");
                JTextField nomClientField = new JTextField();

                JLabel dateLabel = new JLabel("Date (JJ/MM/AAAA) : ");
                JTextField dateField = new JTextField();

                JLabel contenuLabel = new JLabel("Texte : ");
                JTextField contenuField = new JTextField();

                JLabel noteLabel = new JLabel("Note : ");
                JTextField noteField = new JTextField();

                JLabel articleLabel = new JLabel("Article Associé : ");
                JComboBox<String> articleComboBox = new JComboBox<String>();
                for (Article a : getListeArticle()) {
                    articleComboBox.addItem(a.getNom());
                }

                Object[] message = {
                        nomClientLabel, nomClientField,
                        dateLabel, dateField,
                        contenuLabel, contenuField,
                        noteLabel, noteField,
                        articleLabel, articleComboBox
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Ajouter article",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    Article a = stringToArticle(articleComboBox.getSelectedItem().toString());
                    a.ajouterAvis(new Avis(Double.parseDouble(noteField.getText()), contenuField.getText(),
                            nomClientField.getText(),
                            dateField.getText(), a));

                            try {
                                String queryCountComment = "SELECT MAX(CommentID) FROM comment";
                                PreparedStatement statementCountComment = panneauMenu.getSceneManager().getConnectionBDD()
                                        .prepareStatement(queryCountComment);
                                ResultSet resultCountComment = statementCountComment.executeQuery();
                                int numComment = 0;
        
                                if (resultCountComment.next())
                                    numComment = resultCountComment.getInt("MAX(CommentID)") + 1;
        
                                String queryFindItemID = "SELECT `ItemID` FROM `item` WHERE `Name` = '"
                                        + articleComboBox.getSelectedItem().toString() + "'";
                                PreparedStatement statementFindItemID = panneauMenu.getSceneManager().getConnectionBDD()
                                        .prepareStatement(queryFindItemID);
                                ResultSet resultFindItemID = statementFindItemID.executeQuery();
                                int numFindItemID = 0;
        
                                if (resultFindItemID.next())
                                    numFindItemID = resultFindItemID.getInt("ItemID");
        
                                String queryAvis = "INSERT INTO `comment`(`CommentID`, `UserID`, `ItemID`, `StarRate`, `Content`) VALUES ('"+numComment+"','"+nomClientField.getText()+"','"+numFindItemID+"','"+noteField.getText()+"','"+panneauMenu.getStatementForSQL(contenuField.getText())+"')";
                                PreparedStatement statementAvis = panneauMenu.getSceneManager().getConnectionBDD()
                                        .prepareStatement(queryAvis);
                                statementAvis.executeUpdate();
        
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }

                    panneauMenu.getSceneManager()
                            .setSizeFenetre(panneauMenu.getSceneManager().getPage("Avis").getSize());
                    panneauMenu.getSceneManager()
                            .setLocationFenetre(panneauMenu.getSceneManager().getPage("Avis").getLocation());
                    panneauMenu.getSceneManager().getPage("Avis").setVisible(false);
                    panneauMenu.getSceneManager().creePage("Avis", true);
                    panneauMenu.getSceneManager().creePage("Article", false);
                }
            }
        });
    }

    private ArrayList<Article> getListeArticle() {
        ArrayList<Article> listArticle = new ArrayList<Article>();
        for (int k = 0; k < app.getStock().getArrayCategorie().size(); k++)
            for (int i = 0; i < app.getStock().getArrayCategorie().get(k).getArticles().size(); i++)
                listArticle.add(app.getStock().getArrayCategorie().get(k).getArticles().get(i)); // Liste d'articles
        return listArticle;
    }

    private JPanel creePanelAvis(Avis avis, App app) {
        JPanel panelAvis = new JPanel();
        panelAvis.setPreferredSize(new Dimension((int) (LARGEUR_PAGE * RAPPORT_ARTICLE_PAGE), 50));
        panelAvis.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelAvis.setBackground(Color.LIGHT_GRAY);

        // Infos à mettre : note contenu nomClient date articleAssocie
        JLabel labelInfo = new JLabel("Article : " + avis.getArticleAssocie().getNom() + " | Note : " + avis.getNote()
                + " | Contenu : " + avis.getContenu());

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

                try {
                    String querySelectCommentID = "SELECT `CommentID` FROM `comment` WHERE Content = '"
                            + panneauMenu.getStatementForSQL(avis.getContenu()) + "'";
                    String[] newAvisInfo = app.modifierAvis(avis);
                    if (Float.parseFloat(newAvisInfo[1]) <= 5 && Float.parseFloat(newAvisInfo[1]) >= 0 && (Float.parseFloat(newAvisInfo[1]) % 0.25) == 0) {
                        PreparedStatement statementSelectCommentID = panneauMenu.getSceneManager().getConnectionBDD().prepareStatement(querySelectCommentID);
                        ResultSet resultSelectCommentID = statementSelectCommentID.executeQuery();
                        int numIDComment = 1;
                        if (resultSelectCommentID.next())
                            numIDComment = resultSelectCommentID.getInt("CommentID");
                        String queryUpdateItem = "UPDATE `comment` SET `UserID`='" + newAvisInfo[0]+ "',`StarRate`='" + newAvisInfo[1] + "',`Content`='" + panneauMenu.getStatementForSQL(newAvisInfo[2]) + "' WHERE CommentID = '" + numIDComment + "'";
                        PreparedStatement statementUpdateItem = panneauMenu.getSceneManager().getConnectionBDD().prepareStatement(queryUpdateItem);
                        int resultUpdateItem = statementUpdateItem.executeUpdate();
                    }
                    else {
                        JOptionPane.showMessageDialog(null,
                                "La note de l'article doit être comprise entre 0 et 5 modulo 0.25");
                    }

                } catch (Exception exception2) {
                    exception2.printStackTrace();
                    System.out.println("Exception");
                }

                panneauMenu.getSceneManager().setSizeFenetre(panneauMenu.getSceneManager().getPage("Avis").getSize());
                panneauMenu.getSceneManager()
                        .setLocationFenetre(panneauMenu.getSceneManager().getPage("Avis").getLocation());
                getPanneauMenu().getSceneManager().getPage("Avis").setVisible(false);
                getPanneauMenu().getSceneManager().creePage("Avis", true);
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
                        "Etes-vous sûr de vouloir supprimer l'avis de " + avis.getNomClient(),
                        "Supprimer l'avis ?", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {

                    try {
                        String queryDeleteComment = "DELETE FROM `comment` WHERE Content = '"+panneauMenu.getStatementForSQL(avis.getContenu())+"' AND UserID = "+avis.getNomClient();
                        PreparedStatement statementDeleteComment = panneauMenu.getSceneManager().getConnectionBDD().prepareStatement(queryDeleteComment);
                        int resultDeleteComment = statementDeleteComment.executeUpdate();
                    }
                    catch (Exception e3) {
                        e3.printStackTrace();
                    }

                    app.supprimeAvis(avis);
                    panneauMenu.getSceneManager()
                            .setSizeFenetre(panneauMenu.getSceneManager().getPage("Avis").getSize());
                    panneauMenu.getSceneManager()
                            .setLocationFenetre(panneauMenu.getSceneManager().getPage("Avis").getLocation());
                    getPanneauMenu().getSceneManager().getPage("Avis").setVisible(false);
                    getPanneauMenu().getSceneManager().creePage("Avis", true);
                }
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

    private Article stringToArticle(String str) {
        ArrayList<Article> allArticles = this.app.getAllArticles();
        Article ret = null;
        for (int k = 0; k < allArticles.size(); k++)
            if (allArticles.get(k).getNom().equals(str))
                ret = allArticles.get(k);
        return ret;
    }

    public PanneauMenu getPanneauMenu() {
        return this.panneauMenu;
    }

    public App getApp() {
        return this.app;
    }
}
