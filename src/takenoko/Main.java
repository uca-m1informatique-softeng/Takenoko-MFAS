package takenoko;

import takenoko.joueur.*;
import takenoko.moteur.Affichage;
import takenoko.moteur.Enums;
import takenoko.moteur.Partie;
import takenoko.moteur.Enums.CouleurBot;

import java.util.ArrayList;

/**
 * La classe principal deroulement d'une partie entre tout les joueurs
 */

public class Main {


    public static void main(String[] args) {

        ArrayList <Joueur> listeDesJoueurs=new ArrayList<Joueur>();

        BotJardinier botJardinier = new BotJardinier(CouleurBot.VERT);
        BotPanda botPanda = new BotPanda(CouleurBot.BLEU);
        BotRandom botRandom = new BotRandom(CouleurBot.ROUGE);


        listeDesJoueurs.add(botJardinier);
        listeDesJoueurs.add(botPanda);
        listeDesJoueurs.add(botRandom);
        Affichage.setVerbose(true);
        Partie partie = new Partie();
        partie.jouer(listeDesJoueurs);
        Affichage.affichagePlateau();
        Affichage.affichageResultatsPartie(listeDesJoueurs);
    }
}
