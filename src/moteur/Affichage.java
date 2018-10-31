package moteur;

import javafx.geometry.Point3D;
import joueur.Joueur;
import moteur.objectifs.Objectif;

import java.util.ArrayList;

public final class Affichage {

    public static boolean  verbose=true;

    private Affichage(){

    }

    public static void setVerbose(boolean v) {
        verbose = v;
    }

    public static void affichagePoseParcelle(Parcelle parcelle, Point3D coordonne){
        if (!verbose) return;
        System.out.println("Parcelle "+parcelle.getType()+" posée en "+stringCoordonne(coordonne));
    }

    public static void affichagePiocheObjectif(Objectif objectif){
        if (!verbose) return;
        System.out.println("Objectif "+objectif.toString()+" pioché");
    }

    public static void affichagePoseIrrigation(Point3D coordonne) {
        if (!verbose) return;
        System.out.println("Irrigation posée en "+stringCoordonne(coordonne));
    }

    public static void affichageDebutTour(Joueur joueurCourant){
        if (!verbose) return;
        System.out.println("C'est au tour du joueur "+initCouleurJoueur(joueurCourant)+joueurCourant.getCouleur());
    }

    public static void affichageFinTour(Joueur joueurCourant){
        if (!verbose) return;
        System.out.println(resetCouleur()+"C'est la fin du tour du joueur "+joueurEnCouleur(joueurCourant));
    }

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

    public static void affichageObjectifReussi(Joueur joueurcourant, Objectif objectif) {
        if (!verbose) {
            return;
        }
        System.out.println("Le joueur " + joueurcourant.getCouleur() + " réalise son objectif " + objectif.toString());
    }

    public static void affichageNombreBambou(Plateau plateau, Point3D pointBambou) {
        if (!verbose) {
            return;
        }
        System.out.println("Il y a " + plateau.getParcelle(pointBambou).getNbBambou() + " bambou en "+stringCoordonne(pointBambou));
    }

    public static void affichageJardinier(Point3D coord, Plateau plateau) {
        if (!verbose) {
            return;
        }
        System.out.println("Jardinier en "+stringCoordonne(coord)+" (Parcelle " + plateau.getParcelle((coord)).getType() + ")");
    }

    public static void affichagePanda(Point3D coord, Plateau plateau) {
        if (!verbose){
            return;
        }
        System.out.println("Panda en "+stringCoordonne(coord)+" (Parcelle " + plateau.getParcelle((coord)).getType() + ")");
    }

    public static void affichageResultatsPartie(ArrayList<Joueur> list) {
        for (Joueur joueur:list){
            System.out.println("Le joueur " + joueurEnCouleur(joueur)+ " à "+joueur.getNbVictoire()+ " victoire(s) et "+joueur.getNbEgalite()+ " égalité(s)");
        }

    }

    public static String stringCoordonne(Point3D point){
        return "(" + point.getX() + ", " + point.getY() + ", " + point.getZ() + ")";
    }

    public static String joueurEnCouleur(Joueur joueur){
        return initCouleurJoueur(joueur)+joueur.getCouleur()+resetCouleur();
    }

    public static String resetCouleur(){
        return "\u001B[0m";
    }

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
