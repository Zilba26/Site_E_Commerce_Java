package test.contenu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

import src.contenu.Article;
import src.contenu.Avis;
import src.contenu.Categorie;

public class ArticleTest {
    
    double delta = 0;

    @Test
    public void testConstructeurArticle() {
        Article article = new Article("Fourchette", 50.4, 30, "Photo fourchette", "30 fourchettes en acier pour 50.4€", "Art de table");
        assertEquals(article.getNom(), "Fourchette");
        assertEquals(article.getPrix(), 50.4, delta);
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
        assertEquals(article.getPrix(), 50.4, delta);

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
        assertEquals(article.getPrix(), Double.parseDouble(infos[1]), delta);
        assertEquals(article.getQuantite(), Integer.parseInt(infos[2]));
        assertEquals(article.getPhoto(), infos[3]);
        assertEquals(article.getDescription(), infos[4]);
    }

    @Test
    public void testCalculNote() {
        Article article = new Article("Fourchette", 50.4, 30, "Photo fourchette", "30 fourchettes en acier pour 50.4€", "Art de table");
        assertEquals(article.calculNote(), 2.5, delta);
    
        Avis avis = new Avis(4, "Bon four", "Didier", "06/01/2023", article);
        article.getListeAvis().add(avis);
        assertEquals(article.calculNote(), 4, delta);
    
        avis = new Avis(2, "Four ok", "Franck", "06/01/2023", article);
        article.getListeAvis().add(avis);
        assertEquals(article.calculNote(), 3, delta);

    }

    @Test
    public void testSupprimerAvis() {
        Article article = new Article("Fourchette", 50.4, 30, "Photo fourchette", "30 fourchettes en acier pour 50.4€", "Art de table");
    
        Avis avis = new Avis(4, "Bon four", "Didier", "06/01/2023", article);
        article.getListeAvis().add(avis);
        Avis avis2 = new Avis(2, "Four ok", "Franck", "06/01/2023", article);
        article.getListeAvis().add(avis2);
        Avis avis3 = new Avis(1, "Four mauvais", "Charles", "06/01/2023", article);

        assertEquals(article.supprimerAvis(avis, true), "Le contenu de l'avis de Didier qui mentionnait 'Bon four' datant du 06/01/2023 noté 4.0/5.0 associé à l'article 'Fourchette'a été supprimé.");
        assertEquals(article.supprimerAvis(avis2, false), "L'avis de Franck qui mentionnait 'Four ok' datant du 06/01/2023 noté 2.0/5.0 associé à l'article 'Fourchette'a été supprimé.");
        assertEquals(article.supprimerAvis(avis3, true), "Attention. L'avis de Charles mentionnant 'Four mauvais' datant du 06/01/2023 noté 1.0/5.0 n'est pas associé à l'article 'Fourchette'.");
        assertEquals(article.supprimerAvis(avis3, false), "Attention. L'avis de Charles mentionnant 'Four mauvais' datant du 06/01/2023 noté 1.0/5.0 n'est pas associé à l'article 'Fourchette'.");
    
    }
}
