package Moteur.Objectifs;

public class Objectif {
    private int valeur;
    private boolean valide = false;

    public Objectif(int valeur){
        this.valeur = valeur;
    }

    public int getValeur() {
        return valeur;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }
}

