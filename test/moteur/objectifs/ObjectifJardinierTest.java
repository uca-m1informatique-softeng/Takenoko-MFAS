package moteur.objectifs;

import joueur.Bot;
import moteur.*;
import javafx.geometry.Point3D;
import org.junit.Test;
import static org.junit.Assert.*;
import moteur.Enums.*;

/**
 * La classe test des objectifs jardinier
 */
public class ObjectifJardinierTest {
    Partie partie=new Partie();
    Plateau plateau = partie.getPlateau();
    Bot bot = new Bot(CouleurBot.ROUGE);
    Parcelle parcelleJaune = new Parcelle(TypeParcelle.JAUNE);


    @Test
    public void validationObjectifJardinier() throws Exception {
        ObjectifJardinier objectifjardinier = new ObjectifJardinier(6,TypeParcelle.JAUNE,4);
        bot.addObjectif(objectifjardinier);

        plateau.poser(parcelleJaune,new Point3D(0,1,-1));
        plateau.getParcelle(new Point3D(0,1,-1)).pousserBambou();
        plateau.getParcelle(new Point3D(0,1,-1)).pousserBambou();
        assertFalse(objectifjardinier.validation(bot));

        plateau.getParcelle(new Point3D(0,1,-1)).pousserBambou();
        assertTrue(objectifjardinier.validation(bot));
    }
}