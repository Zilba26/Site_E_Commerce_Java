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

public class App {

    private BDD bdd;
    private Admin adminConnecte;
    private Stock stock;

    public App() {
        this.stock = new Stock();
    }

    public BDD getBDD() {
        return this.bdd;
    }

    public Stock getStock() {
        return this.stock;
    }

    public boolean connexionAdmin(String email, String mdp) {
        // TODO
        // Si infos en paramètre correspondent à un admin dans la BDD
        // this.adminConnecte = new Admin( -Nom correspondant aux infos paramètres- )
        // Changement de fenètre ayant accès au Stock
        // Sinon
        // Message d'erreur, pas de changement de fenètre
        if (email.equals("test")) {
            this.adminConnecte = new Admin("Nom", email, mdp);
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

    public void modifierArticle(Article article) {

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
    }

    public void modifierCategorie(Categorie categorie) {
        JLabel nomLabel = new JLabel("Nom : ");
        JTextField nomField = new JTextField();
        nomField.setText(categorie.getNom());

        Object[] message = {
                nomLabel, nomField
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Modifier categorie",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            categorie.setNom(nomField.getText());

        }

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