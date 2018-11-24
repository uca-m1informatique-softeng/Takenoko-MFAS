package takenoko.moteur;

import takenoko.moteur.objectifs.Objectif;
import takenoko.moteur.objectifs.ObjectifJardinier;
import takenoko.moteur.objectifs.ObjectifPanda;
import takenoko.moteur.Enums.TypeParcelle;

import java.util.ArrayList;
import java.util.Random;

/**
 * La classe des decks
 */
public class Deck {

    private static Deck instance=null;
    private ArrayList<ObjectifJardinier> deckObjectifsJardinier = new ArrayList<>();
    private ArrayList<ObjectifPanda> deckObjectifsPanda = new ArrayList<>();
    private ArrayList<Parcelle> deckParcelles = new ArrayList<>();

    public Deck() {
        deckObjectifsJardinier = new ArrayList<>();
        deckObjectifsPanda = new ArrayList<>();
        deckParcelles = new ArrayList<>();

        initialiserObjectifsJardinier();
        initialiserObjectifsPanda();
        initialiserDeckParcelle();
    }


    //////////////////////////////GETTER et SETTER//////////////////////////////

    public ArrayList<ObjectifJardinier> getDeckObjectifsJardinier() {
        return deckObjectifsJardinier;
    }

    public void setDeckObjectifsJardinier(ArrayList<ObjectifJardinier> deckObjectifsJardinier) {
        this.deckObjectifsJardinier = deckObjectifsJardinier;
    }

    public ArrayList<ObjectifPanda> getDeckObjectifsPanda() {
        return deckObjectifsPanda;
    }

    public void setDeckObjectifsPanda(ArrayList<ObjectifPanda> deckObjectifsPanda) {
        this.deckObjectifsPanda = deckObjectifsPanda;
    }

    public ArrayList<Parcelle> getDeckParcelles() {
        return deckParcelles;
    }

    public void setDeckParcelles(ArrayList<Parcelle> deckParcelles) {
        this.deckParcelles = deckParcelles;
    }



    public final static Deck getInstance() {
        if (Deck.instance == null) {
            Deck.instance = new Deck();
            Deck.instance.resetDeck();
        }

        return Deck.instance;
    }

    //////////////////////////////Méthodes//////////////////////////////

    /**
     * reinitialise le deck
     */
    public void resetDeck(){
        initialiserObjectifsJardinier();
        initialiserObjectifsPanda();
        initialiserDeckParcelle();
    }


    /**
     * initialise le deck des parcelles
     */
    public void initialiserDeckParcelle() {
        deckParcelles.clear();
        for (int i = 0;i < 11;i++){
            Parcelle parcelle = new Parcelle(TypeParcelle.VERTE);
            parcelle.setType(TypeParcelle.VERTE);
            deckParcelles.add(parcelle);
        }
        for (int i = 11;i < 18;i++){
            Parcelle parcelle = new Parcelle(TypeParcelle.ROSE);
            parcelle.setType(TypeParcelle.ROSE);
            deckParcelles.add(parcelle);
        }
        for (int i = 18;i < 27;i++){
            Parcelle parcelle = new Parcelle(TypeParcelle.JAUNE);
            parcelle.setType(TypeParcelle.JAUNE);
            deckParcelles.add(parcelle);
        }

    }


    /**
     * initialise le deck des objectifs jardinier
     */
    public void initialiserObjectifsJardinier(){
        deckObjectifsJardinier.clear();
        for (int i = 0;i<5;i++){
            deckObjectifsJardinier.add(new ObjectifJardinier(6,TypeParcelle.JAUNE,4));

        }

        for (int i = 5;i<10;i++){
            deckObjectifsJardinier.add(new ObjectifJardinier(7,TypeParcelle.ROSE,4));
        }
        for (int i = 10;i<15;i++){
            deckObjectifsJardinier.add(new ObjectifJardinier(5,TypeParcelle.VERTE,4));
        }



    }

    /**
     * initialise le deck des objectifs panda
     */
    public void initialiserObjectifsPanda(){

        deckObjectifsPanda.clear();
        for (int i = 0;i<4;i++){
            deckObjectifsPanda.add(new ObjectifPanda(4,TypeParcelle.JAUNE,2));
        }

        for (int i = 4;i<7;i++){
            deckObjectifsPanda.add(new ObjectifPanda(5,TypeParcelle.ROSE,2));
        }
        for (int i = 7;i<12;i++){
            deckObjectifsPanda.add(new ObjectifPanda(3,TypeParcelle.VERTE,2));
        }

        deckObjectifsPanda.add(new ObjectifPanda(6,TypeParcelle.JAUNE,3));
        deckObjectifsPanda.add(new ObjectifPanda(6,TypeParcelle.ROSE,3));
        deckObjectifsPanda.add(new ObjectifPanda(6,TypeParcelle.VERTE,3));


    }


    /**
     * La pioche des parcelles
     * @return
     */
    public ArrayList<Parcelle> piocherParcelle(){
        ArrayList<Parcelle> listeParcelleTemporaire = new ArrayList<>();

        int nombreparcelle=3;
        if(deckParcelles.size() < 3){

            nombreparcelle=deckParcelles.size();
        }

        for (int i = 0; i < nombreparcelle; i++) {
            int random = new Random().nextInt(deckParcelles.size());
            listeParcelleTemporaire.add(deckParcelles.get(random));
            deckParcelles.remove(random);
        }

        return listeParcelleTemporaire;
    }
    /**
     * La méthode qui remet les deux parcelles dans le deck
     * @param listParcelle
     */
    public void remettreParcellesDansDeck(ArrayList<Parcelle> listParcelle){
        deckParcelles.addAll(listParcelle);
    }

    /**
     * La pioche des objectifs jardinier
     */
    public ObjectifJardinier piocheObjectifJardinier() {
        return (ObjectifJardinier)piocheobjectif(deckObjectifsJardinier);
    }

    /**
     * La pioche des objectifs panda
     */
    public ObjectifPanda piocheObjectifPanda() {
        return (ObjectifPanda)piocheobjectif(deckObjectifsPanda);
    }

    /**
     * La pioche des objectifs
     * @param list
     * @return
     */
    private Objectif piocheobjectif(ArrayList<?> list){
        if (!list.isEmpty()) {
            int random = new Random().nextInt(list.size());
            Objectif objtemp =(Objectif) list.get(random);
            list.remove(random);
            Affichage.affichagePiocheObjectif(objtemp);
            return objtemp;

        }
        return null;
    }


    /**
     * renvoie si le deck des parcelles est vide
     * @return
     */
    public boolean isDeckParcelleVide(){
        return deckParcelles.isEmpty();
    }

    /**
     * envoie si le deck des objectifs panda est vide
     * @return
     */
    public boolean isDeckObjectifPandaVide(){
        return deckObjectifsPanda.isEmpty();
    }

    /**
     * envoie si le deck des objectifs jardinier est vide
     * @return
     */
    public boolean isDeckObjectifJardinierVide(){
        return deckObjectifsJardinier.isEmpty();
    }

}
