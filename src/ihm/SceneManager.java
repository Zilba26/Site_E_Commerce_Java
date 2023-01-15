package src.ihm;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import src.contenu.Article;
import src.contenu.Categorie;
import src.gestion.BDD;

public class SceneManager extends Application{
    private Stage stage;
    private BDD bdd;
    private Scene loginScene;
    private Scene articleListScene;
    private ListView<Article> articleListView;
    private ObservableList<Article> articlesData;
    private ArrayList<Categorie> categories;

    public SceneManager(Stage stage) {
        this.stage = stage;
        this.bdd = new BDD();
        // Initialiser les différentes scènes (loginScene, articleListScene)
        this.articleListView = new ListView<>();
        this.articlesData = FXCollections.observableArrayList();
        this.articleListView.setItems(articlesData);
        this.categories = new ArrayList<Categorie>();
        // Ajouter la ListView à la scène articleListScene
    }

    public void ajouteCategorie(Categorie cat){
        categories.add(cat);
    }

    public void showLoginScene() {
        stage.setScene(loginScene);
        stage.show();
    }

    public void start(Stage stage) {
        try {
            System.out.println("Connextion à la BDD OK");
            ArrayList<Article> articles = categories.get(0).getArticles();
            this.articlesData.setAll(articles);
            stage.setScene(articleListScene);
            stage.show();
        } catch(Exception e) {
            // Afficher un message d'erreur de connexion
            System.out.println("Erreur de connexion à la BDD");
        }
    }

    public static void main(String[] args) {
        Article article1 = new Article("Article 1", 0, 5,
        "lien", "Description de l'article 1", 
        "Categorie1");

        Categorie categorie1 = new Categorie("Categorie1");
        categorie1.ajouteArticle(article1);

        SceneManager sceneManager = new SceneManager(new Stage());
        sceneManager.ajouteCategorie(categorie1);
        launch(args);
    }
}
