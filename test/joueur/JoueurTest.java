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
        bot.addObjectif(ob);

        plateau.poser(parcelleJaune,new Point3D(0,1,-1));
        plateau.poser(parcelleJaune,new Point3D(1,0,-1));
        plateau.poser(parcelleJaune,new Point3D(1,-1,0));
        plateau.poser(parcelleJaune,new Point3D(0,-1,1));
        for(int i = 0 ; i <3 ; i++){
            plateau.getParcelle(new Point3D(0,-1,1)).pousserBambou();
        }

        bot.verifierMesObjectif(partie);
        assertEquals(0,bot.getNombreObjectifsRemplis());

        plateau.getParcelle(new Point3D(0,-1,1)).pousserBambou();

        bot.verifierMesObjectif(partie);
        assertEquals(1,bot.getNombreObjectifsRemplis());
    }

    @Test
    public void joueurPoseUneParcelle(){
        assertEquals(6,partie.getPlateau().emplacementsAutorise().size());
        assertEquals(1,partie.getPlateau().getKeylist().size());

        bot.joueurPose(partie);

        assertEquals(5,partie.getPlateau().emplacementsAutorise().size());
        assertEquals(2,partie.getPlateau().getKeylist().size());
        assertEquals(partie.getPlateau().getKeylist().get(1),new Point3D(0,1,-1));
    }


    @Test
    public void joueurPoseDeuxParcelle(){
        bot.joueurPose(partie);
        bot.joueurPose(partie);
        assertEquals(partie.getPlateau().getKeylist().get(1),new Point3D(0,1,-1));
        assertEquals(partie.getPlateau().getKeylist().get(2),new Point3D(1,0,-1));
    }



    @Test
    public void joueurDeplaceJardinier(){
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