package takeclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import takeclient.Client;
import takenoko.joueur.Bot;
import takenoko.moteur.Affichage;
import takenoko.moteur.Deck;
import takenoko.moteur.Partie;
import takenoko.moteur.Plateau;

import java.util.ArrayList;

@Component
public class JeuClient {

    @Autowired
    private Client client;

    @Autowired
    private Plateau plateau;

    @Autowired
    private Deck deck;

    @Autowired
    private Partie partie;


    private Bot[] players;
    private int CLIENT;

    public JeuClient(){
        plateau = Plateau.getInstance();
        deck = Deck.getInstance();
    }

    public void init() throws Exception{
        //CLIENT = client.getClient();
        //players = new Bot[CLIENT];
        //players[0] = new BotPanda();
        //deck.deJson(client.getDeck());
    }

    public void jouer(){
        ArrayList<Bot> temp = new ArrayList<>();
        temp.add(players[0]);
        partie.jouer(temp);
        Affichage.affichagePlateau();
    }

    public void piocher(){
        ArrayList<Bot> temp = new ArrayList<>();
        temp.add(players[0]);
        players[0].piocheUneParcelle();
    }

}