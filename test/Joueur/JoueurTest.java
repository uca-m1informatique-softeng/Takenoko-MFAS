package Joueur;

import Moteur.Enums;
import Moteur.Objectifs.ObjectifJardinier;
import Moteur.Parcelle;
import Moteur.Partie;
import Moteur.Plateau;
import javafx.geometry.Point3D;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Le classe test du joueur
 */
public class JoueurTest {

    Partie partie=new Partie();
    Plateau pla =partie.getPlateau();
    Parcelle par = new Parcelle(Enums.TypeParcelle.Jaune);
    Bot j = new Bot(Enums.CouleurBot.Rouge);


    @Test
    public void verifierMesObjectifs(){
        ObjectifJardinier ob = new ObjectifJardinier(6, Enums.TypeParcelle.Jaune,4);

        /*On crée notre plateau composé que de parcelles jaunes*/

        pla.poser(par,new Point3D(0,1,-1));
        pla.poser(par,new Point3D(1,0,-1));
        pla.poser(par,new Point3D(1,-1,0));
        pla.poser(par,new Point3D(0,-1,1));

        /*On donne un objectif au jardinier */
        j.AddObjectif(ob);
        System.out.println("score "+j.getScore());

        /*on fait pousser du bambou de taille 3 sur une parcelle (objectif pas réalisé)*/
        for(int i = 0 ; i <3 ; i++){
            pla.getParcelle(new Point3D(0,-1,1)).pousserBambou();
        }

        j.verifierMesObjectif(partie);
        assertEquals(0,j.getNombreObjectifs());

        ///// Test 2 /////

        /*on fait pousser du bambou de taille 4 sur une parcelle (objectif réalisé)*/
        pla.getParcelle(new Point3D(0,-1,1)).pousserBambou();
        j.verifierMesObjectif(partie);

        assertEquals(1,j.getNombreObjectifs());
    }

    @Test

    public void joueurPose(){
        assertEquals(6,partie.getPlateau().emplacementsAutorise().size());
        assertEquals(1,partie.getPlateau().getKeylist().size());

        j.joueurPose(partie);

        assertEquals(5,partie.getPlateau().emplacementsAutorise().size());
        assertEquals(2,partie.getPlateau().getKeylist().size());

    }


    @Test
    public void joueurDeplaceJardinier(){
        /*On crée notre plateau composé que de parcelles jaunes*/

        pla.poser(par,new Point3D(0,1,-1));
        pla.poser(par,new Point3D(1,0,-1));

        j.joueurDeplaceJardinier(partie.getJardinier());

        assertEquals(new Point3D(0,1,-1),partie.getJardinier().getCoord());
    }


    @Test
    public void joueurDeplacePanda(){
        pla.poser(par,new Point3D(0,1,-1));
        pla.poser(par,new Point3D(1,0,-1));

        j.joueurDeplacePanda(partie.getPanda());

        assertEquals(new Point3D(0,1,-1),partie.getPanda().getCoord());
    }
}