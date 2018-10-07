package Moteur;

import Joueur.Joueur;
import Moteur.Objectifs.ObjectifJardinier;
import Moteur.Objectifs.ObjectifPanda;
import Moteur.Enums.TypeParcelle;
import java.util.Random;

public class Deck {

    private ObjectifJardinier[] deckObjectifsJardinier = new ObjectifJardinier[12];
    private ObjectifPanda[] deckObjectifsPanda = new ObjectifPanda[9];
    private Parcelle [] DP = new Parcelle[28];
    private int nb = 27;
    private int nbop = 9;
    private int nboj = 12;
    private Partie partie;

    public Deck(Partie p) {
        partie=p;
        initialiserObjectifsJardinier();
        initialiserObjectifsPanda();
        initialiserDeckParcelle();
    }

    public void initialiserDeckParcelle() {
        for (int i = 0;i < 11;i++){
            DP [i] = new Parcelle(TypeParcelle.Verte);
        }
        for (int i = 11;i < 18;i++){
            DP [i] = new Parcelle(TypeParcelle.Rose);
        }
        for (int i = 18;i < 27;i++){
            DP [i] = new Parcelle(TypeParcelle.Jaune);
        }

    }

    public void initialiserObjectifsJardinier(){
        for (int i = 0;i<4;i++){
            deckObjectifsJardinier[i] = new ObjectifJardinier(6,TypeParcelle.Jaune,4);
        }
        for (int i = 4;i<8;i++){
            deckObjectifsJardinier[i] = new ObjectifJardinier(7,TypeParcelle.Rose,4);
        }
        for (int i = 8;i<12;i++){
            deckObjectifsJardinier[i] = new ObjectifJardinier(5,TypeParcelle.Verte,4);
        }



    }
    public void initialiserObjectifsPanda(){
        for (int i = 0;i<3;i++){
            deckObjectifsPanda[i] = new ObjectifPanda(4,TypeParcelle.Jaune,2);
        }
        for (int i = 3;i<6;i++){
            deckObjectifsPanda[i] = new ObjectifPanda(5,TypeParcelle.Rose,2);
        }
        for (int i = 6;i<9;i++){
            deckObjectifsPanda[i] = new ObjectifPanda(3,TypeParcelle.Verte,2);
        }
    }

    public ObjectifJardinier[] getDeckObjectifsJardinier() {
        return deckObjectifsJardinier;
    }


    public Parcelle piocherParcelle(){
        Deck d = this.partie.getDeck();
        Parcelle[] deck = d.getDeckParcelle();
        if(nb > 0) {
            int r = new Random().nextInt(nb);
            nb--;
            Parcelle par = deck[r];
            deck[r] = deck[d.getNb()];
            deck[d.getNb()] = par;
            return par;
        }
        if(nb == 0){
            return deck[0];
        }
        return new Parcelle(TypeParcelle.etang);
    }


    public void piocheObjectifJardinier(Joueur bot){
        Deck d = this.partie.getDeck();
        ObjectifJardinier[] j = d.getDeckObjectifsJardinier();
        int r = new Random().nextInt(nboj);
        nboj--;

        ObjectifJardinier tmp = j[r];
        j[r] = j[d.getNboj()];
        j[d.getNboj()] = tmp;
        bot.AddObjectif(tmp);
    }

    public void piocheObjectifPanda(Joueur bot){
        Deck d = this.partie.getDeck();
        ObjectifPanda[] p = d.getDeckObjectifsPanda();
        int n = d.getNbop();
        int r = new Random().nextInt(n);
        d.setNbop(n - 1);
        ObjectifPanda tmp = p[r];
        p[r] = p[d.getNbop()];
        p[d.getNbop()] = tmp;
        bot.AddObjectif(tmp);
    }

    public ObjectifPanda[] getDeckObjectifsPanda() {
        return deckObjectifsPanda;
    }
    public Parcelle [] getDeckParcelle() {
        return DP;
    }


    public int getNb() {
        return nb;
    }

    public void setNb(int num){
        nb = num;
    }

    public int getNbop() {
        return nbop;
    }

    public void setNbop(int nbop) {
        this.nbop = nbop;
    }

    public int getNboj() {
        return nboj;
    }

    public void setNboj(int nboj) {
        this.nboj = nboj;
    }
}
