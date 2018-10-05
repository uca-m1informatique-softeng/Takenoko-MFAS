package Moteur.Objectifs;

import Joueur.Bot;
import Moteur.Parcelle;
import Moteur.Partie;
import Moteur.Plateau;
import Moteur.TypeParcelle;
import javafx.geometry.Point3D;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectifJardinierTest {
    Partie partie=new Partie();
    Plateau pla = partie.getPlateau();
    Bot j = new Bot("rouge");
    Parcelle par = new Parcelle(TypeParcelle.Jaune);


    @Test
    public void validation() throws Exception {
        ObjectifJardinier ob = new ObjectifJardinier(6,TypeParcelle.Jaune,4);

        /*On crée notre plateau est composé que de parcelles jaunes*/

        pla.poser(par,new Point3D(0,1,-1));
        pla.poser(par,new Point3D(1,0,-1));
        pla.poser(par,new Point3D(1,-1,0));
        pla.poser(par,new Point3D(0,-1,1));

        /*On donne un objectif au jardinier */

        j.setObjectif(ob);

        /*on fait pousser du bambou de taille 3 sur une parcelle (objectif pas réalisé)*/
        for(int i = 0 ; i < 3 ; i++){
            pla.getParcelle(new Point3D(0,-1,1)).pousserBambou();
        }

        assertFalse(ob.validation(pla.getMap(),pla.getKeylist()));

        /*on fait pousser du bambou de taille 4 sur la meme parcelle (objectif réalisé)*/

        pla.getParcelle(new Point3D(0,-1,1)).pousserBambou();
        assertTrue(ob.validation(pla.getMap(),pla.getKeylist()));


    }

}