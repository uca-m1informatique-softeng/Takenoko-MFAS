package takenoko.moteur;

import org.junit.Test;
import takenoko.moteur.Enums.TypeParcelle;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ParcelleTest {

    Parcelle parcelleRose = new Parcelle(TypeParcelle.ROSE);

    @Test
    public void pousserBambou(){
        assertTrue(parcelleRose.pousserBambou());
        int tailleBambou = parcelleRose.getNbBambou();
        assertEquals(tailleBambou,1);


        assertTrue(parcelleRose.pousserBambou());
        assertTrue(parcelleRose.pousserBambou());
        tailleBambou = parcelleRose.getListBambou().size();
        assertEquals(tailleBambou,3);

        assertTrue(parcelleRose.pousserBambou());
        assertFalse(parcelleRose.pousserBambou());
        assertFalse(parcelleRose.pousserBambou());
        tailleBambou = parcelleRose.getListBambou().size();
        assertEquals(tailleBambou,4);

    }

    @Test
    public void mangerBambou(){

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

}
