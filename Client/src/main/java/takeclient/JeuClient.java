package takeclient;


import javafx.geometry.Point3D;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import takenoko.joueur.BotPanda;
import takenoko.moteur.*;
import takenoko.joueur.Bot;

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


    private ArrayList<Bot> players = new ArrayList<>();
    private int CLIENT;

    public JeuClient(){
        plateau = Plateau.getInstance();
        deck = Deck.getInstance();
    }

    public int init() throws Exception {
        //CLIENT = client.getClient();
        players.add(new BotPanda());
        deck.deJson(client.getDeck());
       // plateau.deJsonP(client.getPlateau());
       // return plateau.getPlateauPar().size();
        return 1;

    }
/*
    public void jouer(){
        ArrayList<Bot> temp = new ArrayList<>();
        temp.add(players.get(0));
        partie.jouer(temp);
        Affichage.affichagePlateau();
    }*/

    public void piocher_poser() throws Exception{
        //ArrayList<Bot> temp = new ArrayList<>();
        //temp.add(players.get(0));
        this.init();
        players.get(0).choixAction();
        //deuxieme action
        players.get(0).choixAction();

        //verifier objectif
        players.get(0).verifierMesObjectif();

    }


/*
    public void resetPartie(ArrayList<Bot> listJoueurs) throws Exception{
        client.getPlateau();
        client.getDeck();
        client.getJardinier();
        client.getPanda();

    }

/*
    public void jouer_un_tour(){
            Bot joueurCourant = players.get(0);
            Affichage.affichageDebutTour(joueurCourant);

            //premiere action
            joueurCourant.choixAction();
            //deuxieme action
            joueurCourant.choixAction();

            //verifier objectif
            joueurCourant.verifierMesObjectif();
            if(joueurCourant.getNombreObjectifsRemplis()>nbObjectifFinDuJeu && !finDePartie){
                premierTermine=i;
                finDePartie=true;
                joueurCourant.setScore(joueurCourant.getScore()+2);
                Affichage.affichageEmpereur(joueurCourant);
            }
            Affichage.affichageFinTour(joueurCourant);
            joueurCourant.resetListAction();
        }
        compteurTour++;
        if (compteurTour>100) {
            finDePartie=true;
            Affichage.affichagePartieAnnule();
        }
    }
*/

    public void par(){
        ArrayList <Bot> listeDesJoueurs=new ArrayList<>();
        players.add(new BotPanda());
        players.get(0).setCouleur(Enums.CouleurBot.VERT);

        listeDesJoueurs.add(players.get(0));
        partie.jouer(listeDesJoueurs);

    }


}