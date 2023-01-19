package src.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Point;
import java.awt.Window;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JFrame;

import src.app.App;
import src.contenu.Article;
import src.contenu.Avis;
import src.contenu.Categorie;
import src.gestion.Admin;

public class SceneManager {
    private JFrame pageMenu;
    private JFrame pageArticle;
    private JFrame pageAvis;
    private JFrame pageCategorie;
    private PanneauMenu panneauMenu;
    private App app;

    private ArrayList<Admin> listeAdmin;

    private Dimension sizeFenetre;
    private Point locationFenetre;

    private Connection connection;

    public SceneManager() {
        this.listeAdmin = new ArrayList<Admin>();
    }

    public static void main(String[] args) {

        SceneManager sceneManager = new SceneManager();
        sceneManager.pageMenu = new JFrame("Page d'administration du site");
        sceneManager.sizeFenetre = Toolkit.getDefaultToolkit().getScreenSize();
        sceneManager.pageMenu.setSize(sceneManager.sizeFenetre);
        sceneManager.setLocationFenetre(sceneManager.pageMenu.getLocation());

        sceneManager.app = new App(sceneManager);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");
            sceneManager.connection = con;
            String queryCategory = "SELECT * FROM subcategory";
            PreparedStatement statementCategory = con.prepareStatement(queryCategory);
            ResultSet resultCategory = statementCategory.executeQuery();

            while (resultCategory.next()) {
                sceneManager.app.creerCategorie(resultCategory.getString("Name"));
                String queryArticle = "SELECT * FROM item WHERE SubCategoryID = "
                        + resultCategory.getInt("SubCategoryID");
                PreparedStatement statementArticle = con.prepareStatement(queryArticle);
                ResultSet resultArticle = statementArticle.executeQuery();
                while (resultArticle.next()) {
                    String nom = resultArticle.getString("Name");
                    Double prix = resultArticle.getDouble("Price");
                    int quantite = resultArticle.getInt("Quantity");
                    String photo = resultArticle.getString("Picture");
                    String description = resultArticle.getString("Description");
                    sceneManager.app.getStock().getArrayCategorie().get(resultCategory.getInt("SubCategoryID") - 1)
                            .ajouterArticle(nom, prix, quantite, photo, description);
                    String queryAvis = "SELECT * FROM comment WHERE ItemID = "+ resultArticle.getInt("ItemID");
                    PreparedStatement statementAvis = con.prepareStatement(queryAvis);
                    ResultSet resultAvis = statementAvis.executeQuery();
                    while(resultAvis.next()) {
                        String IDClient = resultAvis.getString("UserID");
                        Article article = sceneManager.app.getStock().getArrayCategorie().get(resultCategory.getInt("SubCategoryID") - 1).getArticles().get(sceneManager.app.getStock().getArrayCategorie().get(resultCategory.getInt("SubCategoryID") - 1).getArticles().size()-1);
                        double starRate = resultAvis.getDouble("StarRate");
                        String content = resultAvis.getString("Content");
                        Avis avis = new Avis(starRate, content, IDClient, "", article);
                        article.ajouterAvis(avis);
                    }
                }
            }

            String queryAdmin = "SELECT * FROM user";
            PreparedStatement statementAdmin = con.prepareStatement(queryAdmin);
            ResultSet resultAdmin = statementAdmin.executeQuery();
            while (resultAdmin.next()) {
                int rankAdmin = resultAdmin.getInt("admin");
                if (rankAdmin == 1) {
                    String nameBDD = resultAdmin.getString("FirstName");
                    String emailBDD = resultAdmin.getString("Email");
                    String passwordBDD = resultAdmin.getString("Password");
                    sceneManager.listeAdmin.add(new Admin(nameBDD, emailBDD, passwordBDD));
                }
            }

            System.out.println("Réussite de la connexion à la BDD");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Echec de la connexion à la BDD");
        }

        // Categorie[] categorieTest = sceneManager.initTestCategories();
        // sceneManager.app.getStock().ajouteCategorie(categorieTest[0]);
        // sceneManager.app.getStock().ajouteCategorie(categorieTest[1]);

        new PanneauConnexion(sceneManager.app);

        while (!sceneManager.app.adminEstConnecte()) {
            new PanneauConnexion(sceneManager.app);
        }
        sceneManager.initPages(sceneManager.app);
    }

    private Categorie[] initTestCategories() {
        Categorie categorie1 = new Categorie("Categorie 1");
        Article article1 = new Article("Article 11", 11d, 11, "", "Description 11", categorie1);
        Article article2 = new Article("Article 12", 12d, 12, "", "Description 12", categorie1);
        Categorie categorie2 = new Categorie("Categorie 2");
        Article article3 = new Article("Article 21", 21d, 21, "", "Description 21", categorie2);
        Article article4 = new Article("Article 22", 22d, 22, "", "Description 22", categorie2);

        Avis avis11 = new Avis(5, "Avis 11", "Client 1", "02/10/2023", article1);
        Avis avis12 = new Avis(4, "Avis 12", "Client 2", "05/11/2022", article1);
        Avis avis21 = new Avis(4.25, "Avis 21", "Client 3", "01/01/2001", article2);
        Avis avis22 = new Avis(0.75, "Avis 22", "Client 3", "02/02/2002", article2);
        Avis avis31 = new Avis(2, "Avis 31", "Client 5", "03/03/2003", article3);
        Avis avis32 = new Avis(3, "Avis 32", "Client 6", "04/04/2004", article3);
        Avis avis41 = new Avis(5, "Avis 41", "Client 7", "05/05/2005", article4);
        Avis avis42 = new Avis(3.25, "Avis 42", "Client 8", "06/06/2006", article4);
        article1.ajouterAvis(avis11);
        article1.ajouterAvis(avis12);
        article2.ajouterAvis(avis21);
        article2.ajouterAvis(avis22);
        article3.ajouterAvis(avis31);
        article3.ajouterAvis(avis32);
        article4.ajouterAvis(avis41);
        article4.ajouterAvis(avis42);

        categorie1.ajouteArticle(article1);
        categorie1.ajouteArticle(article2);
        categorie2.ajouteArticle(article3);
        categorie2.ajouteArticle(article4);
        Categorie[] ret = new Categorie[2];
        ret[0] = categorie1;
        ret[1] = categorie2;
        return ret;
    }

    private void initPages(App app) {
        this.panneauMenu = new PanneauMenu(app, this);

        this.creePage("Menu", true);
        this.creePage("Article", false);
        this.creePage("Avis", false);
        this.creePage("Categorie", false);

        this.WindowListenerMethod(this);

    }

    public JFrame getPageMenu() {
        return this.pageMenu;
    }

    public void showPanneau(String nomPanneau) {
        this.hidePanneau();
        switch (nomPanneau) {
            case "Article":
                this.pageArticle.setSize(this.getSizeFenetre());
                this.pageArticle.setLocation(this.getLocationFenetre());
                this.pageArticle.setVisible(true);
                break;
            case "Avis":
                this.pageAvis.setSize(this.getSizeFenetre());
                this.pageAvis.setLocation(this.getLocationFenetre());
                this.pageAvis.setVisible(true);
                break;
            case "Categorie":
                this.pageCategorie.setSize(this.getSizeFenetre());
                this.pageCategorie.setLocation(this.getLocationFenetre());
                this.pageCategorie.setVisible(true);
                break;
            case "Menu":
                this.setLocationFenetre(this.pageMenu.getLocation());
                this.pageMenu.setSize(this.getSizeFenetre());
                this.pageMenu.setLocation(this.getLocationFenetre());
                this.pageMenu.setVisible(true);
                break;
            default:
                this.pageMenu.setSize(this.getSizeFenetre());
                this.pageMenu.setLocation(this.getLocationFenetre());
                this.pageMenu.setVisible(true);
                break;
        }
    }

    private void hidePanneau() {
        this.pageArticle.setVisible(false);
        this.pageAvis.setVisible(false);
        this.pageCategorie.setVisible(false);
        this.pageMenu.setVisible(false);
    }

    public void deconnectAdmin() {
        this.pageMenu.dispose();
        this.pageArticle.dispose();
        this.pageAvis.dispose();
        this.pageCategorie.dispose();
        System.gc();

        SceneManager.main(null);
    }

    // TODO : Garder en mémoire la taille des fenetres pour les appliquer à toutes
    // lors d'un changement de fenetre

    public void creePage(String str, boolean visible) {
        switch (str) {
            case "Menu":
                this.pageMenu.add(this.panneauMenu);
                this.pageMenu.add(new PanneauBarreHeader(this, this.pageMenu), BorderLayout.NORTH);
                this.pageMenu.setVisible(visible);
                this.pageMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.pageMenu.setSize(this.getSizeFenetre());
                break;
            case "Article":
                this.pageArticle = new JFrame("Page de gestion des articles");
                this.pageArticle.add(new PanneauArticle(panneauMenu, app));
                this.pageArticle.setVisible(visible);
                this.pageArticle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.pageArticle.add(new PanneauBarreHeader(this, this.pageArticle), BorderLayout.NORTH);
                this.pageArticle.setSize(this.getSizeFenetre());
                break;
            case "Avis":
                this.pageAvis = new JFrame("Page de gestion des avis");
                this.pageAvis.add(new PanneauAvis(panneauMenu, app));
                this.pageAvis.setVisible(visible);
                this.pageAvis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.pageAvis.add(new PanneauBarreHeader(this, this.pageAvis), BorderLayout.NORTH);
                this.pageAvis.setSize(this.getSizeFenetre());
                break;
            case "Categorie":
                this.pageCategorie = new JFrame("Page de gestion des categories");
                this.pageCategorie.add(new PanneauCategorie(panneauMenu, app));
                this.pageCategorie.setVisible(visible);
                this.pageCategorie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.pageCategorie.add(new PanneauBarreHeader(this, this.pageCategorie), BorderLayout.NORTH);
                this.pageCategorie.setSize(this.getSizeFenetre());
                break;
            default:
                break;
        }
    }

    public JFrame getPage(String str) {
        JFrame ret;
        switch (str) {
            case "Menu":
                ret = this.pageMenu;
                break;
            case "Article":
                ret = this.pageArticle;
                break;
            case "Avis":
                ret = this.pageAvis;
                break;
            case "Categorie":
                ret = this.pageCategorie;
                break;
            default:
                ret = this.pageMenu;
                break;
        }
        return ret;
    }

    private void WindowListenerMethod(SceneManager sceneManager) {
        WindowListener wL = new WindowListener() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Seul cas utilisé ici
                try {
                    sceneManager.getConnectionBDD().close();
                    System.out.println("Succès de la fermeture de la BDD");
                } catch (Exception e2) {
                    e2.getStackTrace();
                    System.out.println("Erreur de la fermeture de la BDD");
                }
            }

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        };

        this.pageMenu.addWindowListener(wL);
        this.pageArticle.addWindowListener(wL);
        this.pageAvis.addWindowListener(wL);
        this.pageCategorie.addWindowListener(wL);
    }

    public ArrayList<Admin> getListeAdmin() {
        return this.listeAdmin;
    }

    public Dimension getSizeFenetre() {
        return this.sizeFenetre;
    }

    public void setSizeFenetre(Dimension newSize) {
        this.sizeFenetre = newSize;
    }

    public Point getLocationFenetre() {
        return this.locationFenetre;
    }

    public void setLocationFenetre(Point locationFenetre) {
        this.locationFenetre = locationFenetre;
    }

    public Connection getConnectionBDD() {
        return this.connection;
    }

}
