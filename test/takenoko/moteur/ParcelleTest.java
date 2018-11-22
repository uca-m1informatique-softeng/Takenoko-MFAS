/*
package takenoko.moteur;

import org.junit.Test;
import org.junit.Before;
import takenoko.moteur.Enums.TypeParcelle;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

*/
/**
 * La classe test des parcelles
 *//*


public class ParcelleTest {

    //Parcelle parcelleRose = new Parcelle(TypeParcelle.ETANG);
    Parcelle parcelleRose = new Parcelle();

    @Before
    public void setUp() {
        parcelleRose.setType(TypeParcelle.ROSE);
        parcelleRose.setIrriguee(false);
        parcelleRose.setListBambou(new ArrayList<Bambou>());

    }
    @Test
    public void pousserBambou(){
        assertFalse(parcelleRose.pousserBambou());
        int tailleBambou = parcelleRose.getNbBambou();
        assertEquals(tailleBambou,0);


        parcelleRose.setIrriguee(true);
        assertTrue(parcelleRose.pousserBambou());
        assertTrue(parcelleRose.pousserBambou());
        tailleBambou = parcelleRose.getListBambou().size();
        assertEquals(tailleBambou,2);

        assertTrue(parcelleRose.pousserBambou());
        assertTrue(parcelleRose.pousserBambou());
        assertFalse(parcelleRose.pousserBambou());
        tailleBambou = parcelleRose.getListBambou().size();
        assertEquals(tailleBambou,4);

    }

    @Test
    public void mangerBambou(){
        parcelleRose.setIrriguee(true);

        assertFalse(parcelleRose.mangerBambou());
        int tailleBambou = parcelleRose.getListBambou().size();
        assertEquals(tailleBambou,0);

        parcelleRose.pousserBambou();
        parcelleRose.pousserBambou();

        assertTrue(parcelleRose.mangerBambou());
        tailleBambou = parcelleRose.getListBambou().size();
        assertEquals(tailleBambou,1);

        assertTrue(parcelleRose.mangerBambou());
        tailleBambou = parcelleRose.getListBambou().size();
        assertEquals(tailleBambou,0);
    }

}*/
