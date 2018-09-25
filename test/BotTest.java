import Joueur.Bot;
import Moteur.Parcelle;
import Moteur.Plateau;
import Moteur.TypeParcelle;
import javafx.geometry.Point3D;
import org.junit.Test;

import static org.junit.Assert.*;

public class BotTest {
    Plateau pla = new Plateau();
    Point3D p1 = new Point3D(1,0,-1);
    Parcelle par1 = new Parcelle(TypeParcelle.etang);
    Bot j = new Bot("rouge");

    @Test
    public void play() throws Exception {

        /*on crée notre plateau*/
        pla.poser(par1,p1);
        pla.poser(par1,new Point3D(0,0,0));
        pla.poser(par1,new Point3D(1,-1,0));
        pla.poser(par1,new Point3D(2,-1,-1));
        pla.poser(par1,new Point3D(2,0,-2));

        Point3D p2 = new Point3D(0,1,-1);// coordonnée de l'emplacement où le joueur va poser sa parcelle

        /*On test d'abord s'il y a une parcelle au point p2 */
        assertFalse(pla.getMap().containsKey(p2));


        j.play(pla,p1);

        /*Maintenant on regarde si la parcelle a été posée au bon endroit*/
        assertEquals(par1.toString(),pla.getParcelle(p2).toString());
        assertTrue(pla.getMap().containsKey(p2));
    }

}