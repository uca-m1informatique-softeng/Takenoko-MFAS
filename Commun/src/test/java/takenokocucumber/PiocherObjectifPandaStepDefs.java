package takenokocucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PiocherObjectifPandaStepDefs extends PiocherObjectifPandaSpring {
    @Given("^Un deck de départ avec (\\d+) objectifs pandas$")
    public void Un_deck_de_départ_avec_des_objectifs_pandas(int arg1) throws Exception {
        deck.resetDeck();
        assertEquals(arg1, deck.getDeckObjectifsPanda().size());
    }

    @When("^Je pioche une objectif panda")
    public void je_pioche_un_objectif_panda() throws Exception {
        PiocherObjectifPanda();
        assertTrue(deck.getDeckObjectifsPanda().size() < 15);
    }

    @Then("^Il y a (\\d+) objectifs pandas dans le deck$")
    public void il_y_a_objectifs_panda_le_deck(int arg1) throws Exception {
        assertEquals(arg1, deck.getDeckObjectifsPanda().size());
    }
}
