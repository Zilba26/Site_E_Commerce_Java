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
import src.contenu.Categorie;

public class PanneauCategorie extends JPanel{
    
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
    }

    private JPanel creePanelCategorie(Categorie categorie, App app) {
        JPanel panelCategorie = new JPanel();
        panelCategorie.setPreferredSize(new Dimension((int) (LARGEUR_PAGE * RAPPORT_ARTICLE_PAGE), 50));
        panelCategorie.setBorder(BorderFactory.createEmptyBorder(MARGE_ENTRE_PANEL / 2, 0, MARGE_ENTRE_PANEL / 2, 0));
        panelCategorie.setBackground(Color.RED);

        JLabel nom = new JLabel(categorie.getNom());
        panelCategorie.add(nom, BorderLayout.WEST);

        JButton boutonRetirer = new JButton("Modifier");
        panelCategorie.add(boutonRetirer, BorderLayout.EAST);
        boutonRetirer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.modifierCategorie(categorie);
                // TODO : Trouver la bonne categorie et ouvrir fenetre pour la modifier
            }
        });

        return panelCategorie;
    }

}
