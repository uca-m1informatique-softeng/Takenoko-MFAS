package Moteur;

import javafx.geometry.Point3D;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PersonnageTest {
    Partie partie=new Partie();
    Plateau pla = partie.getPlateau();
    Point3D p = new Point3D(0,0,0);
    Point3D p1 = new Point3D(1,0,-1);
    Parcelle par1 = new Parcelle(TypeParcelle.etang);
    Jardinier jardinier=partie.getJardinier();


    @Test
    public void DeplacerJardinier(){


        assertEquals(partie.getJardinier().getCoord(),new Point3D(0,0,0));

        pla.poser(par1,new Point3D(1,0,-1));
        pla.poser(par1,new Point3D(1,-1,0));
        pla.poser(par1,new Point3D(2,-1,1));
        pla.poser(par1,new Point3D(2,-2,0));
        pla.poser(par1,new Point3D(3,-2,-1));
        pla.poser(par1,new Point3D(4,-4,0));

        jardinier.Deplacer(new Point3D(1,0,-1));

        assertEquals(jardinier.getCoord(),new Point3D(1,0,-1));

    }
    @Test
    public void DestinationsPossiblesJardinier(){
        /* Création plateau */
        pla.poser(par1,new Point3D(1,0,-1));
        pla.poser(par1,new Point3D(1,-1,0));
        pla.poser(par1,new Point3D(2,-1,1));
        pla.poser(par1,new Point3D(2,-2,0));
        pla.poser(par1,new Point3D(3,-2,-1));
        pla.poser(par1,new Point3D(3,-3,0));

        pla.poser(par1,new Point3D(0,1,-1));


        ArrayList<Point3D> list = new ArrayList<>();// liste attendu
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
    public void DestinationsPossiblesJardinier2(){
        /* Création plateau */
        pla.poser(par1,new Point3D(1,0,-1));
        pla.poser(par1,new Point3D(1,-1,0));
        pla.poser(par1,new Point3D(2,-1,1));
        pla.poser(par1,new Point3D(2,-2,0));
        pla.poser(par1,new Point3D(3,-2,-1));
        pla.poser(par1,new Point3D(3,-3,0));

        pla.poser(par1,new Point3D(0,1,-1));

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

}
