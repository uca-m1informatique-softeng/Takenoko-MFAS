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
    public void validationJardinier() throws Exception {
        ObjectifJardinier objectifjardinier = new ObjectifJardinier(6,TypeParcelle.JAUNE,4);
        bot.addObjectif(objectifjardinier);

        plateau.poser(parcelleJaune,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));
        plateau.poser(parcelleJaune,new Point3D(1,-1,0));
        plateau.poser(parcelleJaune,new Point3D(0,-1,1));
        for(int i = 0 ; i < 3 ; i++){
            plateau.getParcelle(new Point3D(0,-1,1)).pousserBambou();
        }

        assertFalse(objectifjardinier.validation(partie,bot));

        plateau.getParcelle(new Point3D(0,-1,1)).pousserBambou();

        assertTrue(objectifjardinier.validation(partie,bot));
    }
}