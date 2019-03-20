package takenoko;


import takenoko.joueur.Bot;
import takenoko.joueur.BotJardinier;
import takenoko.joueur.BotPanda;
import takenoko.moteur.Affichage;
import takenoko.moteur.Partie;
import takenoko.moteur.Enums.CouleurBot;
import takenoko.joueur.BotParcelle;

import java.util.ArrayList;

/**
 * La classe principal deroulement de mille partie entre tout les joueurs
 */
public class Main2 {

    public static void main(String[] args) {

        ArrayList <Bot> listeDesJoueurs=new ArrayList<Bot>();

        BotJardinier botJardinier = new BotJardinier();
        BotPanda botPanda = new BotPanda();
        BotParcelle botParcelle = new BotParcelle();
        botJardinier.setCouleur(CouleurBot.VERT);
        botPanda.setCouleur(CouleurBot.BLEU);
        botParcelle.setCouleur(CouleurBot.ROUGE);



        listeDesJoueurs.add(botJardinier);
        listeDesJoueurs.add(botPanda);
        listeDesJoueurs.add(botParcelle);

        Affichage.setVerbose(false);
        for (int i=0;i<1000;i++) {
            Partie partie = new Partie();
            partie.jouer(listeDesJoueurs);
        }

        Affichage.affichageResultatsPartie(listeDesJoueurs);

    }
}
