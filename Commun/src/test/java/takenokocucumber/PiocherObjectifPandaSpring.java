package takenokocucumber;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import takenoko.Main;
import takenoko.joueur.BotPanda;
import takenoko.moteur.Deck;

@SpringBootTest(classes = Main.class)
@ContextConfiguration()
public class PiocherObjectifPandaSpring {

    static Deck deck = Deck.getInstance();
    static BotPanda botPanda = new BotPanda();

    public void PiocherObjectifPanda(){

        botPanda.actionPiocheObjectifPanda();

    }
}
