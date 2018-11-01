package moteur;

import javafx.geometry.Point3D;
import joueur.Joueur;
import moteur.objectifs.Objectif;

import java.util.ArrayList;

/**
 * La classe d'affichage
 */
public final class Affichage {

    public static boolean  verbose=true;

    /**
     * Le constructeur
     */
    private Affichage(){

    }

    public static void setVerbose(boolean v) {
        verbose = v;
    }

    /**
     * affiche la pose d'une parcelle
     * @param parcelle
     * @param coordonne
     */
    public static void affichagePoseParcelle(Parcelle parcelle, Point3D coordonne){
        if (!verbose){
            return;
        }
        System.out.println("Parcelle "+parcelle.getType()+" posée en "+stringCoordonne(coordonne));
    }

    /**
     * affiche la pioche d'un objectif
     * @param objectif
     */
    public static void affichagePiocheObjectif(Objectif objectif){
        if (!verbose){
            return;
        }
        System.out.println("Objectif "+objectif.toString()+" pioché");
    }

    /**
     * affichage la pose d'une irrigation
     * @param coordonne
     */
    public static void affichagePoseIrrigation(Point3D coordonne) {
        if (!verbose){
            return;
        }
        System.out.println("Irrigation posée en "+stringCoordonne(coordonne));
    }

    /**
     * affiche le debut du tour
     * @param joueurCourant
     */
    public static void affichageDebutTour(Joueur joueurCourant){
        if (!verbose){
            return;
        }
        System.out.println("C'est au tour du joueur "+initCouleurJoueur(joueurCourant)+joueurCourant.getCouleur());
    }

    /**
     * affiche la fin du tour
     * @param joueurCourant
     */
    public static void affichageFinTour(Joueur joueurCourant){
        if (!verbose){
            return;
        }
        System.out.println(resetCouleur()+"C'est la fin du tour du joueur "+joueurEnCouleur(joueurCourant));
    }

    /**
     * affiche la fin de la partie
     * @param vainqueur
     */
    public static void affichageFinPartie(ArrayList<Joueur> vainqueur){
        if (!verbose){
            return;
        }
        if (vainqueur.size()>1){
            System.out.println("\nC'est une egalité");
            for(Joueur joueur:vainqueur){
                System.out.println(joueurEnCouleur(joueur)+" fait égalité avec " + joueur.getScore() + " points.");
            }
            System.out.println("");
        }
        else {
            Joueur joueur=vainqueur.get(0);
            System.out.println("\n"+joueurEnCouleur(joueur)+" gagne avec " + joueur.getScore() + " points.\n");
        }

    }

    /**
     * affiche qu'un objectif a été validé
     * @param joueurcourant
     * @param objectif
     */
    public static void affichageObjectifReussi(Joueur joueurcourant, Objectif objectif) {
        if (!verbose) {
            return;
        }
        System.out.println("Le joueur " + joueurcourant.getCouleur() + " réalise son objectif " + objectif.toString());
    }

    /**
     * affiche le nombre de bambou sur une parcelle
     * @param plateau
     * @param pointBambou
     */
    public static void affichageNombreBambou(Plateau plateau, Point3D pointBambou) {
        if (!verbose) {
            return;
        }
        System.out.println("Il y a " + plateau.getParcelle(pointBambou).getNbBambou() + " bambou en "+stringCoordonne(pointBambou));
    }

    /**
     * affiche l'emplacement du jardinier
     * @param coord
     * @param plateau
     */
    public static void affichageJardinier(Point3D coord, Plateau plateau) {
        if (!verbose) {
            return;
        }
        System.out.println("Jardinier en "+stringCoordonne(coord)+" (Parcelle " + plateau.getParcelle((coord)).getType() + ")");
    }

    /**
     * affiche l'emplacement du panda
     * @param coord
     * @param plateau
     */
    public static void affichagePanda(Point3D coord, Plateau plateau) {
        if (!verbose){
            return;
        }
        System.out.println("Panda en "+stringCoordonne(coord)+" (Parcelle " + plateau.getParcelle((coord)).getType() + ")");
    }

    /**
     * affiche le nombre de victoires pour chaque joueur dans une liste de joueur
     * @param list
     */
    public static void affichageResultatsPartie(ArrayList<Joueur> list) {
        for (Joueur joueur:list){
            System.out.println("Le joueur " + joueurEnCouleur(joueur)+ " à "+joueur.getNbVictoire()+ " victoire(s) et "+joueur.getNbEgalite()+ " égalité(s)");
        }

    }

    /**
     * renvoie une string pour une coordonne
     * @param point
     * @return
     */
    public static String stringCoordonne(Point3D point){
        return "(" + point.getX() + ", " + point.getY() + ", " + point.getZ() + ")";
    }

    /**
     * renvoie une string avec de la couleur pour un joueur
     * @param joueur
     * @return
     */
    public static String joueurEnCouleur(Joueur joueur){
        return initCouleurJoueur(joueur)+joueur.getCouleur()+resetCouleur();
    }

    /**
     * renvoie le carcatere de reinitialisation de la couleur
     * @return
     */
    public static String resetCouleur(){
        return "\u001B[0m";
    }

    /**
     * renvoie le caractere initialisant la couleur
     * @param joueurCourant
     * @return
     */
    public static String initCouleurJoueur(Joueur joueurCourant){
        switch(joueurCourant.getCouleur()){
            case BLEU :
                return "\u001B[34m";
            case ROUGE :
                return "\u001B[31m";
            case VERT :
                return "\u001B[32m";
            default:
                return "\u001B[0m";
        }
    }

}
