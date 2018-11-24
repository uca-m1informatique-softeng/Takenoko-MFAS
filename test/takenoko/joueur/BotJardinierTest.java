package takenoko.joueur;

import javafx.geometry.Point3D;
import takenoko.moteur.Enums;
import takenoko.moteur.Parcelle;
import takenoko.moteur.Partie;
import takenoko.moteur.Plateau;
import takenoko.moteur.objectifs.ObjectifJardinier;
import takenoko.moteur.objectifs.ObjectifPanda;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;



public class BotJardinierTest {
    Partie partie=new Partie();
    Plateau plateau =partie.getPlateau();
    Parcelle parcelleJaune = new Parcelle(Enums.TypeParcelle.JAUNE);
    Parcelle parcelleVerte = new Parcelle(Enums.TypeParcelle.VERTE);
    BotJardinier botJardinier = new BotJardinier(Enums.CouleurBot.ROUGE);

    @Test
    public void choixDeplacementJardinierZeroParcelleCouleurVoulu() {
        plateau.resetPlateau();
        plateau.poser(parcelleVerte,new Point3D(0,1,-1));
        plateau.poser(parcelleVerte,new Point3D(1,0,-1));
        botJardinier.addObjectif(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));

        ArrayList<Point3D> possibilites = partie.getJardinier().destinationsPossibles();
        Point3D pointAttendu = new Point3D(0,1,-1);

        assertEquals(botJardinier.choixDeplacementJardinier(possibilites),pointAttendu);

    }

    @Test
    public void choixDeplacementJardinierUneParcelleCouleurVoulu(){
        plateau.resetPlateau();
        plateau.poser(parcelleVerte,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));
        botJardinier.addObjectif(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));

        ArrayList<Point3D> possibilites = partie.getJardinier().destinationsPossibles();
        Point3D pointAttendu = new Point3D(1,0,-1);

        assertEquals(botJardinier.choixDeplacementJardinier(possibilites),pointAttendu);
    }

    @Test
    public void choixDeplacementJardinierDeuxParcellesCouleurVoulu(){
        plateau.resetPlateau();
        Parcelle parcelleJaune2 = new Parcelle(Enums.TypeParcelle.JAUNE);
        plateau.poser(parcelleJaune,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune2,new Point3D(1,0,-1));
        plateau.poser(parcelleVerte,new Point3D(1,-1,0));
        botJardinier.addObjectif(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));

        ArrayList<Point3D> possibilites = partie.getJardinier().destinationsPossibles();
        Point3D pointAttendu = new Point3D(0,1,-1);

        assertEquals(botJardinier.choixDeplacementJardinier(possibilites),pointAttendu);

        parcelleJaune2.pousserBambou();

        pointAttendu = new Point3D(1,0,-1);
        assertEquals(botJardinier.choixDeplacementJardinier(possibilites),pointAttendu);

    }

    @Test
    public void choixParcellePiocheZeroParcelleVoulu(){
        botJardinier.addObjectif(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));
        ArrayList<Parcelle> possibilites = new ArrayList<>();
        possibilites.add(parcelleVerte);
        possibilites.add(new Parcelle(Enums.TypeParcelle.VERTE));
        possibilites.add(new Parcelle(Enums.TypeParcelle.VERTE));
        assertEquals(botJardinier.choixParcellePioche(possibilites), parcelleVerte);
    }

    @Test
    public void choixParcellePiocheUneParcelleVoulu(){
        botJardinier.addObjectif(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));
        ArrayList<Parcelle> possibilites = new ArrayList<>();
        possibilites.add(parcelleVerte);
        possibilites.add(parcelleJaune);
        possibilites.add(new Parcelle(Enums.TypeParcelle.VERTE));
        assertEquals(botJardinier.choixParcellePioche(possibilites), parcelleJaune);


    }

    @Test
    public void choixParcellePiocheDeuxParcellesVoulus(){
        botJardinier.addObjectif(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));
        Parcelle parcelleJaune2 = new Parcelle(Enums.TypeParcelle.JAUNE);
        ArrayList<Parcelle> possibilites = new ArrayList<>();
        possibilites.add(parcelleVerte);
        possibilites.add(parcelleJaune);
        possibilites.add(parcelleJaune2);

        assertEquals(botJardinier.choixParcellePioche(possibilites), parcelleJaune);
    }

    @Test
    public void choixObjectifPrioritaire(){
        ObjectifPanda objectifPanda = new ObjectifPanda(4, Enums.TypeParcelle.JAUNE,2);
        botJardinier.addObjectif(objectifPanda);

        assertEquals(botJardinier.choixObjectifPrioritaire(),objectifPanda);

        ObjectifJardinier objectifJardinierJaune = new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4);
        botJardinier.addObjectif(objectifJardinierJaune);

        assertEquals(botJardinier.choixObjectifPrioritaire(),objectifJardinierJaune);

        ObjectifJardinier objectifJardinierRose = new ObjectifJardinier(7, Enums.TypeParcelle.ROSE,4);
        botJardinier.addObjectif(objectifJardinierRose);

        assertEquals(botJardinier.choixObjectifPrioritaire(),objectifJardinierJaune);

    }

}