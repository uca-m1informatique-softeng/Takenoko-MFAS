package takenokocucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import javafx.geometry.Point3D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PoserPandaStepDefs extends PoserPandaSpring {


    @Given("^La parcelle posée a (\\d+) bambou$")
    public void parcelle_posee(int arg1) throws Exception {
        plateau.resetPlateau();
        init();
        assertEquals(arg1, plateau.getParcelle(new Point3D(1,0,-1)).getNbBambou());
    }

    @When("^Je déplace le panda vers la parcelle$")
    public void deplacement_panda() throws Exception {
        deplacer_panda();
        assertTrue(plateau.getParcelle(new Point3D(1,0,-1)).getNbBambou()<1);
    }

    @Then("^Il y a (\\d+) bambou sur la parcelle$")
    public void plateau_apres_deplacement(int arg1) throws Exception {
        assertEquals(arg1, plateau.getParcelle(new Point3D(1,0,-1)).getNbBambou());
    }

}

/*
public class PoserParcelleStepDefs extends PoserParcelleSpring{
    Plateau plateau = Plateau.getInstance();
    Parcelle parcelle = new Parcelle();

    @Given("^Un Plateau de départ avec (\\d+) Parcelle$")
    public void un_Plateau_de_départ_avec_Parcelle(int arg1) throws Exception {

        plateau.resetPlateau();
        assertEquals(arg1, plateau.getKeylist().size());
    }

    @When("^Je pose une Parcelle sur le Plateau$")
    public void je_pose_une_Parcelle_sur_le_Plateau() throws Exception {
        plateau.resetPlateau();
        plateau.poser(parcelle ,new Point3D(1,0,-1));
        assertTrue(plateau.getKeylist().size() != 1);
    }

    @Then("^Il y a (\\d+) Parcelles sur le Plateau$")
    public void il_y_a_Parcelles_sur_le_Plateau(int arg1) throws Exception {
        assertEquals(arg1, plateau.getKeylist().size());
    }

}
*/


