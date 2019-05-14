package takeserveur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import takenoko.joueur.Bot;
import takenoko.moteur.Affichage;
import takenoko.moteur.Deck;
import takenoko.moteur.Partie;
import java.util.ArrayList;

/**
 * C'est la classe du JeuServeur
 */
@Component
public class JeuServeur {
    @Autowired
    private Partie partie;

    @Autowired
    private Serveur serveur;

    @Autowired
    private Deck deck;

    @Autowired
    private ArrayList<Bot> joueurs;


    @Autowired
    @Qualifier("client")
    private int client;

    public void jouerTour(){
        System.out.println("++++++ Début de la partie ++++++");
        ArrayList<Bot> temp = new ArrayList<>();
        temp.add(joueurs.get(0));
        partie.jouer(temp);
        Affichage.affichagePlateau();

    }

    public void piocher(){
        joueurs.get(0).piocheUneParcelle();
    }

    public Deck getDeck(){
        return deck.getInstance() ;
    }

}
