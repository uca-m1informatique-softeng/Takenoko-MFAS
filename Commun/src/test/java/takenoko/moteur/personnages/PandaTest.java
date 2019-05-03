package takenoko.moteur.personnages;

import javafx.geometry.Point3D;
import org.junit.Test;

import static org.junit.Assert.*;
import takenoko.moteur.*;

/**
 * La classe test du panda
 */
public class PandaTest {
    Partie partie;
    Plateau plateau ;
    Parcelle parcelleVerte ;
    Parcelle parcelleRose;

    public void setup(){
        this.partie = new Partie();
        this.plateau = partie.getPlateau();
        this.parcelleVerte = new Parcelle();
        parcelleVerte.setIrriguee(false);
        parcelleVerte.setType(Enums.TypeParcelle.VERTE);
        this.parcelleRose = new Parcelle();
        parcelleRose.setIrriguee(false);
        parcelleRose.setType(Enums.TypeParcelle.VERTE);

    }

    @Test
    public void faireActionBambou() throws Exception {
        setup();
        plateau.poser(parcelleRose,new Point3D(0,1,-1));
        plateau.poser(parcelleRose,new Point3D(1,0,-1));
        plateau.poser(parcelleRose,new Point3D(1,-1,0));
        plateau.poser(parcelleVerte,new Point3D(1,1,-2));


        assertEquals(1,plateau.getParcelle(new Point3D(0,1,-1)).getNbBambou());
        assertFalse(partie.getPanda().faireActionBambou(new Point3D(1,1,-2)));

        boolean exp = partie.getPanda().faireActionBambou(new Point3D(0,1,-1));
        assertEquals(0,plateau.getParcelle(new Point3D(0,1,-1)).getNbBambou());
        assertTrue(exp);
    }

}