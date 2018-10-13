package joueur;

import moteur.*;
import moteur.objectifs.Objectif;
import moteur.personnages.Jardinier;
import moteur.personnages.Panda;
import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.Iterator;
import moteur.Enums.CouleurBot;


/**
 * La classe du joueur
 */
public class Joueur {
    private CouleurBot couleur;
    private int nombreObjectifsRemplis;
    private ArrayList<Objectif> listObjectifs;

    public ArrayList<Bambou> getListBambou() {
        return listBambou;
    }

    public void setListBambou(ArrayList<Bambou> listBambou) {
        this.listBambou = listBambou;
    }

    private ArrayList<Bambou> listBambou;
    private int score = 0;

    /**
     * Le constructeur
     * @param couleur
     */
    public Joueur(CouleurBot couleur){
        this.couleur = couleur;
        this.nombreObjectifsRemplis=0;
        this.listObjectifs=new ArrayList<Objectif>();
        this.listBambou=new ArrayList<Bambou>();
    }

    public CouleurBot getCouleur() {
        return couleur;
    }

    public void setCouleur(CouleurBot couleur) {
        this.couleur = couleur;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public ArrayList<Objectif> getListObjectifs() {
        return listObjectifs;
    }

    public void setListObjectifs(ArrayList<Objectif> listObjectifs2) {
        listObjectifs = listObjectifs2;
    }

    /**
     * @param objectif
     */
    public void addObjectif(Objectif objectif){
        this.listObjectifs.add(objectif);

    }

    public int getNombreObjectifsRemplis() {
        return nombreObjectifsRemplis;
    }

    public void setNombreObjectifsRemplis(int nombreObjectifsRemplis) {
        this.nombreObjectifsRemplis = nombreObjectifsRemplis;
    }

    /**
     * La méthode qui vérifie que le bot a bien réaliser son objectif
     * @param partie
     */
    public void verifierMesObjectif(Partie partie) {
        Iterator<Objectif> i = listObjectifs.iterator();
        while (i.hasNext()) {
            Objectif objectif = i.next();
            if (objectif.validation(partie,this) && nombreObjectifsRemplis < 1) {
                System.out.println("Le joueur "+ couleur +" réalise son objectif "+objectif.toString());
                nombreObjectifsRemplis++;
                score += objectif.getValeur();
                i.remove();
            }
        }
    }

    /**
     * @param numeroActionDansLeTour
     * @param partie
     * @return
     */
    public boolean choixAction(int numeroActionDansLeTour,Partie partie){
        joueurPose(partie);
        joueurDeplaceJardinier(partie.getJardinier());
        return true;
    }

    /**
     * @param partie
     */
    public void joueurPose(Partie partie){
        Plateau plateau = partie.getPlateau();
        ArrayList<Point3D> list = plateau.emplacementsAutorise();
        Parcelle parcelle = partie.getDeck().piocherParcelle();
        if(!list.isEmpty()){
            Point3D point = list.get(0);
            plateau.poser(parcelle, point);
        }
    }

    /**
     * @param jardinier
     */
    public void joueurDeplaceJardinier(Jardinier jardinier){
        ArrayList<Point3D> listdeplacementJardinier=jardinier.DestinationsPossibles();
        if(!listdeplacementJardinier.isEmpty()){
            Point3D pointJaridnier = listdeplacementJardinier.get(0);
            jardinier.Deplacer(pointJaridnier);
        }
    }

    /**
     * @param panda
     */
    public void joueurDeplacePanda(Panda panda){
        Plateau plateau=panda.getPlateau();
        ArrayList<Point3D> listdeplacementPanda = panda.DestinationsPossibles();
        if(!listdeplacementPanda.isEmpty()){
            Point3D pointPanda = listdeplacementPanda.get(0);
            if(panda.Deplacer(pointPanda)){
                listBambou.add(new Bambou(plateau.getMap().get(pointPanda).getType()));
            }
            System.out.println("Il y a " + plateau.getParcelle(pointPanda).getNbBambou() + " bambou sur cette case");
        }
    }




    public void setNombreObjectifs(int nombreObjectifs) {
        this.nombreObjectifsRemplis = nombreObjectifs;
    }

    @Override
    public String toString() {
        return this.couleur.toString();
    }

    /**
     * Pour avoir le déroulement de la partie en couleur
     * @return
     */
    public String debutChatcouleur(){
        switch(couleur){
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

    public String finChatcouleur(){
        return "\u001B[0m";
    }
}
