package takeserveurcucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ServeurStepDefs extends ServeurSpring {


    @Given("^aucun client n est connecte$")
    public void aucun_client() throws Throwable{
        assertEquals(serveur.getNbClientReady(),0);
    }

    @When("^un client se connecte$")
    public void the_client_issues_GET_version() throws Throwable{
        serveur.acceptConnection();
        assertTrue(serveur.getNbClientReady()>0);
    }

    @Then("^il y a (\\d+) client$")
    public void the_client_receives_status_code_of(int arg1) throws Throwable {
        assertEquals(serveur.getNbClientReady(),arg1);
    }


}
