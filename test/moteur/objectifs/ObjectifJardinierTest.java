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
    Plateau pla = partie.getPlateau();
    Bot j = new Bot(CouleurBot.ROUGE);
    Parcelle par = new Parcelle(TypeParcelle.JAUNE);


    @Test
    public void validation() throws Exception {
        ObjectifJardinier ob = new ObjectifJardinier(6,TypeParcelle.JAUNE,4);

        /*On crée notre plateau composé que de parcelles jaunes*/

        pla.poser(par,new Point3D(0,1,-1));
        pla.poser(par,new Point3D(1,0,-1));
        pla.poser(par,new Point3D(1,-1,0));
        pla.poser(par,new Point3D(0,-1,1));

        /*On donne un objectif au jardinier */

        j.addObjectif(ob);

        /*on fait pousser du bambou de taille 3 sur une parcelle (objectif pas réalisé)*/
        for(int i = 0 ; i < 3 ; i++){
            pla.getParcelle(new Point3D(0,-1,1)).pousserBambou();
        }

        assertFalse(ob.validation(partie,j));

        /*on fait pousser du bambou de taille 4 sur la meme parcelle (objectif réalisé)*/

        pla.getParcelle(new Point3D(0,-1,1)).pousserBambou();
        assertTrue(ob.validation(partie,j));


    }
}