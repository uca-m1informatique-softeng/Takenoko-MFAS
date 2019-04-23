package takenokocucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PiocherParcelleStepDefs extends PiocherParcelleSpring {
    @Given("^Un deck de départ avec (\\d+) Parcelle$")
    public void Un_deck_de_départ_avec_Parcelle(int arg1) throws Exception {
        deck.resetDeck();
        assertEquals(arg1, deck.getDeckParcelles().size());
    }

    @When("^Je pioche une Parcelle$")
    public void je_pose_une_Parcelle_sur_le_Plateau() throws Exception {
        PiocherParcelle();
        assertTrue(deck.getDeckParcelles().size() < 27);
    }

    @Then("^Il y a (\\d+) Parcelles dans le deck$")
    public void il_y_a_Parcelles_sur_le_Plateau(int arg1) throws Exception {
        assertEquals(arg1, deck.getDeckParcelles().size());
    }
}
