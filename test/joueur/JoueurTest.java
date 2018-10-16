package joueur;

import moteur.Enums;
import moteur.objectifs.ObjectifJardinier;
import moteur.Parcelle;
import moteur.Partie;
import moteur.Plateau;
import javafx.geometry.Point3D;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Le classe test du joueur
 */
public class JoueurTest {

    Partie partie=new Partie();
    Plateau plateau =partie.getPlateau();
    Parcelle parcelleJaune = new Parcelle(Enums.TypeParcelle.JAUNE);
    Bot bot = new Bot(Enums.CouleurBot.ROUGE);


    @Test
    public void verifierMesObjectifs(){
        ObjectifJardinier ob = new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4);

        /*On crée notre plateau composé que de parcelles jaunes*/

        plateau.poser(parcelleJaune,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));
        plateau.poser(parcelleJaune,new Point3D(1,-1,0));
        plateau.poser(parcelleJaune,new Point3D(0,-1,1));

        /*On donne un objectif au jardinier */
        bot.addObjectif(ob);
        System.out.println("score "+bot.getScore());

        /*on fait pousser du bambou de taille 3 sur une parcelle (objectif pas réalisé)*/
        for(int i = 0 ; i <3 ; i++){
            plateau.getParcelle(new Point3D(0,-1,1)).pousserBambou();
        }

        bot.verifierMesObjectif(partie);
        assertEquals(0,bot.getNombreObjectifsRemplis());

        ///// Test 2 /////

        /*on fait pousser du bambou de taille 4 sur une parcelle (objectif réalisé)*/
        plateau.getParcelle(new Point3D(0,-1,1)).pousserBambou();
        bot.verifierMesObjectif(partie);

        assertEquals(1,bot.getNombreObjectifsRemplis());
    }

    @Test

    public void joueurPose(){
        assertEquals(6,partie.getPlateau().emplacementsAutorise().size());
        assertEquals(1,partie.getPlateau().getKeylist().size());

        bot.joueurPose(partie);

        assertEquals(5,partie.getPlateau().emplacementsAutorise().size());
        assertEquals(2,partie.getPlateau().getKeylist().size());

    }


    @Test
    public void joueurDeplaceJardinier(){
        /*On crée notre plateau composé que de parcelles jaunes*/

        plateau.poser(parcelleJaune,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));

        bot.joueurDeplaceJardinier(partie.getJardinier());

        assertEquals(new Point3D(0,1,-1),partie.getJardinier().getCoord());
    }


    @Test
    public void joueurDeplacePanda(){
        plateau.poser(parcelleJaune,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));

        bot.joueurDeplacePanda(partie.getPanda());

        assertEquals(new Point3D(0,1,-1),partie.getPanda().getCoord());
    }
}