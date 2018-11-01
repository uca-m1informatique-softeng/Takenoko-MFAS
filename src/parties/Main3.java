package parties;

import moteur.Affichage;
import moteur.Partie;
import joueur.*;
import moteur.Enums.CouleurBot;
import java.util.ArrayList;

/**
 * La classe principal deroulement de mille partie entre les deux meilleurs joueurs(BotJardinier)
 */
public class Main3 {

    public static void main(String[] args) {
        ArrayList <Joueur> listeDesJoueurs=new ArrayList<Joueur>();

        BotJardinier botJardinier = new BotJardinier(CouleurBot.VERT);
        BotJardinier botJardinier2 = new BotJardinier(CouleurBot.BLEU);

        listeDesJoueurs.add(botJardinier);
        listeDesJoueurs.add(botJardinier2);

        Affichage.setVerbose(false);

        for (int i=0;i<1000;i++) {
            Partie partie = new Partie();
            partie.jouer(listeDesJoueurs);
        }

        Affichage.affichageResultatsPartie(listeDesJoueurs);

    }
}
