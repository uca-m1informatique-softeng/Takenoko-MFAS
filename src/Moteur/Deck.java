package Moteur;

import Moteur.Objectifs.Objectif;
import Moteur.Objectifs.ObjectifJardinier;
import Moteur.Objectifs.ObjectifPanda;

import java.util.ArrayList;

public class Deck {

    private ObjectifJardinier[] deckObjectifsJardinier = new ObjectifJardinier[3];
    private ObjectifPanda[] deckObjectifsPanda = new ObjectifPanda[3];
    private Parcelle [] DP = new Parcelle[28];
    private int nb = 27;

    public Deck() {
        initialiserObjectifsJardinier();
        initialiserObjectifsPanda();
        initialiserDeckParcelle();
    }

    public void initialiserDeckParcelle() {
        for (int i = 0;i < 11;i++){
            DP [i] = new Parcelle(TypeParcelle.Vert);
        }
        for (int i = 11;i < 18;i++){
            DP [i] = new Parcelle(TypeParcelle.Rose);
        }
        for (int i = 18;i < 27;i++){
            DP [i] = new Parcelle(TypeParcelle.Jaune);
        }

    }

    public void initialiserObjectifsJardinier(){
        deckObjectifsJardinier[0] = new ObjectifJardinier(6,TypeParcelle.Jaune,4);
        deckObjectifsJardinier[1] = new ObjectifJardinier(7,TypeParcelle.Rose,4);
        deckObjectifsJardinier[2] = new ObjectifJardinier(5,TypeParcelle.Vert,4);
    }
    public void initialiserObjectifsPanda(){
        deckObjectifsPanda[0] = new ObjectifPanda(4,TypeParcelle.Jaune,2);
        deckObjectifsPanda[1] = new ObjectifPanda(5,TypeParcelle.Rose,2);
        deckObjectifsPanda[2] = new ObjectifPanda(3,TypeParcelle.Vert,2);

    }

    public ObjectifJardinier[] getDeckObjectifsJardinier() {
        return deckObjectifsJardinier;
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
}
