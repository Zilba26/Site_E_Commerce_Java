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
        articles.remove(articles.indexOf(article));
        // TODO : Remove from Database
    }

}
