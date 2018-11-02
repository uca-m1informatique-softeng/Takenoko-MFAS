package moteur.personnages;

import javafx.geometry.Point3D;
import moteur.Irrigation;
import moteur.Parcelle;
import moteur.Partie;
import moteur.Plateau;
import org.junit.Test;

import static org.junit.Assert.*;
import moteur.Enums.TypeParcelle;

import java.util.ArrayList;

/**
 * La classe test du jardinier
 */
public class JardinierTest {
    Partie partie=new Partie();
    Plateau plateau = partie.getPlateau();
    Parcelle parcelleRose = new Parcelle(TypeParcelle.ROSE);

    @Test
    public void ouPousserBambou() throws Exception{
        plateau.poser(parcelleRose,new Point3D(0,1,-1));
        plateau.poser(parcelleRose,new Point3D(1,0,-1));
        plateau.poser(parcelleRose,new Point3D(1,-1,0));

        ArrayList<Point3D> exp = partie.getJardinier().ouPousserBambou(new Point3D(0,1,-1));
        assertEquals(2,exp.size());
    }

    @Test
    public void ouPousserBambou2() throws Exception {
        plateau.resetPlateau();
        Parcelle parcelleVerteIrrigueDestination = new Parcelle(TypeParcelle.VERTE);
        Parcelle parcelleVerteAdjacenteIrrigue = new Parcelle(TypeParcelle.VERTE);
        Parcelle parcelleVerteNonAdjacenteIrrigue = new Parcelle(TypeParcelle.VERTE);
        Parcelle parcelleVerteAdjacenteNonIrrigue = new Parcelle(TypeParcelle.VERTE);


        plateau.poser(parcelleVerteIrrigueDestination, new Point3D(1, 0, -1));
        plateau.poser(parcelleVerteAdjacenteIrrigue, new Point3D(2, 0, -2));
        parcelleVerteAdjacenteIrrigue.setIrriguee(true);
        plateau.poser(parcelleVerteNonAdjacenteIrrigue, new Point3D(3, 0, -3));
        parcelleVerteNonAdjacenteIrrigue.setIrriguee(true);
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
        plateau.resetPlateau();
        plateau.poser(parcelleRose,new Point3D(0,1,-1));
        plateau.poser(parcelleRose,new Point3D(1,0,-1));
        plateau.poser(parcelleRose,new Point3D(1,-1,0));
        plateau.poser(new Parcelle(TypeParcelle.VERTE),new Point3D(1,1,-2));

        assertFalse(partie.getJardinier().faireActionBambou(new Point3D(1,1,-2)));

        boolean exp = partie.getJardinier().faireActionBambou(new Point3D(0,1,-1));
        assertTrue(exp);
    }

    @Test
    public void faireActionbambou2() throws Exception{
        plateau.resetPlateau();
        Parcelle parcelleVerteIrrigueDestination = new Parcelle(TypeParcelle.VERTE);
        Parcelle parcelleVerteAdjacenteIrrigue = new Parcelle(TypeParcelle.VERTE);
        Parcelle parcelleVerteNonAdjacenteIrrigue = new Parcelle(TypeParcelle.VERTE);
        Parcelle parcelleVerteAdjacenteNonIrrigue = new Parcelle(TypeParcelle.VERTE);


        plateau.poser(parcelleVerteIrrigueDestination, new Point3D(1, 0, -1));
        plateau.poser(parcelleVerteAdjacenteIrrigue, new Point3D(2, 0, -2));
        parcelleVerteAdjacenteIrrigue.setIrriguee(true);
        plateau.poser(parcelleVerteNonAdjacenteIrrigue, new Point3D(3, 0, -3));
        parcelleVerteNonAdjacenteIrrigue.setIrriguee(true);
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