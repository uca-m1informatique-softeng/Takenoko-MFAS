package takenokoServeurCucumber;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.http.HttpStatus;
import takeserveur.Serveur;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static sun.nio.cs.Surrogate.is;


public class testStepDefs extends testSpring{


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
