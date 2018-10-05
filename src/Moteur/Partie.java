package Moteur;

import Joueur.Bot;

import java.util.Random;

public class Partie {

    private Plateau plateau;
    private Jardinier jardinier;
    private Panda panda;
    private Deck deck;

    public Partie() {
        plateau=new Plateau();
        jardinier=new Jardinier(plateau);
        panda=new Panda(plateau);
        deck = new Deck(this);
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public Jardinier getJardinier() {
        return jardinier;
    }

    public void setJardinier(Jardinier jardinier) {
        this.jardinier = jardinier;
    }

    public Panda getPanda() {
        return panda;
    }

    public void setPanda(Panda panda) {
        this.panda = panda;
    }

    public Deck getDeck() { return deck; }

    public void setDeck(Deck deck) { this.deck = deck; }

}
