package src.app;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import src.contenu.Article;
import src.contenu.Avis;
import src.contenu.Categorie;
import src.contenu.Stock;
import src.gestion.Admin;
import src.gestion.BDD;
import src.ihm.SceneManager;

public class App {

    private BDD bdd;
    private Admin adminConnecte;
    private Stock stock;
    private SceneManager sceneManager;

    public App(SceneManager sceneManager) {
        this.stock = new Stock();
        this.sceneManager = sceneManager;
    }

    public BDD getBDD() {
        return this.bdd;
    }

    public Stock getStock() {
        return this.stock;
    }

    public boolean connexionAdmin(String email, String password) {
        try {

            ArrayList<Admin> listeAdmin = this.sceneManager.getListeAdmin();

            for (int i=0 ; i<listeAdmin.size() ; i++) {
                if (listeAdmin.get(i).getEmail().equals(email) && listeAdmin.get(i).getMdp().equals(password)) {
                    this.adminConnecte = listeAdmin.get(i);
                }
            }
        } catch (Exception e) {
            if (email.equals("test")) {
                this.adminConnecte = new Admin("Connexion Failed", email, password);
            }
        }

        return (this.adminConnecte == null);
    }

    public Admin getAdminConnecte() {
        return this.adminConnecte;
    }

    public boolean adminEstConnecte() {
        return !(adminConnecte == null);
    }

    public void creerArticle(String nom, double prix, int quantite, String photo, String description,
            Categorie categorie) {
        Article article = new Article(nom, prix, quantite, photo, description, categorie);
        String[] infosNewArticle = article.getInfoArticle();
        boolean articleAlreadyExist = false;

        for (int i = 0; i < this.stock.getArrayCategorie().size(); i++) {
            for (int k = 0; k < this.stock.getArrayCategorie().get(i).getArticles().size(); k++) {
                if (this.stock.getArrayCategorie().get(i).getArticles().get(k).getInfoArticle() == infosNewArticle) {
                    articleAlreadyExist = true;
                    // TODO : Message prévenant de l'échec de l'opération
                }
            }
        }

        if (!articleAlreadyExist) {
            boolean categorieEstTrouve = false;
            for (int i = 0; i < this.stock.getArrayCategorie().size(); i++) {
                if (this.stock.getArrayCategorie().get(i) == categorie) {
                    this.stock.getArrayCategorie().get(i).getArticles().add(article);
                    categorieEstTrouve = true;
                    // TODO : Message validant l'opération
                }
            }

            if (!categorieEstTrouve) {
                // TODO : Message prévenant de l'échec de l'opération | Variante
            }
        }
    }

    public void supprimerArticle(Article article) {
        boolean estTrouve = false;
        for (int i = 0; i < this.stock.getArrayCategorie().size(); i++) {
            for (int k = 0; k < this.stock.getArrayCategorie().get(i).getArticles().size(); k++) {
                if (this.stock.getArrayCategorie().get(i).getArticles().get(k) == article) {
                    this.stock.getArrayCategorie().get(i).getArticles().remove(k);
                    estTrouve = true;
                }
            }
        }

        if (estTrouve) {
            // TODO : Message validant l'opération
        } else {
            // TODO : Message prévenant de l'échec de l'opération
        }
    }

    public void supprimeAvis(Avis avisASupprimer) {
        for (Categorie categorie : this.getStock().getArrayCategorie())
            for (Article article : categorie.getArticles())
                for (Avis avis : article.getListeAvis())
                    if (avis.equals(avisASupprimer)) {
                        article.getListeAvis().remove(avis);
                        return;
                    }
    }

    public ArrayList<Article> getAllArticles() {
        ArrayList<Article> ret = new ArrayList<Article>();
        for (int k = 0; k < this.getStock().getArrayCategorie().size(); k++)
            for (int j = 0; j < this.getStock().getArrayCategorie().get(k).getArticles().size(); j++)
                ret.add(this.getStock().getArrayCategorie().get(k).getArticles().get(j));
        return ret;
    }

    public void supprimeCategorie(Categorie categorieASupprimer) {
        for (Categorie categorie : this.getStock().getArrayCategorie())
            if (categorie.equals(categorieASupprimer)) {
                this.getStock().getArrayCategorie().remove(categorieASupprimer);
                return;
            }
    }

    public String[] modifierArticle(Article article) {

        JLabel nomLabel = new JLabel("Nom de l'article :");
        JTextField nomField = new JTextField();
        nomField.setText(article.getNom());

        JLabel quantiteLabel = new JLabel("Quantité : ");
        JTextField quantiteField = new JTextField();
        quantiteField.setText("" + article.getQuantite());

        JLabel prixLabel = new JLabel("Prix : ");
        JTextField prixField = new JTextField();
        prixField.setText("" + article.getPrix());

        JLabel descLabel = new JLabel("Description : ");
        JTextField descField = new JTextField();
        descField.setText(article.getDescription());

        JLabel categorieLabel = new JLabel("Categorie : ");
        JComboBox<String> categorieComboBox = new JComboBox<>();
        for (Categorie c : this.getStock().getArrayCategorie()) {
            categorieComboBox.addItem(c.getNom());
        }
        categorieComboBox.setSelectedItem(article.getNomCategorie());

        Object[] message = {
                nomLabel, nomField,
                quantiteLabel, quantiteField,
                prixLabel, prixField,
                descLabel, descField,
                categorieLabel, categorieComboBox
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Modifier article",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            article.modifierArticle(nomField.getText(),
                    Integer.parseInt(quantiteField.getText()),
                    Double.parseDouble(prixField.getText()),
                    descField.getText(),
                    stringToCategorie(categorieComboBox.getSelectedItem().toString()));
        }

        String[] ret = new String[5];
        ret[0] = nomField.getText();
        ret[1] = quantiteField.getText();
        ret[2] = prixField.getText();
        ret[3] = descField.getText();
        ret[4] = categorieComboBox.getSelectedItem().toString();
        return ret;
    }

    public String[] modifierCategorie(Categorie categorie) {
        JLabel nomLabel = new JLabel("Nom : ");
        JTextField nomField = new JTextField();
        nomField.setText(categorie.getNom());
        JLabel categorieIDLabel = new JLabel("Catégorie ID (1 à 8) : ");
        JTextField categorieIDField = new JTextField();

        Object[] message = {
                nomLabel, nomField,
                categorieIDLabel, categorieIDField
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Modifier categorie",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            categorie.setNom(nomField.getText());
        }

        String[] ret = new String[2];
        ret[0] = nomField.getText();
        ret[1] = categorieIDField.getText();
        return ret;
    }

    public Categorie stringToCategorie(String strCat) {
        for (Categorie categorie : this.getStock().getArrayCategorie())
            if (categorie.getNom().equals(strCat))
                return categorie;
        return null; // Never happen
    }

    public boolean supprimerAvisClient(Article article, Avis avis, boolean gardeNote) {
        article.supprimerAvis(avis, gardeNote);
        return gardeNote;
    }

    public void creerAdmin(String nom, String email, String mdp) {
        // TODO : Ajoutez nouvel Admin sur BDD
    }

    public void creerCategorie(String nom) {
        boolean categorieAlreadyExist = false;
        for (int i = 0; i < this.stock.getArrayCategorie().size(); i++) {
            if (this.stock.getArrayCategorie().get(i).getNom() == nom) {
                categorieAlreadyExist = true;
            }
        }
        if (!categorieAlreadyExist) {
            this.stock.creerCategorie(nom);
            // TODO : Message validant l'opération
        } else {
            // TODO : Message prévenant de l'échec de l'opération
        }
    }

}