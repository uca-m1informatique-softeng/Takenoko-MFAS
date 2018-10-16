package moteur;

import joueur.Joueur;
import moteur.objectifs.ObjectifJardinier;
import moteur.objectifs.ObjectifPanda;
import moteur.Enums.TypeParcelle;
import moteur.objectifs.ObjectifParcelle;

import java.util.Random;

/**
 * La classe des decks
 */
public class Deck {

    private ObjectifJardinier[] deckObjectifsJardinier = new ObjectifJardinier[12];
    private ObjectifPanda[] deckObjectifsPanda = new ObjectifPanda[9];
    private ObjectifParcelle[] deckObjectifParcelle = new ObjectifParcelle[12];
    private Parcelle [] deckParcelles = new Parcelle[28];
    private int nombreParcelle = 27;
    private int nombreObjectifPanda = 9;
    private int nombreObjectifJardinier = 12;
    private int nombreObjectifParcelle = 12;
    private Partie partie;

    /**
     * Le constructeur
     * @param partie
     */
    public Deck(Partie partie) {
        this.partie=partie;
        initialiserObjectifsJardinier();
        initialiserObjectifsPanda();
        initialiserDeckParcelle();
        initialiserDeckParcelleMotif();
    }


    /**
     * Le deck des objectifs parcelles
     */
    public void initialiserDeckParcelleMotif() {
        deckObjectifParcelle[0] = new ObjectifParcelle(3,TypeParcelle.JAUNE,0);
        deckObjectifParcelle[1] = new ObjectifParcelle(3,TypeParcelle.JAUNE,1);
        deckObjectifParcelle[2] = new ObjectifParcelle(3,TypeParcelle.JAUNE,2);
        deckObjectifParcelle[3] = new ObjectifParcelle(4,TypeParcelle.JAUNE,3);
        deckObjectifParcelle[4] = new ObjectifParcelle(2,TypeParcelle.VERTE,0);
        deckObjectifParcelle[5] = new ObjectifParcelle(2,TypeParcelle.VERTE,1);
        deckObjectifParcelle[6] = new ObjectifParcelle(2,TypeParcelle.VERTE,2);
        deckObjectifParcelle[7] = new ObjectifParcelle(3,TypeParcelle.VERTE,3);
        deckObjectifParcelle[8] = new ObjectifParcelle(4,TypeParcelle.ROSE,0);
        deckObjectifParcelle[9] = new ObjectifParcelle(4,TypeParcelle.ROSE,1);
        deckObjectifParcelle[10] = new ObjectifParcelle(4,TypeParcelle.ROSE,2);
        deckObjectifParcelle[11] = new ObjectifParcelle(5,TypeParcelle.ROSE,3);

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
        if(nombreParcelle > 0) {
            int nbaleatoire = new Random().nextInt(nombreParcelle);
            nombreParcelle--;
            Parcelle parcelle = deck[nbaleatoire];
            deck[nbaleatoire] = deck[d.getNombreParcelle()];
            deck[d.getNombreParcelle()] = parcelle;
            return parcelle;
        }
        if(nombreParcelle == 0){
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
        ObjectifJardinier[] p = d.getDeckObjectifsJardinier();
        int n = d.getNombreObjectifJardinier();
        int r = new Random().nextInt(n);
        d.setNombreObjectifJardinier(n - 1);
        ObjectifJardinier tmp = p[r];
        p[r] = p[d.getNombreObjectifJardinier()];
        p[d.getNombreObjectifJardinier()] = tmp;
        bot.addObjectif(tmp);
    }

    /**
     * La pioche des objectifs panda
     * @param bot
     */
    public void piocheObjectifPanda(Joueur bot){
        Deck d = this.partie.getDeck();
        ObjectifPanda[] p = d.getDeckObjectifsPanda();
        int n = d.getNombreObjectifPanda();
        int r = new Random().nextInt(n);
        d.setNombreObjectifPanda(n - 1);
        ObjectifPanda tmp = p[r];
        p[r] = p[d.getNombreObjectifPanda()];
        p[d.getNombreObjectifPanda()] = tmp;
        bot.addObjectif(tmp);
    }

    /**
     * La pioche des objectifs parcelles
     * @param bot
     */
    public void piocheObjectifParcelle(Joueur bot){
        Deck deck = this.partie.getDeck();
        ObjectifParcelle[] objectifparcelles = deck.getDeckObjectifsParcelles();
        int n = deck.getNombreObjectifParcelle();
        int r = new Random().nextInt(n);
        deck.setNombreObjectifParcelle(n - 1);
        ObjectifParcelle tmp = objectifparcelles[r];
        objectifparcelles[r] = objectifparcelles[deck.getNombreObjectifParcelle()];
        objectifparcelles[deck.getNombreObjectifParcelle()] = tmp;
        bot.addObjectif(tmp);
    }

    public ObjectifPanda[] getDeckObjectifsPanda() {
        return deckObjectifsPanda;
    }
    public Parcelle [] getDeckParcelle() {
        return deckParcelles;
    }
    public ObjectifParcelle[] getDeckObjectifsParcelles() {
        return deckObjectifParcelle;
    }


    public int getNombreParcelle() {
        return nombreParcelle;
    }

    public void setNombreParcelle(int num){
        nombreParcelle = num;
    }

    public int getNombreObjectifPanda() {
        return nombreObjectifPanda;
    }

    public void setNombreObjectifPanda(int nbop) {
        this.nombreObjectifPanda = nbop;
    }

    public int getNombreObjectifJardinier() {
        return nombreObjectifJardinier;
    }

    public void setNombreObjectifJardinier(int nboj) {
        this.nombreObjectifJardinier = nboj;
    }

    public int getNombreObjectifParcelle() {
        return nombreObjectifParcelle;
    }

    public void setNombreObjectifParcelle(int nombreObjectifParcelle) {
        this.nombreObjectifParcelle = nombreObjectifParcelle;
    }
}
