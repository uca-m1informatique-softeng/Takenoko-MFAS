package moteur;

import joueur.Joueur;
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

    private ArrayList<ObjectifJardinier> deckObjectifsJardinier = new ArrayList<>();
    private ArrayList<ObjectifPanda> deckObjectifsPanda = new ArrayList<>();
    private ArrayList<ObjectifParcelle> deckObjectifsParcelle = new ArrayList<>();
    private ArrayList<Parcelle> deckParcelles = new ArrayList<>();

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


    /**
     * Le deck des objectifs parcelles
     */
    public void initialiserDeckParcelleMotif() {
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
     * Le deck des parcelles
     */
    public void initialiserDeckParcelle() {
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
     * Le deck des objectifs jardinier
     */
    public void initialiserObjectifsJardinier(){
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
     * Le deck des objectifs panda
     */
    public void initialiserObjectifsPanda(){
        for (int i = 0;i<3;i++){
            deckObjectifsPanda.add(new ObjectifPanda(4,TypeParcelle.JAUNE,2));
        }
        for (int i = 3;i<6;i++){
            deckObjectifsPanda.add(new ObjectifPanda(5,TypeParcelle.ROSE,2));
        }
        for (int i = 6;i<9;i++){
            deckObjectifsPanda.add(new ObjectifPanda(3,TypeParcelle.VERTE,2));
        }
    }


    /**
     * La pioche des parcelles
     * @return
     */
    public Parcelle piocherParcelle(){
        if(!deckParcelles.isEmpty()){
            int random = new Random().nextInt(deckParcelles.size());
            Parcelle parcelleTemporaire =  deckParcelles.get(random);
            deckParcelles.remove(random);
            return parcelleTemporaire;
        }
        return new Parcelle(TypeParcelle.ETANG);
    }

    /**
     * La pioche des objectifs parcelles
     * @param bot
     */
    public void piocheObjectifParcelle(Joueur bot) {
        if (!deckObjectifsParcelle.isEmpty()) {
            int random = new Random().nextInt(deckObjectifsParcelle.size());
            ObjectifParcelle objectifTemporaireParcelle = deckObjectifsParcelle.get(random);
            deckObjectifsParcelle.remove(random);
            bot.addObjectif(objectifTemporaireParcelle);
        }
    }


    /**
     * La pioche des objectifs jardinier
     * @param bot
     */
    public void piocheObjectifJardinier(Joueur bot) {
        if (!deckObjectifsJardinier.isEmpty()) {
            int random = new Random().nextInt(deckObjectifsJardinier.size());
            ObjectifJardinier objectifTemporaireJardinier = deckObjectifsJardinier.get(random);
            deckObjectifsJardinier.remove(random);
            bot.addObjectif(objectifTemporaireJardinier);

        }
    }

    /**
     * La pioche des objectifs panda
     * @param bot
     */
    public void piocheObjectifPanda(Joueur bot) {
        if (!deckObjectifsPanda.isEmpty()) {
            int random = new Random().nextInt(deckObjectifsPanda.size());
            ObjectifPanda objectifTemporairePanda = deckObjectifsPanda.get(random);
            deckObjectifsPanda.remove(random);
            bot.addObjectif(objectifTemporairePanda);

        }
    }



    public void piocheIrrigation(Joueur bot){
        bot.addIrrigation(new Irrigation());
    }




}
