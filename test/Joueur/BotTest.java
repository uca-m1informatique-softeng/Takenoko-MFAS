package Joueur;

import Joueur.Bot;
import Moteur.Objectifs.ObjectifJardinier;
import Moteur.Parcelle;
import Moteur.Partie;
import Moteur.Plateau;
import Moteur.TypeParcelle;
import javafx.geometry.Point3D;
import org.junit.Test;

import static org.junit.Assert.*;

public class BotTest {
    Partie partie=new Partie();
    Plateau pla =partie.getPlateau();
    Parcelle par = new Parcelle(TypeParcelle.etang);
    Bot j = new Bot("rouge");


    @Test
    public void verifierMonObjectif(){
        ObjectifJardinier ob = new ObjectifJardinier(6,TypeParcelle.Jaune,4);

        /*On crée notre plateau composé que de parcelles jaunes*/

        pla.poser(par,new Point3D(0,1,-1));
        pla.poser(par,new Point3D(1,0,-1));
        pla.poser(par,new Point3D(1,-1,0));
        pla.poser(par,new Point3D(0,-1,1));

        /*On donne u objectif au jardinier */
        j.setObjectif(ob);

        /*on fait pousser du bambou de taille 3 sur une parcelle (objectif pas réalisé)*/
        for(int i = 0 ; i <3 ; i++){
            pla.getParcelle(new Point3D(0,-1,1)).pousserBambou();
        }

        j.verifierMonObjectif(ob,partie.getPlateau().getMap(), partie.getPlateau().getKeylist());

        assertEquals(0,j.getNombreObjectifs());

        ///// Test 2 /////

        /*on fait pousser du bambou de taille 3 sur une parcelle (objectif pas réalisé)*/
        pla.getParcelle(new Point3D(0,-1,1)).pousserBambou();
        j.verifierMonObjectif(ob,partie.getPlateau().getMap(), partie.getPlateau().getKeylist());
        assertEquals(1,j.getNombreObjectifs());
    }

    @Test

    public void joueurPose(){
        assertEquals(6,partie.getPlateau().emplacementsAutorise().size());
        assertEquals(1,partie.getPlateau().getKeylist().size());

        j.joueurPose(partie.getPlateau());

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
    public void play(){
        partie.piocheObjectifJardinier(j);
        j.play(partie);
        assertEquals(new Point3D(0,1,-1),partie.getPlateau().getKeylist().get(1));//on vérifie si le joueur a posé
        assertEquals(new Point3D(0,1,-1),partie.getJardinier().getCoord());//on vérifie si le joueur a deplacé le jardinier
        assertEquals(0,j.getNombreObjectifs());
    }
}