package Moteur;

import javafx.geometry.Point3D;
import org.junit.Test;

import static org.junit.Assert.*;

public class PandaTest {
    Partie partie=new Partie();
    Plateau pla = partie.getPlateau();
    Parcelle par = new Parcelle(TypeParcelle.Verte);

    @Test
    public void pousserOuMangerBambou() throws Exception {
        /*On crée notre plateau composé que de parcelles vertes*/

        pla.poser(par,new Point3D(0,1,-1));
        pla.poser(par,new Point3D(1,0,-1));
        pla.poser(par,new Point3D(1,-1,0));

        /* On fait pousser 2 bambou*/
        pla.getParcelle(new Point3D(0,1,-1)).pousserBambou();
        pla.getParcelle(new Point3D(0,1,-1)).pousserBambou();

        /* on verifie le nombre de bambou sur la parcelle avant le passage du panda*/
        assertEquals(2,pla.getParcelle(new Point3D(0,1,-1)).getNbBambou());

        /* on verifie le nombre de bambou sur la parcelle apres le passage du panda*/
        partie.getPanda().PousserOuMangerBambou(new Point3D(0,1,-1));
        assertEquals(1,pla.getParcelle(new Point3D(0,1,-1)).getNbBambou());
    }

}