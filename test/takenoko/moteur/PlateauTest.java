package takenoko.moteur;

import javafx.geometry.Point3D;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;
import takenoko.moteur.Enums.TypeParcelle;


/**
 * La classe des test du Plateau
 */
public class PlateauTest {
    Partie partie = new Partie();
    Plateau plateau = partie.getPlateau();
    Point3D coordonneCentre = new Point3D(0,0,0);
    Point3D coordonneVoisin = new Point3D(1,0,-1);
    Parcelle parcelleEtang = new Parcelle(TypeParcelle.ETANG);
    Parcelle parcelleJaune = new Parcelle(TypeParcelle.JAUNE);
    Parcelle parcelleRose = new Parcelle(TypeParcelle.ROSE);
    Parcelle parcelleVert = new Parcelle(TypeParcelle.VERTE);

    @Test
    public void getParcelle() {
        plateau.poser(parcelleJaune,coordonneVoisin);
        Parcelle parcelle = plateau.getParcelle(coordonneVoisin);
        assertEquals(parcelle.getType(),parcelleJaune.getType());
    }

    @Test
    public void getAllParcelle() {
        plateau.resetPlateau();
        plateau.poser(parcelleJaune,coordonneVoisin);
        plateau.poser(parcelleJaune,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune,new Point3D(1,-1,0));
        assertEquals(plateau.getAllParcelle().size(),4);
    }

    @Test
    public void getParcelleVoisine() {
        plateau.resetPlateau();
        ArrayList<Point3D> listeAttendue = plateau.getParcelleVoisine(coordonneVoisin);
        ArrayList<Point3D> listeParcelle = new ArrayList<>();

        listeParcelle.add(new Point3D(1,1,-2));
        listeParcelle.add(new Point3D(2,0,-2));
        listeParcelle.add(new Point3D(2,-1,-1));
        listeParcelle.add(new Point3D(1,-1,0));
        listeParcelle.add(new Point3D(0,0,0));
        listeParcelle.add(new Point3D(0,1,-1));

        assertEquals(listeAttendue.size(),listeParcelle.size());
        for (int i = 0 ; i < listeAttendue.size()-1 ;i++){
            assertEquals(listeAttendue.get(i),listeParcelle.get(i));
        }
    }

    @Test
    public void poser() {
        plateau.resetPlateau();
        plateau.poser(parcelleEtang,coordonneVoisin);
        assertEquals((int)plateau.getKeylist().get(1).getX(),(int)coordonneVoisin.getX());
        assertEquals((int)plateau.getKeylist().get(1).getY(),(int)coordonneVoisin.getY());
        assertEquals((int)plateau.getKeylist().get(1).getZ(),(int)coordonneVoisin.getZ());
        assertEquals(plateau.getParcelle(coordonneVoisin).getType(),parcelleEtang.getType());
    }

    @Test
    public void getDeuxParcelleVoisineLibre() {
        plateau.resetPlateau();
        plateau.poser(parcelleEtang,coordonneVoisin);
        plateau.poser(parcelleEtang,new Point3D(1,1,-2));
        plateau.poser(parcelleEtang,new Point3D(2,0,-2));
        plateau.poser(parcelleEtang,new Point3D(2,-1,-1));

        ArrayList<Point3D> listAttendue = plateau.getParcelleVoisineLibre(coordonneVoisin);

        ArrayList<Point3D> listeParcelle = new ArrayList<>();
        listeParcelle.add(new Point3D(1.0,-1.0,0.0));
        listeParcelle.add(new Point3D(0.0,1.0,-1.0));

        assertEquals(listAttendue.size(),listeParcelle.size());
        for (int i = 0 ; i < listAttendue.size(); i++){
            assertEquals(listAttendue.get(i),listeParcelle.get(i));
        }
    }

    @Test
    public void getZeroParcelleVoisineLibre() {
        plateau.resetPlateau();
        plateau.poser(parcelleEtang,coordonneVoisin);
        plateau.poser(parcelleEtang,new Point3D(1,1,-2));
        plateau.poser(parcelleEtang,new Point3D(2,0,-2));
        plateau.poser(parcelleEtang,new Point3D(2,-1,-1));
        plateau.poser(parcelleEtang,new Point3D(1,-1,0));
        plateau.poser(parcelleEtang,new Point3D(0.0,1.0,-1.0));

        ArrayList<Point3D> listeAttendue = plateau.getParcelleVoisineLibre(coordonneVoisin);

        ArrayList<Point3D> listeParcelle = new ArrayList<>();

        assertEquals(listeAttendue.size(),listeParcelle.size());
        for (int i = 0 ; i < listeAttendue.size(); i++){
            assertEquals(listeAttendue.get(i),listeParcelle.get(i));
        }
    }

    @Test
    public void getSixParcelleVoisineLibre() {
        plateau.resetPlateau();
        ArrayList<Point3D> listeAttendue = plateau.getParcelleVoisineLibre(coordonneCentre);

        ArrayList<Point3D> listeParcelle = new ArrayList<>();
        listeParcelle.add(new Point3D(0.0,1.0,-1.0));
        listeParcelle.add(new Point3D(1.0,0.0,-1.0));
        listeParcelle.add(new Point3D(1.0,-1.0,0.0));
        listeParcelle.add(new Point3D(0.0,-1.0,1.0));
        listeParcelle.add(new Point3D(-1.0,0.0,1.0));
        listeParcelle.add(new Point3D(-1.0,1.0,0.0));

        assertEquals(listeAttendue.size(),listeParcelle.size());
        for (int i = 0 ; i < listeAttendue.size(); i++){
            assertEquals(listeAttendue.get(i),listeParcelle.get(i));
        }
    }

    @Test
    public void isParcelleOccupee(){
        plateau.resetPlateau();
        plateau.poser(parcelleJaune,new Point3D(1,-1,0));
        assertTrue(plateau.isParcelleOccupee(new Point3D(1,-1,0)));
        assertFalse(plateau.isParcelleOccupee(new Point3D(2,-1,-1)));
    }

    @Test
    public void isEmplacementOccupee(){
        plateau.resetPlateau();
        plateau.poser(parcelleJaune,new Point3D(1,-1,0));
        assertTrue(plateau.isEmplacementAutorise(new Point3D(1,-1,0)));
        assertFalse(plateau.isEmplacementAutorise(new Point3D(2,-1,-1)));
    }

    @Test
    public void getParcelleVoisineOccupe() {
        plateau.resetPlateau();
        Point3D coordonneCentrale = new Point3D(0,0,0);

        ArrayList<Point3D> listeAttendue = plateau.getParcelleVoisineOccupe(coordonneCentrale);
        assertEquals(0,listeAttendue.size());

        plateau.poser(parcelleEtang,new Point3D(0,1,-1));
        listeAttendue = plateau.getParcelleVoisineOccupe(coordonneCentrale);
        assertEquals(1,listeAttendue.size());

        plateau.poser(parcelleEtang,new Point3D(1,0,-1));
        plateau.poser(parcelleEtang,new Point3D(1,-1,0));
        listeAttendue = plateau.getParcelleVoisineOccupe(coordonneCentrale);
        assertEquals(3,listeAttendue.size());

        plateau.poser(parcelleEtang,new Point3D(0,-1,1));
        plateau.poser(parcelleEtang,new Point3D(-1,0,1));
        plateau.poser(parcelleEtang,new Point3D(-1,1,0));
        listeAttendue = plateau.getParcelleVoisineOccupe(coordonneCentrale);
        assertEquals(6,listeAttendue.size());

        ArrayList<Point3D> listeParcelle = new ArrayList<>();
        listeParcelle.add(new Point3D(0.0,1.0,-1.0));
        listeParcelle.add(new Point3D(1.0,0.0,-1.0));
        listeParcelle.add(new Point3D(1.0,-1.0,0.0));
        listeParcelle.add(new Point3D(0.0,-1.0,1.0));
        listeParcelle.add(new Point3D(-1.0,0.0,1.0));
        listeParcelle.add(new Point3D(-1.0,1.0,0.0));

        for (int i = 0 ; i < listeAttendue.size(); i++){
            assertEquals(listeAttendue.get(i),listeParcelle.get(i));
        }
    }

    @Test
    public void isEmplacementAutorise() {
        plateau.resetPlateau();
        boolean autorisation;

        autorisation = plateau.isEmplacementAutorise(new Point3D(0,1,-1));
        assertTrue(autorisation);

        plateau.poser(parcelleEtang,new Point3D(1,0,-1));
        plateau.poser(parcelleEtang,new Point3D(1,-1,0));

        autorisation = plateau.isEmplacementAutorise(new Point3D(2,-1,-1));

        assertTrue(autorisation);

        autorisation = plateau.isEmplacementAutorise(new Point3D(1,1,-2));

        assertFalse(autorisation);

        autorisation = plateau.isEmplacementAutorise(new Point3D(0,-2,2));

        assertFalse(autorisation);
    }

    @Test
    public void emplacementAutoriseParcellesRegroupees(){
        plateau.resetPlateau();
        ArrayList<Point3D> listeAttendue = plateau.emplacementsAutorise();
        ArrayList<Point3D> listeParcelle = new ArrayList<>();
        listeParcelle.add(new Point3D(0.0,1.0,-1.0));
        listeParcelle.add(new Point3D(1.0,0.0,-1.0));
        listeParcelle.add(new Point3D(1.0,-1.0,0.0));
        listeParcelle.add(new Point3D(0.0,-1.0,1.0));
        listeParcelle.add(new Point3D(-1.0,0.0,1.0));
        listeParcelle.add(new Point3D(-1.0,1.0,0.0));

        assertEquals(6,listeAttendue.size());
        for (int i = 0 ; i < listeAttendue.size(); i++){
            assertEquals(listeAttendue.get(i),listeParcelle.get(i));
        }

        plateau.poser(parcelleEtang,new Point3D(0.0,1.0,-1.0));
        listeParcelle.remove(0);
        listeAttendue = plateau.emplacementsAutorise();

        for (int i = 0 ; i < listeAttendue.size(); i++){
            assertEquals(listeAttendue.get(i),listeParcelle.get(i));
        }

        plateau.poser(parcelleEtang,new Point3D(1.0,0.0,-1.0));
        listeParcelle.remove(0);
        listeParcelle.add(new Point3D(1.0,1.0,-2.0));

        listeAttendue = plateau.emplacementsAutorise();

        assertEquals(5,listeAttendue.size());
        for (int i = 0 ; i < listeAttendue.size(); i++){
            assertEquals(listeAttendue.get(i),listeParcelle.get(i));
        }
    }

    @Test
    public void emplacementAutoriseParcellesDegroupees(){
        plateau.resetPlateau();
        plateau.poser(parcelleEtang,new Point3D(0.0,1.0,-1.0));
        plateau.poser(parcelleEtang,new Point3D(0.0,-1.0,1.0));

        ArrayList<Point3D> listeParcelle = new ArrayList<>();
        listeParcelle.add(new Point3D(1.0,0.0,-1.0));
        listeParcelle.add(new Point3D(1.0,-1.0,0.0));
        listeParcelle.add(new Point3D(-1.0,0.0,1.0));
        listeParcelle.add(new Point3D(-1.0,1.0,0.0));

        ArrayList<Point3D> listeAttendue = plateau.emplacementsAutorise();

        assertEquals(4,listeAttendue.size());

        for (int i = 0 ; i < listeAttendue.size(); i++){
            assertEquals(listeAttendue.get(i),listeParcelle.get(i));
        }
    }

    @Test
    public void getParcelleVoisineMemeCouleur(){
        plateau.resetPlateau();
        plateau.poser(parcelleJaune,new Point3D(1.0,0.0,-1.0));
        plateau.poser(parcelleJaune,new Point3D(0.0,1.0,-1.0));
        plateau.poser(parcelleJaune,new Point3D(1.0,1.0,-2.0));
        plateau.poser(parcelleRose,new Point3D(2.0,0.0,-2.0));
        plateau.poser(parcelleVert,new Point3D(2.0,-1.0,-1.0));
        plateau.poser(parcelleJaune,new Point3D(1.0,-1.0,0.0));

        ArrayList<Point3D> exp = plateau.getParcelleVoisineMemeCouleur(new Point3D(1.0,0.0,-1.0));

        assertEquals(3,exp.size());

        assertTrue(exp.contains(new Point3D(0.0,1.0,-1.0)));
        assertTrue(exp.contains(new Point3D(1.0,1.0,-2.0)));
        assertTrue(exp.contains(new Point3D(1.0,-1.0,0.0)));
    }







}
