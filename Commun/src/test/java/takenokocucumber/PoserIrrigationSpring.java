package takenokocucumber;

import javafx.geometry.Point3D;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import takenoko.Main;
import takenoko.joueur.Bot;
import takenoko.joueur.BotJardinier;
import takenoko.moteur.Deck;
import takenoko.moteur.Parcelle;
import takenoko.moteur.Plateau;

@SpringBootTest(classes = Main.class)
@ContextConfiguration()
public class PoserIrrigationSpring {

    static Deck deck = Deck.getInstance();
    static Plateau plateau = Plateau.getInstance();
    static Bot bot= new Bot();
    static Parcelle parcelle = new Parcelle();
    Point3D point1 = new Point3D(1,0,-1);
    Point3D point2 = new Point3D(1,0,-2);

    public void init(){
        plateau.poser(parcelle,point1);
        plateau.poser(parcelle,point2);
    }
    public void poser_irrigation(){

        bot.actionPoseIrrigation();

    }
}
