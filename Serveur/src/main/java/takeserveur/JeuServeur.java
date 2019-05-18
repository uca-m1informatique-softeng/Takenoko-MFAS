package takeserveur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import takenoko.joueur.*;
import takenoko.moteur.Affichage;
import takenoko.moteur.Deck;
import takenoko.moteur.Enums;
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

    /* public void jouerTour(){
         System.out.println("++++++ Début de la partie ++++++");
         ArrayList<Bot> temp = new ArrayList<>();
         temp.add(joueurs.get(0));
         partie.jouer(temp);
         Affichage.affichagePlateau();

     }

     public void piocher(){
         joueurs.get(0).piocheUneParcelle();
     }
 */
    public String game(Integer a){

        partie = new Partie();

        ArrayList <Bot> listeDesJoueurs=new ArrayList<>();


        if(a == 1){
            joueurs.add(new BotPanda());
            joueurs.get(0).setCouleur(Enums.CouleurBot.ROUGE);}

        else if(a == 2){
            joueurs.add(new BotJardinier());
            joueurs.get(0).setCouleur(Enums.CouleurBot.ROUGE);}
        else if (a == 3){
            joueurs.add(new BotParcelle());
            joueurs.get(0).setCouleur(Enums.CouleurBot.BLEU);}
        else {
            joueurs.add(new BotRandom());
            joueurs.get(0).setCouleur(Enums.CouleurBot.BLEU);
        }


        listeDesJoueurs.add(joueurs.get(0));
        BotRandom adversaire = new BotRandom();
        adversaire.setCouleur(Enums.CouleurBot.VERT);
        listeDesJoueurs.add(adversaire);
        partie.jouer(listeDesJoueurs);

        return  "Score du client : " + listeDesJoueurs.get(0).getScore()+"  -  Score du BotRandom : "+listeDesJoueurs.get(1).getScore();

    }

    public Deck getDeck(){
        return deck.getInstance() ;
    }

}
