package src.app;

import java.util.ArrayList;

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
            System.out.println("Email : " + email + " | Password : " + password);

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
            String nomCategorie) {
        Article article = new Article(nom, prix, quantite, photo, description, nomCategorie);
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
                if (this.stock.getArrayCategorie().get(i).getNom() == nomCategorie) {
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

    public void supprimeCategorie(Categorie categorieASupprimer) {
        for (Categorie categorie : this.getStock().getArrayCategorie())
            if (categorie.equals(categorieASupprimer)) {
                this.getStock().getArrayCategorie().remove(categorieASupprimer);
                return;
            }
    }

    public void modifierArticle(Article article) {
        // TODO : Affichage qui demande ce qu'il veut changer, par exemple le nom
        article.setNom("Test");
    }

    public void modifierCategorie(Categorie categorie) {
        // TODO : Affichage qui demande ce qu'il veut changer, par exemple le nom
        categorie.setNom("Test");
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