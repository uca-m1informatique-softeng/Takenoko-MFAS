package parties;

import moteur.Affichage;
import moteur.Partie;
import joueur.*;
import moteur.Enums.CouleurBot;
import java.util.ArrayList;

/**
 * La classe principal
 */
public class Main {

    public static void main(String[] args) {
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


    }
}
