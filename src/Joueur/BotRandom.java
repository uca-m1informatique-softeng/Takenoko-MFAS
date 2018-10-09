package Joueur;
import Moteur.*;
import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import Moteur.Enums.CouleurBot;

/**
 * La classe du bot Random(pour le moment elle n'est plus utiliser)
 */
public class BotRandom extends Bot{

    /**
     * Le constructeur
     * @param s
     */
    public BotRandom (CouleurBot s){
        super(s);
    }

    /**
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
