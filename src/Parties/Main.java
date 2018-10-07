package Parties;

import Moteur.Partie;
import Joueur.*;
import Moteur.Enums.CouleurBot;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList <Joueur> ListeDesJoueurs=new ArrayList<Joueur>();
        //Joueur bot1 = new Joueur(CouleurBot.Bleu);
        //Joueur bot2 = new Joueur(CouleurBot.Rouge);
        BotJardinier botJardinier = new BotJardinier(CouleurBot.Vert);
        BotPanda botPanda = new BotPanda(CouleurBot.Bleu);

        ListeDesJoueurs.add(botJardinier);
        ListeDesJoueurs.add(botPanda);

        Partie partie = new Partie();
        partie.Jouer(ListeDesJoueurs);



    }
}
