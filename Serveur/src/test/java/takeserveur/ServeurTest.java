package takeserveur;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * C'est la classe test du serveur
 */
public class ServeurTest {

    Serveur serveur;

    @Before
    public void setup(){
        serveur = new Serveur(8080,"localhost",1);
    }
    @Test
    public void isPartiePrete() {
        assertFalse(serveur.isPartiePrete());
        serveur.setNbClientReady(1);
        serveur.setDebut(1);
        assertTrue(serveur.isPartiePrete());

    }


    @Test
    public void acceptConnection() {
        assertEquals(serveur.getNbClientReady(),0);
        String string = serveur.acceptConnection() + "";
        assertEquals(string, 1 + "");
        assertEquals(serveur.getNbClientReady(),1);
    }

    @Test
    public void type_bot() {
        assertEquals(serveur.getDebut(),0);
        String string = serveur.type_bot() + "";
        assertEquals(string, 1 + "");
        assertEquals(serveur.getDebut(),1);
    }
}