/*
package takenoko.moteur.personnages;

import javafx.geometry.Point3D;
import takenoko.moteur.Parcelle;
import takenoko.moteur.Partie;
import takenoko.moteur.Plateau;
import org.junit.Test;

import static org.junit.Assert.*;
import takenoko.moteur.Enums.TypeParcelle;

import java.util.ArrayList;

*/
/**
 * La classe test du jardinier
 *//*

public class JardinierTest {
    Partie partie=new Partie();
    Plateau plateau = partie.getPlateau();
    Parcelle parcelleRose = new Parcelle(TypeParcelle.ROSE);
    Parcelle parcelleVerte = new Parcelle(TypeParcelle.VERTE);

    @Test
    public void ouPousserBambou() throws Exception{
        plateau.poser(parcelleVerte,new Point3D(0,1,-1));
        plateau.poser(parcelleRose,new Point3D(1,0,-1));
        plateau.poser(parcelleRose,new Point3D(1,-1,0));

        ArrayList<Point3D> exp = partie.getJardinier().ouPousserBambou(new Point3D(1,0,-1));
        System.out.println(exp);
        assertEquals(2,exp.size());
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


    }*/
