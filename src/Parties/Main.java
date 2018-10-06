package Parties;

import Moteur.CouleurBot;
import Moteur.Partie;
import Joueur.Joueur;
import Joueur.BotPanda;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList <Joueur> ListeDesJoueurs=new ArrayList<Joueur>();
        Joueur bot1 = new Joueur(CouleurBot.Bleu);
        Joueur bot2 = new Joueur(CouleurBot.Rouge);
        BotPanda botPanda = new BotPanda(CouleurBot.Vert);
        ListeDesJoueurs.add(bot1);
        ListeDesJoueurs.add(botPanda);

        Partie partie = new Partie();
        partie.Jouer(ListeDesJoueurs);



    }
}
