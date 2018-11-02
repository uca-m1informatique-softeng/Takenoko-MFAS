package joueur;

import javafx.geometry.Point3D;
import moteur.Enums;
import moteur.Parcelle;
import moteur.Partie;
import moteur.Plateau;
import moteur.objectifs.Objectif;
import moteur.objectifs.ObjectifJardinier;
import moteur.objectifs.ObjectifPanda;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * La classe test du Bot.
 */
public class BotTest {
    Partie partie=new Partie();
    Plateau plateau =partie.getPlateau();
    Bot bot = new Bot(Enums.CouleurBot.ROUGE);

    @Test
    public void choixCoordonnePoseParcelle(){
        plateau.poser(new Parcelle (Enums.TypeParcelle.JAUNE),new Point3D(-1,1,0));
        ArrayList<Point3D> listeAttendue = plateau.emplacementsAutorise();
        assertEquals(new Point3D(0,1,-1),bot.choixCoordonnePoseParcelle(listeAttendue,new Parcelle (Enums.TypeParcelle.JAUNE)));
    }

    @Test
    public void choixCoordonnePoseIrrigation(){
        plateau.resetPlateau();
        plateau.poser(new Parcelle (Enums.TypeParcelle.JAUNE),new Point3D(-1,1,0));
        ArrayList<Point3D> listeAttendue = plateau.emplacementsAutoriseIrrigation();
        assertEquals(new Point3D(-1,0.5,0.5),bot.choixCoordonnePoseIrrigation(listeAttendue));
    }

    @Test
    public void choixParcellePioche(){
        ArrayList<Parcelle> listeAttendue = new ArrayList<>();
        listeAttendue.add(new Parcelle(Enums.TypeParcelle.VERTE));
        listeAttendue.add(new Parcelle(Enums.TypeParcelle.JAUNE));

        assertEquals(Enums.TypeParcelle.VERTE,bot.choixParcellePioche(listeAttendue).getType());
    }

    @Test
    public void choixDeplacementJardinier(){
        plateau.resetPlateau();
        plateau.poser(new Parcelle (Enums.TypeParcelle.JAUNE),new Point3D(-1,1,0));
        plateau.poser(new Parcelle (Enums.TypeParcelle.JAUNE),new Point3D(0,1,-1));
        ArrayList<Point3D> liste = new ArrayList<>();
        liste.add(new Point3D(-1,1,0));
        liste.add(new Point3D(0,1,-1));

        assertEquals(new Point3D(-1,1,0),bot.choixDeplacementJardinier(liste));
    }

    @Test
    public void choixDeplacementPanda(){
        plateau.resetPlateau();
        plateau.poser(new Parcelle (Enums.TypeParcelle.JAUNE),new Point3D(-1,1,0));
        plateau.poser(new Parcelle (Enums.TypeParcelle.JAUNE),new Point3D(0,1,-1));
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

        bot.addObjectif(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));
        bot.addObjectif(new ObjectifPanda(6, Enums.TypeParcelle.JAUNE,3));
        assertEquals(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4).getCouleur(),bot.choixObjectifPrioritaire().getCouleur());
        assertEquals(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4).getValeur(),bot.choixObjectifPrioritaire().getValeur());

    }

}