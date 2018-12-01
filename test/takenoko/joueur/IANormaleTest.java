package takenoko.joueur;

import javafx.geometry.Point3D;
import org.junit.jupiter.api.Test;
import takenoko.moteur.Enums;
import takenoko.moteur.Parcelle;
import takenoko.moteur.Plateau;
import takenoko.moteur.objectifs.ObjectifJardinier;
import takenoko.moteur.objectifs.ObjectifPanda;
import takenoko.moteur.personnages.Jardinier;
import takenoko.moteur.personnages.Panda;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class IANormaleTest {
    Plateau plateau = Plateau.getInstance() ;
    IANormale ia = new IANormale(Enums.CouleurBot.ROUGE);
    ObjectifJardinier objectifJardinierJ = new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4);
    ObjectifJardinier objectifJardinierR = new ObjectifJardinier(7,Enums.TypeParcelle.ROSE,4);
    ObjectifJardinier objectifJardinierV = new ObjectifJardinier(5,Enums.TypeParcelle.VERTE,4);
    ObjectifPanda getObjectifPandaJ = new ObjectifPanda(4, Enums.TypeParcelle.JAUNE,2);
    ObjectifPanda getObjectifPandaR = new ObjectifPanda(5,Enums.TypeParcelle.ROSE,2);
    ObjectifPanda getObjectifPandaV = new ObjectifPanda(3,Enums.TypeParcelle.VERTE,2);
    Parcelle parcelleJ = new Parcelle(Enums.TypeParcelle.JAUNE);
    Parcelle parcelleR = new Parcelle(Enums.TypeParcelle.ROSE);
    Parcelle parcelleV = new Parcelle(Enums.TypeParcelle.VERTE);



    @Test
    public void possedeObjectifPanda() {
        ia.addObjectif(objectifJardinierJ);
        assertEquals(null,ia.possedeObjectifPanda());
        ia.addObjectif(getObjectifPandaJ);
        assertEquals(ia.possedeObjectifPanda(),getObjectifPandaJ);
    }

    @Test
    public void couleurParcelleDestination() {
        ia.addObjectif(objectifJardinierJ);
        ia.addObjectif(objectifJardinierJ);
        ia.addObjectif(objectifJardinierR);
        ia.addObjectif(objectifJardinierV);
        assertEquals(ia.couleurParcelleDestination(0), Enums.TypeParcelle.JAUNE);
        ia.addObjectif(objectifJardinierR);
        ia.addObjectif(objectifJardinierR);
        assertEquals(ia.couleurParcelleDestination(0), Enums.TypeParcelle.ROSE);
        ia.addObjectif(objectifJardinierV);
        ia.addObjectif(objectifJardinierV);
        ia.addObjectif(objectifJardinierV);
        assertEquals(ia.couleurParcelleDestination(0), Enums.TypeParcelle.VERTE);

    }

    @Test
    public void choixParcellePioche() {
        ArrayList<Parcelle> possiblités = new ArrayList<>() ;
        possiblités.add(parcelleJ);
        possiblités.add(parcelleJ);
        possiblités.add(parcelleR);
        ia.addObjectif(objectifJardinierJ);
        ia.addObjectif(objectifJardinierR);
        assertEquals(ia.choixParcellePioche(possiblités),parcelleR);

        possiblités.clear();
        ia.addObjectif(objectifJardinierJ);
        possiblités.add(parcelleV);
        possiblités.add(parcelleR);
        possiblités.add(parcelleJ);
        assertEquals(ia.choixParcellePioche(possiblités),parcelleJ);

        possiblités.clear();
        ia.getListObjectifs().clear();
        ia.addObjectif(objectifJardinierJ);
        possiblités.add(parcelleV);
        possiblités.add(parcelleR);
        possiblités.add(parcelleR);
        assertEquals(ia.choixParcellePioche(possiblités),parcelleV);
    }

    @Test
    public void choixCoordonnePoseParcelle() {
        plateau.resetPlateau();
        plateau.poser(parcelleJ,new Point3D(1,0,-1));
        plateau.poser(parcelleV,new Point3D(1,-1,0));
        plateau.poser(parcelleR,new Point3D(0,-1,1));

        ArrayList<Point3D> possiblités = plateau.emplacementsAutorise();
        assertEquals(ia.choixCoordonnePoseParcelle(possiblités,parcelleJ),new Point3D(0,1,-1));

        plateau.poser(parcelleJ,new Point3D(0,1,-1));
        possiblités.clear();
        possiblités = plateau.emplacementsAutorise();
        assertEquals(ia.choixCoordonnePoseParcelle(possiblités,parcelleJ),new Point3D(1,1,-2));
    }

    @Test
    public void choixDeplacementJardinier() {
        plateau.resetPlateau();
        ia.addObjectif(objectifJardinierJ);
        plateau.poser(parcelleV,new Point3D(1,-1,0));
        plateau.poser(parcelleV,new Point3D(0,-1,1));
        plateau.poser(parcelleR,new Point3D(2,-1,-1));
        assertEquals(ia.choixDeplacementJardinier(Jardinier.getInstance().destinationsPossibles()),new Point3D(1,-1,0));

        plateau.poser(parcelleJ,new Point3D(1,0,-1));
        assertEquals(ia.choixDeplacementJardinier(Jardinier.getInstance().destinationsPossibles()),new Point3D(1,0,-1));

        ia.addObjectif(objectifJardinierR);
        assertEquals(ia.choixDeplacementJardinier(Jardinier.getInstance().destinationsPossibles()),new Point3D(1,0,-1));
    }

    @Test
    public void couleurSurPlateau() {
        plateau.resetPlateau();
        plateau.poser(parcelleJ,new Point3D(1,0,-1));
        plateau.poser(parcelleV,new Point3D(1,-1,0));
        plateau.poser(parcelleR,new Point3D(0,-1,1));

        assertTrue(ia.couleurSurPlateau(Enums.TypeParcelle.ROSE));

        Panda.getInstance().deplacer(new Point3D(0,-1,1));
        assertFalse(ia.couleurSurPlateau(Enums.TypeParcelle.ROSE));
    }

    @Test
    public void choixDeplacementPanda() {
        plateau.resetPlateau();
        ia.addObjectif(objectifJardinierR);
        plateau.poser(parcelleV,new Point3D(0,-1,1));
        plateau.poser(parcelleV,new Point3D(-1,0,1));
        plateau.poser(parcelleV,new Point3D(-1,1,0));
        plateau.poser(parcelleR,new Point3D(0,1,-1));

        Panda.getInstance().setCoord(new Point3D(0,-1,1));
        assertEquals(ia.choixDeplacementPanda(Panda.getInstance().destinationsPossibles()),new Point3D(0,1,-1));
    }

    @Test
    public void choixDeplacementPanda2() {
        plateau.resetPlateau();
        ia.addObjectif(objectifJardinierR);
        plateau.poser(parcelleV,new Point3D(0,-1,1));
        plateau.poser(parcelleV,new Point3D(-1,0,1));
        plateau.poser(parcelleR,new Point3D(-1,1,0));
        plateau.poser(parcelleV,new Point3D(0,1,-1));

        Panda.getInstance().setCoord(new Point3D(0,-1,1));
        assertEquals(ia.choixDeplacementPanda(Panda.getInstance().destinationsPossibles()),new Point3D(0,1,-1));
    }


}