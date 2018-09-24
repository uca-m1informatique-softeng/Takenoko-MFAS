import javafx.geometry.Point3D;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {

    Plateau pla = new Plateau();
    Point3D p = new Point3D(0,0,0);
    Point3D p1 = new Point3D(1,0,-1);
    Parcelle par1 = new Parcelle(TypeParcelle.etang);


    @Test
    void getParcelle() {
        Parcelle par2 = pla.getParcelle(p);
        assertEquals(par1.toString(),par2.toString());
    }

    @Test
    void getParcelleVoisine() {

        ArrayList<Point3D> lis1 = pla.getParcelleVoisine(p1);
        ArrayList<Point3D> lis2 = new ArrayList<>(); // liste voisine attendue

        lis2.add(new Point3D(1,1,-2));
        lis2.add(new Point3D(2,0,-2));
        lis2.add(new Point3D(2,-1,-1));
        lis2.add(new Point3D(1,-1,0));
        lis2.add(new Point3D(0,0,0));
        lis2.add(new Point3D(0,1,-1));

        for (int i = 0 ; i < lis1.size()-1 ;i++){
            assertEquals(lis1.get(i),lis2.get(i));
        }

    }

    @Test
    void poser() {
        pla.poser(par1,p1);
        Parcelle par3 = pla.getParcelle(p1);
        assertEquals(par1,par3);
    }

    @Test
    void getParcelleVoisineLibre() {

        pla.poser(par1,p1);
        pla.poser(par1,new Point3D(1,1,-2));
        pla.poser(par1,new Point3D(2,0,-2));
        pla.poser(par1,new Point3D(2,-1,-1));

        /*Recuperer la liste des emplacements voisins libres*/
        ArrayList<Point3D> list = pla.getParcelleVoisineLibre(p1);

        /*Creer la liste des enplacements voisins libres*/
        ArrayList<Point3D> list2 = new ArrayList<>();
        list2.add(new Point3D(1.0,-1.0,0.0));
        list2.add(new Point3D(0.0,1.0,-1.0));

        for (int i = 0 ; i < list.size(); i++){
            assertEquals(list.get(i),list2.get(i));
        }


    }


}