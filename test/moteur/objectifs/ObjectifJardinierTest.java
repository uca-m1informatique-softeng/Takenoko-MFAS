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
    public void validation() throws Exception {
        ObjectifJardinier objectifjardinier = new ObjectifJardinier(6,TypeParcelle.JAUNE,4);

        /*On crée notre plateau composé que de parcelles jaunes*/

        plateau.poser(parcelleJaune,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));
        plateau.poser(parcelleJaune,new Point3D(1,-1,0));
        plateau.poser(parcelleJaune,new Point3D(0,-1,1));

        /*On donne un objectif au jardinier */

        bot.addObjectif(objectifjardinier);

        /*on fait pousser du bambou de taille 3 sur une parcelle (objectif pas réalisé)*/
        for(int i = 0 ; i < 3 ; i++){
            plateau.getParcelle(new Point3D(0,-1,1)).pousserBambou();
        }

        assertFalse(objectifjardinier.validation(partie,bot));

        /*on fait pousser du bambou de taille 4 sur la meme parcelle (objectif réalisé)*/

        plateau.getParcelle(new Point3D(0,-1,1)).pousserBambou();
        assertTrue(objectifjardinier.validation(partie,bot));


    }
}