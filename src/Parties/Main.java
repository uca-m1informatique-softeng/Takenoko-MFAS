package Parties;

import Moteur.Plateau;
import javafx.geometry.Point3D;
import Joueur.Bot;

public class Main {

    public static void main(String[] args) {
        Plateau pla = new Plateau();

        Bot bot1 = new Bot("Bleu");
        Bot bot2 = new Bot("Rouge");

        while (bot1.getNombreObjectifs() == 0 && bot2.getNombreObjectifs() == 0){
            bot1.play(pla, new Point3D(0,0,0));
            bot2.play(pla, new Point3D(0,0,0));
        }

    }
}
