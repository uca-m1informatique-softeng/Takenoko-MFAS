package takenoko.moteur;

import javafx.geometry.Point3D;
import takenoko.joueur.Bot;
import takenoko.moteur.objectifs.Objectif;

import java.util.ArrayList;

/**
 * La classe d'affichage
 */
public final class Affichage {

    public static boolean  verbose=true;


    public static void setVerbose(boolean v) {
        verbose = v;
    }

    /**
     * Affiche la pose d'une parcelle
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
     * Affiche la pioche d'un objectif
     * @param objectif
     */
    public static void affichagePiocheObjectif(Objectif objectif){
        if (!verbose){
            return;
        }
        System.out.println("Objectif "+objectif.toString()+" pioché");
    }

    /**
     * Affichage la pose d'une irrigation
     * @param coordonne
     */
    public static void affichagePoseIrrigation(Point3D coordonne) {
        if (!verbose){
            return;
        }
        System.out.println("Irrigation posée en "+stringCoordonne(coordonne));
    }

    /**
     * Affiche le debut du tour
     * @param joueurCourant
     */
    public static void affichageDebutTour(Bot joueurCourant){
        if (!verbose){
            return;
        }
        System.out.println("C'est au tour du joueur "+initCouleurJoueur(joueurCourant)+joueurCourant.getCouleur());
    }

    /**
     * Affiche la fin du tour
     * @param joueurCourant
     */
    public static void affichageFinTour(Bot joueurCourant){
        if (!verbose){
            return;
        }
        System.out.println(resetCouleur()+"C'est la fin du tour du joueur "+joueurEnCouleur(joueurCourant));
    }

    /**
     * Affiche la realisation de l'objectif empereur par le joueur
     * @param joueur
     */
    public static void affichageEmpereur(Bot joueur){
        if (!verbose){
            return;
        }
        System.out.println("Le joueur " + joueur.getCouleur() + " réalise l'objectif empereur");
    }

    /**
     * Affiche la fin d'une partie a cause d'un nombre de coup trop élevé
     */
    public static void affichagePartieAnnule(){
        if (!verbose){
            return;
        }
        System.out.println("Nombre de coups trop élevé, la partie a été interrompue");
    }

    /**
     * Affiche la fin de la partie
     * @param vainqueur
     */
    public static void affichageFinPartie(ArrayList<Bot> vainqueur){
        if (!verbose){
            return;
        }
        if (vainqueur.size()>1){
            System.out.println("\nC'est une egalité");
            for(Bot joueur:vainqueur){
                System.out.println(joueurEnCouleur(joueur)+" fait égalité avec " + joueur.getScore() + " points.");
            }
            System.out.println("");
        }
        else {
            Bot joueur=vainqueur.get(0);
            System.out.println("\n"+joueurEnCouleur(joueur)+" gagne avec " + joueur.getScore() + " points.\n");
        }

    }

    /**
     * Affiche qu'un objectif a été validé
     * @param joueurcourant
     * @param objectif
     */
    public static void affichageObjectifReussi(Bot joueurcourant, Objectif objectif) {
        if (!verbose) {
            return;
        }
        System.out.println("Le joueur " + joueurcourant.getCouleur() + " réalise son objectif " + objectif.toString());
    }

    /**
     * Affiche le nombre de bambou sur une parcelle
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
     * Affiche l'emplacement du jardinier
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
     * Affiche l'emplacement du panda
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
     * Affiche le nombre de victoires pour chaque joueur dans une liste de joueur
     * @param list
     */
    public static void affichageResultatsPartie(ArrayList<Bot> list) {
        for (Bot joueur:list){
            System.out.println("Le joueur " + joueurEnCouleur(joueur)+ " à "+joueur.getNbVictoire()+ " victoire(s)");
        }

    }

    /**
     * Affiche le nombre de victoires pour chaque joueur dans une liste de joueur
     */
    public static void affichagePlateau() {
        System.out.println(grilleString());
    }


    /**
     * Renvoie une matrice de parcelle
     * @return
     **/
    private static Point3D[][] grilleParcelleSimple(){

        Plateau plateau=Plateau.getInstance();
        ArrayList<Point3D> listCoord=plateau.getKeylist();
        int[] tabtmp=getHauteurLargeurMaxMin(listCoord);
        int maxHauteur=tabtmp[0];
        int minHauteur=tabtmp[1];
        int maxLargeur=tabtmp[2];
        int minLargeur=tabtmp[3];
        Point3D[][] result=new Point3D[ecartMinMax(minLargeur,maxLargeur)+1][ecartMinMax(minHauteur,maxHauteur)+1];

        for (int i=0;i<=ecartMinMax(maxHauteur,minHauteur);i++) {
            for (int j=0;j<=ecartMinMax(minLargeur,maxLargeur);j++) {
                result[j][i]=null;
            }
        }


        for(Point3D coordCourante:plateau.getKeylist()){

            int x3D=(int)coordCourante.getX();
            int z3D=(int)coordCourante.getZ();
            int x2D=convertX2D(x3D,z3D);
            int y2D=z3D;
            result[x2D+Math.abs(minLargeur)][y2D+Math.abs(maxHauteur)]=coordCourante;
        }


        return result;

    }

    /**
     * Renvoie la hauteur et la largeur de la hashmap du plateau
     * @return
     * @param keylist
     **/
    private static int[] getHauteurLargeurMaxMin(ArrayList<Point3D> keylist){
        int[] result=new int[4];
        for(Point3D coordCourante:keylist){
            int z=(int)coordCourante.getZ();
            int x=(int)coordCourante.getX();
            if(z<result[0]){
                result[0]=z;
            }
            if(z>result[1]){
                result[1]=z;
            }
            if(convertX2D(x,z)>result[2]){
                result[2]=convertX2D(x,z);
            }
            if(convertX2D(x,z)<result[3]){
                result[3]=convertX2D(x,z);
            }
        }
        return result;
    }

    /**
     * Renvoie la convertion d'une coordonne 3D en coordonne 2D
     * @return
     * @param x
     * @param z
     */
    private static int convertX2D(int x,int z){
        return (x+(z-(z&1))/2);
    }

    /**
     * Renvoie l'ecart entre le min et le max
     * @return
     * @param min
     * @param max
     */
    private static int ecartMinMax(int min,int max){
        return Math.abs(min-max);
    }

    /**
     * Renvoie une string pour la grille
     * @return
     */
    private static StringBuilder grilleString(){
        StringBuilder result=new StringBuilder();
        Plateau plateau=Plateau.getInstance();

        Point3D[][] grilleParcelle=grilleParcelleSimple();
        int ligneOrigine=ligneDeLaParcelleEtang();

        for (int i=0;i<grilleParcelle[0].length;i++) {
            boolean lignePasDecalee=(ligneOrigine%2==i%2);
            for(int numLigne=0;numLigne<4;numLigne++) {

                if(!lignePasDecalee) {
                    result.append("    ");
                }
                for (int j=0;j<grilleParcelle.length;j++) {
                    Point3D coord=grilleParcelle[j][i];
                    result.append(ligne(plateau,coord,numLigne));
                }
                result.append("\n");}
        }
        return result;
    }

    /**
     * Renvoie la ligne a laquelle se trouve la parcelle etang
     **/
    private static int ligneDeLaParcelleEtang() {
        Point3D[][] grilleParcelle=grilleParcelleSimple();
        for (int i=0;i<grilleParcelle[0].length;i++) {
            for (int j=0;j<grilleParcelle.length;j++) {
                Parcelle parcellecourante = Plateau.getInstance().getParcelle(grilleParcelle[j][i]);
                if (parcellecourante!=null && parcellecourante.getType() == Enums.TypeParcelle.ETANG) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Renvoie une string pour une ligne
     * @return
     * @param plateau
     * @param coord
     * @param nbligne
     */
    private static String ligne(Plateau plateau, Point3D coord,int nbligne) {
        StringBuilder result=new StringBuilder();
        Parcelle parcellecourante=plateau.getParcelle(coord);
        ArrayList<Point3D> listirrig=null;
        if(coord!=null) {
            listirrig=plateau.getIrrigationVoisineDeParcelle(coord);
        }
        result.append(ligneNb(nbligne,coord,parcellecourante,listirrig,plateau));

        if(nbligne==3) {
            result.append(resetCouleur()+" ");
        }
        else {
            if(listirrig!= null && plateau.getIrrigation(listirrig.get(1))!=null) {
                result.append("\u001B[44m");
            }
            result.append(" "+resetCouleur());
        }
        return result.toString();
    }

    /**
     * Renvoie une string pour une ligne de ligne de parcelle
     * @return
     * @param nbligne
     * @param coord
     * @param parcellecourante
     * @param listirrig
     * @param plateau
     */
    private static String ligneNb(int nbligne, Point3D coord, Parcelle parcellecourante,ArrayList<Point3D>listirrig,Plateau plateau){
        switch (nbligne){
            case 0:
                if(coord!=null) {
                    return ""+couleurParcelle(parcellecourante)+"  "+parcellecourante.getNbBambou()+" "+irrigationParcelle(parcellecourante)+"  "+resetCouleur()+"";
                }
                return "       ";
            case 1:
                if(coord!=null) {
                    return ""+couleurParcelle(parcellecourante)+" "+coordonne(coord.getX())+coordonne(coord.getY())+coordonne(coord.getZ())+""+resetCouleur();
                }
                return "       ";
            case 2:
                if(coord!=null) {
                    return ""+couleurParcelle(parcellecourante)+"       "+resetCouleur()+"";
                }
                return "       ";
            case 3:
                StringBuilder result=new StringBuilder();
                result.append("");
                if(listirrig!= null && plateau.getIrrigation(listirrig.get(3))!=null) {
                    result.append("\u001B[44m");
                }
                result.append("   ");
                result.append(resetCouleur());
                result.append(" ");
                if(listirrig!= null && plateau.getIrrigation(listirrig.get(2))!=null) {
                    result.append("\u001B[44m");
                }
                result.append("   ");
                result.append(resetCouleur());
                result.append("");
                return result.toString();
            default:
                return "";
        }
    }

    /**
     * Renvoie une string initialiser la couleur de la parcelle
     * @param parcellecourante
     * @return
     */
    private static String couleurParcelle(Parcelle parcellecourante) {
        String type="";
        if(parcellecourante==null){
            return type;
        }
        switch (parcellecourante.getType()) {
            case JAUNE:
                type = "\u001B[30m\u001B[43m";
                break;
            case ROSE:
                type = "\u001B[30m\u001B[45m";
                break;
            case VERTE:
                type = "\u001B[30m\u001B[42m";
                break;
            case ETANG:
                type = "\u001B[30m\u001B[47m";
                break;
        }

        return type;
    }

    /**
     * Renvoie une string pour une coordonne
     * @param coord
     * @return
     */
    private static String coordonne(double coord){
        String result="";
        int r=(int)coord;
        if(r>=0){
            result+="+";
        }
        result+=r%10;
        return result;
    }

    /**
     * Renvoie une string pour l'irrigation d'une parcelle
     * @param parcelle
     * @return
     */
    private static String irrigationParcelle(Parcelle parcelle){
        if(parcelle==null) {
            return "";
        }
        if(parcelle.isIrriguee()) {
            return "~";
        }
        return "x";
    }

    /**
     * Renvoie une string pour une coordonne
     * @param point
     * @return
     */
    public static String stringCoordonne(Point3D point){
        return "(" + point.getX() + ", " + point.getY() + ", " + point.getZ() + ")";
    }

    /**
     * Renvoie une string avec de la couleur pour un joueur
     * @param joueur
     * @return
     */
    public static String joueurEnCouleur(Bot joueur){
        return initCouleurJoueur(joueur)+joueur.getCouleur()+resetCouleur();
    }

    /**
     * Renvoie le carcatere de reinitialisation de la couleur
     * @return
     */
    public static String resetCouleur(){
        return "\u001B[0m";
    }

    /**
     * Renvoie le caractere initialisant la couleur
     * @param joueurCourant
     * @return
     */
    public static String initCouleurJoueur(Bot joueurCourant){
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
