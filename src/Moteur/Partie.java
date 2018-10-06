package Moteur;

import Joueur.Joueur;

import java.util.ArrayList;

public class Partie {

    private Plateau plateau;
    private Jardinier jardinier;
    private Panda panda;
    private Deck deck;
    private boolean FinDePartie;

    public Partie() {
        plateau=new Plateau();
        jardinier=new Jardinier(plateau);
        panda=new Panda(plateau);
        deck = new Deck(this);
        FinDePartie=false;
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

    public void Jouer(ArrayList<Joueur> ListJoueurs){

        //initialisation de la Partie
        for (Joueur J: ListJoueurs) {
            this.getDeck().piocheObjectifJardinier(J);
            this.getDeck().piocheObjectifPanda(J);
        }

        //coeur du jeu
        while (!FinDePartie)
        {
            for (Joueur J: ListJoueurs) {
                System.out.println("C'est au tour du Joueur "+J.toString());
                int action=0;
                boolean FinDuTour=false;
                System.out.print(J.DebutChatcouleur());
                while (!FinDuTour)
                {

                    //verifier objectif
                    FinDuTour=J.choixAction(0,this);
                    //verifier objectif
                    J.verifierMesObjectif(this);
                    if(J.getNombreObjectifs()>0)//nombre d'objectifs à réaliser pour terminer le jeu
                    {
                        FinDePartie=true;
                    }
                }
                System.out.println("C'est la fin du tour du Joueur "+J.toString());
                System.out.print(J.FinChatcouleur());
            }

        }

        //fin de partie
        Joueur Vainqueur=ListJoueurs.get(0);
        boolean egalite=false;
        //recup vainqueur
        for (int i=1;i<ListJoueurs.size();i++) {
            Joueur J=ListJoueurs.get(i);
            if (J.getScore()>Vainqueur.getScore()) {
                Vainqueur = J;
                egalite = false;
            }
            else{if (J.getScore()==Vainqueur.getScore()) {
                egalite = true;
            }}
        }

        if (egalite){
            System.out.println("C'est une egalité");
        }
        else {
            System.out.println(Vainqueur.toString()+" gagne avec " + Vainqueur.getScore() + " points.");
        }

    }

}
