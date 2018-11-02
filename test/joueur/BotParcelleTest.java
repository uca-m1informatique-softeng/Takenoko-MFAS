package joueur;

import javafx.geometry.Point3D;
import moteur.Enums;
import moteur.Parcelle;
import moteur.Partie;
import moteur.Plateau;
import moteur.objectifs.ObjectifJardinier;
import moteur.objectifs.ObjectifPanda;
import moteur.objectifs.ObjectifParcelle;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;


/**
 * La classe test du BotParcelle.
 */
public class BotParcelleTest {

    Partie partie=new Partie();
    Plateau plateau =partie.getPlateau();
    Parcelle parcelleJaune = new Parcelle(Enums.TypeParcelle.JAUNE);
    Parcelle parcelleVerte = new Parcelle(Enums.TypeParcelle.VERTE);
    BotParcelle botParcelle = new BotParcelle(Enums.CouleurBot.ROUGE);
    ObjectifPanda objectifPandaJaune = new ObjectifPanda(4, Enums.TypeParcelle.JAUNE,2);
    ObjectifParcelle objectifParcelleJauneDroit = new ObjectifParcelle(3, Enums.TypeParcelle.JAUNE,0);

    @Test
    public void choixDeplacementPanda() {
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
        possibilites.add(new Parcelle(Enums.TypeParcelle.VERTE));
        possibilites.add(new Parcelle(Enums.TypeParcelle.VERTE));
        assertEquals(botParcelle.choixParcellePioche(possibilites), parcelleVerte);
    }

    @Test
    public void choixParcellePiocheUneParcelleVoulu(){
        botParcelle.addObjectif(objectifParcelleJauneDroit);
        ArrayList<Parcelle> possibilites = new ArrayList<>();
        possibilites.add(parcelleVerte);
        possibilites.add(parcelleJaune);
        possibilites.add(new Parcelle(Enums.TypeParcelle.VERTE));
        assertEquals(botParcelle.choixParcellePioche(possibilites), parcelleJaune);

    }

    @Test
    public void choixParcellePiocheDeuxParcellesVoulus(){
        botParcelle.addObjectif(objectifParcelleJauneDroit);
        Parcelle parcelleJaune2 = new Parcelle(Enums.TypeParcelle.JAUNE);
        ArrayList<Parcelle> possibilites = new ArrayList<>();
        possibilites.add(parcelleVerte);
        possibilites.add(parcelleJaune);
        possibilites.add(parcelleJaune2);

        assertEquals(botParcelle.choixParcellePioche(possibilites), parcelleJaune);
    }

    @Test
    public void choixObjectifPrioritaire(){
        ObjectifJardinier objectifJardinier = new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4);
        botParcelle.addObjectif(objectifJardinier);

        assertEquals(botParcelle.choixObjectifPrioritaire(),objectifJardinier);

        botParcelle.addObjectif(objectifParcelleJauneDroit);

        assertEquals(botParcelle.choixObjectifPrioritaire(),objectifParcelleJauneDroit);

        ObjectifParcelle ObjectifParcelleRose= new ObjectifParcelle(4, Enums.TypeParcelle.ROSE,0);
        botParcelle.addObjectif(ObjectifParcelleRose);

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