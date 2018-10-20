package joueur;

import javafx.geometry.Point3D;
import moteur.Enums;
import moteur.Partie;
import moteur.Plateau;
import org.junit.Test;

import static org.junit.Assert.*;

public class BotRandomTest {

    Partie partie=new Partie();
    Plateau plateau =partie.getPlateau();
    Joueur joueur = new Joueur(Enums.CouleurBot.ROUGE);

    @Test
    public void choixAction(){
        assertEquals(1,plateau.getKeylist().size());
        assertEquals(new Point3D(0,0,0),partie.getJardinier().getCoord());
        joueur.choixAction(1,partie);
        assertEquals(2,plateau.getKeylist().size());
        assertEquals(new Point3D(0,1,-1),partie.getJardinier().getCoord());
    }
}