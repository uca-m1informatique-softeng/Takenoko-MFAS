package takenoko;


import takenoko.joueur.BotJardinier;
import takenoko.moteur.Affichage;
import takenoko.moteur.Partie;
import takenoko.moteur.Enums.CouleurBot;
import takenoko.joueur.Joueur;

import java.util.ArrayList;

/**
 * La classe principal deroulement de mille partie entre les deux meilleurs joueurs(BotJardinier)
 */
public class Main3 {

    public static void main(String[] args) {

        ArrayList <Joueur> listeDesJoueurs=new ArrayList<Joueur>();

        BotJardinier botJardinierVert = new BotJardinier(CouleurBot.VERT);
        BotJardinier botJardinierBleu = new BotJardinier(CouleurBot.BLEU);


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
