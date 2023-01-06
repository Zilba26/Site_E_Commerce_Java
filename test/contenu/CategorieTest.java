package test.contenu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

import src.app.App;

public class CategorieTest {

    @Test
    public void testCreationCategorie() {
        App app = new App();
        app.creerCategorie("Test");
        assertEquals(app.getStock().getArrayCategorie().get(0).getNom(), "Test");
    }
}
