package src.contenu;

import java.util.ArrayList;

public class Categorie {
    private String nom;
    private ArrayList<Article> articles;

    // Getters
    public String getNom() {
        return nom;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    // Constructor
    public Categorie(String nom) {
        this.nom = nom;
        this.articles = new ArrayList<Article>();
    }

    // Methods
    public void supprimeArticle(Article article) {
        this.getArticles().remove(this.articles.indexOf(article));
        article.setNomCategorie(null);
        // TODO : Update Database
    }

    public void ajouterArticle(String nom, double prix, int quantite, String photo, String description) {
        Article article = new Article(nom, prix, quantite, photo, description, this.getNom());
        this.getArticles().add(article);
    }
    public void ajouterArticle(Article a){
        this.articles.add(a);
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void ajouteArticle(Article article){
        this.articles.add(article);
    }

}
