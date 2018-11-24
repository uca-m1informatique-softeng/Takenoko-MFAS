package takenoko.moteur;

import takenoko.joueur.Joueur;
import takenoko.moteur.personnages.Jardinier;
import takenoko.moteur.personnages.Panda;

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

    public Partie(){
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

        for (Joueur joueurCourant: listJoueurs) {
            Affichage.affichageDebutTour(joueurCourant);
            joueurCourant.resetJoueur();
            Affichage.affichageFinTour(joueurCourant);
        }

        plateau.setListdesjoueurs(listJoueurs);
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
                Joueur joueurCourant=listJoueurs.get(i);
                Affichage.affichageDebutTour(joueurCourant);

                //premiere action
                joueurCourant.choixAction();
                //deuxieme action
                joueurCourant.choixAction();

                //verifier objectif
                joueurCourant.verifierMesObjectif();
                if(joueurCourant.getNombreObjectifsRemplis()>nbObjectifFinDuJeu && !finDePartie){
                    premierTermine=i;
                    finDePartie=true;
                    joueurCourant.setScore(joueurCourant.getScore()+2);
                    Affichage.affichageEmpereur(joueurCourant);
                }
                Affichage.affichageFinTour(joueurCourant);
                joueurCourant.resetListAction();
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
            Joueur joueurCourant=listJoueurs.get(i);
            Affichage.affichageDebutTour(joueurCourant);
            //premiere action
            joueurCourant.choixAction();
            //deuxieme action
            joueurCourant.choixAction();
            //verifier objectif
            joueurCourant.verifierMesObjectif();
            Affichage.affichageFinTour(joueurCourant);
            joueurCourant.resetListAction();
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
