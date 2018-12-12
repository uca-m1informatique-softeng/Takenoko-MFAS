package takenoko;


import takenoko.joueur.*;
import takenoko.moteur.Affichage;
import takenoko.moteur.Partie;
import takenoko.moteur.Enums.CouleurBot;

import java.util.ArrayList;

/**
 * La classe principal deroulement de mille partie entre tout les joueurs
 */
public class Main2 {

    public static void main(String[] args) {

        ArrayList <Joueur> listeDesJoueurs = new ArrayList<Joueur>();
        ArrayList <Joueur> listeDesJoueurs2 = new ArrayList<Joueur>();
        ArrayList <Joueur> listeDesJoueurs3 = new ArrayList<Joueur>();
        ArrayList <Joueur> listeDesJoueurs4 = new ArrayList<Joueur>();
        ArrayList <Joueur> listeDesJoueurs5 = new ArrayList<Joueur>();



        IANormale iaNormale = new IANormale(CouleurBot.BLEU);
        BotJardinier botJardinier = new BotJardinier(CouleurBot.VERT);

        IANormale iaNormale2 = new IANormale(CouleurBot.BLEU);
        BotPanda botPanda= new BotPanda(CouleurBot.ROUGE);

        IANormale iaNormale3 = new IANormale(CouleurBot.BLEU);
        BotRandom botRandom = new BotRandom(CouleurBot.VERT);

        IANormale iaNormale4 = new IANormale(CouleurBot.BLEU);
        IANormale iaNormale5 = new IANormale(CouleurBot.ROUGE);

        IAAvancee iaAvancee = new IAAvancee(CouleurBot.BLEU);
        BotPanda botPanda2 = new BotPanda(CouleurBot.ROUGE);



        listeDesJoueurs.add(iaNormale);
        listeDesJoueurs.add(botJardinier);

        listeDesJoueurs2.add(iaNormale2);
        listeDesJoueurs2.add(botPanda);

        listeDesJoueurs3.add(iaNormale3);
        listeDesJoueurs3.add(botRandom);

        listeDesJoueurs4.add(iaNormale4);
        listeDesJoueurs4.add(iaNormale5);

        listeDesJoueurs5.add(iaAvancee);
        listeDesJoueurs5.add(botPanda2);


        iaNormale.setIperm(119);
        iaNormale.setIperm2(22);

        iaNormale2.setIperm(71);
        iaNormale2.setIperm2(21);

        iaNormale3.setIperm(119);
        iaNormale3.setIperm2(23);

        iaNormale4.setIperm(116);
        iaNormale4.setIperm2(4);

        Affichage.setVerbose(false);
        for (int i=0;i<1000;i++) {
            Partie partie = new Partie();
            partie.jouer(listeDesJoueurs);
        }

        for (int i=0;i<1000;i++) {
            Partie partie = new Partie();
            partie.jouer(listeDesJoueurs2);
        }


        for (int i=0;i<1000;i++) {
            Partie partie = new Partie();
            partie.jouer(listeDesJoueurs3);
        }

        for (int i=0;i<1000;i++) {
            Partie partie = new Partie();
            partie.jouer(listeDesJoueurs4);
        }

        for (int i=0;i<1000;i++) {
            Partie partie = new Partie();
            partie.jouer(listeDesJoueurs5);
        }


        System.out.println("#############################C'est l'heure des duels#############################");
        System.out.println(" ");


        System.out.println("Premier Match : IANormale vs BotJardinier");
        Affichage.affichageResultatsPartie(listeDesJoueurs);
        System.out.println(" ");


        System.out.println("Deuxième Match : IANormale vs BotPanda");
        Affichage.affichageResultatsPartie(listeDesJoueurs2);
        System.out.println(" ");


        System.out.println("Troisième Match : IANormale vs BotRandom");
        Affichage.affichageResultatsPartie(listeDesJoueurs3);
        System.out.println(" ");


        System.out.println("Quatrième Match : IANormale vs IANormale");
        Affichage.affichageResultatsPartie(listeDesJoueurs4);
        System.out.println(" ");

        System.out.println("Cinquième Match : IAAvancee vs BotPanda");
        Affichage.affichageResultatsPartie(listeDesJoueurs5);
        System.out.println(" ");


        System.out.println("#############################Fin des duels#############################");

    }
}
