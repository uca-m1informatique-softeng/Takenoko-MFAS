package Parties;

import Joueur.BotRandom;
import Moteur.CouleurBot;
import Moteur.Partie;
import Moteur.Joueur;
import Moteur.Plateau;
import Joueur.Bot;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList <Joueur> ListeDesJoueurs=new ArrayList<Joueur>();
        Joueur bot1 = new Joueur(CouleurBot.Bleu);
        Joueur bot2 = new Joueur(CouleurBot.Rouge);
        ListeDesJoueurs.add(bot1);
        ListeDesJoueurs.add(bot2);

        Partie partie = new Partie();
        partie.Jouer(ListeDesJoueurs);



    }
}
