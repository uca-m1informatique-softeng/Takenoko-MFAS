package Parties;

import Joueur.JoueurRandom;
import Moteur.Plateau;
import javafx.geometry.Point3D;
import Joueur.Bot;

public class Main {

    public static void main(String[] args) {
        Plateau pla = new Plateau();

        Bot bot1 = new Bot("Bleu");
        Bot bot2 = new Bot("Rouge");
        JoueurRandom joueurRandom = new JoueurRandom("Vert");

        while (bot1.getNombreObjectifs() == 0 && joueurRandom.getNombreObjectifs() == 0){
            bot1.play(pla);
            //bot2.play(pla);
            joueurRandom.play(pla);
        }

    }
}
