package parties;

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
        //joueur bot1 = new joueur(CouleurBot.Bleu);
        //joueur bot2 = new joueur(CouleurBot.Rouge);
        BotJardinier botJardinier = new BotJardinier(CouleurBot.VERT);
        BotPanda botPanda = new BotPanda(CouleurBot.BLEU);

        listeDesJoueurs.add(botJardinier);
        listeDesJoueurs.add(botPanda);

        Partie partie = new Partie();
        partie.jouer(listeDesJoueurs);



    }
}
