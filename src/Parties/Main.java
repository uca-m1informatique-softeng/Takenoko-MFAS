package Parties;

import Joueur.BotRandom;
import Moteur.Partie;
import Moteur.Plateau;
import Joueur.Bot;

public class Main {

    public static void main(String[] args) {
        Partie partie = new Partie();

        Bot bot1 = new Bot("Bleu");
        Bot bot2 = new Bot("Rouge");
        BotRandom botRandom = new BotRandom("Vert");
        partie.getDeck().piocheObjectifJardinier(bot1);
        partie.getDeck().piocheObjectifJardinier(botRandom);

        while (bot1.getNombreObjectifs() == 0 && botRandom.getNombreObjectifs() == 0 && partie.getDeck().getNb()>=0){
            bot1.play(partie);
            //bot2.play(pla);
            botRandom.play(partie);
        }
        if(bot1.getNombreObjectifs() > 0 && botRandom.getNombreObjectifs() == 0){
            System.out.println("Le joueur "+ bot1.getCouleur() + " gagne avec "+ bot1.getScore() + " points.");
        }

        if(bot1.getNombreObjectifs() == 0 && botRandom.getNombreObjectifs() > 0){
            System.out.println("Le joueur "+ botRandom.getCouleur() + " gagne avec "+ botRandom.getScore() + " points.");
        }

        if(bot1.getNombreObjectifs() > 0 && botRandom.getNombreObjectifs() > 0){
            if(bot1.getScore() > botRandom.getScore()){
                System.out.println("Le joueur "+ bot1.getCouleur() + " gagne avec "+ bot1.getScore() + " points.");
            }
            if(bot1.getScore() < botRandom.getScore()){
                System.out.println("Le joueur " + botRandom.getCouleur() + " gagne avec " + botRandom.getScore() + " points.");
            }
            if(bot1.getScore() == botRandom.getScore()) {
                System.out.println("it's a tie");
            }
        }

    }
}
