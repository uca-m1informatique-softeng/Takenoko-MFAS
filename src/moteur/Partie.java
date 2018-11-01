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

        int nbObjectifFinDuJeu=11-listJoueurs.size();
        int premierTermine=0;
        int compteurTour=0;

        //coeur du jeu
        while (!finDePartie) {
            for (int i=0;i<listJoueurs.size();i++) {
                Joueur JoueurCourant=listJoueurs.get(i);
                Affichage.affichageDebutTour(JoueurCourant);

                //premiere action
                JoueurCourant.choixAction();
                //deuxieme action
                JoueurCourant.choixAction();

                //verifier objectif
                JoueurCourant.verifierMesObjectif();
                if(JoueurCourant.getNombreObjectifsRemplis()>nbObjectifFinDuJeu && finDePartie==false){
                    premierTermine=i;
                    finDePartie=true;
                    JoueurCourant.setScore(JoueurCourant.getScore()+2);
                    Affichage.affichageEmpereur(JoueurCourant);
                }
                Affichage.affichageFinTour(JoueurCourant);
                JoueurCourant.resetListAction();
            }
            compteurTour++;
            if (compteurTour>100)
            {
                finDePartie=true;
                Affichage.affichagePartieAnnule();
            }
        }
        // dernier tour
        for (int i=0;i<premierTermine;i++) {
            Joueur JoueurCourant=listJoueurs.get(i);
            Affichage.affichageDebutTour(JoueurCourant);
            //premiere action
            JoueurCourant.choixAction();
            //deuxieme action
            JoueurCourant.choixAction();
            //verifier objectif
            JoueurCourant.verifierMesObjectif();
            Affichage.affichageFinTour(JoueurCourant);
            JoueurCourant.resetListAction();
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
                if (joueur.getScore()==vainqueurCourant.getScore())
                {
                    if(joueur.getNbObjectifPandarealise()>vainqueurCourant.getNbObjectifPandarealise())
                    {   vainqueur.clear();
                        vainqueur.add(joueur);
                    }
                    else{
                        if((joueur.getNbObjectifPandarealise()==vainqueurCourant.getNbObjectifPandarealise()))
                            vainqueur.add(joueur);
                    }
                }
            }
        }

        for (Joueur joueur:vainqueur){
            joueur.addVictoire();
        }
        Affichage.affichageFinPartie(vainqueur);
    }

}
