package Moteur.Objectifs;

import java.util.Random;

public class Objectif {
    int nbBambouObjectif;

    public Objectif(){
        Random random = new Random();
        nbBambouObjectif = random.nextInt(4) + 1;
    }

    public int getNbBambouObjectif() {
        return nbBambouObjectif;
    }

}
