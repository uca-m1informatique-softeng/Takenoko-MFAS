package moteur.personnages;

import moteur.Parcelle;
import moteur.Partie;
import moteur.Plateau;
import moteur.personnages.Jardinier;
import javafx.geometry.Point3D;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import moteur.Enums.TypeParcelle;


/**
 * La classe test des personnages
 */
public class PersonnageTest {
    Partie partie = new Partie();
    Plateau plateau = partie.getPlateau();
    Parcelle parcelleEtang = new Parcelle(TypeParcelle.ETANG);
    Jardinier jardinier = partie.getJardinier();
    Panda panda = partie.getPanda();


    @Test
    public void DeplacerJardinier(){


        assertEquals(partie.getJardinier().getCoord(),new Point3D(0,0,0));

        plateau.poser(parcelleEtang,new Point3D(1,0,-1));
        plateau.poser(parcelleEtang,new Point3D(1,-1,0));
        plateau.poser(parcelleEtang,new Point3D(2,-1,1));
        plateau.poser(parcelleEtang,new Point3D(2,-2,0));
        plateau.poser(parcelleEtang,new Point3D(3,-2,-1));
        plateau.poser(parcelleEtang,new Point3D(4,-4,0));

        jardinier.Deplacer(new Point3D(1,0,-1));

        assertEquals(jardinier.getCoord(),new Point3D(1,0,-1));

    }
    @Test
    public void DestinationsPossiblesJardinierDépart(){
        plateau.poser(parcelleEtang,new Point3D(1,0,-1));
        plateau.poser(parcelleEtang,new Point3D(1,-1,0));
        plateau.poser(parcelleEtang,new Point3D(2,-1,1));
        plateau.poser(parcelleEtang,new Point3D(2,-2,0));
        plateau.poser(parcelleEtang,new Point3D(3,-2,-1));
        plateau.poser(parcelleEtang,new Point3D(3,-3,0));

        plateau.poser(parcelleEtang,new Point3D(0,1,-1));


        ArrayList<Point3D> list = new ArrayList<>();
        list.add(new Point3D(0.0,1.0,-1.0));
        list.add(new Point3D(1.0,0.0,-1.0));
        list.add(new Point3D(1.0,-1.0,0.0));
        list.add(new Point3D(2.0,-2.0,0.0));
        list.add(new Point3D(3.0,-3.0,0.0));

        ArrayList<Point3D> list2 = jardinier.DestinationsPossibles();
        for(int i = 0;i<list2.size();i++){
            assertEquals(list.get(i),list2.get(i));
        }
    }

    @Test
    public void DestinationsPossiblesJardinierDéplacer(){
        plateau.poser(parcelleEtang,new Point3D(1,0,-1));
        plateau.poser(parcelleEtang,new Point3D(1,-1,0));
        plateau.poser(parcelleEtang,new Point3D(2,-1,1));
        plateau.poser(parcelleEtang,new Point3D(2,-2,0));
        plateau.poser(parcelleEtang,new Point3D(3,-2,-1));
        plateau.poser(parcelleEtang,new Point3D(3,-3,0));

        plateau.poser(parcelleEtang,new Point3D(0,1,-1));

        jardinier.Deplacer(new Point3D(3,-3,0));

        ArrayList<Point3D> list3 = new ArrayList<>();
        list3.add(new Point3D(3.0,-2.0,-1.0));
        list3.add(new Point3D(2.0,-2.0,0.0));
        list3.add(new Point3D(1.0,-1.0,0.0));
        list3.add(new Point3D(0.0,0.0,0.0));

        ArrayList<Point3D> list4 = jardinier.DestinationsPossibles();
        for(int i = 0;i<list4.size();i++){
            assertEquals(list4.get(i),list3.get(i));
        }

    }

    @Test
    public void DeplacerPanda(){
        assertEquals(partie.getPanda().getCoord(),new Point3D(0,0,0));

        plateau.poser(parcelleEtang,new Point3D(1,0,-1));
        plateau.poser(parcelleEtang,new Point3D(1,-1,0));
        plateau.poser(parcelleEtang,new Point3D(2,-1,1));
        plateau.poser(parcelleEtang,new Point3D(2,-2,0));
        plateau.poser(parcelleEtang,new Point3D(3,-2,-1));
        plateau.poser(parcelleEtang,new Point3D(4,-4,0));

        panda.Deplacer(new Point3D(1,0,-1));

        assertEquals(panda.getCoord(),new Point3D(1,0,-1));

    }


    @Test
    public void DestinationsPossiblesPanda(){
        plateau.poser(parcelleEtang,new Point3D(1,0,-1));
        plateau.poser(parcelleEtang,new Point3D(1,-1,0));
        plateau.poser(parcelleEtang,new Point3D(2,-1,1));
        plateau.poser(parcelleEtang,new Point3D(2,-2,0));
        plateau.poser(parcelleEtang,new Point3D(3,-2,-1));
        plateau.poser(parcelleEtang,new Point3D(3,-3,0));

        plateau.poser(parcelleEtang,new Point3D(0,1,-1));
        ArrayList<Point3D> list = new ArrayList<>();// liste attendu
        list.add(new Point3D(0.0,1.0,-1.0));
        list.add(new Point3D(1.0,0.0,-1.0));
        list.add(new Point3D(1.0,-1.0,0.0));
        list.add(new Point3D(2.0,-2.0,0.0));
        list.add(new Point3D(3.0,-3.0,0.0));

        ArrayList<Point3D> list2 = panda.DestinationsPossibles();
        for(int i = 0;i<list2.size();i++){
            assertEquals(list.get(i),list2.get(i));
        }
    }



}
