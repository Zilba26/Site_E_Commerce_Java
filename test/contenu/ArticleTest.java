package test.contenu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

import src.contenu.Article;
import src.contenu.Categorie;

public class ArticleTest {
    
    @Test
    public void testConstructeurArticle() {
        Article article = new Article("Fourchette", 50.4, 30, "Photo fourchette", "30 fourchettes en acier pour 50.4€", "Art de table");
        assertEquals(article.getNom(), "Fourchette");
        assertEquals(article.getPrix(), new Double(50.4));
        assertEquals(article.getQuantite(), 30);
        assertEquals(article.getPhoto(), "Photo fourchette");
        assertEquals(article.getDescription(), "30 fourchettes en acier pour 50.4€");
        assertEquals(article.getNomCategorie(), "Art de table");
        assertEquals(article.getNombreNoteClient(), 0);
    }

    @Test
    public void testSetterArticle() {
        Article article = new Article("Cuillère", 24.2, 80, "Photo cuillère", "80 cuillère en bois pour 24.2€", "Table");
        
        article.setNom("Fourchette");
        assertEquals(article.getNom(), "Fourchette");

        article.setPrix(50.4);
        assertEquals(article.getPrix(), new Double(50.4));

        article.setPhoto("Photo fourchette");
        assertEquals(article.getPhoto(), "Photo fourchette");

        article.setDescription("30 fourchettes en acier pour 50.4€");
        assertEquals(article.getDescription(), "30 fourchettes en acier pour 50.4€");

        article.setNomCategorie("Art de table");
        assertEquals(article.getNomCategorie(), "Art de table");
    }

    @Test
    public void testAjouterQuantite() {
        Article article = new Article("Fourchette", 50.4, 30, "Photo fourchette", "30 fourchettes en acier pour 50.4€", "Art de table");

        article.ajouterQuantite(30);
        assertEquals(article.getQuantite(), 60);

        article.ajouterQuantite(0);
        assertEquals(article.getQuantite(), 60);

        article.ajouterQuantite(-30);
        assertEquals(article.getQuantite(), 60);
    }

    @Test
    public void testRetirerQuantite() {
        Article article = new Article("Fourchette", 50.4, 30, "Photo fourchette", "30 fourchettes en acier pour 50.4€", "Art de table");

        article.retirerQuantite(10);
        assertEquals(article.getQuantite(), 20);

        article.ajouterQuantite(0);
        assertEquals(article.getQuantite(), 20);

        article.ajouterQuantite(-30);
        assertEquals(article.getQuantite(), 20);
    }

    @Test
    public void testGetInfoArticle() {
        Article article = new Article("Fourchette", 50.4, 30, "Photo fourchette", "30 fourchettes en acier pour 50.4€", "Art de table");
        String[] infos = article.getInfoArticle();

        assertEquals(article.getNom(), infos[0]);
        assertEquals(article.getPrix(), new Double(infos[1]));
        assertEquals(article.getQuantite(), Integer.parseInt(infos[2]));
        assertEquals(article.getPhoto(), infos[3]);
        assertEquals(article.getDescription(), infos[4]);
    }
}
