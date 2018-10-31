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
public class Joueur implements IA {
    private ArrayList<Enums.Action> listAction;
    private CouleurBot couleur;
    private int nombreObjectifsRemplis;
    private ArrayList<Objectif> listObjectifs;
    private ArrayList<Bambou> listBambou;
    private int score;
    private ArrayList<Irrigation> listIrrigation;
    private int nbVictoire;
    private int nbEgalite;



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
        this.listAction=new ArrayList<>();
        this.score=0;
        this.nbVictoire=0;
        this.nbEgalite=0;

    }

    //////////////////////////////GETTER et SETTER//////////////////////////////


    public ArrayList<Enums.Action> getListAction() {
        return listAction;
    }

    public void setListAction(ArrayList<Enums.Action> listAction) {
        this.listAction = listAction;
    }

    public int getNbVictoire() {
        return nbVictoire;
    }

    public void setNbVictoire(int victoire) {
        nbVictoire = victoire;
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

    public void supprBambou(Enums.TypeParcelle couleur,int nb) {
        for(int i=0;i<nb;i++){
            int j=0;
            while(listBambou.get(j).getCouleur()!=couleur) {
                j++;
            }
            listBambou.remove(j);
        }
    }

    public void resetListAction(){
        setListAction(new ArrayList<Enums.Action>());
    }

    public void addListAction(Enums.Action action){
        listAction.add(action);
    }

    public void resetJoueur(){
        setNombreObjectifsRemplis(0);
        setListObjectifs(new ArrayList<Objectif>());
        setListBambou(new ArrayList<Bambou>());
        setListIrrigation(new ArrayList<Irrigation>());
        setListAction(new ArrayList<>());
        setScore(0);
        actionPiocheObjectifJardinier();
        actionPiocheObjectifPanda();
        actionPiocheObjectifParcelle();
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
     */
    public final void verifierMesObjectif() {
        for(int i=listObjectifs.size()-1;i>=0;i--){
            Objectif objectif = listObjectifs.get(i);
            if (objectif.validation(this)){
                Affichage.affichageObjectifReussi(this,objectif);
                nombreObjectifsRemplis++;
                score += objectif.getValeur();
                listObjectifs.remove(i);
            }
        }
        /*
        Iterator<Objectif> i = listObjectifs.iterator();
        while (i.hasNext()) {
            Objectif objectif = i.next();
            if (objectif.validation(this) && nombreObjectifsRemplis < 1) {
                Affichage.affichageObjectifReussi(this,objectif);
                nombreObjectifsRemplis++;
                score += objectif.getValeur();
                i.remove();
            }
        }*/
    }

    public final ArrayList<Enums.Action> listActionRestantePossible(){
        ArrayList<Enums.Action> result=new ArrayList<>();
        for (Enums.Action action : Enums.Action.values()){
            result.add(action);
        }
        for (int i=result.size()-1;i>=0;i--){
            Enums.Action action=result.get(i);
            if(action==Enums.Action.PIOCHEROBJECTIFJARDINIER || action==Enums.Action.PIOCHEROBJECTIFPANDA || action==Enums.Action.PIOCHEROBJECTIFPARCELLE){
                if((!verifActionPossible(action)) ||listAction.contains(Enums.Action.PIOCHEROBJECTIFJARDINIER)||listAction.contains(Enums.Action.PIOCHEROBJECTIFPANDA)||listAction.contains(Enums.Action.PIOCHEROBJECTIFPARCELLE)){
                    result.remove(action);
                }
            }
            else{
                if(listAction.contains(action) || (!verifActionPossible(action))){
                    result.remove(action);
                }
            }
        }
        return result;
    }

    private final boolean verifActionPossible(Enums.Action action){
        switch (action){
            case DEPLACERPANDA:return verifActionDeplacerPanda();
            case PIOCHERPARCELLE:return verifActionPoserParcelle();
            case POSERIRRIGATION:return verifActionPoserIrrigation();
            case DEPLACERJARDINIER:return verifActionDeplacerJardinier();
            case PIOCHEROBJECTIFPANDA:return verifActionPiocherObjPanda();
            case PIOCHEROBJECTIFPARCELLE:return verifActionPiocherObjParcelle();
            case PIOCHEROBJECTIFJARDINIER:return verifActionPiocherObjJardinier();
        }
        return false;
    }

    private final boolean verifActionDeplacerPanda(){
        return (Plateau.getInstance().getKeylist().size()>1);
    }

    private final boolean verifActionDeplacerJardinier(){
        return (Plateau.getInstance().getKeylist().size()>1);
    }

    private final boolean verifActionPoserParcelle(){
        return (!Deck.getInstance().isDeckParcelleVide());
    }

    private final boolean verifActionPiocherObjPanda(){
        return (!Deck.getInstance().isDeckObjectifPandaVide() && this.getListObjectifs().size()<5);
    }

    private final boolean verifActionPiocherObjJardinier(){
        return (!Deck.getInstance().isDeckObjectifJardinierVide() && this.getListObjectifs().size()<5);
    }

    private final boolean verifActionPiocherObjParcelle(){
        return (!Deck.getInstance().isDeckObjectifParcelleVide() && this.getListObjectifs().size()<5);
    }

    private final boolean verifActionPoserIrrigation(){
        return (!Plateau.getInstance().emplacementsAutoriseIrrigation().isEmpty());
    }

    /**
     * C'est la méthode qui permet de réunir les actions du bot.
     * @return
     */
    public final boolean choixAction(){
        Enums.Action action=choixTypeAction(listActionRestantePossible());
        switch (action){
            case DEPLACERPANDA:
                actionDeplacePanda();
                addListAction(action);
                return true;
            case PIOCHERPARCELLE:
                actionPose();
                addListAction(action);
                return true;
            case DEPLACERJARDINIER:
                actionDeplaceJardinier();
                addListAction(action);
                return true;
            case POSERIRRIGATION:
                actionPoseIrrigation();
                addListAction(action);
                return true;
            case PIOCHEROBJECTIFJARDINIER:
                actionPiocheObjectifJardinier();
                addListAction(action);
                return true;
            case PIOCHEROBJECTIFPANDA:
                actionPiocheObjectifPanda();
                addListAction(action);
                return true;
            case PIOCHEROBJECTIFPARCELLE:
                actionPiocheObjectifParcelle();
                addListAction(action);
                return true;
        }
        return false;
    }

    public final Parcelle piocheUneParcelle(){
        ArrayList<Parcelle> possibilites=Deck.getInstance().piocherParcelle();
        Parcelle parcelleChoisie=choixParcellePioche(possibilites);
        possibilites.remove(parcelleChoisie);
        Deck.getInstance().remettreParcellesDansDeck(possibilites);
        return parcelleChoisie;
    }

    public final void actionPose(){
        Plateau plateau = Plateau.getInstance();
        ArrayList<Point3D> list = plateau.emplacementsAutorise();
        Parcelle parcelle=piocheUneParcelle();
        Point3D choixPose=choixCoordonnePoseParcelle(list,parcelle); //choix dans fils
        joueurPose(plateau,parcelle,choixPose);
    }

    public final void joueurPose(Plateau plateau, Parcelle parcelle, Point3D coord){
        plateau.poser(parcelle, coord);
    }

    /**
     * C'est la méthode qui permet au Joueur de déplacer le jardinier
     */
    public final void actionDeplaceJardinier(){
        Jardinier jardinier=Jardinier.getInstance();
        ArrayList<Point3D> listdeplacementJardinier=jardinier.destinationsPossibles();
        Point3D choixDeplacementJardinier=choixDeplacementJardinier(listdeplacementJardinier);
        joueurDeplaceJardinier(jardinier,choixDeplacementJardinier);
    }

    public final void joueurDeplaceJardinier(Jardinier jardinier, Point3D coord){
        jardinier.deplacer(coord);
    }

    /**
     * C'est la méthode qui permet au Joueur de déplacer le panda
     */
    public final void actionDeplacePanda(){
        Panda panda=Panda.getInstance();
        ArrayList<Point3D> listdeplacementPanda=panda.destinationsPossibles();
        Point3D choixDeplacementPanda=choixDeplacementPanda(listdeplacementPanda);
        joueurDeplacePanda(panda,choixDeplacementPanda);
    }

    public final void joueurDeplacePanda(Panda panda, Point3D coord){
        if(panda.deplacer(coord)){
            listBambou.add(new Bambou(Plateau.getInstance().getMap().get(coord).getType()));
        }
    }

    public final Irrigation piocheUneIrrigation(){
        return Deck.getInstance().piocheIrrigation();
    }

    public final void actionPoseIrrigation(){
        Plateau plateau = Plateau.getInstance();
        ArrayList<Point3D> list = plateau.emplacementsAutoriseIrrigation();
        Irrigation irrigation = piocheUneIrrigation();
        Point3D choixPose=choixCoordonnePoseIrrigation(list); //choix dans fils
        joueurPoseIrrigation(plateau,irrigation,choixPose);
    }

    public final void joueurPoseIrrigation(Plateau plateau, Irrigation irrigation, Point3D coord){
        plateau.poserIrrigation(irrigation, coord);
    }

    public final void actionPiocheObjectifJardinier(){
        addObjectif(Deck.getInstance().piocheObjectifJardinier());
    }

    public final void actionPiocheObjectifPanda(){
        addObjectif(Deck.getInstance().piocheObjectifPanda());
    }

    public final void actionPiocheObjectifParcelle(){
        addObjectif(Deck.getInstance().piocheObjectifParcelle());
    }



    public void addVictoire(){
        nbVictoire++;
    }
    public void addEgalite(){
        nbEgalite++;
    }

    public Point3D choixCoordonnePoseParcelle(ArrayList<Point3D> possibilites, Parcelle parcelle) {
        return null;
    }
    public Point3D choixCoordonnePoseIrrigation(ArrayList<Point3D> possibilites) {
        return null;
    }
    public Parcelle choixParcellePioche(ArrayList<Parcelle> possibilites) {
        return null;
    }
    public Point3D choixDeplacementJardinier(ArrayList<Point3D> possibilites) {
        return null;
    }
    public Point3D choixDeplacementPanda(ArrayList<Point3D> possibilites) {
        return null;
    }
    public Enums.Action choixTypeAction(ArrayList<Enums.Action> possibilites) {
        return null;
    }
    public Objectif choixObjectifPrioritaire() {
        return null;
    }

}
