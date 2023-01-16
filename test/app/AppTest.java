package test.app;

import src.app.App;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class AppTest {

    @Test
    public void testConstructeurApp() {
        App app = new App();
        assertTrue(app!=null);
    }
}
