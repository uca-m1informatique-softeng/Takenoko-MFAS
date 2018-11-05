package takenoko.joueur;

import javafx.geometry.Point3D;

import org.junit.Before;
import org.junit.Test;
import takenoko.moteur.*;
import takenoko.moteur.objectifs.ObjectifJardinier;
import takenoko.moteur.objectifs.ObjectifPanda;
import takenoko.moteur.objectifs.ObjectifParcelle;
import takenoko.moteur.personnages.Jardinier;
import takenoko.moteur.personnages.Panda;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;


public class BotParcelleTest {

    Partie partie;
    Plateau plateau ;
    Parcelle parcelleJaune;
    Parcelle parcelleJaune2;
    Parcelle parcelleVerte ;
    BotParcelle botParcelle;
    ObjectifPanda objectifPandaJaune ;
    ObjectifParcelle objectifParcelleJauneDroit ;
    ObjectifParcelle objectifParcelleRose;
    ObjectifJardinier objectifJardinier;

    @Before
    public void setup(){
        this.partie = new Partie();
        this.plateau = Plateau.getInstance();
        plateau.resetPlateau();
        Panda.getInstance().resetPersonnage();
        Jardinier.getInstance().resetPersonnage();
        this.parcelleJaune = new Parcelle();
        this.parcelleJaune2 = new Parcelle();
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
        this.objectifPandaJaune = new ObjectifPanda();
        objectifPandaJaune.setValeur(4);
        objectifPandaJaune.setCouleur(Enums.TypeParcelle.JAUNE);
        objectifPandaJaune.setNombreBambou(2);
        this.botParcelle = new BotParcelle();
        botParcelle.setCouleur(Enums.CouleurBot.ROUGE);
        this.objectifParcelleJauneDroit = new ObjectifParcelle();
        objectifParcelleJauneDroit.setType(0);
        objectifParcelleJauneDroit.setValeur(3);
        objectifParcelleJauneDroit.setCouleur(Enums.TypeParcelle.JAUNE);
        this.objectifParcelleRose= new ObjectifParcelle();
        objectifParcelleRose.setCouleur(Enums.TypeParcelle.ROSE);
        objectifParcelleRose.setValeur(4);
        objectifParcelleRose.setType(0);
        this.objectifJardinier = new ObjectifJardinier();
        objectifJardinier.setTailleBambou(4);
        objectifJardinier.setValeur(6);
        objectifJardinier.setCouleur(Enums.TypeParcelle.JAUNE);
    }

    @Test
    public void choixDeplacementPanda() {
        setup();
        plateau.resetPlateau();

        plateau.poser(parcelleVerte,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));

        ArrayList<Point3D> possibilites = partie.getPanda().destinationsPossibles();
        Point3D pointAttendu = new Point3D(0,1,-1);
        assertEquals(botParcelle.choixDeplacementPanda(possibilites),pointAttendu);

        parcelleJaune.pousserBambou();
        pointAttendu = new Point3D(1,0,-1);
        assertEquals(botParcelle.choixDeplacementPanda(possibilites),pointAttendu);

    }


    @Test
    public void choixParcellePiocheZeroParcelleVoulu(){
        botParcelle.addObjectif(objectifParcelleJauneDroit);
        ArrayList<Parcelle> possibilites = new ArrayList<>();
        possibilites.add(parcelleVerte);
        possibilites.add(parcelleVerte);
        possibilites.add(parcelleVerte);
        assertEquals(botParcelle.choixParcellePioche(possibilites), parcelleVerte);
    }

    @Test
    public void choixParcellePiocheUneParcelleVoulu(){
        botParcelle.addObjectif(objectifParcelleJauneDroit);
        ArrayList<Parcelle> possibilites = new ArrayList<>();
        possibilites.add(parcelleVerte);
        possibilites.add(parcelleJaune);
        possibilites.add(parcelleVerte);
        assertEquals(botParcelle.choixParcellePioche(possibilites).getType(), parcelleJaune.getType());

    }

    @Test
    public void choixParcellePiocheDeuxParcellesVoulus(){
        botParcelle.addObjectif(objectifParcelleJauneDroit);
        ArrayList<Parcelle> possibilites = new ArrayList<>();
        possibilites.add(parcelleVerte);
        possibilites.add(parcelleJaune);
        possibilites.add(parcelleJaune2);

        assertEquals(botParcelle.choixParcellePioche(possibilites).getType(), parcelleJaune.getType());
    }

    @Test
    public void choixObjectifPrioritaire(){
        botParcelle.addObjectif(objectifJardinier);

        assertEquals(botParcelle.choixObjectifPrioritaire(),objectifJardinier);

        botParcelle.addObjectif(objectifParcelleJauneDroit);

        assertEquals(botParcelle.choixObjectifPrioritaire(),objectifParcelleJauneDroit);


        botParcelle.addObjectif(objectifParcelleRose);

        assertEquals(botParcelle.choixObjectifPrioritaire(),objectifParcelleJauneDroit);

    }

    @Test
    public void choixCoordonnePoseParcelle(){
        plateau.resetPlateau();
        plateau.poser(parcelleVerte,new Point3D(0,1,-1));

        plateau.poser(parcelleJaune,new Point3D(1,-1,0));

        Point3D pointAttendu = new Point3D(1,0,-1);

        assertEquals(botParcelle.choixCoordonnePoseParcelle(plateau.emplacementsAutorise(),parcelleVerte),pointAttendu);

        plateau.poser(parcelleVerte,pointAttendu);

        pointAttendu = new Point3D(1,1,-2);

        assertEquals(botParcelle.choixCoordonnePoseParcelle(plateau.emplacementsAutorise(),parcelleVerte),pointAttendu);

    }


}