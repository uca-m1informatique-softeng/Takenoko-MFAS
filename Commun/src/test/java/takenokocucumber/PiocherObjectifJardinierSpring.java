package takenokocucumber;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import takenoko.Main;
import takenoko.joueur.BotJardinier;
import takenoko.moteur.Deck;

@SpringBootTest(classes = Main.class)
@ContextConfiguration()
public class PiocherObjectifJardinierSpring {

    static Deck deck = Deck.getInstance();
    static BotJardinier botJardinier = new BotJardinier();

    public void PiocherObjectifJardinier(){

        botJardinier.actionPiocheObjectifJardinier();

    }
}
