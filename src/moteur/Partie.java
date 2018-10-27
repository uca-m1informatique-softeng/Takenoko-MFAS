package moteur;

import joueur.Joueur;
import moteur.personnages.Jardinier;
import moteur.personnages.Panda;

import java.util.ArrayList;

/**
 * La classe partie
 */
public class Partie {

    private Plateau plateau;
    private Jardinier jardinier;
    private Panda panda;
    private Deck deck;
    private boolean finDePartie;

    /**
     * Le constructeur
     */
    public Partie() {
        plateau=new Plateau();
        jardinier=new Jardinier(plateau);
        panda=new Panda(plateau);
        deck = new Deck(this);
        finDePartie=false;
    }

    //////////////////////////////GETTER et SETTER//////////////////////////////


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

    //////////////////////////////Méthodes//////////////////////////////


    /**
     * Le déroulement de la partie
     * @param listJoueurs
     */
    public void jouer(ArrayList<Joueur> listJoueurs){

        //initialisation de la Partie
        for (Joueur JoueurCourant: listJoueurs) {
            JoueurCourant.resetJoueur();
            this.getDeck().piocheObjectifJardinier(JoueurCourant);
            this.getDeck().piocheObjectifPanda(JoueurCourant);
            this.getDeck().piocheObjectifParcelle(JoueurCourant);
        }

        //coeur du jeu
        while (!finDePartie) {
            for (Joueur JoueurCourant: listJoueurs) {
                Affichage.affichageDebutTour(JoueurCourant);
                int action=0;
                boolean finDuTour=false;
                while (!finDuTour) {

                    //verifier objectif
                    finDuTour=JoueurCourant.choixAction(0,this);
                    //verifier objectif
                    JoueurCourant.verifierMesObjectif(this);
                    if(JoueurCourant.getNombreObjectifsRemplis()>0){ //nombre d'objectifs à réaliser pour terminer le jeu
                        finDePartie=true;
                    }
                }
                Affichage.affichageFinTour(JoueurCourant);
            }

        }
        //fin de partie
        calculVainqueur(listJoueurs);
    }

    private void calculVainqueur(ArrayList <Joueur> listJoueurs){
        ArrayList<Joueur> vainqueur=new ArrayList<>();
        vainqueur.add(listJoueurs.get(0));
        //recup vainqueur
        for (int i=1;i<listJoueurs.size();i++) {
            Joueur joueur=listJoueurs.get(i);
            Joueur vainqueurCourant=vainqueur.get(0);
            if (joueur.getScore()>vainqueurCourant.getScore()) {
                vainqueur.clear();
                vainqueur.add(joueur);
            }
            else{
                if (joueur.getScore()==vainqueurCourant.getScore()) { vainqueur.add(joueur);}
            }
        }
        if(isEgalite(vainqueur))
        {
            for (Joueur joueur:vainqueur){
                joueur.addEgalite();
            }
        }
        else {
            vainqueur.get(0).addVictoire();
        }
        Affichage.affichageFinPartie(vainqueur);
    }

    private boolean isEgalite(ArrayList <Joueur> vainqueur){
        return vainqueur.size()>1;
    }

}
