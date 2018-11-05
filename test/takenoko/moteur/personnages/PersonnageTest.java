package takenoko.moteur.personnages;

import takenoko.moteur.*;
import takenoko.moteur.personnages.Jardinier;
import javafx.geometry.Point3D;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import takenoko.moteur.Enums.TypeParcelle;
import takenoko.moteur.personnages.Panda;

/**
 * La classe test des personnages
 */
public class PersonnageTest {
    Partie partie ;
    Plateau plateau ;
    Parcelle parcelleEtang ;
    Jardinier jardinier ;
    Panda panda;
    Parcelle parcelleJaune ;

    public void setup(){
        this.partie = new Partie();
        this.plateau = partie.getPlateau();
        this.jardinier = partie.getJardinier();
        this.panda = partie.getPanda();
        this.parcelleJaune = new Parcelle();
        parcelleJaune.setListBambou(new ArrayList<Bambou>());
        parcelleJaune.setIrriguee(false);
        parcelleJaune.setType(TypeParcelle.JAUNE);
        this.parcelleEtang = new Parcelle();
        parcelleEtang.setListBambou(new ArrayList<Bambou>());
        parcelleEtang.setIrriguee(true);
        parcelleEtang.setType(TypeParcelle.ETANG);

    }


    @Test
    public void DeplacerJardinier(){
        setup();
        plateau.resetPlateau();
        partie.getJardinier().resetPersonnage();
        assertEquals(partie.getJardinier().getCoord(),new Point3D(0,0,0));

        plateau.poser(parcelleJaune,new Point3D(1,0,-1));
        plateau.poser(parcelleJaune,new Point3D(1,-1,0));
        plateau.poser(parcelleJaune,new Point3D(2,-1,1));
        plateau.poser(parcelleJaune,new Point3D(2,-2,0));
        plateau.poser(parcelleJaune,new Point3D(3,-2,-1));
        plateau.poser(parcelleJaune,new Point3D(4,-4,0));

        jardinier.deplacer(new Point3D(1,0,-1));

        assertEquals(jardinier.getCoord(),new Point3D(1,0,-1));

    }
    @Test
    public void DestinationsPossiblesJardinierDépart(){
        setup();
        plateau.resetPlateau();
        partie.getJardinier().resetPersonnage();
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));
        plateau.poser(parcelleJaune,new Point3D(1,-1,0));
        plateau.poser(parcelleJaune,new Point3D(2,-1,1));
        plateau.poser(parcelleJaune,new Point3D(2,-2,0));
        plateau.poser(parcelleJaune,new Point3D(3,-2,-1));
        plateau.poser(parcelleJaune,new Point3D(3,-3,0));

        plateau.poser(parcelleJaune,new Point3D(0,1,-1));


        ArrayList<Point3D> list = new ArrayList<>();
        list.add(new Point3D(0.0,1.0,-1.0));
        list.add(new Point3D(1.0,0.0,-1.0));
        list.add(new Point3D(1.0,-1.0,0.0));
        list.add(new Point3D(2.0,-2.0,0.0));
        list.add(new Point3D(3.0,-3.0,0.0));

        ArrayList<Point3D> list2 = jardinier.destinationsPossibles();
        for(int i = 0;i<list2.size();i++){
            assertEquals(list.get(i),list2.get(i));
        }
    }

    @Test
    public void DestinationsPossiblesJardinierDéplacer(){
        setup();
        plateau.resetPlateau();
        partie.getJardinier().resetPersonnage();
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));
        plateau.poser(parcelleJaune,new Point3D(1,-1,0));
        plateau.poser(parcelleJaune,new Point3D(2,-1,1));
        plateau.poser(parcelleJaune,new Point3D(2,-2,0));
        plateau.poser(parcelleJaune,new Point3D(3,-2,-1));
        plateau.poser(parcelleJaune,new Point3D(3,-3,0));

        plateau.poser(parcelleJaune,new Point3D(0,1,-1));

        jardinier.deplacer(new Point3D(3,-3,0));

        ArrayList<Point3D> list3 = new ArrayList<>();
        list3.add(new Point3D(3.0,-2.0,-1.0));
        list3.add(new Point3D(2.0,-2.0,0.0));
        list3.add(new Point3D(1.0,-1.0,0.0));
        list3.add(new Point3D(0.0,0.0,0.0));

        ArrayList<Point3D> list4 = jardinier.destinationsPossibles();
        for(int i = 0;i<list4.size();i++){
            assertEquals(list4.get(i),list3.get(i));
        }

    }

    @Test
    public void DeplacerPanda(){
        setup();
        plateau.resetPlateau();
        partie.getPanda().resetPersonnage();
        assertEquals(partie.getPanda().getCoord(),new Point3D(0,0,0));

        plateau.poser(parcelleJaune,new Point3D(1,0,-1));
        plateau.poser(parcelleJaune,new Point3D(1,-1,0));
        plateau.poser(parcelleJaune,new Point3D(2,-1,1));
        plateau.poser(parcelleJaune,new Point3D(2,-2,0));
        plateau.poser(parcelleJaune,new Point3D(3,-2,-1));
        plateau.poser(parcelleJaune,new Point3D(4,-4,0));

        panda.deplacer(new Point3D(1,0,-1));

        assertEquals(panda.getCoord(),new Point3D(1,0,-1));

    }


    @Test
    public void DestinationsPossiblesPanda(){
        setup();
        plateau.resetPlateau();
        partie.getPanda().resetPersonnage();
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));
        plateau.poser(parcelleJaune,new Point3D(1,-1,0));
        plateau.poser(parcelleJaune,new Point3D(2,-1,1));
        plateau.poser(parcelleJaune,new Point3D(2,-2,0));
        plateau.poser(parcelleJaune,new Point3D(3,-2,-1));
        plateau.poser(parcelleJaune,new Point3D(3,-3,0));

        plateau.poser(parcelleEtang,new Point3D(0,1,-1));
        ArrayList<Point3D> list = new ArrayList<>();// liste attendu
        list.add(new Point3D(0.0,1.0,-1.0));
        list.add(new Point3D(1.0,0.0,-1.0));
        list.add(new Point3D(1.0,-1.0,0.0));
        list.add(new Point3D(2.0,-2.0,0.0));
        list.add(new Point3D(3.0,-3.0,0.0));

        ArrayList<Point3D> list2 = panda.destinationsPossibles();
        for(int i = 0;i<list2.size();i++){
            assertEquals(list.get(i),list2.get(i));
        }
    }

}
