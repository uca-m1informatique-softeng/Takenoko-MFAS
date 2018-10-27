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
    private ArrayList<Bambou> listBambou;
    private int score;
    private ArrayList<Irrigation> listIrrigation;
    private int nbVictoire;
    private int nbEgalite;

    public int getNbVictoire() {
        return nbVictoire;
    }

    public void setNbVictoire(int victoire) {
        nbVictoire = victoire;
    }

    /**
     * Le constructeur
     * @param couleur
     */
    public Joueur(CouleurBot couleur){
        this.couleur = couleur;
        this.nombreObjectifsRemplis=0;
        this.listObjectifs=new ArrayList<>();
        this.listBambou=new ArrayList<>();
        this.listIrrigation=new ArrayList<>();
        this.score=0;
        this.nbVictoire=0;
        this.nbEgalite=0;

    }

    //////////////////////////////GETTER et SETTER//////////////////////////////

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

    public int getNombreObjectifsRemplis() {
        return nombreObjectifsRemplis;
    }

    public void setNombreObjectifsRemplis(int nombreObjectifsRemplis) {
        this.nombreObjectifsRemplis = nombreObjectifsRemplis;
    }

    public ArrayList<Bambou> getListBambou() {
        return listBambou;
    }

    public void setListBambou(ArrayList<Bambou> listBambou) {
        this.listBambou = listBambou;
    }


    public ArrayList<Objectif> getListObjectifs() {
        return listObjectifs;
    }

    public void setListObjectifs(ArrayList<Objectif> listObjectifs2) {
        listObjectifs = listObjectifs2;
    }

    public ArrayList<Irrigation> getListIrrigation() {
        return listIrrigation;
    }

    public void setListIrrigation(ArrayList<Irrigation> listIrrigation) {
        this.listIrrigation = listIrrigation;
    }

    public int getNbEgalite() {
        return nbEgalite;
    }

    public void setNbEgalite(int egalite) {
        nbEgalite = egalite;
    }

    //////////////////////////////Méthodes//////////////////////////////

    public void resetJoueur(){
        setNombreObjectifsRemplis(0);
        setListObjectifs(new ArrayList<Objectif>());
        setListBambou(new ArrayList<Bambou>());
        setListIrrigation(new ArrayList<Irrigation>());
        setScore(0);
    }

    /**
     * @param irrigation
     */
    public void addIrrigation (Irrigation irrigation){
        listIrrigation.add(irrigation);
    }

    /**
     * C'est la méthode qui permet d'ajouter un objectif à la liste du joueur
     * @param objectif
     */
    public void addObjectif(Objectif objectif){
        this.listObjectifs.add(objectif);

    }

    /**
     * La méthode qui vérifie que le bot a bien réaliser son objectif.
     * @param partie
     */
    public void verifierMesObjectif(Partie partie) {
        Iterator<Objectif> i = listObjectifs.iterator();
        while (i.hasNext()) {
            Objectif objectif = i.next();
            if (objectif.validation(partie,this) && nombreObjectifsRemplis < 1) {
                Affichage.affichageObjectifReussi(this,objectif);
                nombreObjectifsRemplis++;
                score += objectif.getValeur();
                i.remove();
            }
        }
    }

    /**
     * C'est la méthode qui permet de réunir les actions du bot.
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
     * C'est la méthode pour que le joueur pose une parcelle
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
     * C'est la méthode qui permet au Joueur de déplacer le jardinier
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
     * C'est la méthode qui permet au Joueur de déplacer le panda
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
        }
    }

    /**
     * @param partie
     */
    public void joueurPoseIrrigation(Partie partie){
        Plateau plateau = partie.getPlateau();
        ArrayList<Point3D> list = plateau.emplacementsAutoriseIrrigation();
        Irrigation irrig = new Irrigation();
        if(!list.isEmpty()){
            Point3D point = list.get(0);
            plateau.poserIrrigation(irrig, point);
        }
    }

    public void addVictoire(){nbVictoire++;}
    public void addEgalite(){nbEgalite++;}

}
