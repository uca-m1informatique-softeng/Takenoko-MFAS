/*
package takenoko.moteur.personnages;

import javafx.geometry.Point3D;
import takenoko.moteur.Parcelle;
import takenoko.moteur.Partie;
import takenoko.moteur.Plateau;
import org.junit.Test;

import static org.junit.Assert.*;
import takenoko.moteur.Enums.TypeParcelle;

*/
/**
 * La classe test du panda
 *//*

public class PandaTest {
    Partie partie=new Partie();
    Plateau plateau = partie.getPlateau();
    Parcelle parcelleVerte = new Parcelle(TypeParcelle.VERTE);

    @Test
    public void faireActionBambou() throws Exception {
        plateau.poser(new Parcelle(TypeParcelle.ROSE),new Point3D(0,1,-1));
        plateau.poser(new Parcelle(TypeParcelle.ROSE),new Point3D(1,0,-1));
        plateau.poser(new Parcelle(TypeParcelle.ROSE),new Point3D(1,-1,0));
        plateau.poser(parcelleVerte,new Point3D(1,1,-2));


        assertEquals(1,plateau.getParcelle(new Point3D(0,1,-1)).getNbBambou());
        assertFalse(partie.getPanda().faireActionBambou(new Point3D(1,1,-2)));

        boolean exp = partie.getPanda().faireActionBambou(new Point3D(0,1,-1));
        assertEquals(0,plateau.getParcelle(new Point3D(0,1,-1)).getNbBambou());
        assertTrue(exp);
    }

}*/
