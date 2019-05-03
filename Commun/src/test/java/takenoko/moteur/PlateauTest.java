package takenoko.moteur;

import javafx.geometry.Point3D;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;
import takenoko.moteur.Enums.TypeParcelle;


/**
 * La classe test du plateau
 */

public class PlateauTest {
    Partie partie ;
    Plateau plateau ;
    Point3D coordonneCentre ;
    Point3D coordonneVoisin ;
    Parcelle parcelleEtang;
    Parcelle parcelleJaune ;
    Parcelle parcelleRose ;
    Parcelle parcelleVerte ;

    @Before
    public void setup(){
        this.partie = new Partie();
        this.plateau = Plateau.getInstance();
        this.coordonneCentre = new Point3D(0,0,0);
        this.coordonneVoisin = new Point3D(1,0,-1);
        this.parcelleJaune = new Parcelle();
        parcelleJaune.setIrriguee(false);
        parcelleJaune.setType(TypeParcelle.JAUNE);
        this.parcelleVerte = new Parcelle();
        parcelleVerte.setIrriguee(false);
        parcelleVerte.setType(Enums.TypeParcelle.VERTE);
        this.parcelleRose = new Parcelle();
        parcelleRose.setIrriguee(false);
        parcelleRose.setType(Enums.TypeParcelle.VERTE);
        this.parcelleEtang = new Parcelle();
        parcelleEtang.setIrriguee(true);
        parcelleEtang.setType(TypeParcelle.ETANG);

    }

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
        plateau.poser(parcelleVerte,new Point3D(2.0,-1.0,-1.0));
        plateau.poser(parcelleJaune,new Point3D(1.0,-1.0,0.0));

        ArrayList<Point3D> exp = plateau.getParcelleVoisineMemeCouleur(new Point3D(1.0,0.0,-1.0));

        assertEquals(3,exp.size());

        assertTrue(exp.contains(new Point3D(0.0,1.0,-1.0)));
        assertTrue(exp.contains(new Point3D(1.0,1.0,-2.0)));
        assertTrue(exp.contains(new Point3D(1.0,-1.0,0.0)));
    }

    @Test
    public void ParcelleSuivantMotif(){
        plateau.resetPlateau();
        Point3D pointCourant = new Point3D(1.0,0.0,-1.0);
        plateau.poser(parcelleJaune,pointCourant);
        plateau.poser(parcelleJaune,new Point3D(0.0,1.0,-1.0));

        assertTrue(plateau.parcelleSuivanteMotif(pointCourant,TypeParcelle.JAUNE,5));

        plateau.poser(parcelleRose,new Point3D(1.0,-1.0,0.0));
        assertFalse(plateau.parcelleSuivanteMotif(pointCourant,TypeParcelle.JAUNE,3));
    }

    @Test
    public void chercheMotifParcelleAligne(){
        plateau.resetPlateau();
        plateau.poser(parcelleJaune,new Point3D(1.0,0.0,-1.0));
        plateau.poser(parcelleJaune,new Point3D(0.0,1.0,-1.0));

        assertFalse(plateau.chercheMotifParcelle(new Point3D(0.0,1.0,-1.0),TypeParcelle.JAUNE,0));

        plateau.poser(parcelleJaune,new Point3D(2,-1.0,-1.0));

        assertTrue(plateau.chercheMotifParcelle(new Point3D(0.0,1.0,-1.0),TypeParcelle.JAUNE,0));
    }

    @Test
    public void chercheMotifParcelleCourbée(){
        plateau.resetPlateau();
        plateau.poser(parcelleJaune,new Point3D(-1.0,1.0,0.0));
        plateau.poser(parcelleJaune,new Point3D(0.0,1.0,-1.0));

        assertFalse(plateau.chercheMotifParcelle(new Point3D(-1.0,1.0,0.0),TypeParcelle.JAUNE,1));

        plateau.poser(parcelleJaune,new Point3D(1.0,0.0,-1.0));

        assertTrue(plateau.chercheMotifParcelle(new Point3D(-1.0,1.0,0.0),TypeParcelle.JAUNE,1));
    }

    @Test
    public void chercheMotifParcelleTriangle(){
        plateau.resetPlateau();
        plateau.poser(parcelleJaune,new Point3D(0.0,1.0,-1.0));
        plateau.poser(parcelleJaune,new Point3D(1.0,0.0,-1.0));
        assertFalse(plateau.chercheMotifParcelle(new Point3D(0.0,1.0,-1.0),TypeParcelle.JAUNE,2));
        plateau.poser(parcelleJaune,new Point3D(1.0,1.0,-2.0));
        assertTrue(plateau.chercheMotifParcelle(new Point3D(0.0,1.0,-1.0),TypeParcelle.JAUNE,2));

    }
    @Test
    public void chercheMotifParcelleLosange(){
        plateau.resetPlateau();
        plateau.poser(parcelleJaune,new Point3D(0.0,1.0,-1.0));
        plateau.poser(parcelleJaune,new Point3D(1.0,0.0,-1.0));
        plateau.poser(parcelleJaune,new Point3D(1.0,1.0,-2.0));
        assertFalse(plateau.chercheMotifParcelle(new Point3D(1.0,0.0,-1.0),TypeParcelle.JAUNE,3));
        plateau.poser(parcelleJaune,new Point3D(2.0,0.0,-2.0));
        assertTrue(plateau.chercheMotifParcelle(new Point3D(1.0,0.0,-1.0),TypeParcelle.JAUNE,3));
    }

    /*irrigation*/

    @Test
    public void getIrrigationVoisineDeParcelle(){
        plateau.resetPlateau();
        plateau.poser(parcelleJaune,new Point3D(0.0,1.0,-1.0));
        ArrayList<Point3D> irrigationArrayList = new ArrayList<Point3D>();
        ArrayList<Point3D> listAttendue = plateau.getIrrigationVoisineDeParcelle(new Point3D(0.0,1.0,-1.0));

        irrigationArrayList.add(new Point3D(0.5,1.0,-1.5));
        irrigationArrayList.add(new Point3D(0.5,0.5,-1.0));
        irrigationArrayList.add(new Point3D(0.0,0.5,-0.5));
        irrigationArrayList.add(new Point3D(-0.5,1.0,-0.5));
        irrigationArrayList.add(new Point3D(-0.5,1.5,-1.0));
        irrigationArrayList.add(new Point3D(0.0,1.5,-1.5));

        assertEquals(listAttendue,irrigationArrayList);
    }
    @Test
    public void sensIrrigation (){

        plateau.resetPlateau();
        int num = 1;
        int val = plateau.sensIrrigation(new Point3D(0.5,1.0,-1.5));
        assertEquals(num,val);
        val=plateau.sensIrrigation(new Point3D(0.0,1.5,-1.5));
        num = 3;
        assertEquals(num,val);
    }

    @Test
    public void getIrrigationVoisine(){

        plateau.resetPlateau();
        ArrayList<Point3D> listAttendue = plateau.getIrrigationVoisine(new Point3D(0.5,1.0,-1.5));
        ArrayList<Point3D> irrigationArrayList = new ArrayList<Point3D>();

        irrigationArrayList.add(new Point3D(0.5,1.5,-2.0));
        irrigationArrayList.add(new Point3D(0.0,1.5,-1.5));
        irrigationArrayList.add(new Point3D(1.0,0.5,-1.5));
        irrigationArrayList.add(new Point3D(0.5,0.5,-1.0));

        assertEquals(listAttendue,irrigationArrayList);

        ArrayList<Point3D> listAttendue2 = plateau.getIrrigationVoisine(new Point3D(0.5,1.0,-1.5));
        ArrayList<Point3D> irrigationArrayList2 = new ArrayList<Point3D>();

        irrigationArrayList2.add(new Point3D(0.5,1.5,-2.0));
        irrigationArrayList2.add(new Point3D(0.0,1.5,-1.5));
        irrigationArrayList2.add(new Point3D(1.0,0.5,-1.5));
        irrigationArrayList2.add(new Point3D(0.5,0.5,-1.0));

        assertEquals(listAttendue2,irrigationArrayList2);


    }

    @Test
    public void getIrrigationVoisineLibre() {
        plateau.resetPlateau();
        ArrayList<Point3D> listAttendue = plateau.getIrrigationVoisineLibre(new Point3D(0.5, 1.0, -1.5));
        ArrayList<Point3D> irrigationArrayList = new ArrayList<Point3D>();

        irrigationArrayList.add(new Point3D(0.5,1.5,-2.0));
        irrigationArrayList.add(new Point3D(0.0,1.5,-1.5));
        irrigationArrayList.add(new Point3D(1.0,0.5,-1.5));
        irrigationArrayList.add(new Point3D(0.5,0.5,-1.0));

        assertEquals(listAttendue,irrigationArrayList);

        plateau.poserIrrigation(new Irrigation(),new Point3D(0.0,1.5,-1.5));
        ArrayList<Point3D> listAttendue2 = plateau.getIrrigationVoisineLibre(new Point3D(0.5, 1.0, -1.5));
        ArrayList<Point3D> irrigationArrayList2 = new ArrayList<Point3D>();

        irrigationArrayList2.add(new Point3D(0.5,1.5,-2.0));
        irrigationArrayList2.add(new Point3D(1.0,0.5,-1.5));
        irrigationArrayList2.add(new Point3D(0.5,0.5,-1.0));

        assertEquals(listAttendue2,irrigationArrayList2);

    }

    @Test
    public void getIrrigationVoisineOccupe() {

        plateau.resetPlateau();
        ArrayList<Point3D> listAttendue = plateau.getIrrigationVoisineOccupe(new Point3D(0.5, 1.0, -1.5));
        ArrayList<Point3D> irrigationArrayList = new ArrayList<Point3D>();

        assertEquals(listAttendue,irrigationArrayList);

        plateau.poserIrrigation(new Irrigation(),new Point3D(0.0,1.5,-1.5));
        ArrayList<Point3D> listAttendue2 = plateau.getIrrigationVoisineOccupe(new Point3D(0.5, 1.0, -1.5));
        ArrayList<Point3D> irrigationArrayList2 = new ArrayList<Point3D>();

        irrigationArrayList2.add(new Point3D(0.0,1.5,-1.5));

        assertEquals(listAttendue2,irrigationArrayList2);

    }

    @Test
    public void getcoordonneParcelleAdjacenteIrrigation(){

        plateau.resetPlateau();
        ArrayList<Point3D> listAttendue = plateau.getcoordonneParcelleAdjacenteIrrigation(new Point3D(0.5, 1.0, -1.5));
        ArrayList<Point3D> parcelleArrayList = new ArrayList<Point3D>();

        parcelleArrayList.add(new Point3D(0.0,1.0,-1.0));
        parcelleArrayList.add(new Point3D(1.0,1.0,-2.0));

        assertEquals(listAttendue,parcelleArrayList);

        ArrayList<Point3D> listAttendue2 = plateau.getcoordonneParcelleAdjacenteIrrigation(new Point3D(0.5,0.0,-0.5));
        ArrayList<Point3D> parcelleArrayList2 = new ArrayList<Point3D>();

        parcelleArrayList2.add(new Point3D(0,0,0));
        parcelleArrayList2.add(new Point3D(1,0,-1));

        assertEquals(listAttendue2,parcelleArrayList2);

    }
    @Test
    public void isEmplacementIrrigationAutorise (){

        plateau.resetPlateau();
        plateau.poser(parcelleVerte,new Point3D(1,0.0,-1));
        ArrayList<Point3D> irrigationArrayList = plateau.getIrrigationVoisineDeParcelle(new Point3D(1,0.0,-1));

        assertEquals(false,plateau.isEmplacementIrrigationAutorise(irrigationArrayList.get(0)));
        assertEquals(false,plateau.isEmplacementIrrigationAutorise(irrigationArrayList.get(1)));
        assertEquals(true,plateau.isEmplacementIrrigationAutorise(irrigationArrayList.get(2)));
        assertEquals(true,plateau.isEmplacementIrrigationAutorise(irrigationArrayList.get(3)));
        assertEquals(true,plateau.isEmplacementIrrigationAutorise(irrigationArrayList.get(4)));
        assertEquals(false,plateau.isEmplacementIrrigationAutorise(irrigationArrayList.get(5)));
    }

    @Test
    public void emplacementsAutoriseIrrigation (){
        plateau.resetPlateau();
        plateau.poser(parcelleVerte,new Point3D(1,0,-1));
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));
        plateau.poser(parcelleJaune,new Point3D(1,-1,0));
        plateau.poser(parcelleJaune,new Point3D(0,-1,1));
        plateau.poser(parcelleJaune,new Point3D(-1,0,1));
        plateau.poser(parcelleJaune,new Point3D(-1,1,0));


        ArrayList<Point3D> listAttendue=plateau.emplacementsAutoriseIrrigation ();
        ArrayList<Point3D> irrigationArrayList = new ArrayList<Point3D>();

        irrigationArrayList.add(new Point3D(0.5,0.5,-1.0));
        irrigationArrayList.add(new Point3D(1.0,-0.5,-0.5));
        irrigationArrayList.add(new Point3D(0.5,-1.0,0.5));
        irrigationArrayList.add(new Point3D(-0.5,-0.5,1.0));
        irrigationArrayList.add(new Point3D(-1.0,0.5,0.5));
        irrigationArrayList.add(new Point3D(-0.5,1.0,-0.5));

        assertEquals(listAttendue,irrigationArrayList);

        plateau.poserIrrigation(new Irrigation(),new Point3D(0.5,0.5,-1.0));

        plateau.poser(parcelleJaune,new Point3D(1,1,-2));
        ArrayList<Point3D> listAttendue2=plateau.emplacementsAutoriseIrrigation ();
        ArrayList<Point3D> irrigationArrayList2 = new ArrayList<Point3D>();

        irrigationArrayList2.add(new Point3D(1.0,-0.5,-0.5));
        irrigationArrayList2.add(new Point3D(0.5,-1.0,0.5));
        irrigationArrayList2.add(new Point3D(-0.5,-0.5,1.0));
        irrigationArrayList2.add(new Point3D(-1.0,0.5,0.5));
        irrigationArrayList2.add(new Point3D(-0.5,1.0,-0.5));
        irrigationArrayList2.add(new Point3D(0.5,1.0,-1.5));
        irrigationArrayList2.add(new Point3D(1.0,0.5,-1.5));

        assertEquals(listAttendue2,irrigationArrayList2);

    }

    @Test
    public void poserIrrigation(){

        plateau.resetPlateau();
        plateau.poserIrrigation(new Irrigation(),new Point3D(0.5,0.5,-1.0));
        assertEquals(plateau.getKeylistIrrigation().get(6),new Point3D(0.5,0.5,-1.0));

        plateau.poserIrrigation(new Irrigation(),new Point3D(1.0,0.5,-1.5));
        assertEquals(plateau.getKeylistIrrigation().get(7),new Point3D(1.0,0.5,-1.5));
    }

}