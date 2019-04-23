package takenoko.serveur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import takenoko.joueur.Bot;
import takenoko.moteur.Partie;
import java.util.ArrayList;

/**
 * C'est la classe du JeuServeur
 */
@Component
public class JeuServeur {

    @Autowired
    private ArrayList<Bot> listJoueur;
    private Partie partie;

    //remplacer partie.jouer par une partie en communication avec le serveur
    @Autowired
    private Serveur serveur;

    public void jouer(){
        System.out.println("Début de la partie");
        partie.jouer(listJoueur);
    }

}
