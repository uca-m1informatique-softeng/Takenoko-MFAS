package takenoko.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import takenoko.moteur.Deck;
import takenoko.moteur.Plateau;

/**
 * C'est la classe du JeuClient
 */
@Component
public class JeuClient {

    @Autowired
    private Client client;
    @Autowired
    private Plateau plateau;
    @Autowired
    private Deck deck;

    /**
     * Le constructeur
     * @param client
     */
    public JeuClient(Client client){
        this.client = client;
        plateau = new Plateau();
        deck = new Deck();
    }

}
