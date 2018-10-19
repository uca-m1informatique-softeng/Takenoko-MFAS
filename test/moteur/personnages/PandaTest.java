package moteur.personnages;

import javafx.geometry.Point3D;
import moteur.Parcelle;
import moteur.Partie;
import moteur.Plateau;
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

        plateau.poser(parcelleVerte,new Point3D(0,1,-1));
        plateau.poser(parcelleVerte,new Point3D(1,0,-1));
        plateau.poser(parcelleVerte,new Point3D(1,-1,0));

        assertEquals(0,plateau.getParcelle(new Point3D(0,1,-1)).getNbBambou());

        plateau.getParcelle(new Point3D(0,1,-1)).pousserBambou();
        plateau.getParcelle(new Point3D(0,1,-1)).pousserBambou();

        assertEquals(2,plateau.getParcelle(new Point3D(0,1,-1)).getNbBambou());

        partie.getPanda().PousserOuMangerBambou(new Point3D(0,1,-1));
        assertEquals(1,plateau.getParcelle(new Point3D(0,1,-1)).getNbBambou());
    }

}