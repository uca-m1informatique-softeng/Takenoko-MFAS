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

    /**
     * Le déroulement de la partie
     * @param listJoueurs
     */
    public void jouer(ArrayList<Joueur> listJoueurs){

        //initialisation de la Partie
        for (Joueur JoueurCourant: listJoueurs) {
            this.getDeck().piocheObjectifJardinier(JoueurCourant);
            this.getDeck().piocheObjectifPanda(JoueurCourant);
            this.getDeck().piocheObjectifParcelle(JoueurCourant);
        }

        //coeur du jeu
        while (!finDePartie) {
            for (Joueur JoueurCourant: listJoueurs) {
                System.out.println("C'est au tour du joueur "+JoueurCourant.toString());
                int action=0;
                boolean finDuTour=false;
                System.out.print(JoueurCourant.debutChatcouleur());
                while (!finDuTour) {

                    //verifier objectif
                    finDuTour=JoueurCourant.choixAction(0,this);
                    //verifier objectif
                    JoueurCourant.verifierMesObjectif(this);
                    if(JoueurCourant.getNombreObjectifsRemplis()>0){ //nombre d'objectifs à réaliser pour terminer le jeu
                        finDePartie=true;
                    }
                }
                System.out.println("C'est la fin du tour du joueur "+JoueurCourant.toString());
                System.out.print(JoueurCourant.finChatcouleur());
            }

        }
        //fin de partie
        Joueur vainqueur=listJoueurs.get(0);
        boolean egalite=false;
        //recup vainqueur
        for (int i=1;i<listJoueurs.size();i++) {
            Joueur joueur=listJoueurs.get(i);
            if (joueur.getScore()>vainqueur.getScore()) {
                vainqueur = joueur;
                egalite = false;
            }
            else{if (joueur.getScore()==vainqueur.getScore()) {
                egalite = true;
            }}
        }
        if (egalite){
            System.out.println("C'est une egalité");
        }
        else {
            System.out.println(vainqueur.toString()+" gagne avec " + vainqueur.getScore() + " points.");
        }

    }

}
