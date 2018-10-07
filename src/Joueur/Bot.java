package Joueur;

import Moteur.*;
import Moteur.Enums.CouleurBot;

/**
 *C'est la classe des bots
 */
public class Bot extends Joueur {

    public Bot(CouleurBot s){
        super(s);
    }

    public boolean choixAction(int numeroActionDansLeTour,Partie P){
        joueurPose(P);
        joueurDeplaceJardinier(P.getJardinier());
        return true;
    }
}
