package moteur.objectifs;

import javafx.geometry.Point3D;
import joueur.Bot;
import moteur.Enums;
import moteur.Parcelle;
import moteur.Partie;
import moteur.Plateau;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * La classe test des objectifs parcelles.
 */
public class ObjectifParcelleTest {
    Partie partie=new Partie();
    Plateau plateau = partie.getPlateau();
    Bot bot = new Bot(Enums.CouleurBot.ROUGE);
    Parcelle parcelleRose = new Parcelle(Enums.TypeParcelle.ROSE);

    @Test
    public void validationObjectifMotifCourbé() throws Exception {
        plateau.resetPlateau();

        ObjectifParcelle objectifparcellecourbé = new ObjectifParcelle(4, Enums.TypeParcelle.ROSE,1);
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

        ObjectifParcelle objectifparcelledroit = new ObjectifParcelle(4, Enums.TypeParcelle.ROSE,0);
        bot.addObjectif(objectifparcelledroit);


        plateau.poser(parcelleRose,new Point3D(-1,1,0));
        plateau.poser(parcelleRose,new Point3D(0,1,-1));

        assertFalse(objectifparcelledroit.validation(bot));

        Parcelle deuxiemeParcelleRose = new Parcelle( Enums.TypeParcelle.ROSE);
        plateau.poser(deuxiemeParcelleRose,new Point3D(1,1,-2));

        assertFalse(objectifparcelledroit.validation(bot));

        deuxiemeParcelleRose.setIrriguee(true);

        assertTrue(objectifparcelledroit.validation(bot));


    }

    @Test
    public void validationObjectifMotifTriangle() throws Exception {
        plateau.resetPlateau();

        ObjectifParcelle objectifparcelletriangle = new ObjectifParcelle(4, Enums.TypeParcelle.ROSE,2);
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

        ObjectifParcelle objectifparcelle4 = new ObjectifParcelle(5, Enums.TypeParcelle.ROSE,3);
        bot.addObjectif(objectifparcelle4);

        plateau.poser(parcelleRose,new Point3D(0,1,-1));
        plateau.poser(parcelleRose,new Point3D(1,0,-1));
        plateau.poser(parcelleRose,new Point3D(1,1,-2));

        assertFalse(objectifparcelle4.validation(bot));

        plateau.poser(parcelleRose,new Point3D(0,2,-2));

        assertTrue(objectifparcelle4.validation(bot));
    }

}