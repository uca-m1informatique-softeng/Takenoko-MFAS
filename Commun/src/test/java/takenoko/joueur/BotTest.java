package takenoko.joueur;

import javafx.geometry.Point3D;

import org.junit.Before;
import org.junit.Test;
import takenoko.moteur.*;
import takenoko.moteur.objectifs.ObjectifJardinier;
import takenoko.moteur.objectifs.ObjectifPanda;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * La classe test du Bot
 */
public class BotTest {
    Partie partie;
    Plateau plateau ;
    Bot bot ;
    Parcelle parcelleJaune;
    Parcelle parcelleVerte;
    ObjectifJardinier objectifJardinier;
    ObjectifPanda objectifPanda;

    @Before
    public void setup(){
        this.partie = new Partie();
        this.plateau = Plateau.getInstance();
        this.bot = new Bot();
        bot.setCouleur(Enums.CouleurBot.ROUGE);
        this.parcelleJaune = new Parcelle();
        parcelleJaune.setIrriguee(false);
        parcelleJaune.setType(Enums.TypeParcelle.JAUNE);
        this.parcelleVerte= new Parcelle();
        parcelleVerte.setIrriguee(false);
        parcelleVerte.setType(Enums.TypeParcelle.VERTE);
        this.objectifJardinier = new ObjectifJardinier();
        objectifJardinier.setCouleur(Enums.TypeParcelle.JAUNE);
        objectifJardinier.setValeur(6);
        objectifJardinier.setTailleBambou(4);
        this.objectifPanda = new ObjectifPanda();
        objectifPanda.setValeur(6);
        objectifPanda.setCouleur(Enums.TypeParcelle.JAUNE);
        objectifPanda.setNombreBambou(3);
    }

    @Test
    public void choixCoordonnePoseParcelle(){
        plateau.poser(parcelleJaune,new Point3D(-1,1,0));
        ArrayList<Point3D> listeAttendue = plateau.emplacementsAutorise();
        assertEquals(new Point3D(0,1,-1),bot.choixCoordonnePoseParcelle(listeAttendue,parcelleJaune));
    }

    @Test
    public void choixCoordonnePoseIrrigation(){
        plateau.resetPlateau();
        plateau.poser(parcelleJaune,new Point3D(-1,1,0));
        ArrayList<Point3D> listeAttendue = plateau.emplacementsAutoriseIrrigation();
        assertEquals(new Point3D(-1,0.5,0.5),bot.choixCoordonnePoseIrrigation(listeAttendue));
    }

    @Test
    public void choixParcellePioche(){
        ArrayList<Parcelle> listeAttendue = new ArrayList<>();
        listeAttendue.add(parcelleVerte);
        listeAttendue.add(parcelleJaune);

        assertEquals(Enums.TypeParcelle.VERTE,bot.choixParcellePioche(listeAttendue).getType());
    }

    @Test
    public void choixDeplacementJardinier(){
        plateau.resetPlateau();
        plateau.poser(parcelleJaune,new Point3D(-1,1,0));
        plateau.poser(parcelleJaune,new Point3D(0,1,-1));
        ArrayList<Point3D> liste = new ArrayList<>();
        liste.add(new Point3D(-1,1,0));
        liste.add(new Point3D(0,1,-1));

        assertEquals(new Point3D(-1,1,0),bot.choixDeplacementJardinier(liste));
    }

    @Test
    public void choixDeplacementPanda(){
        plateau.resetPlateau();
        plateau.poser(parcelleJaune,new Point3D(-1,1,0));
        plateau.poser(parcelleJaune,new Point3D(0,1,-1));
        ArrayList<Point3D> liste = new ArrayList<>();
        liste.add(new Point3D(-1,1,0));
        liste.add(new Point3D(0,1,-1));

        assertEquals(new Point3D(-1,1,0),bot.choixDeplacementPanda(liste));
    }

    @Test
    public void choixTypeAction(){
        ArrayList<Enums.Action> liste = new ArrayList<>();
        liste.add(Enums.Action.PIOCHERPARCELLE);
        liste.add(Enums.Action.PIOCHEROBJECTIFPARCELLE);

        assertEquals(Enums.Action.PIOCHERPARCELLE,bot.choixTypeAction(liste));
    }

    @Test
    public void choixObjectifPrioritaire(){

        bot.addObjectif(objectifJardinier);
        bot.addObjectif(objectifPanda);
        assertEquals(objectifJardinier.getCouleur(),bot.choixObjectifPrioritaire().getCouleur());
        assertEquals(objectifJardinier.getValeur(),bot.choixObjectifPrioritaire().getValeur());

    }

}