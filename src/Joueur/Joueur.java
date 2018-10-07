package Joueur;

import Moteur.*;
import Moteur.Objectifs.Objectif;
import Moteur.Personnages.Jardinier;
import Moteur.Personnages.Panda;
import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.Iterator;
import Moteur.Enums.CouleurBot;
public class Joueur {
    private CouleurBot couleur;
    private int nombreObjectifsRemplis;
    private ArrayList<Objectif> ListObjectifs;

    public ArrayList<Bambou> getListBambou() {
        return listBambou;
    }

    public void setListBambou(ArrayList<Bambou> listBambou) {
        this.listBambou = listBambou;
    }

    private ArrayList<Bambou> listBambou;
    private int score = 0;

    public Joueur(CouleurBot couleur){
        this.couleur = couleur;
        this.nombreObjectifsRemplis=0;
        this.ListObjectifs=new ArrayList<Objectif>();
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
        return ListObjectifs;
    }

    public void setListObjectifs(ArrayList<Objectif> listObjectifs) {
        ListObjectifs = listObjectifs;
    }

    public void AddObjectif(Objectif O){
        this.ListObjectifs.add(O);

    }

    public int getNombreObjectifsRemplis() {
        return nombreObjectifsRemplis;
    }

    public void setNombreObjectifsRemplis(int nombreObjectifsRemplis) {
        this.nombreObjectifsRemplis = nombreObjectifsRemplis;
    }

    public void verifierMesObjectif(Partie P) {
        Iterator<Objectif> i = ListObjectifs.iterator();
        while (i.hasNext()) {
            Objectif O=i.next();
            if (O.validation(P,this) && nombreObjectifsRemplis < 1) {
                System.out.println("Le joueur "+ couleur +" réalise son objectif "+O.toString());
                nombreObjectifsRemplis++;
                score += O.getValeur();
                i.remove();
            }
        }
    }

    public boolean choixAction(int numeroActionDansLeTour,Partie P){
        joueurPose(P);
        joueurDeplaceJardinier(P.getJardinier());
        return true;
    }

    public void joueurPose(Partie Partie){
        Plateau plateau=Partie.getPlateau();
        ArrayList<Point3D> list = plateau.emplacementsAutorise();
        Parcelle parcelle = Partie.getDeck().piocherParcelle();
        if(list.size() > 0){
            Point3D point = list.get(0);
            plateau.poser(parcelle, point);
        }
    }

    public void joueurDeplaceJardinier(Jardinier jardinier){
        Plateau plateau=jardinier.getPlateau();
        ArrayList<Point3D> listdeplacementJardinier=jardinier.DestinationsPossibles();
        if(listdeplacementJardinier.size() > 0){
            Point3D pointJaridnier = listdeplacementJardinier.get(0);
            jardinier.Deplacer(pointJaridnier);
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
            System.out.println("Il y a " + plateau.getParcelle(pointPanda).getNbBambou() + " bambou sur cette case");
        }
    }



    public int getNombreObjectifs() {
        return nombreObjectifsRemplis;
    }


    public void setNombreObjectifs(int nombreObjectifs) {
        this.nombreObjectifsRemplis = nombreObjectifs;
    }

    @Override
    public String toString() {
        return this.couleur.toString();
    }

    public String DebutChatcouleur(){
        switch(couleur){
            case Bleu :
                return "\u001B[34m";
            case Rouge :
                return "\u001B[31m";
            case Vert :
                return "\u001B[32m";
        }
        return "\u001B[0m";
    }

    public String FinChatcouleur(){
        return "\u001B[0m";
    }
}
