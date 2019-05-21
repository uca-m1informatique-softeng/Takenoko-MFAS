package takeserveur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import takenoko.joueur.*;
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


    /**
     * Le jeu
     * @param a
     * @return
     */
    public String game(Integer a,Integer b){

        partie = new Partie();

        ArrayList <Bot> listeDesJoueurs=new ArrayList<>();

        //Création du bot pour le joueur 1
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

        //Création du bot pour le joueur 2

        if(b== 1){
            joueurs.add(new BotPanda());
            joueurs.get(1).setCouleur(Enums.CouleurBot.VERT);}

        else if(b == 2){
            joueurs.add(new BotJardinier());
            joueurs.get(1).setCouleur(Enums.CouleurBot.ROUGE);}
        else if (b == 3){
            joueurs.add(new BotParcelle());
            joueurs.get(1).setCouleur(Enums.CouleurBot.BLEU);}
        else {
            joueurs.add(new BotRandom());
            joueurs.get(1).setCouleur(Enums.CouleurBot.VERT);
        }

        listeDesJoueurs.add(joueurs.get(0));
        listeDesJoueurs.add(joueurs.get(1));
        partie.jouer(listeDesJoueurs);

        String res =  "Score du Joueur 1 : " + listeDesJoueurs.get(0).getScore()+"  -  Score du Joueur 2 : "+listeDesJoueurs.get(1).getScore();

        return res;

    }

    public Deck getDeck(){
        return deck.getInstance() ;
    }

}
