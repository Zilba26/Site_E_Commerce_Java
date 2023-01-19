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

        JLabel labelBienvenu = new JLabel("Bienvenue " + this.app.getAdminConnecte().getNom());

        JButton boutonArticle = new JButton("Article");
        this.add(boutonArticle, BorderLayout.CENTER);
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
        this.add(boutonAvis, BorderLayout.CENTER);
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
        this.add(boutonCategorie, BorderLayout.CENTER);
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
        panelLabel.setBackground(Color.CYAN); // Label en CYAN
        panelPrincipal.add(panelLabel, BorderLayout.NORTH);

        JPanel panelBoutons = new JPanel();
        panelBoutons.setBackground(Color.BLACK); // Bouton en BLACK
        panelBoutons.add(boutonArticle);
        panelBoutons.add(boutonAvis);
        panelBoutons.add(boutonCategorie);
        panelPrincipal.add(panelBoutons, BorderLayout.CENTER);
        panelPrincipal.setBackground(Color.RED); // Principal en RED
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panelPrincipal.setAlignmentY(CENTER_ALIGNMENT);

        this.add(panelPrincipal);
        this.setBackground(Color.MAGENTA);
    }

    public SceneManager getSceneManager() {
        return this.sceneManager;
    }

    public App getApp() {
        return this.app;
    }

}
