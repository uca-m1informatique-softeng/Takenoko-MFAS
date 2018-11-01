package moteur;

import joueur.Joueur;
import moteur.objectifs.Objectif;
import moteur.objectifs.ObjectifJardinier;
import moteur.objectifs.ObjectifPanda;
import moteur.Enums.TypeParcelle;
import moteur.objectifs.ObjectifParcelle;
import java.util.ArrayList;
import java.util.Random;

/**
 * La classe des decks
 */
public class Deck {

    private static Deck instance=null;

    private ArrayList<ObjectifJardinier> deckObjectifsJardinier;
    private ArrayList<ObjectifPanda> deckObjectifsPanda;
    private ArrayList<ObjectifParcelle> deckObjectifsParcelle;
    private ArrayList<Parcelle> deckParcelles;


    /**
     * Le constructeur
     */
    public Deck() {

        deckObjectifsJardinier = new ArrayList<>();
        deckObjectifsPanda = new ArrayList<>();
        deckObjectifsParcelle = new ArrayList<>();
        deckParcelles = new ArrayList<>();

        initialiserObjectifsJardinier();
        initialiserObjectifsPanda();
        initialiserDeckParcelle();
        initialiserDeckParcelleMotif();
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

    public ArrayList<ObjectifParcelle> getDeckObjectifsParcelle() {
        return deckObjectifsParcelle;
    }

    public void setDeckObjectifsParcelle(ArrayList<ObjectifParcelle> deckObjectifsParcelle) {
        this.deckObjectifsParcelle = deckObjectifsParcelle;
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
        initialiserDeckParcelleMotif();
    }

    /**
     * initialise le deck des objectifs parcelles
     */
    public void initialiserDeckParcelleMotif() {
        deckObjectifsParcelle.clear();
        deckObjectifsParcelle.add(new ObjectifParcelle(3,TypeParcelle.JAUNE,0));
        deckObjectifsParcelle.add(new ObjectifParcelle(3,TypeParcelle.JAUNE,1));
        deckObjectifsParcelle.add(new ObjectifParcelle(3,TypeParcelle.JAUNE,2));
        deckObjectifsParcelle.add(new ObjectifParcelle(4,TypeParcelle.JAUNE,3));
        deckObjectifsParcelle.add(new ObjectifParcelle(2,TypeParcelle.VERTE,0));
        deckObjectifsParcelle.add(new ObjectifParcelle(2,TypeParcelle.VERTE,1));
        deckObjectifsParcelle.add(new ObjectifParcelle(2,TypeParcelle.VERTE,2));
        deckObjectifsParcelle.add(new ObjectifParcelle(3,TypeParcelle.VERTE,3));
        deckObjectifsParcelle.add(new ObjectifParcelle(4,TypeParcelle.ROSE,0));
        deckObjectifsParcelle.add(new ObjectifParcelle(4,TypeParcelle.ROSE,1));
        deckObjectifsParcelle.add(new ObjectifParcelle(4,TypeParcelle.ROSE,2));
        deckObjectifsParcelle.add(new ObjectifParcelle(5,TypeParcelle.ROSE,3));
    }

    /**
     * initialise le deck des parcelles
     */
    public void initialiserDeckParcelle() {
        deckParcelles.clear();
        for (int i = 0;i < 11;i++){
            deckParcelles.add(new Parcelle(TypeParcelle.VERTE));
        }
        for (int i = 11;i < 18;i++){
            deckParcelles.add(new Parcelle(TypeParcelle.ROSE));
        }
        for (int i = 18;i < 27;i++){
            deckParcelles.add(new Parcelle(TypeParcelle.JAUNE));
        }

    }

    /**
     * initialise le deck des objectifs jardinier
     */
    public void initialiserObjectifsJardinier(){
        deckObjectifsJardinier.clear();
        for (int i = 0;i<4;i++){
            deckObjectifsJardinier.add(new ObjectifJardinier(6,TypeParcelle.JAUNE,4));
        }
        for (int i = 4;i<8;i++){
            deckObjectifsJardinier.add(new ObjectifJardinier(7,TypeParcelle.ROSE,4));
        }
        for (int i = 8;i<12;i++){
            deckObjectifsJardinier.add(new ObjectifJardinier(5,TypeParcelle.VERTE,4));
        }



    }

    /**
     * initialise le deck des objectifs panda
     */
    public void initialiserObjectifsPanda(){
        deckObjectifsPanda.clear();
        for (int i = 0;i<3;i++){
            deckObjectifsPanda.add(new ObjectifPanda(4,TypeParcelle.JAUNE,2));
        }
        for (int i = 3;i<6;i++){
            deckObjectifsPanda.add(new ObjectifPanda(5,TypeParcelle.ROSE,2));
        }
        for (int i = 6;i<90;i++){
            deckObjectifsPanda.add(new ObjectifPanda(3,TypeParcelle.VERTE,2));
        }
    }


    /**
     * La pioche des parcelles
     * @return
     */
    public ArrayList<Parcelle> piocherParcelle(){
        ArrayList<Parcelle> listeParcelleTemporaire = new ArrayList<>();
        if(deckParcelles.size() > 3){
            for (int i = 0; i < 2; i++) {
                int random = new Random().nextInt(deckParcelles.size());
                listeParcelleTemporaire.add(deckParcelles.get(random));
                deckParcelles.remove(random);
            }
        }

        else if(deckParcelles.size() > 0){
            return deckParcelles;
        }
        else if(deckParcelles.size() == 0) {
            return null;
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
     * La pioche des objectifs parcelles
     */
    public ObjectifParcelle piocheObjectifParcelle(){
        return (ObjectifParcelle)piocheobjectif(deckObjectifsParcelle);
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
    private Object piocheobjectif(ArrayList<?> list){
        if (!list.isEmpty()) {
            int random = new Random().nextInt(list.size());
            Object objtemp = list.get(random);
            list.remove(random);
            Affichage.affichagePiocheObjectif((Objectif)objtemp);
            return objtemp;

        }
        return null;
    }


    /**
     * La pioche de l'irrigation
     * @return
     */
    public Irrigation piocheIrrigation(){
        return new Irrigation();
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

    /**
     * envoie si le deck des objectifs parcelle est vide
     * @return
     */
    public boolean isDeckObjectifParcelleVide(){
        return deckObjectifsParcelle.isEmpty();
    }

}
