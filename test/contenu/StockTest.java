package test.contenu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import org.junit.Test;

import src.contenu.Categorie;
import src.contenu.Stock;

public class StockTest {
    
    @Test
    public void testConstructeurStock() {
        Stock stock = new Stock();
        assertTrue(stock.getArrayCategorie()!=null);
    }

    @Test
    public void testGetAndCreerCategorie() {
        Stock stock = new Stock();
        stock.creerCategorie("Test");
        ArrayList<Categorie> categorie = stock.getArrayCategorie();
        assertEquals(categorie.get(0).getNom(), "Test");
        assertEquals(stock.getArrayCategorie().get(0).getNom(), "Test");
    }
}
