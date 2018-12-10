package takenoko.joueur;

import org.junit.Test;
import takenoko.moteur.Affichage;
import takenoko.moteur.Enums;
import takenoko.moteur.Partie;
import takenoko.moteur.Plateau;

import java.util.ArrayList;

public class IAAvanceeTest {
    Plateau plateau = Plateau.getInstance() ;
    IAAvancee iaAvancee = new IAAvancee(Enums.CouleurBot.ROUGE);

    @Test
    public void permutations() {
        BotPanda botPanda= new BotPanda(Enums.CouleurBot.VERT);
        plateau.resetPlateau();
        int nbVictoires = 0;
        int res1 = -1;
        int res2 = -1;

        for (int i = 0; i < 120; i++) {

            ArrayList<Joueur> listeDesJoueurs=new ArrayList<>();


            listeDesJoueurs.add(botPanda);
            listeDesJoueurs.add(iaAvancee);
            Affichage.setVerbose(false);
            for (int k = 0; k < 6; k++) {
                iaAvancee.setNbVictoire(0);
                botPanda.setNbVictoire(0);

                for (int j = 0; j < 1000; j++) {
                    Partie partie = new Partie();
                    iaAvancee.setIperm(i);
                    iaAvancee.setIperm2(k);
                    partie.jouer(listeDesJoueurs);
                }
                if (iaAvancee.getNbVictoire() - botPanda.getNbVictoire() >= nbVictoires) {
                    nbVictoires = iaAvancee.getNbVictoire();
                    res1 = i;
                    res2 = k;
                }
            }

        }
        System.out.println("nombre de victoires : " + nbVictoires + " res1 = " + res1 + " , res2 = " + res2);
    }

}