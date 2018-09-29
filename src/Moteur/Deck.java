package Moteur;

import Moteur.Objectifs.Objectif;
import Moteur.Objectifs.ObjectifJardinier;
import Moteur.Objectifs.ObjectifPanda;

import java.util.ArrayList;

public class Deck {

    private ObjectifJardinier[] deckObjectifsJardinier = new ObjectifJardinier[3];
    private ObjectifPanda[] deckObjectifsPanda = new ObjectifPanda[3];

    public Deck() {
        initialiserObjectifsJardinier();
        initialiserObjectifsPanda();
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
}
