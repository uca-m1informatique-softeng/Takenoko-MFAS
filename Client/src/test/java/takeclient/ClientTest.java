package takeclient;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {

    Client client;

    @Before
    public void setup(){
        client = new Client(8080,"localhost",8088,"localhost");
    }
    @Test
    public void panda() {
        assertEquals(client.getBot(),4);
        assertEquals((int) client.panda(),1);
        assertEquals(client.getBot(),1);
    }

    @Test
    public void jardinier() {
        assertEquals(client.getBot(),4);
        assertEquals((int) client.jardinier(),2);
        assertEquals(client.getBot(),2);
    }

    @Test
    public void parcelle() {
        assertEquals(client.getBot(),4);
        assertEquals((int) client.parcelle(),3);
        assertEquals(client.getBot(),3);
    }

    @Test
    public void random() {
        assertEquals(client.getBot(),4);
        assertEquals((int) client.random(),4);
        assertEquals(client.getBot(),4);
    }

    @Test
    public void type() {
        assertFalse(client.getType());
        String string = client.type();
        assertTrue(client.getType());
        assertEquals(string,4 + "");

    }
}