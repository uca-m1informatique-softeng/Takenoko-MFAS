package takenoko.joueur;


import javafx.geometry.Point3D;

import org.junit.Before;
import org.junit.Test;
import takenoko.moteur.*;
import takenoko.moteur.objectifs.ObjectifJardinier;
import takenoko.moteur.objectifs.ObjectifPanda;
import takenoko.moteur.personnages.Jardinier;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class BotJardinierTest {
    Partie partie;
    Plateau plateau ;
    Parcelle parcelleJaune ;
    Parcelle parcelleJaune2;
    Parcelle parcelleVerte;
    BotJardinier botJardinier ;
    ObjectifJardinier objectifJardinier;
    ObjectifJardinier objectifJardinier2;
    ObjectifPanda objectifPanda;

    @Before
    public void setup(){
        this.partie = new Partie();
        this.plateau = Plateau.getInstance();
        this.parcelleJaune = new Parcelle();
        this.parcelleVerte = new Parcelle();
        parcelleVerte.setListBambou(new ArrayList<Bambou>());
        parcelleVerte.setIrriguee(false);
        parcelleVerte.setType(Enums.TypeParcelle.VERTE);
        this.parcelleJaune = new Parcelle();
        parcelleJaune.setListBambou(new ArrayList<Bambou>());
        parcelleJaune.setIrriguee(false);
        parcelleJaune.setType(Enums.TypeParcelle.JAUNE);
        this.parcelleJaune2 = new Parcelle();
        parcelleJaune2.setListBambou(new ArrayList<Bambou>());
        parcelleJaune2.setIrriguee(false);
        parcelleJaune2.setType(Enums.TypeParcelle.JAUNE);
        this.botJardinier = new BotJardinier();
        botJardinier.setCouleur(Enums.CouleurBot.ROUGE);
        this.objectifJardinier = new ObjectifJardinier();
        objectifJardinier.setTailleBambou(4);
        objectifJardinier.setValeur(6);
        objectifJardinier.setCouleur(Enums.TypeParcelle.JAUNE);
        this.objectifJardinier2 = new ObjectifJardinier();
        objectifJardinier2.setTailleBambou(4);
        objectifJardinier2.setValeur(7);
        objectifJardinier2.setCouleur(Enums.TypeParcelle.ROSE);
        this.objectifPanda = new ObjectifPanda();
        objectifPanda.setNombreBambou(2);
        objectifPanda.setCouleur(Enums.TypeParcelle.JAUNE);
        objectifPanda.setValeur(4);


    }

    @Test
    public void choixDeplacementJardinierZeroParcelleCouleurVoulu() {
        plateau.resetPlateau();
        plateau.poser(parcelleVerte,new Point3D(0.0,1.0,-1.0));
        plateau.poser(parcelleVerte,new Point3D(1.0,0.0,-1.0));
        botJardinier.addObjectif(objectifJardinier);

        ArrayList<Point3D> possibilites = Jardinier.getInstance().destinationsPossibles();
        System.out.println("poss " + possibilites);
        Point3D pointAttendu = new Point3D(0.0,1.0,-1.0);
        System.out.println(botJardinier.choixDeplacementJardinier(possibilites));

        assertEquals(botJardinier.choixDeplacementJardinier(possibilites),pointAttendu);

    }

    @Test
    public void choixDeplacementJardinierUneParcelleCouleurVoulu(){
        plateau.resetPlateau();
        partie.getJardinier().resetPersonnage();
        Parcelle pv = new Parcelle();
        plateau.poser(pv,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));
        botJardinier.addObjectif(objectifJardinier);

        System.out.println(plateau.isParcelleOccupee(new Point3D(0,1,-1)));
        ArrayList<Point3D> possibilites = partie.getJardinier().destinationsPossibles();
        Point3D pointAttendu = new Point3D(1,0,-1);

        assertEquals(botJardinier.choixDeplacementJardinier(possibilites),pointAttendu);
    }

    @Test
    public void choixDeplacementJardinierDeuxParcellesCouleurVoulu(){
        plateau.resetPlateau();
        //Parcelle parcelleJaune2 = new Parcelle(Enums.TypeParcelle.JAUNE);
        plateau.poser(parcelleJaune,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune2,new Point3D(1,0,-1));
        plateau.poser(parcelleVerte,new Point3D(1,-1,0));
        botJardinier.addObjectif(objectifJardinier);

        ArrayList<Point3D> possibilites = partie.getJardinier().destinationsPossibles();
        Point3D pointAttendu = new Point3D(0,1,-1);

        assertEquals(botJardinier.choixDeplacementJardinier(possibilites),pointAttendu);

        parcelleJaune.pousserBambou();

        pointAttendu = new Point3D(0,1,-1);
        assertEquals(botJardinier.choixDeplacementJardinier(possibilites),pointAttendu);

    }

    @Test
    public void choixParcellePiocheZeroParcelleVoulu(){
        botJardinier.addObjectif(objectifJardinier);
        ArrayList<Parcelle> possibilites = new ArrayList<>();
        possibilites.add(parcelleVerte);
        possibilites.add(parcelleVerte);
        possibilites.add(parcelleVerte);
        assertEquals(botJardinier.choixParcellePioche(possibilites), parcelleVerte);
    }

    @Test
    public void choixParcellePiocheUneParcelleVoulu(){
        botJardinier.addObjectif(objectifJardinier);
        ArrayList<Parcelle> possibilites = new ArrayList<>();
        possibilites.add(parcelleVerte);
        possibilites.add(parcelleJaune);
        //
        possibilites.add(parcelleVerte);
        assertEquals(botJardinier.choixParcellePioche(possibilites), parcelleJaune);


    }

    @Test
    public void choixParcellePiocheDeuxParcellesVoulus(){
        botJardinier.addObjectif(objectifJardinier);
        //Parcelle parcelleJaune2 = new Parcelle(Enums.TypeParcelle.JAUNE);
        ArrayList<Parcelle> possibilites = new ArrayList<>();
        possibilites.add(parcelleVerte);
        possibilites.add(parcelleJaune);
        possibilites.add(parcelleJaune);

        assertEquals(botJardinier.choixParcellePioche(possibilites), parcelleJaune);
    }

    @Test
    public void choixObjectifPrioritaire(){
        botJardinier.addObjectif(objectifPanda);

        assertEquals(botJardinier.choixObjectifPrioritaire(),objectifPanda);

        ObjectifJardinier objectifJardinierJaune = objectifJardinier;
        botJardinier.addObjectif(objectifJardinierJaune);

        assertEquals(botJardinier.choixObjectifPrioritaire(),objectifJardinierJaune);

        ObjectifJardinier objectifJardinierRose = objectifJardinier2;
        botJardinier.addObjectif(objectifJardinierRose);

        assertEquals(botJardinier.choixObjectifPrioritaire(),objectifJardinierJaune);

    }

}