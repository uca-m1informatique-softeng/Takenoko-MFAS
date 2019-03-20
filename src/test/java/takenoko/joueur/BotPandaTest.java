package takenoko.joueur;

import javafx.geometry.Point3D;

import org.junit.Before;
import org.junit.Test;
import takenoko.joueur.BotPanda;
import takenoko.moteur.*;
import takenoko.moteur.objectifs.ObjectifJardinier;
import takenoko.moteur.objectifs.ObjectifPanda;
import takenoko.moteur.personnages.Jardinier;
import takenoko.moteur.personnages.Panda;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

/**
 * La classe test du BotPanda
 */
public class BotPandaTest {

    Partie partie;
    Plateau plateau ;
    Parcelle parcelleJaune;
    Parcelle parcelleJaune2;
    Parcelle parcelleVerte ;
    BotPanda botPanda ;
    ObjectifJardinier objectifJardinier;
    ObjectifPanda objectifPandaJaune ;
    ObjectifPanda objectifPandaJaune2;
    ObjectifPanda objectifPandaRose;


    @Before
    public void setup(){
        this.partie = new Partie();
        this.plateau = Plateau.getInstance();
        Panda.getInstance().resetPersonnage();
        Jardinier.getInstance().resetPersonnage();
        this.parcelleJaune = new Parcelle();
        this.parcelleVerte = new Parcelle();
        parcelleVerte.setIrriguee(false);
        parcelleVerte.setType(Enums.TypeParcelle.VERTE);
        this.parcelleJaune = new Parcelle();
        parcelleJaune.setIrriguee(false);
        parcelleJaune.setType(Enums.TypeParcelle.JAUNE);
        this.parcelleJaune2 = new Parcelle();
        parcelleJaune2.setIrriguee(false);
        parcelleJaune2.setType(Enums.TypeParcelle.JAUNE);
        this.botPanda = new BotPanda();
        botPanda.setCouleur(Enums.CouleurBot.ROUGE);
        this.objectifPandaJaune = new ObjectifPanda();
        objectifPandaJaune.setValeur(4);
        objectifPandaJaune.setCouleur(Enums.TypeParcelle.JAUNE);
        objectifPandaJaune.setNombreBambou(2);
        this.objectifPandaJaune2 = new ObjectifPanda();
        objectifPandaJaune2.setValeur(6);
        objectifPandaJaune2.setCouleur(Enums.TypeParcelle.JAUNE);
        objectifPandaJaune2.setNombreBambou(4);
        this.objectifJardinier = new ObjectifJardinier();
        objectifJardinier.setCouleur(Enums.TypeParcelle.JAUNE);
        objectifJardinier.setValeur(6);
        objectifJardinier.setTailleBambou(4);

         this.objectifPandaRose = new ObjectifPanda();
         objectifPandaRose.setNombreBambou(2);
         objectifPandaRose.setCouleur(Enums.TypeParcelle.ROSE);
         objectifPandaRose.setValeur(5);

    }

    @Test
    public void choixDeplacementJardinierZeroParcelleCouleurVoulu() {
        plateau.resetPlateau();
        plateau.poser(parcelleVerte,new Point3D(0,1,-1));
        plateau.poser(parcelleVerte,new Point3D(1,0,-1));
        botPanda.addObjectif(objectifPandaJaune);

        ArrayList<Point3D> possibilites = partie.getJardinier().destinationsPossibles();
        Point3D pointAttendu = new Point3D(0,1,-1);

        assertEquals(botPanda.choixDeplacementJardinier(possibilites),pointAttendu);

    }

    @Test
    public void choixDeplacementJardinierUneParcelleCouleurVoulu(){
        plateau.resetPlateau();
        plateau.poser(parcelleVerte,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));
        botPanda.addObjectif(objectifPandaJaune);

        ArrayList<Point3D> possibilites = partie.getJardinier().destinationsPossibles();
        Point3D pointAttendu = new Point3D(1,0,-1);

        assertEquals(botPanda.choixDeplacementJardinier(possibilites),pointAttendu);
    }

    @Test
    public void choixDeplacementJardinierDeuxParcellesCouleurVoulu(){
        plateau.resetPlateau();
        plateau.poser(parcelleJaune2,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));
        plateau.poser(parcelleVerte,new Point3D(1,-1,0));
        botPanda.addObjectif(objectifPandaJaune2);

        ArrayList<Point3D> possibilites = partie.getJardinier().destinationsPossibles();
        Point3D pointAttendu = new Point3D(0,1,-1);

        assertEquals(botPanda.choixDeplacementJardinier(possibilites),pointAttendu);

        parcelleJaune2.pousserBambou();

        assertEquals(botPanda.choixDeplacementJardinier(possibilites),pointAttendu);

    }

    @Test
    public void choixDeplacementPandaZeroParcelleCouleurVoulu() {
        plateau.resetPlateau();
        plateau.poser(parcelleVerte,new Point3D(0,1,-1));
        plateau.poser(parcelleVerte,new Point3D(1,0,-1));
        botPanda.addObjectif(objectifPandaJaune2);

        ArrayList<Point3D> possibilites = partie.getPanda().destinationsPossibles();
        Point3D pointAttendu = new Point3D(0,1,-1);

        assertEquals(botPanda.choixDeplacementPanda(possibilites),pointAttendu);

    }

    @Test
    public void choixDeplacementPandaUneParcelleCouleurVoulu(){
        plateau.resetPlateau();
        plateau.poser(parcelleVerte,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));
        botPanda.addObjectif(objectifPandaJaune2);

        ArrayList<Point3D> possibilites = partie.getPanda().destinationsPossibles();
        Point3D pointAttendu = new Point3D(1,0,-1);

        assertEquals(botPanda.choixDeplacementPanda(possibilites),pointAttendu);
    }

    @Test
    public void choixDeplacementPandaDeuxParcellesCouleurVoulu(){
        plateau.resetPlateau();
        plateau.poser(parcelleJaune,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune2,new Point3D(1,0,-1));
        plateau.poser(parcelleVerte,new Point3D(1,-1,0));
        botPanda.addObjectif(objectifPandaJaune);

        ArrayList<Point3D> possibilites = partie.getPanda().destinationsPossibles();
        Point3D pointAttendu = new Point3D(0,1,-1);
        System.out.println(parcelleJaune.getNbBambou());
        assertEquals(botPanda.choixDeplacementPanda(possibilites),pointAttendu);

        parcelleJaune2.pousserBambou();

        pointAttendu = new Point3D(1,0,-1);
        assertEquals(botPanda.choixDeplacementPanda(possibilites),pointAttendu);
    }

    @Test
    public void choixParcellePiocheZeroParcelleVoulu(){
        botPanda.addObjectif(objectifPandaJaune);
        ArrayList<Parcelle> possibilites = new ArrayList<>();
        possibilites.add(parcelleVerte);
        possibilites.add(parcelleVerte);
        possibilites.add(parcelleVerte);
        assertEquals(botPanda.choixParcellePioche(possibilites), parcelleVerte);
    }

    @Test
    public void choixParcellePiocheUneParcelleVoulu(){
        botPanda.addObjectif(objectifPandaJaune);
        ArrayList<Parcelle> possibilites = new ArrayList<>();
        possibilites.add(parcelleVerte);
        possibilites.add(parcelleJaune);
        possibilites.add(parcelleVerte);
        assertEquals(botPanda.choixParcellePioche(possibilites), parcelleJaune);

    }

    @Test
    public void choixParcellePiocheDeuxParcellesVoulus(){
        botPanda.addObjectif(objectifPandaJaune);
        ArrayList<Parcelle> possibilites = new ArrayList<>();
        possibilites.add(parcelleVerte);
        possibilites.add(parcelleJaune);
        possibilites.add(parcelleJaune2);

        assertEquals(botPanda.choixParcellePioche(possibilites), parcelleJaune);
    }

    @Test
    public void choixObjectifPrioritaire(){

        botPanda.addObjectif(objectifJardinier);

        assertEquals(botPanda.choixObjectifPrioritaire(),objectifJardinier);

        botPanda.addObjectif(objectifPandaJaune);

        assertEquals(botPanda.choixObjectifPrioritaire(),objectifPandaJaune);

        botPanda.addObjectif(objectifPandaRose);

        assertEquals(botPanda.choixObjectifPrioritaire(),objectifPandaJaune);

    }
}