package takenokocucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import javafx.geometry.Point3D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PoserIrrigationStepDefs extends PoserIrrigationSpring {
    @Given("^Le plateau de départ contient (\\d+) Parcelle$")
    public void plateau_avant(int arg1) throws Exception {
        deck.resetDeck();
        plateau.resetPlateau();
        assertEquals(arg1, plateau.getAllParcelle().size());
    }

    @When("^Je pose 2 parcelles$")
    public void je_pose_deux_parcelles_sur_le_plateau() throws Exception {
        init();
        assertTrue(plateau.getKeylist().size() == 3);
    }

    @Then("^Je pose l'irrigation sur les coordonées : <(\\d+).(\\d+) , (\\d+).(\\d+) , -(\\d+).(\\d+)>$")
    public void irrigation_posee(int arg1,int arg2,int arg3,int arg4,int arg5,int arg6) throws Exception {
        poser_irrigation();
        Double num1 = arg1+0.1*arg2;
        Double num2 = arg3+0.1*arg4;
        Double num3 = (arg5+0.1*arg6)*-1;
        Point3D point3D = plateau.getKeylistIrrigation().get(0);
        assertEquals((Double)point3D.getX(),num1);
        assertEquals((Double)point3D.getY(),num2);
        assertEquals((Double)point3D.getZ(), num3);
    }
}
