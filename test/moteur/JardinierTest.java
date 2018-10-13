package moteur;

import javafx.geometry.Point3D;
import org.junit.Test;

import static org.junit.Assert.*;
import moteur.Enums.TypeParcelle;

import java.util.ArrayList;

/**
 * La classe test du jardinier
 */
public class JardinierTest {
    Partie partie=new Partie();
    Plateau pla = partie.getPlateau();
    Parcelle par = new Parcelle(TypeParcelle.ROSE);

    @Test
    public void pousserOuMangerBambou() throws Exception {

        /*On crée notre plateau composé que de parcelles vertes*/
        pla.poser(new Parcelle(TypeParcelle.VERTE),new Point3D(0,1,-1));
        pla.poser(new Parcelle(TypeParcelle.VERTE),new Point3D(1,0,-1));
        pla.poser(new Parcelle(TypeParcelle.VERTE),new Point3D(1,-1,0));

        /* on verifie le nombre de bambou sur la parcelle avant le passage du jardinier*/
        assertEquals(0,pla.getParcelle(new Point3D(0,1,-1)).getNbBambou());

        /* on verifie le nombre de bambou sur la parcelle apres le passage du jardinier*/
        partie.getJardinier().PousserOuMangerBambou(new Point3D(0,1,-1));

        assertEquals(1,pla.getParcelle(new Point3D(0,1,-1)).getNbBambou());
    }

    @Test
    public void ouPousserBambou() throws Exception{

        /*On crée notre plateau composé que de parcelles roses*/

        pla.poser(par,new Point3D(0,1,-1));
        pla.poser(par,new Point3D(1,0,-1));
        pla.poser(par,new Point3D(1,-1,0));
        pla.poser(new Parcelle(TypeParcelle.VERTE),new Point3D(0,-1,1));

        ArrayList<Point3D> exp = partie.getJardinier().ouPousserBambou(new Point3D(0,1,-1));
        assertEquals(3,exp.size());

    }
}