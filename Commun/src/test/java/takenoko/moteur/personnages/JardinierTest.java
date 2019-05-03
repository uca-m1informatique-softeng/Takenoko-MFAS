package takenoko.moteur.personnages;

import javafx.geometry.Point3D;
import org.junit.Test;

import static org.junit.Assert.*;
import takenoko.moteur.Enums.TypeParcelle;
import takenoko.moteur.*;

import java.util.ArrayList;

/**
 * La classe test du jardinier
 */
public class JardinierTest {
    Partie partie;
    Plateau plateau ;
    Parcelle parcelleVerte ;
    Parcelle parcelleRose;
    Parcelle parcelleVerteIrrigueDestination;
    Parcelle parcelleVerteAdjacenteIrrigue ;
    Parcelle parcelleVerteNonAdjacenteIrrigue;
    Parcelle parcelleVerteAdjacenteNonIrrigue ;

    public void setup(){
        this.partie = new Partie();
        this.plateau = partie.getPlateau();
        this.parcelleVerte = new Parcelle();
        parcelleVerte.setIrriguee(false);
        parcelleVerte.setType(Enums.TypeParcelle.VERTE);
        this.parcelleRose = new Parcelle();
        parcelleRose.setIrriguee(false);
        parcelleRose.setType(TypeParcelle.ROSE);
        this.parcelleVerteAdjacenteIrrigue = new Parcelle();
        parcelleVerteAdjacenteIrrigue.setIrriguee(true);
        parcelleVerteAdjacenteIrrigue.setType(Enums.TypeParcelle.VERTE);
        this.parcelleVerteAdjacenteNonIrrigue = new Parcelle();
        parcelleVerteAdjacenteNonIrrigue.setIrriguee(false);
        parcelleVerteAdjacenteNonIrrigue.setType(Enums.TypeParcelle.VERTE);
        this.parcelleVerteIrrigueDestination =new Parcelle();
        parcelleVerteIrrigueDestination.setIrriguee(true);
        parcelleVerteIrrigueDestination.setType(Enums.TypeParcelle.VERTE);
        this.parcelleVerteNonAdjacenteIrrigue =new Parcelle();
        parcelleVerteNonAdjacenteIrrigue.setIrriguee(true);
        parcelleVerteNonAdjacenteIrrigue.setType(Enums.TypeParcelle.VERTE);

    }

    @Test
    public void ouPousserBambou() throws Exception{
        setup();
        plateau.poser(parcelleRose,new Point3D(0,1,-1));
        plateau.poser(parcelleRose,new Point3D(1,0,-1));
        plateau.poser(parcelleRose,new Point3D(1,-1,0));

        ArrayList<Point3D> exp = partie.getJardinier().ouPousserBambou(new Point3D(0,1,-1));
        assertEquals(2,exp.size());
    }

    @Test
    public void ouPousserBambou2() throws Exception {
        setup();
        plateau.resetPlateau();

        plateau.poser(parcelleVerteIrrigueDestination, new Point3D(1, 0, -1));
        plateau.poser(parcelleVerteAdjacenteIrrigue, new Point3D(2, 0, -2));
        plateau.poser(parcelleVerteNonAdjacenteIrrigue, new Point3D(3, 0, -3));
        plateau.poser(parcelleRose, new Point3D(1, -1, 0));
        plateau.poser(parcelleVerteAdjacenteNonIrrigue, new Point3D(1, 1, -2));

        ArrayList<Point3D> listDestinationAttendue = partie.getJardinier().ouPousserBambou(new Point3D(1, 0, -1));
        assertTrue(listDestinationAttendue.contains(new Point3D(1, 0, -1)));
        assertTrue(listDestinationAttendue.contains(new Point3D(2, 0, -2)));
        assertFalse(listDestinationAttendue.contains(new Point3D(3, 0, -3)));
        assertFalse(listDestinationAttendue.contains(new Point3D(1, 1, -2)));
        assertFalse(listDestinationAttendue.contains(new Point3D(1, -1, 0)));

    }


    @Test
    public void faireActionbambou() throws Exception{
        setup();
        plateau.resetPlateau();
        plateau.poser(parcelleRose,new Point3D(0,1,-1));
        plateau.poser(parcelleRose,new Point3D(1,0,-1));
        plateau.poser(parcelleRose,new Point3D(1,-1,0));
        plateau.poser(parcelleVerte,new Point3D(1,1,-2));

        assertFalse(partie.getJardinier().faireActionBambou(new Point3D(1,1,-2)));

        boolean exp = partie.getJardinier().faireActionBambou(new Point3D(0,1,-1));
        assertTrue(exp);
    }

    @Test
    public void faireActionbambou2() throws Exception{
        setup();
        plateau.resetPlateau();

        plateau.poser(parcelleVerteIrrigueDestination, new Point3D(1, 0, -1));
        plateau.poser(parcelleVerteAdjacenteIrrigue, new Point3D(2, 0, -2));
        plateau.poser(parcelleVerteNonAdjacenteIrrigue, new Point3D(3, 0, -3));
        plateau.poser(parcelleRose, new Point3D(1, -1, 0));
        plateau.poser(parcelleVerteAdjacenteNonIrrigue, new Point3D(1, 1, -2));
        partie.getJardinier().faireActionBambou(new Point3D(1,0,-1));

        // verte adjacente irrigué
        assertEquals(1,plateau.getParcelle(new Point3D(2,0,-2)).getNbBambou());
        // rose adjacente irrigué
        assertEquals(1,plateau.getParcelle(new Point3D(1,-1,0)).getNbBambou());
        // verte irrigué mais hors périmetre
        assertEquals(0,plateau.getParcelle(new Point3D(3,0,-3)).getNbBambou());
        // verte non irrigué
        assertEquals(0,plateau.getParcelle(new Point3D(1,1,-2)).getNbBambou());
    }
}