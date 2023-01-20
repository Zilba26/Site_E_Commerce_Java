package src.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.app.App;

public class PanneauMenu extends JPanel {

    private App app;
    public SceneManager sceneManager;
    public static final int LARGEUR_PAGE = 600;
    public static final int HAUTEUR_PAGE = 800;

    public PanneauMenu(App app, SceneManager sceneManager) {

        this.app = app;
        this.sceneManager = sceneManager;

        JPanel panelGlobal = new JPanel();

        JLabel labelBienvenu = new JLabel("Bienvenue " + this.app.getAdminConnecte().getNom());

        JButton boutonArticle = new JButton("Article");
        boutonArticle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sceneManager.setLocationFenetre(sceneManager.getPage("Menu").getLocation());
                sceneManager.getPage("Article").setLocation(sceneManager.getLocationFenetre());

                sceneManager.setSizeFenetre(sceneManager.getPage("Menu").getSize());
                sceneManager.getPage("Article").setSize(sceneManager.getSizeFenetre());

                sceneManager.showPanneau(boutonArticle.getText());
            }
        });

        JButton boutonAvis = new JButton("Avis");
        boutonAvis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sceneManager.setLocationFenetre(sceneManager.getPage("Menu").getLocation());
                sceneManager.getPage("Avis").setLocation(sceneManager.getLocationFenetre());

                sceneManager.setSizeFenetre(sceneManager.getPage("Menu").getSize());
                sceneManager.getPage("Avis").setSize(sceneManager.getSizeFenetre());

                sceneManager.showPanneau(boutonAvis.getText());
            }
        });

        JButton boutonCategorie = new JButton("Categorie");
        boutonCategorie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sceneManager.setLocationFenetre(sceneManager.getPage("Menu").getLocation());
                sceneManager.getPage("Categorie").setLocation(sceneManager.getLocationFenetre());

                sceneManager.setSizeFenetre(sceneManager.getPage("Menu").getSize());
                sceneManager.getPage("Categorie").setSize(sceneManager.getSizeFenetre());

                sceneManager.showPanneau(boutonCategorie.getText());
            }
        });

        

        JPanel panelPrincipal = new JPanel(new BorderLayout(5, 5));

        JPanel panelLabel = new JPanel();
        panelLabel.add(labelBienvenu, BorderLayout.CENTER);
        panelLabel.setBackground(Color.LIGHT_GRAY);
        panelPrincipal.add(panelLabel, BorderLayout.NORTH);

        JPanel panelBoutons = new JPanel();
        panelBoutons.add(boutonArticle);
        panelBoutons.add(boutonAvis);
        panelBoutons.add(boutonCategorie);
        panelBoutons.setBackground(Color.PINK);
        panelPrincipal.add(panelBoutons, BorderLayout.CENTER);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panelPrincipal.setAlignmentY(CENTER_ALIGNMENT);

        panelPrincipal.setBackground(Color.PINK);
        panelGlobal.add(panelPrincipal);
        panelGlobal.setBackground(Color.PINK);
        this.setBackground(Color.PINK);
        this.add(panelGlobal, BorderLayout.CENTER);

    }

    public SceneManager getSceneManager() {
        return this.sceneManager;
    }

    public App getApp() {
        return this.app;
    }

    public String getStatementForSQL(String statement) {
        char[] ch = statement.toCharArray();
        String ret="";
        for (int i = 0; i < ch.length - 1; i++) {
            if (!String.valueOf(ch[i]).equals("'")) {
                ret+=String.valueOf(ch[i]);
            }
            else {
                ret += "\\" + String.valueOf(ch[i]);
            }
        }
        return ret;
    }

}
