package Joueur;

import Moteur.*;
import Moteur.Objectifs.Objectif;
import Moteur.Objectifs.ObjectifJardinier;
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

    protected Parcelle piocherParcelle(){
        return new Parcelle(TypeParcelle.etang);
    }

    /**
     * Le déroulement des actions du bot
     * @param partie
     */
    public void play(Partie partie){ // tester plus tard que coord correspond bien à une parcelle

        Plateau plateau=partie.getPlateau();
        verifierMonObjectif(objectif, partie.getPlateau().getMap(),partie.getPlateau().getKeylist());
        joueurPose(plateau);

        joueurDeplaceJardinier(partie.getJardinier());

        verifierMonObjectif(objectif, partie.getPlateau().getMap(),partie.getPlateau().getKeylist());


    }

    /**
     * La méthode qui vérifie l'objectif du bot
     * @param objectif
     */
    public void verifierMonObjectif(ObjectifJardinier objectif,HashMap<Point3D, Parcelle> map, ArrayList<Point3D> keyList) {
        if (objectif.validation(map,keyList)) {
            System.out.println("Le joueur "+ couleur +" réalise son objectif de faire pousser "+ objectif.getTailleBambou() + " bambous " + objectif.getCouleur());
            nombreObjectifs++;
        }
    }

    public void joueurPose(Plateau plateau){
        ArrayList<Point3D> list = plateau.emplacementsAutorise();
        Parcelle p = piocherParcelle();
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


    public int getNombreObjectifs() {
        return nombreObjectifs;
    }


    public void setNombreObjectifs(int nombreObjectifs) {
        this.nombreObjectifs = nombreObjectifs;
    }


}
