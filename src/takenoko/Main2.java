package takenoko;


import takenoko.joueur.*;
import takenoko.moteur.Affichage;
import takenoko.moteur.Partie;
import takenoko.moteur.Enums.CouleurBot;

import java.util.ArrayList;

/**
 * La classe principal deroulement de mille partie entre tout les joueurs
 */
public class Main2 {

    public static void main(String[] args) {

        ArrayList <Joueur> listeDesJoueurs=new ArrayList<Joueur>();

        BotJardinier botJardinier = new BotJardinier(CouleurBot.VERT);
        IANormale iaNormale = new IANormale(CouleurBot.BLEU);
        BotRandom botRandom = new BotRandom(CouleurBot.ROUGE);
        //BotPanda botPanda= new BotPanda(CouleurBot.ROUGE);



        //listeDesJoueurs.add(botJardinier);
        listeDesJoueurs.add(iaNormale);
        listeDesJoueurs.add(botRandom);

        Affichage.setVerbose(false);
        for (int i=0;i<1000;i++) {
            Partie partie = new Partie();
            partie.jouer(listeDesJoueurs);
        }

        Affichage.affichageResultatsPartie(listeDesJoueurs);

    }
}
