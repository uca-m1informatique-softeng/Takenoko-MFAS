package Moteur;

import Moteur.Parcelle;
import Moteur.Plateau;
import Moteur.TypeParcelle;
import javafx.geometry.Point3D;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlateauTest {

    Plateau pla = new Plateau();
    Point3D p = new Point3D(0,0,0);
    Point3D p1 = new Point3D(1,0,-1);
    Parcelle par1 = new Parcelle(TypeParcelle.etang);


    @Test
    public void getParcelle() {
        Parcelle par2 = pla.getParcelle(p);
        assertEquals(par1.toString(),par2.toString());
    }

    @Test
    public void getParcelleVoisine() {

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
    public void poser() {
        pla.poser(par1,p1);
        Parcelle par3 = pla.getParcelle(p1);
        assertEquals(par1,par3);
    }

    @Test
    public void getParcelleVoisineLibre() {

        pla.poser(par1,p1);
        pla.poser(par1,new Point3D(1,1,-2));
        pla.poser(par1,new Point3D(2,0,-2));
        pla.poser(par1,new Point3D(2,-1,-1));

        /*Recuperer la liste des emplacements voisins libres*/
        ArrayList<Point3D> list = pla.getParcelleVoisineLibre(p1);

        /*Creer la liste des emplacements voisins libres*/
        ArrayList<Point3D> list2 = new ArrayList<>();
        list2.add(new Point3D(1.0,-1.0,0.0));
        list2.add(new Point3D(0.0,1.0,-1.0));

        for (int i = 0 ; i < list.size(); i++){
            assertEquals(list.get(i),list2.get(i));
        }
    }

    @Test
    public void getParcelleVoisineLibre2() {

        pla.poser(par1,p1);
        pla.poser(par1,new Point3D(1,1,-2));
        pla.poser(par1,new Point3D(2,0,-2));
        pla.poser(par1,new Point3D(2,-1,-1));
        pla.poser(par1,new Point3D(1,-1,0));
        pla.poser(par1,new Point3D(0.0,1.0,-1.0));

        /*Recuperer la liste des emplacements voisins libres*/
        ArrayList<Point3D> list = pla.getParcelleVoisineLibre(p1);

        /*Creer la liste des emplacements voisins libres*/
        ArrayList<Point3D> list2 = new ArrayList<>();

        for (int i = 0 ; i < list.size(); i++){
            assertEquals(list.get(i),list2.get(i));
        }
    }

    @Test
    public void getParcelleVoisineLibre3() {


        /*Recuperer la liste des emplacements voisins libres*/
        ArrayList<Point3D> list = pla.getParcelleVoisineLibre(p);

        /*Creer la liste des emplacements voisins libres*/
        ArrayList<Point3D> list2 = new ArrayList<>();
        list2.add(new Point3D(0.0,1.0,-1.0));
        list2.add(new Point3D(1.0,0.0,-1.0));
        list2.add(new Point3D(1.0,-1.0,0.0));
        list2.add(new Point3D(0.0,-1.0,1.0));
        list2.add(new Point3D(-1.0,0.0,1.0));
        list2.add(new Point3D(-1.0,1.0,0.0));

        for (int i = 0 ; i < list.size(); i++){
            assertEquals(list.get(i),list2.get(i));
        }
    }

    @Test
    public void getParcelleVoisineOccupe() {

        Point3D p1=new Point3D(0,0,0);

        /*Recuperer la liste des emplacements voisins occupees*/
        ArrayList<Point3D> list = pla.getParcelleVoisineOccupe(p1);
        assertEquals(list.size(),0);


        /*Creer la liste des emplacements voisins occupees*/
        pla.poser(par1,new Point3D(0,1,-1));
        list = pla.getParcelleVoisineOccupe(p1);
        assertEquals(list.size(),1);

        /*On ajoute des parcelles adjacentes*/
        pla.poser(par1,new Point3D(1,0,-1));
        pla.poser(par1,new Point3D(1,-1,0));
        list = pla.getParcelleVoisineOccupe(p1);
        assertEquals(list.size(),3);

        /*On ajoute des parcelles adjacentes*/
        pla.poser(par1,new Point3D(0,-1,1));
        pla.poser(par1,new Point3D(-1,0,1));
        pla.poser(par1,new Point3D(-1,1,0));
        list = pla.getParcelleVoisineOccupe(p1);
        assertEquals(list.size(),6);

        ArrayList<Point3D> list2 = new ArrayList<>();
        list2.add(new Point3D(0.0,1.0,-1.0));
        list2.add(new Point3D(1.0,0.0,-1.0));
        list2.add(new Point3D(1.0,-1.0,0.0));
        list2.add(new Point3D(0.0,-1.0,1.0));
        list2.add(new Point3D(-1.0,0.0,1.0));
        list2.add(new Point3D(-1.0,1.0,0.0));

        for (int i = 0 ; i < list.size(); i++){
            assertEquals(list.get(i),list2.get(i));
        }
    }

    @Test
    public void isEmplacementAutorise() {
        //il n'y a que la parcelle de départ sur le plateau
        boolean autorisation;

        // on vérifie si on peut poser à coté de la parcelle de départ
        autorisation = pla.isEmplacementAutorise(new Point3D(0,1,-1));

        assertTrue(autorisation);

        //on ajoute 2 parcelles adjacentes entres elles et adjacentes à la parcelle de départ
        pla.poser(par1,new Point3D(1,0,-1));
        pla.poser(par1,new Point3D(1,-1,0));

        //on vérifie si on peut poser en étant adjacent aux 2 nouvelles parcelles posées
        autorisation = pla.isEmplacementAutorise(new Point3D(2,-1,-1));

        assertTrue(autorisation);

        //on vérifie si on peut poser en étant adjacant à une seule parcelle qui n'est pas celle de départ
        autorisation = pla.isEmplacementAutorise(new Point3D(1,1,-2));

        assertFalse(autorisation);

        //on vérifie si on peut poser à un endroit adjacent à aucune parcelle
        autorisation = pla.isEmplacementAutorise(new Point3D(0,-2,2));

        assertFalse(autorisation);
    }

    @Test
    public void emplacementAutorise(){

        ArrayList<Point3D> list = pla.emplacementsAutorise();
        assertEquals(list.size(),6);

        ArrayList<Point3D> list2 = new ArrayList<>();
        list2.add(new Point3D(0.0,1.0,-1.0));
        list2.add(new Point3D(1.0,0.0,-1.0));
        list2.add(new Point3D(1.0,-1.0,0.0));
        list2.add(new Point3D(0.0,-1.0,1.0));
        list2.add(new Point3D(-1.0,0.0,1.0));
        list2.add(new Point3D(-1.0,1.0,0.0));

        for (int i = 0 ; i < list.size(); i++){
            assertEquals(list.get(i),list2.get(i));
        }

        pla.poser(par1,new Point3D(0.0,1.0,-1.0));
        list2.remove(0);
        list = pla.emplacementsAutorise();

        for (int i = 0 ; i < list.size(); i++){
            assertEquals(list.get(i),list2.get(i));
        }
        pla.poser(par1,new Point3D(1.0,0.0,-1.0));//on ajoute une parcelle adjacente aux 2 parcelles déja prÃ©sentes sur le plateau
        //on actualise la liste des points attendus
        list2.remove(0);
        list2.add(new Point3D(1.0,1.0,-2.0));

        list = pla.emplacementsAutorise();

        assertEquals(list.size(),5);

        for (int i = 0 ; i < list.size(); i++){
            assertEquals(list.get(i),list2.get(i));
        }
    }

    @Test
    public void emplacementAutorise1(){
        //on pose 2 parcelles non adjacentes entre elles
        pla.poser(par1,new Point3D(0.0,1.0,-1.0));
        pla.poser(par1,new Point3D(0.0,-1.0,1.0));

        //on creer la liste des points attendus
        ArrayList<Point3D> list2 = new ArrayList<>();
        list2.add(new Point3D(1.0,0.0,-1.0));
        list2.add(new Point3D(1.0,-1.0,0.0));
        list2.add(new Point3D(-1.0,0.0,1.0));
        list2.add(new Point3D(-1.0,1.0,0.0));

        ArrayList<Point3D> list = pla.emplacementsAutorise();

        assertEquals(list.size(),4);

        for (int i = 0 ; i < list.size(); i++){
            assertEquals(list.get(i),list2.get(i));
        }
    }

    @Test
    public void DeplacerJardinier(){

        assertEquals(pla.getJardinier().getCoord(),new Point3D(0,0,0));

        pla.poser(par1,new Point3D(1,0,-1));
        pla.poser(par1,new Point3D(1,-1,0));
        pla.poser(par1,new Point3D(2,-1,1));
        pla.poser(par1,new Point3D(2,-2,0));
        pla.poser(par1,new Point3D(3,-2,-1));
        pla.poser(par1,new Point3D(4,-4,0));

        pla.DeplacerJardinier(new Point3D(1,0,-1));

        assertEquals(pla.getJardinier().getCoord(),new Point3D(1,0,-1));

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

        ArrayList<Point3D> list2 = pla.DestinationsPossiblesJardinier();
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

        pla.DeplacerJardinier(new Point3D(3,-3,0));

        ArrayList<Point3D> list3 = new ArrayList<>();
        list3.add(new Point3D(3.0,-2.0,-1.0));
        list3.add(new Point3D(2.0,-2.0,0.0));
        list3.add(new Point3D(1.0,-1.0,0.0));
        list3.add(new Point3D(0.0,0.0,0.0));

        ArrayList<Point3D> list4 = pla.DestinationsPossiblesJardinier();
        for(int i = 0;i<list4.size();i++){
            assertEquals(list4.get(i),list3.get(i));
        }

    }



}