package Joueur;

import Moteur.*;
import Moteur.Enums.CouleurBot;

/**
 *C'est la classe des bots
 */
public class Bot extends Joueur {

    /**
     * Le constructeur
     * @param s
     */
    public Bot(CouleurBot s){
        super(s);
    }

    /**
     * Une méthode qui renvois un boolean pour le choix d'action du bot
     * @param numeroActionDansLeTour
     * @param P
     * @return
     */
    public boolean choixAction(int numeroActionDansLeTour,Partie P){
        joueurPose(P);
        joueurDeplaceJardinier(P.getJardinier());
        return true;
    }
}
