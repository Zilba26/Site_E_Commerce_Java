package test.contenu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

import src.app.App;
import src.contenu.Article;
import src.contenu.Categorie;

public class CategorieTest {

    @Test
    public void testConstructeurCategorie() {
        Categorie categorie = new Categorie("Art de table");
        assertEquals(categorie.getNom(), "Art de table");
    }

    @Test
    public void testSetterCategorie() {
        Categorie categorie = new Categorie("Art de table");
        categorie.setNom("Electromenager");
        assertEquals(categorie.getNom(), "Electromenager");
    }

    @Test
    public void ajouterArticle() {
        Categorie categorie = new Categorie("Art de table");
        Article article = new Article("Fourchette", 50.4, 30, "Photo fourchette", "30 fourchettes en acier pour 50.4€", "Art de table");
        categorie.ajouterArticle("Fourchette", 50.4, 30, "Photo fourchette", "30 fourchettes en acier pour 50.4€");
        assertEquals(categorie.getArticles().get(0).getInfoArticle(), article.getInfoArticle());
    }

    @Test
    public void supprimerArticle() {
        Categorie categorie = new Categorie("Art de table");
        categorie.ajouterArticle("Fourchette", 50.4, 30, "Photo fourchette", "30 fourchettes en acier pour 50.4€");
        categorie.supprimeArticle(categorie.getArticles().get(0));
        assertEquals(categorie.getArticles().size(), 0);
    }
}
