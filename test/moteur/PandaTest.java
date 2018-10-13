package moteur;

import javafx.geometry.Point3D;
import org.junit.Test;

import static org.junit.Assert.*;
import moteur.Enums.TypeParcelle;

/**
 * La classe test du panda
 */
public class PandaTest {
    Partie partie=new Partie();
    Plateau plateau = partie.getPlateau();
    Parcelle parcelleVerte = new Parcelle(TypeParcelle.VERTE);

    @Test
    public void pousserOuMangerBambou() throws Exception {
        /*On crée notre plateau composé que de parcelles vertes*/

        plateau.poser(parcelleVerte,new Point3D(0,1,-1));
        plateau.poser(parcelleVerte,new Point3D(1,0,-1));
        plateau.poser(parcelleVerte,new Point3D(1,-1,0));

        /* On fait pousser 2 bambou*/
        plateau.getParcelle(new Point3D(0,1,-1)).pousserBambou();
        plateau.getParcelle(new Point3D(0,1,-1)).pousserBambou();

        /* on verifie le nombre de bambou sur la parcelle avant le passage du panda*/
        assertEquals(2,plateau.getParcelle(new Point3D(0,1,-1)).getNbBambou());

        /* on verifie le nombre de bambou sur la parcelle apres le passage du panda*/
        partie.getPanda().PousserOuMangerBambou(new Point3D(0,1,-1));
        assertEquals(1,plateau.getParcelle(new Point3D(0,1,-1)).getNbBambou());
    }

}