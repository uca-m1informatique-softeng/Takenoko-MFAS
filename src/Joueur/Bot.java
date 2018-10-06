package Joueur;

import Moteur.*;
import Moteur.Objectifs.Objectif;
import Moteur.Objectifs.ObjectifJardinier;
import Moteur.Objectifs.ObjectifPanda;
import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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
