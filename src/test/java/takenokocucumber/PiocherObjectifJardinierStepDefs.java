package takenokocucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PiocherObjectifJardinierStepDefs extends PiocherObjectifJardinierSpring {
    @Given("^Un deck de départ avec (\\d+) objectifs jardiniers$")
    public void Un_deck_de_départ_avec_des_objectifs_jardiniers(int arg1) throws Exception {
        deck.resetDeck();
        assertEquals(arg1, deck.getDeckObjectifsJardinier().size());
    }

    @When("^Je pioche une objectif jardinier")
    public void je_pioche_un_objectif_jardinier() throws Exception {
        PiocherObjectifJardinier();
        assertTrue(deck.getDeckObjectifsJardinier().size() < 15);
    }

    @Then("^Il y a (\\d+) objectifs jardiniers dans le deck$")
    public void il_y_a_objectifs_jardiniers_le_deck(int arg1) throws Exception {
        assertEquals(arg1, deck.getDeckObjectifsJardinier().size());
    }
}
