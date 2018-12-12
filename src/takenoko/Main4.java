package takenoko;

import takenoko.joueur.*;
import takenoko.moteur.*;

import java.util.ArrayList;

public class Main4 {

    public static void Test(Joueur joueur, int x, int y) {
        IANormale iaNormale = new IANormale(Enums.CouleurBot.ROUGE);
        IAAvancee iaAvancee = new IAAvancee(Enums.CouleurBot.ROUGE);
        ArrayList<Joueur> listeDesJoueurs=new ArrayList<Joueur>();

        iaNormale.setIperm(x);
        iaNormale.setIperm2(y);

        listeDesJoueurs.add(iaNormale);
        listeDesJoueurs.add(joueur);

        for (int j = 0; j < 5; j++) {
            if (j == 0) {
                System.out.println("------- SANS  JARDINIER ------- ");
                iaNormale.setJardinierBool(false);
            }
            if (j == 1) {
                System.out.println("------- SANS PANDA ------- ");
                iaNormale.setJardinierBool(true);
                iaNormale.setPandaBool(false);

            }
            if (j == 2) {
                System.out.println("------- SANS PIOCHE ------- ");
                iaNormale.setPandaBool(true);
                iaNormale.setParcellepioche(false);

            }
            if (j == 3) {
                System.out.println("------- SANS POSE ------- ");
                iaNormale.setParcellepioche(true);
                iaNormale.setParcellepose(false);
            }
            if (j == 4) {
                System.out.println("------- BASE ------- ");
                iaNormale.setParcellepose(true);
            }
            iaNormale.setNbVictoire(0);
            joueur.setNbVictoire(0);
            Affichage.setVerbose(false);
            for (int i = 0; i < 1000; i++) {
                Partie partie = new Partie();
                partie.jouer(listeDesJoueurs);
            }

            Affichage.affichageResultatsPartie(listeDesJoueurs);

        }
    }
    public static void main(String[] args) {
        BotRandom botRandom = new BotRandom(Enums.CouleurBot.BLEU);
        BotPanda botPanda= new BotPanda(Enums.CouleurBot.BLEU);
        BotJardinier botJardinier = new BotJardinier(Enums.CouleurBot.BLEU);
        IANormale iaNormale = new IANormale(Enums.CouleurBot.BLEU);

        ///// vs botRandom /////
        System.out.println("********** vs Bot Random **********");
        Test(botRandom,119,23);
        System.out.println();

        ///// vs bot Jardinier /////
        System.out.println("********** vs Bot Jardinier **********");
        Test(botJardinier,119,22);

        System.out.println();
        ///// vs bot Panda /////
        System.out.println("********** vs Bot Panda **********");
        Test(botPanda,71,21);

        System.out.println();
        ///// vs iaNormale /////
        System.out.println("********** vs iaNormale **********");
        Test(iaNormale,116,4);
    }

    /* Le test suivant permets de connaître l'ordre des commandes de la methode choixTypeAction */
    /* Il est en commentaire car met 2H à s'exécuter */
/*
    public void permutationsChoixTypeAction() {
        BotJardinier botJardinier = new BotJardinier(Enums.CouleurBot.VERT);
        IANormale iaNormale2 = new IANormale(Enums.CouleurBot.VERT);
        BotPanda botPanda= new BotPanda(Enums.CouleurBot.VERT);
        BotRandom botRandom = new BotRandom(Enums.CouleurBot.BLEU);

        permutations(botRandom); // ratio victoires : 1000 res1 = 119 , res2 23
        permutations(botJardinier);// ratio victoires : 1000 res1 = 119 , res2 22
        permutations(botPanda); //ratio victoires : 986 res1 = 71 , res2 21
        permutations(iaNormale2);  //ratio victoires : 326 res1 = 116 , res2 4
    }


    public void permutations(Joueur joueur) {
        plateau.resetPlateau();
        int nbVictoires = 0;
        int res1 = -1;
        int res2 = -1;

        for (int i = 0; i < 120; i++) {
            ArrayList <Joueur> listeDesJoueurs=new ArrayList<Joueur>();
            listeDesJoueurs.add(joueur);
            listeDesJoueurs.add(iaNormale);
            Affichage.setVerbose(false);
            for (int k = 0; k < 24; k++) {
                iaNormale.setNbVictoire(0);
                joueur.setNbVictoire(0);

                for (int j = 0; j < 1000; j++) {
                    Partie partie = new Partie();
                    iaNormale.setIperm(i);
                    iaNormale.setIperm2(k);
                    partie.jouer(listeDesJoueurs);
                }
                if (iaNormale.getNbVictoire() - joueur.getNbVictoire() >= nbVictoires) {
                    nbVictoires = iaNormale.getNbVictoire() - joueur.getNbVictoire();
                    res1 = i;
                    res2 = k;
                }
            }

        }
        System.out.println("ratio victoires : " + nbVictoires + " res1 = " + res1 + " , res2 " + res2);
    }

    */



// ce test est en commentaire car il met 1h à s'éxecuter.
    //Il test l'IAAvancee
   /* @Test
    public void permutations() { //nombre de victoires : 968 res1 = 4 , res2 = 0
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
    }*/

}
