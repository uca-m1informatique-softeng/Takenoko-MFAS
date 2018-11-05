package takenoko;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import takenoko.joueur.BotJardinier;
import takenoko.joueur.BotPanda;
import takenoko.moteur.Affichage;
import takenoko.moteur.Partie;
import takenoko.joueur.*;
import takenoko.moteur.Enums.CouleurBot;
import takenoko.joueur.BotParcelle;
import takenoko.joueur.Joueur;

import java.util.ArrayList;

/**
 * La classe principal deroulement de mille partie entre tout les joueurs
 */
public class Main2 {

    public static void main(String[] args) {
        /*
        ArrayList <Joueur> listeDesJoueurs=new ArrayList<Joueur>();

        BotJardinier botJardinier = new BotJardinier(CouleurBot.VERT);
        BotPanda botPanda = new BotPanda(CouleurBot.BLEU);
        BotParcelle botParcelle = new BotParcelle(CouleurBot.ROUGE);

        listeDesJoueurs.add(botJardinier);
        listeDesJoueurs.add(botPanda);
        listeDesJoueurs.add(botParcelle);

        Affichage.setVerbose(false);

        for (int i=0;i<1000;i++) {
            Partie partie = new Partie();
            partie.jouer(listeDesJoueurs);
        }

        Affichage.affichageResultatsPartie(listeDesJoueurs);
        */

    }
}
