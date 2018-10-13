package moteur;

import joueur.Joueur;
import moteur.objectifs.ObjectifJardinier;
import moteur.objectifs.ObjectifPanda;
import moteur.Enums.TypeParcelle;
import java.util.Random;

/**
 * La classe des decks
 */
public class Deck {

    private ObjectifJardinier[] deckObjectifsJardinier = new ObjectifJardinier[12];
    private ObjectifPanda[] deckObjectifsPanda = new ObjectifPanda[9];
    private Parcelle [] deckParcelles = new Parcelle[28];
    private int nb = 27;
    private int nbop = 9;
    private int nboj = 12;
    private Partie partie;

    /**
     * Le constructeur
     * @param p
     */
    public Deck(Partie p) {
        partie=p;
        initialiserObjectifsJardinier();
        initialiserObjectifsPanda();
        initialiserDeckParcelle();
    }

    /**
     * Le deck des parcelles
     */
    public void initialiserDeckParcelle() {
        for (int i = 0;i < 11;i++){
            deckParcelles [i] = new Parcelle(TypeParcelle.VERTE);
        }
        for (int i = 11;i < 18;i++){
            deckParcelles [i] = new Parcelle(TypeParcelle.ROSE);
        }
        for (int i = 18;i < 27;i++){
            deckParcelles [i] = new Parcelle(TypeParcelle.JAUNE);
        }

    }

    /**
     * Le deck des objectifs jardinier
     */
    public void initialiserObjectifsJardinier(){
        for (int i = 0;i<4;i++){
            deckObjectifsJardinier[i] = new ObjectifJardinier(6,TypeParcelle.JAUNE,4);
        }
        for (int i = 4;i<8;i++){
            deckObjectifsJardinier[i] = new ObjectifJardinier(7,TypeParcelle.ROSE,4);
        }
        for (int i = 8;i<12;i++){
            deckObjectifsJardinier[i] = new ObjectifJardinier(5,TypeParcelle.VERTE,4);
        }



    }

    /**
     * Le deck des objectifs panda
     */
    public void initialiserObjectifsPanda(){
        for (int i = 0;i<3;i++){
            deckObjectifsPanda[i] = new ObjectifPanda(4,TypeParcelle.JAUNE,2);
        }
        for (int i = 3;i<6;i++){
            deckObjectifsPanda[i] = new ObjectifPanda(5,TypeParcelle.ROSE,2);
        }
        for (int i = 6;i<9;i++){
            deckObjectifsPanda[i] = new ObjectifPanda(3,TypeParcelle.VERTE,2);
        }
    }

    public ObjectifJardinier[] getDeckObjectifsJardinier() {
        return deckObjectifsJardinier;
    }


    /**
     * La pioche des parcelles
     * @return
     */
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
        return new Parcelle(TypeParcelle.ETANG);
    }


    /**
     * La pioche des objectifs jardinier
     * @param bot
     */
    public void piocheObjectifJardinier(Joueur bot){
        Deck d = this.partie.getDeck();
        ObjectifJardinier[] j = d.getDeckObjectifsJardinier();
        int r = new Random().nextInt(nboj);
        nboj--;

        ObjectifJardinier tmp = j[r];
        j[r] = j[d.getNboj()];
        j[d.getNboj()] = tmp;
        bot.addObjectif(tmp);
    }

    /**
     * La pioche des objectifs panda
     * @param bot
     */
    public void piocheObjectifPanda(Joueur bot){
        Deck d = this.partie.getDeck();
        ObjectifPanda[] p = d.getDeckObjectifsPanda();
        int n = d.getNbop();
        int r = new Random().nextInt(n);
        d.setNbop(n - 1);
        ObjectifPanda tmp = p[r];
        p[r] = p[d.getNbop()];
        p[d.getNbop()] = tmp;
        bot.addObjectif(tmp);
    }

    public ObjectifPanda[] getDeckObjectifsPanda() {
        return deckObjectifsPanda;
    }
    public Parcelle [] getDeckParcelle() {
        return deckParcelles;
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
