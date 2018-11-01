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
        plateau=Plateau.getInstance();
        jardinier=Jardinier.getInstance();
        panda=Panda.getInstance();
        deck = Deck.getInstance();
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
     * reinitialise la partie
     * @param listJoueurs
     */
    public void resetPartie(ArrayList<Joueur> listJoueurs){

        plateau.resetPlateau();
        jardinier.resetPersonnage();
        panda.resetPersonnage();
        deck.resetDeck();

        for (Joueur JoueurCourant: listJoueurs) {
            JoueurCourant.resetJoueur();
        }
    }

    /**
     * Le déroulement de la partie
     * @param listJoueurs
     */
    public void jouer(ArrayList<Joueur> listJoueurs){

        //initialisation de la Partie
        resetPartie(listJoueurs);

        Joueur premierTermine=null;
        //coeur du jeu
        while (!finDePartie) {
            for (Joueur JoueurCourant: listJoueurs) {
                Affichage.affichageDebutTour(JoueurCourant);

                //premiere action
                JoueurCourant.choixAction();
                //deuxieme action
                JoueurCourant.choixAction();

                //verifier objectif
                JoueurCourant.verifierMesObjectif();
                if(JoueurCourant.getNombreObjectifsRemplis()>8){ //nombre d'objectifs à réaliser pour terminer le jeu
                    finDePartie=true;
                    JoueurCourant.setScore(JoueurCourant.getScore()+2);
                }
                Affichage.affichageFinTour(JoueurCourant);
                JoueurCourant.resetListAction();
            }

        }
        //fin de partie
        calculVainqueur(listJoueurs);
    }

    /**
     * calcule le ou les vainqueurs d'une partie
     * @param listJoueurs
     */
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
        if(isEgalite(vainqueur)){
            for (Joueur joueur:vainqueur){
                joueur.addEgalite();
            }
        }
        else {
            vainqueur.get(0).addVictoire();
        }
        Affichage.affichageFinPartie(vainqueur);
    }

    /**
     * renvoie si oui ou non il y'a une egalite
     * @param vainqueur
     * @return
     */
    private boolean isEgalite(ArrayList <Joueur> vainqueur){
        return vainqueur.size()>1;
    }

}
