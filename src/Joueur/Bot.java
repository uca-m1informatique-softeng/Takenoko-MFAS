package Joueur;

import Moteur.*;
import Moteur.Objectifs.Objectif;
import Moteur.Objectifs.ObjectifJardinier;
import Moteur.Objectifs.ObjectifPanda;
import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *C'est la classe des bots
 */
public class Bot {

    private String couleur;
    private int nombreObjectifs = 0;
    private ObjectifJardinier objectif;
    private ObjectifPanda objectif2;
    private Partie partie;
    private ArrayList<Bambou> listBambou;
    int score = 0;

    /**
     * Le constructeur
     * @param couleur
     */
    public Bot(String couleur){
        this.couleur = couleur;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public ObjectifJardinier getObjectif() {
        return objectif;
    }

    public void setObjectif(ObjectifJardinier objectif) {
        this.objectif = objectif;
    }
    public void setObjectif2(ObjectifPanda objectif) {
        this.objectif2 = objectif;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Le déroulement des actions du bot
     * @param partie
     */
    public void play(Partie partie){ // tester plus tard que coord correspond bien à une parcelle

        this.setPartie(partie);
        Plateau plateau=partie.getPlateau();
        verifierMonObjectif(partie.getPlateau().getMap(),partie.getPlateau().getKeylist());
        joueurPose(plateau);

        joueurDeplaceJardinier(partie.getJardinier());

        verifierMonObjectif(partie.getPlateau().getMap(),partie.getPlateau().getKeylist());


    }

    /**
     * La méthode qui vérifie l'objectif du bot

     */
    public void verifierMonObjectif(HashMap<Point3D, Parcelle> map, ArrayList<Point3D> keyList) {
        if (objectif != null && objectif.validation(map,keyList) && nombreObjectifs < 1) {
            System.out.println("Le joueur "+ couleur +" réalise son objectif de faire pousser "+ objectif.getTailleBambou() + " bambous " + objectif.getCouleur());
            nombreObjectifs++;
            score += objectif.getValeur();

        }
        if (objectif2!=null && objectif2.validation(listBambou) && nombreObjectifs < 1) {
            System.out.println("Le joueur "+ couleur +" réalise son objectif de manger "+ objectif2.getNombreBambou() + " bambous " + objectif2.getCouleur());
            nombreObjectifs++;
            score += objectif2.getValeur();
        }
    }

    public void joueurPose(Plateau plateau){
        ArrayList<Point3D> list = plateau.emplacementsAutorise();
        Parcelle p = partie.getDeck().piocherParcelle();
        if(list.size() > 0){
            Point3D point = list.get(0);
            plateau.poser(p, point);
            System.out.println("( " + point.getX() + ", " + point.getY() + ", " + point.getZ() + ") tuile posé par le joueur " + couleur);

        }
    }

    public void joueurDeplaceJardinier(Jardinier jardinier){
        Plateau plateau=jardinier.getPlateau();
        ArrayList<Point3D> listdeplacementJardinier=jardinier.DestinationsPossibles();
        if(listdeplacementJardinier.size() > 0){
            Point3D pointJaridnier = listdeplacementJardinier.get(0);
            jardinier.Deplacer(pointJaridnier);
            System.out.println("le joueur " + couleur + " a deplacé le jardinier en (" + pointJaridnier.getX() + ", " + pointJaridnier.getY() + ", " + pointJaridnier.getZ() + ")");
            System.out.println("Il y a " + plateau.getParcelle(pointJaridnier).getNbBambou() + " bambou.");
        }
    }

    public void joueurDeplacePanda(Panda panda){
        Plateau plateau=panda.getPlateau();
        ArrayList<Point3D> listdeplacementPanda = panda.DestinationsPossibles();
        if(listdeplacementPanda.size() > 0){
            Point3D pointPanda = listdeplacementPanda.get(0);
            if(panda.Deplacer(pointPanda)){
                listBambou.add(new Bambou(plateau.getMap().get(pointPanda).getType()));
            }
            System.out.println("le joueur " + couleur + " a deplacé le panda en (" + pointPanda.getX() + ", " + pointPanda.getY() + ", " + pointPanda.getZ() + ")");
            System.out.println("Il y a " + plateau.getParcelle(pointPanda).getNbBambou() + " bambou.");
        }
    }



    public int getNombreObjectifs() {
        return nombreObjectifs;
    }


    public void setNombreObjectifs(int nombreObjectifs) {
        this.nombreObjectifs = nombreObjectifs;
    }


}
