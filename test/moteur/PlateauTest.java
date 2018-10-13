package moteur;

import javafx.geometry.Point3D;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;
import moteur.Enums.TypeParcelle;


/**
 * La classe test du plateau
 */
public class PlateauTest {

    Partie partie=new Partie();
    Plateau plateau = partie.getPlateau();
    Point3D coordonneCentre = new Point3D(0,0,0);
    Point3D coordonneVoisin = new Point3D(1,0,-1);
    Parcelle parcelleEtang = new Parcelle(TypeParcelle.ETANG);


    @Test
    public void getParcelle() {
        Parcelle parcelle2 = plateau.getParcelle(coordonneCentre);
        assertEquals(parcelleEtang.toString(),parcelle2.toString());
    }

    @Test
    public void getParcelleVoisine() {

        ArrayList<Point3D> lis1 = plateau.getParcelleVoisine(coordonneVoisin);
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
        plateau.poser(parcelleEtang,coordonneVoisin);
        Parcelle par3 = plateau.getParcelle(coordonneVoisin);
        assertEquals(parcelleEtang,par3);
    }

    @Test
    public void getParcelleVoisineLibre() {

        plateau.poser(parcelleEtang,coordonneVoisin);
        plateau.poser(parcelleEtang,new Point3D(1,1,-2));
        plateau.poser(parcelleEtang,new Point3D(2,0,-2));
        plateau.poser(parcelleEtang,new Point3D(2,-1,-1));

        /*Recuperer la liste des emplacements voisins libres*/
        ArrayList<Point3D> list = plateau.getParcelleVoisineLibre(coordonneVoisin);

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

        plateau.poser(parcelleEtang,coordonneVoisin);
        plateau.poser(parcelleEtang,new Point3D(1,1,-2));
        plateau.poser(parcelleEtang,new Point3D(2,0,-2));
        plateau.poser(parcelleEtang,new Point3D(2,-1,-1));
        plateau.poser(parcelleEtang,new Point3D(1,-1,0));
        plateau.poser(parcelleEtang,new Point3D(0.0,1.0,-1.0));

        /*Recuperer la liste des emplacements voisins libres*/
        ArrayList<Point3D> list = plateau.getParcelleVoisineLibre(coordonneVoisin);

        /*Creer la liste des emplacements voisins libres*/
        ArrayList<Point3D> list2 = new ArrayList<>();

        for (int i = 0 ; i < list.size(); i++){
            assertEquals(list.get(i),list2.get(i));
        }
    }

    @Test
    public void getParcelleVoisineLibre3() {


        /*Recuperer la liste des emplacements voisins libres*/
        ArrayList<Point3D> list = plateau.getParcelleVoisineLibre(coordonneCentre);

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

        Point3D coordonneVoisin=new Point3D(0,0,0);

        /*Recuperer la liste des emplacements voisins occupees*/
        ArrayList<Point3D> list = plateau.getParcelleVoisineOccupe(coordonneVoisin);
        assertEquals(0,list.size());


        /*Creer la liste des emplacements voisins occupees*/
        plateau.poser(parcelleEtang,new Point3D(0,1,-1));
        list = plateau.getParcelleVoisineOccupe(coordonneVoisin);
        assertEquals(1,list.size());

        /*On ajoute des parcelles adjacentes*/
        plateau.poser(parcelleEtang,new Point3D(1,0,-1));
        plateau.poser(parcelleEtang,new Point3D(1,-1,0));
        list = plateau.getParcelleVoisineOccupe(coordonneVoisin);
        assertEquals(3,list.size());

        /*On ajoute des parcelles adjacentes*/
        plateau.poser(parcelleEtang,new Point3D(0,-1,1));
        plateau.poser(parcelleEtang,new Point3D(-1,0,1));
        plateau.poser(parcelleEtang,new Point3D(-1,1,0));
        list = plateau.getParcelleVoisineOccupe(coordonneVoisin);
        assertEquals(6,list.size());

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
        autorisation = plateau.isEmplacementAutorise(new Point3D(0,1,-1));

        assertTrue(autorisation);

        //on ajoute 2 parcelles adjacentes entres elles et adjacentes à la parcelle de départ
        plateau.poser(parcelleEtang,new Point3D(1,0,-1));
        plateau.poser(parcelleEtang,new Point3D(1,-1,0));

        //on vérifie si on peut poser en étant adjacent aux 2 nouvelles parcelles posées
        autorisation = plateau.isEmplacementAutorise(new Point3D(2,-1,-1));

        assertTrue(autorisation);

        //on vérifie si on peut poser en étant adjacant à une seule parcelle qui n'est pas celle de départ
        autorisation = plateau.isEmplacementAutorise(new Point3D(1,1,-2));

        assertFalse(autorisation);

        //on vérifie si on peut poser à un endroit adjacent à aucune parcelle
        autorisation = plateau.isEmplacementAutorise(new Point3D(0,-2,2));

        assertFalse(autorisation);
    }

    @Test
    public void emplacementAutorise(){

        ArrayList<Point3D> list = plateau.emplacementsAutorise();
        assertEquals(6,list.size());

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

        plateau.poser(parcelleEtang,new Point3D(0.0,1.0,-1.0));
        list2.remove(0);
        list = plateau.emplacementsAutorise();

        for (int i = 0 ; i < list.size(); i++){
            assertEquals(list.get(i),list2.get(i));
        }
        plateau.poser(parcelleEtang,new Point3D(1.0,0.0,-1.0));//on ajoute une parcelle adjacente aux 2 parcelles déja prÃ©sentes sur le plateau
        //on actualise la liste des points attendus
        list2.remove(0);
        list2.add(new Point3D(1.0,1.0,-2.0));

        list = plateau.emplacementsAutorise();

        assertEquals(5,list.size());

        for (int i = 0 ; i < list.size(); i++){
            assertEquals(list.get(i),list2.get(i));
        }
    }

    @Test
    public void emplacementAutorise1(){
        //on pose 2 parcelles non adjacentes entre elles
        plateau.poser(parcelleEtang,new Point3D(0.0,1.0,-1.0));
        plateau.poser(parcelleEtang,new Point3D(0.0,-1.0,1.0));

        //on creer la liste des points attendus
        ArrayList<Point3D> list2 = new ArrayList<>();
        list2.add(new Point3D(1.0,0.0,-1.0));
        list2.add(new Point3D(1.0,-1.0,0.0));
        list2.add(new Point3D(-1.0,0.0,1.0));
        list2.add(new Point3D(-1.0,1.0,0.0));

        ArrayList<Point3D> list = plateau.emplacementsAutorise();

        assertEquals(4,list.size());

        for (int i = 0 ; i < list.size(); i++){
            assertEquals(list.get(i),list2.get(i));
        }
    }

    @Test
    public void getParcelleVoisineMemeCouleur(){
        plateau.poser(new Parcelle(TypeParcelle.JAUNE),new Point3D(1.0,0.0,-1.0));
        plateau.poser(new Parcelle(TypeParcelle.JAUNE),new Point3D(0.0,1.0,-1.0));
        plateau.poser(new Parcelle(TypeParcelle.JAUNE),new Point3D(1.0,1.0,-2.0));
        plateau.poser(new Parcelle(TypeParcelle.ROSE),new Point3D(2.0,0.0,-2.0));
        plateau.poser(new Parcelle(TypeParcelle.VERTE),new Point3D(2.0,-1.0,-1.0));
        plateau.poser(new Parcelle(TypeParcelle.JAUNE),new Point3D(1.0,-1.0,0.0));

        ArrayList<Point3D> exp = plateau.getParcelleVoisineMemeCouleur(new Point3D(1.0,0.0,-1.0));

        assertEquals(3,exp.size());


        assertTrue(exp.contains(new Point3D(0.0,1.0,-1.0)));
        assertTrue(exp.contains(new Point3D(1.0,1.0,-2.0)));
        assertTrue(exp.contains(new Point3D(1.0,-1.0,0.0)));


    }

}