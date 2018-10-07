package Joueur;
import Moteur.*;
import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import Moteur.Enums.CouleurBot;

public class BotRandom extends Bot{

    public BotRandom (CouleurBot s){
        super(s);
    }

    public boolean choixAction(int numeroActionDansLeTour,Partie P){
        joueurPose(P);
        joueurDeplaceJardinier(P.getJardinier());
        return true;
    }

}
