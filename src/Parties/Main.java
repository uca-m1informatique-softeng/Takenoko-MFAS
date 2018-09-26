package Parties;

import Joueur.BotRandom;
import Moteur.Plateau;
import Joueur.Bot;

public class Main {

    public static void main(String[] args) {
        Plateau pla = new Plateau();

        Bot bot1 = new Bot("Bleu");
        Bot bot2 = new Bot("Rouge");
        BotRandom botRandom = new BotRandom("Vert");

        while (bot1.getNombreObjectifs() == 0 && botRandom.getNombreObjectifs() == 0){
            bot1.play(pla);
            //bot2.play(pla);
            botRandom.play(pla);
        }

    }
}
