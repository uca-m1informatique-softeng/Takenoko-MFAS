package takenoko.moteur.objectifs;

import javafx.geometry.Point3D;
import org.junit.Before;
import org.junit.Test;
import takenoko.joueur.Bot;
import takenoko.moteur.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ObjectifParcelleTest {
    Partie partie;
    Plateau plateau ;
    Bot bot ;
    Parcelle parcelleRose ;
    Parcelle parcelleRose2 ;
    ObjectifParcelle objectifparcelledroit;
    ObjectifParcelle objectifparcelletriangle;
    ObjectifParcelle objectifparcellecourbé;
    ObjectifParcelle objectifparcelle4;

    @Before
    public void setup() {
        this.partie = new Partie();
        this.plateau = Plateau.getInstance();
        this.bot = new Bot();
        bot.setCouleur(Enums.CouleurBot.ROUGE);
        this.parcelleRose = new Parcelle();
        parcelleRose.setListBambou(new ArrayList<Bambou>());
        parcelleRose.setIrriguee(false);
        parcelleRose.setType(Enums.TypeParcelle.ROSE);
        this.parcelleRose2 = new Parcelle();
        parcelleRose2.setListBambou(new ArrayList<Bambou>());
        parcelleRose2.setIrriguee(false);
        parcelleRose2.setType(Enums.TypeParcelle.ROSE);
        this.objectifparcellecourbé= new ObjectifParcelle();
        objectifparcellecourbé.setCouleur(Enums.TypeParcelle.ROSE);
        objectifparcellecourbé.setValeur(4);
        objectifparcellecourbé.setType(1);
        this.objectifparcelledroit= new ObjectifParcelle();
        objectifparcelledroit.setCouleur(Enums.TypeParcelle.ROSE);
        objectifparcelledroit.setValeur(4);
        objectifparcelledroit.setType(0);
        this.objectifparcelletriangle= new ObjectifParcelle();
        objectifparcelletriangle.setCouleur(Enums.TypeParcelle.ROSE);
        objectifparcelletriangle.setValeur(4);
        objectifparcelletriangle.setType(2);
        this.objectifparcelle4= new ObjectifParcelle();
        objectifparcelle4.setCouleur(Enums.TypeParcelle.ROSE);
        objectifparcelle4.setValeur(4);
        objectifparcelle4.setType(3);
    }

    @Test
    public void validationObjectifMotifCourbé() throws Exception {
        plateau.resetPlateau();

        bot.addObjectif(objectifparcellecourbé);

        plateau.poser(parcelleRose,new Point3D(0,1,-1));
        plateau.poser(parcelleRose,new Point3D(1,0,-1));
        assertFalse(objectifparcellecourbé.validation(bot));

        plateau.poser(parcelleRose,new Point3D(1,-1,0));
        assertTrue(objectifparcellecourbé.validation(bot));
    }

    @Test
    public void validationObjectifMotifDroit() throws Exception {
        plateau.resetPlateau();

        bot.addObjectif(objectifparcelledroit);


        plateau.poser(parcelleRose,new Point3D(-1,1,0));
        plateau.poser(parcelleRose,new Point3D(0,1,-1));

        assertFalse(objectifparcelledroit.validation(bot));

        //Parcelle deuxiemeParcelleRose = new Parcelle( Enums.TypeParcelle.ROSE);
        plateau.poser(parcelleRose2,new Point3D(1,1,-2));

        assertFalse(objectifparcelledroit.validation(bot));

        parcelleRose2.setIrriguee(true);

        assertTrue(objectifparcelledroit.validation(bot));


    }

    @Test
    public void validationObjectifMotifTriangle() throws Exception {
        plateau.resetPlateau();

        bot.addObjectif(objectifparcelletriangle);

        plateau.poser(parcelleRose,new Point3D(0,1,-1));
        plateau.poser(parcelleRose,new Point3D(1,0,-1));

        assertFalse(objectifparcelletriangle.validation(bot));

        plateau.poser(parcelleRose,new Point3D(1,1,-2));

        assertTrue(objectifparcelletriangle.validation(bot));


    }

    @Test
    public void validationObjectifMotifQuatre() throws Exception {
        plateau.resetPlateau();

        bot.addObjectif(objectifparcelle4);

        plateau.poser(parcelleRose,new Point3D(0,1,-1));
        plateau.poser(parcelleRose,new Point3D(1,0,-1));
        plateau.poser(parcelleRose,new Point3D(1,1,-2));

        assertFalse(objectifparcelle4.validation(bot));

        plateau.poser(parcelleRose,new Point3D(0,2,-2));

        assertTrue(objectifparcelle4.validation(bot));
    }

}