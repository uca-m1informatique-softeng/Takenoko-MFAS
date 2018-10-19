package moteur.objectifs;

import javafx.geometry.Point3D;
import joueur.Bot;
import moteur.Enums;
import moteur.Parcelle;
import moteur.Partie;
import moteur.Plateau;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectifParcelleTest {
    Partie partie=new Partie();
    Plateau plateau = partie.getPlateau();
    Bot bot = new Bot(Enums.CouleurBot.ROUGE);
    Parcelle parcelleRose = new Parcelle(Enums.TypeParcelle.ROSE);

    @Test
    public void validationObjectifMotifCourbé() throws Exception {

        ObjectifParcelle objectifparcellecourbé = new ObjectifParcelle(4, Enums.TypeParcelle.ROSE,1);
        bot.addObjectif(objectifparcellecourbé);

        plateau.poser(parcelleRose,new Point3D(0,1,-1));
        plateau.poser(parcelleRose,new Point3D(1,0,-1));

        assertFalse(objectifparcellecourbé.validation(partie,bot));

        plateau.poser(parcelleRose,new Point3D(1,-1,0));

        assertTrue(objectifparcellecourbé.validation(partie,bot));
    }

    @Test
    public void validationObjectifMotifDroit() throws Exception {

        ObjectifParcelle objectifparcelledroit = new ObjectifParcelle(4, Enums.TypeParcelle.ROSE,0);
        bot.addObjectif(objectifparcelledroit);


        plateau.poser(parcelleRose,new Point3D(-1,1,0));
        plateau.poser(parcelleRose,new Point3D(0,1,-1));

        assertFalse(objectifparcelledroit.validation(partie,bot));

        plateau.poser(parcelleRose,new Point3D(1,1,-2));

        assertTrue(objectifparcelledroit.validation(partie,bot));


    }

    @Test
    public void validationObjectifMotifTriangle() throws Exception {


        ObjectifParcelle objectifparcelletriangle = new ObjectifParcelle(4, Enums.TypeParcelle.ROSE,2);
        bot.addObjectif(objectifparcelletriangle);

        plateau.poser(parcelleRose,new Point3D(0,1,-1));
        plateau.poser(parcelleRose,new Point3D(1,0,-1));

        assertFalse(objectifparcelletriangle.validation(partie,bot));

        plateau.poser(parcelleRose,new Point3D(1,1,-2));

        assertTrue(objectifparcelletriangle.validation(partie,bot));


    }

    @Test
    public void validationObjectifMotifQuatre() throws Exception {

        ObjectifParcelle objectifparcelle4 = new ObjectifParcelle(5, Enums.TypeParcelle.ROSE,3);
        bot.addObjectif(objectifparcelle4);

        plateau.poser(parcelleRose,new Point3D(0,1,-1));
        plateau.poser(parcelleRose,new Point3D(1,0,-1));
        plateau.poser(parcelleRose,new Point3D(1,1,-2));

        assertFalse(objectifparcelle4.validation(partie,bot));

        plateau.poser(parcelleRose,new Point3D(0,2,-2));

        assertTrue(objectifparcelle4.validation(partie,bot));
    }

}