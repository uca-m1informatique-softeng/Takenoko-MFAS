package moteur.personnages;

import javafx.geometry.Point3D;
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
    public void pousserOuMangerBambou() throws Exception {
        plateau.poser(new Parcelle(TypeParcelle.VERTE),new Point3D(0,1,-1));
        plateau.poser(new Parcelle(TypeParcelle.VERTE),new Point3D(1,0,-1));
        plateau.poser(new Parcelle(TypeParcelle.VERTE),new Point3D(1,-1,0));

        assertEquals(0,plateau.getParcelle(new Point3D(0,1,-1)).getNbBambou());

        partie.getJardinier().faireActionBambou(new Point3D(0,1,-1));

        assertEquals(1,plateau.getParcelle(new Point3D(0,1,-1)).getNbBambou());
    }

    @Test
    public void ouPousserBambou() throws Exception{


        plateau.poser(parcelleRose,new Point3D(0,1,-1));
        plateau.poser(parcelleRose,new Point3D(1,0,-1));
        plateau.poser(parcelleRose,new Point3D(1,-1,0));
        plateau.poser(new Parcelle(TypeParcelle.VERTE),new Point3D(0,-1,1));

        ArrayList<Point3D> exp = partie.getJardinier().ouPousserBambou(new Point3D(0,1,-1));
        assertEquals(3,exp.size());

    }
}