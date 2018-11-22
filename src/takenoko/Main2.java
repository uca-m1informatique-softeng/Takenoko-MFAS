package takenoko;


import takenoko.joueur.BotJardinier;
import takenoko.joueur.BotRandom;
import takenoko.joueur.BotPanda;
import takenoko.moteur.Affichage;
import takenoko.moteur.Partie;
import takenoko.moteur.Enums.CouleurBot;
import takenoko.joueur.BotParcelle;
import takenoko.joueur.Joueur;

import java.util.ArrayList;

/**
 * La classe principal deroulement de mille partie entre tout les joueurs
 */
public class Main2 {

    public static void main(String[] args) {

        ArrayList <Joueur> listeDesJoueurs=new ArrayList<Joueur>();

        BotPanda botPanda = new BotPanda();
        BotJardinier botJardinier = new BotJardinier();
        BotRandom botRandom = new BotRandom();
        botJardinier.setCouleur(CouleurBot.VERT);
        botPanda.setCouleur(CouleurBot.BLEU);



        listeDesJoueurs.add(botJardinier);
        listeDesJoueurs.add(botPanda);

        Affichage.setVerbose(false);
        for (int i=0;i<1000;i++) {
            Partie partie = new Partie();
            partie.jouer(listeDesJoueurs);
        }

        Affichage.affichageResultatsPartie(listeDesJoueurs);

    }
}
