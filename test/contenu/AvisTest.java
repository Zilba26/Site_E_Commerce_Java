package test.contenu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

import src.contenu.Article;
import src.contenu.Avis;

public class AvisTest {
    
    double delta = 0;

    @Test
    public void testConstructeurAvis4() {
        Article article = new Article("Fourchette", 50.4, 30, "Photo fourchette", "30 fourchettes en acier pour 50.4€", "Art de table");
        Avis avis = new Avis(5, "Richard", "06/01/2022", article);
        
        assertEquals(avis.getNote(), 5, delta);
        assertEquals(avis.getContenu(), null);
        assertEquals(avis.getNomClient(), "Richard");
        assertEquals(avis.getDate(), "06/01/2022");
        assertEquals(avis.getArticleAssocie(), article);
    }

    @Test
    public void testConstructeurAvis5() {
        Article article = new Article("Fourchette", 50.4, 30, "Photo fourchette", "30 fourchettes en acier pour 50.4€", "Art de table");
        Avis avis = new Avis(5, "Produit de qualité", "Richard", "06/01/2022", article);
        
        assertEquals(avis.getNote(), 5, delta);
        assertEquals(avis.getContenu(), "Produit de qualité");
        assertEquals(avis.getNomClient(), "Richard");
        assertEquals(avis.getDate(), "06/01/2022");
        assertEquals(avis.getArticleAssocie(), article);
    }

    @Test
    public void testSupprimeAvisBDD() {
        // TODO
    }
}
