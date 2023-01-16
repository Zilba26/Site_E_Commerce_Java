package src.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import src.app.App;
import src.contenu.Article;

public class PanneauArticle extends JPanel{
    
    private PanneauMenu panneauMenu;
    private App app;
    public static final int MARGE_ENTRE_PANEL = 30;
    public static final int LARGEUR_PAGE = 600;
    public static final int HAUTEUR_PAGE = 800;

    public PanneauArticle(PanneauMenu panneauMenu, App app) {
        this.panneauMenu = panneauMenu;
        this.app = app;
        JPanel header = new JPanel();
        JButton boutonReturn = new JButton("Menu");
        header.add(boutonReturn);
        boutonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panneauMenu.sceneManager.showPanneau(boutonReturn.getText());
            }
        });
        this.add(header,BorderLayout.NORTH);


        ArrayList<Article> listArticle = new ArrayList<Article>();
        for(int k = 0 ; k <  app.getStock().getArrayCategorie().size() ; k++)
            for(int i = 0 ; i < app.getStock().getArrayCategorie().get(k).getArticles().size() ; i++)
                listArticle.add(app.getStock().getArrayCategorie().get(k).getArticles().get(i)); // Liste de tous les articles
        for(int k = 0 ; k < listArticle.size() ; k++){
            this.add(creePanelArticle(listArticle.get(k)));
        }
        
    }

    private JPanel creePanelArticle(Article article){
        JPanel panelArticle = new JPanel();
        panelArticle.setBorder(BorderFactory.createEmptyBorder(MARGE_ENTRE_PANEL/2, 0, MARGE_ENTRE_PANEL/2, 0));
        panelArticle.setBackground(Color.RED);

        return panelArticle;
    }

}
