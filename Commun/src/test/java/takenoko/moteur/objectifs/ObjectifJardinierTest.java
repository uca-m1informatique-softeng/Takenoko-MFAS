package takenoko.moteur.objectifs;

import javafx.geometry.Point3D;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import takenoko.joueur.Bot;
import takenoko.moteur.*;

/**
 * La classe test des objectifs jardinier
 */
public class ObjectifJardinierTest {
    Partie partie;
    Plateau plateau ;
    Bot bot ;
    Parcelle parcelleJaune ;
    ObjectifJardinier objectifJardinier;

    @Before
    public void setup(){
        this.partie = new Partie();
        this.plateau = Plateau.getInstance();
        this.bot = new Bot();
        bot.setCouleur(Enums.CouleurBot.ROUGE);
        this.parcelleJaune = new Parcelle();
        parcelleJaune.setIrriguee(false);
        parcelleJaune.setType(Enums.TypeParcelle.JAUNE);
        this.objectifJardinier = new ObjectifJardinier();
        objectifJardinier.setCouleur(Enums.TypeParcelle.JAUNE);
        objectifJardinier.setValeur(6);
        objectifJardinier.setTailleBambou(4);
        bot.resetJoueur();
    }

    @Test
    public void validationObjectifJardinier() throws Exception {
        plateau.resetPlateau();
        bot.addObjectif(objectifJardinier);

        plateau.poser(parcelleJaune,new Point3D(0,1,-1));
        plateau.getParcelle(new Point3D(0,1,-1)).pousserBambou();
        plateau.getParcelle(new Point3D(0,1,-1)).pousserBambou();
        assertFalse(objectifJardinier.validation(bot));

        plateau.getParcelle(new Point3D(0,1,-1)).pousserBambou();
        assertTrue(objectifJardinier.validation(bot));
    }
}