package takenoko;


import takenoko.joueur.Bot;
import takenoko.joueur.BotJardinier;
import takenoko.moteur.Affichage;
import takenoko.moteur.Partie;
import takenoko.moteur.Enums.CouleurBot;

import java.util.ArrayList;

/**
 * La classe principal deroulement de mille partie entre les deux meilleurs joueurs(BotJardinier)
 */
public class Main3 {

    public static void main(String[] args) {

        ArrayList <Bot> listeDesJoueurs=new ArrayList<Bot>();

        BotJardinier botJardinierVert = new BotJardinier();
        BotJardinier botJardinierBleu = new BotJardinier();

        botJardinierVert.setCouleur(CouleurBot.VERT);
        botJardinierBleu.setCouleur(CouleurBot.BLEU);

        listeDesJoueurs.add(botJardinierVert);
        listeDesJoueurs.add(botJardinierBleu);

        Affichage.setVerbose(false);

        for (int i=0;i<1000;i++) {
            Partie partie = new Partie();
            partie.jouer(listeDesJoueurs);
        }

        Affichage.affichageResultatsPartie(listeDesJoueurs);


    }
}
