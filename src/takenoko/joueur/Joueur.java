package takenoko.joueur;

import takenoko.moteur.*;
import takenoko.moteur.objectifs.Objectif;
import takenoko.moteur.objectifs.ObjectifPanda;
import takenoko.moteur.personnages.Jardinier;
import takenoko.moteur.personnages.Panda;
import javafx.geometry.Point3D;

import java.util.ArrayList;
import takenoko.moteur.Enums.CouleurBot;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * La classe du joueur
 */
@Component
@Scope("prototype")
@Primary
public class Joueur implements IA {

    private ArrayList<Enums.Action> listAction = new ArrayList<>();;
    private CouleurBot couleur;
    private int nombreObjectifsRemplis = 0;
    private ArrayList<Objectif> listObjectifs = new ArrayList<>();;
    private ArrayList<Bambou> listBambou = new ArrayList<>();;
    private int score = 0;
    private ArrayList<Irrigation> listIrrigation = new ArrayList<>();;
    private int nbVictoire = 0;
    private int nbObjectifPandarealise = 0;


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

    public int getNbObjectifPandarealise() {
        return nbObjectifPandarealise;
    }

    public void setNbObjectifPandarealise(int nbObjectifPandarealise) {
        this.nbObjectifPandarealise = nbObjectifPandarealise;
    }

    //////////////////////////////Méthodes//////////////////////////////

    /**
     * Supprime nb bambous d'une certaine couleur des bambous possedés par le joueur
     * @param couleur
     * @param nb
     */
    public void supprBambou(Enums.TypeParcelle couleur,int nb) {
        for(int i=0;i<nb;i++){
            int j=0;
            while(listBambou.get(j).getCouleur()!=couleur) {
                j++;
            }
            listBambou.remove(j);
        }
    }

    /**
     * reinitialise la liste des actions effectuees par le joueur dans un tour
     */
    public void resetListAction(){
        setListAction(new ArrayList<Enums.Action>());
    }

    /**
     * ajoute une action dans la liste des actions réalisé par le joueur durant le tour
     * @param action
     */
    public void addListAction(Enums.Action action){
        listAction.add(action);
    }



    /**
     * reinitialise le joueur pour commencer une nouvelle partie
     */
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
        setNombreObjectifsRemplis(0);
    }

    /**
     * ajoute une irrigation à la liste des irrigations du joueur
     * @param irrigation
     */
    public void addIrrigation (Irrigation irrigation){
        listIrrigation.add(irrigation);
    }

    /**
     * C'est la méthode qui permet d'ajouter un objectif à la liste des objectifs du joueur
     * @param objectif
     */
    public void addObjectif(Objectif objectif){
        this.listObjectifs.add(objectif);

    }

    /**
     * La méthode qui permet de vérifier que le bot a bien réaliser ses objectifs
     */
    public final void verifierMesObjectif() {
        for(int i=listObjectifs.size()-1;i>=0;i--){
            Objectif objectif = listObjectifs.get(i);
            if (objectif.validation(this)){
                Affichage.affichageObjectifReussi(this,objectif);
                nombreObjectifsRemplis++;
                if(objectif instanceof ObjectifPanda){
                    nbObjectifPandarealise++;
                }
                score += objectif.getValeur();
                listObjectifs.remove(i);
            }
        }
    }

    /**
     * Renvoie la liste des actions possibles à effectuer dans le tour du joueur
     * @return
     */
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

    /**
     * verifie si une action est possible
     * @param action
     * @return
     */
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

    /**
     * verifie que l'action du deplacement du panda est possible
     * @return
     */
    private final boolean verifActionDeplacerPanda(){
        return (Plateau.getInstance().getKeylist().size()>1);
    }

    /**
     * verifie que l'action du deplacement du jardinier est possible
     * @return
     */
    private final boolean verifActionDeplacerJardinier(){
        return (Plateau.getInstance().getKeylist().size()>1);
    }

    /**
     * verifie que l'action de poser une parcelle est possible
     * @return
     */
    private final boolean verifActionPoserParcelle(){
        return (!Deck.getInstance().isDeckParcelleVide());
    }

    /**
     * verifie que l'action de piocher un objectif panda est possible
     * @return
     */
    private final boolean verifActionPiocherObjPanda(){
        return (!Deck.getInstance().isDeckObjectifPandaVide() && this.getListObjectifs().size()<5);
    }

    /**
     * verifie que l'action de piocher un objectif jardinier est possible
     * @return
     */
    private final boolean verifActionPiocherObjJardinier(){
        return (!Deck.getInstance().isDeckObjectifJardinierVide() && this.getListObjectifs().size()<5);
    }

    /**
     * verifie que l'action de piocher un objectif parcelle est possible
     * @return
     */
    private final boolean verifActionPiocherObjParcelle(){
        return (!Deck.getInstance().isDeckObjectifParcelleVide() && this.getListObjectifs().size()<5);
    }

    /**
     * verifie que l'action de poser une irrigation est possible
     * @return
     */
    private final boolean verifActionPoserIrrigation(){
        return (!Plateau.getInstance().emplacementsAutoriseIrrigation().isEmpty());
    }

    /**
     * C'est la méthode qui permet de choisir l'action que le bot va effectuer
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

    /**
     * Pioche une parcelle dans le deck et la renvoie
     * @return
     */
    public final Parcelle piocheUneParcelle(){
        ArrayList<Parcelle> possibilites=Deck.getInstance().piocherParcelle();
        Parcelle parcelleChoisie=choixParcellePioche(possibilites);
        possibilites.remove(parcelleChoisie);
        Deck.getInstance().remettreParcellesDansDeck(possibilites);
        return parcelleChoisie;
    }

    /**
     * Pose une parcelle piochee sur le plateau
     */
    public final void actionPose(){
        Plateau plateau = Plateau.getInstance();
        ArrayList<Point3D> list = plateau.emplacementsAutorise();
        Parcelle parcelle=piocheUneParcelle();
        Point3D choixPose=choixCoordonnePoseParcelle(list,parcelle); //choix dans fils
        joueurPose(plateau,parcelle,choixPose);
    }

    /**
     * pose la parcelle dans le plateau
     * @param plateau
     * @param parcelle
     * @param coord
     */
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

    /**
     * deplace le jardinier
     * @param jardinier
     * @param coord
     */
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

    /**
     * deplace le panda
     * @param panda
     * @param coord
     */
    public final void joueurDeplacePanda(Panda panda, Point3D coord){
        if(panda.deplacer(coord)){
            Bambou bambou = new Bambou();
            bambou.setCouleur(Plateau.getInstance().getMap().get(coord).getType());
            listBambou.add(bambou);
        }
    }

    /**
     * permet de piohcer une irrigation
     * @return
     */
    public final Irrigation piocheUneIrrigation(){
        return Deck.getInstance().piocheIrrigation();
    }

    /**
     * c'est la methode qui permet de poser une irrigation
     */
    public final void actionPoseIrrigation(){
        Plateau plateau = Plateau.getInstance();
        ArrayList<Point3D> list = plateau.emplacementsAutoriseIrrigation();
        Irrigation irrigation = piocheUneIrrigation();
        Point3D choixPose=choixCoordonnePoseIrrigation(list); //choix dans fils
        joueurPoseIrrigation(plateau,irrigation,choixPose);
    }

    /**
     * C'est la méthode qui permet de poser une irrigation.
     * @param plateau
     * @param irrigation
     * @param coord
     */
    public final void joueurPoseIrrigation(Plateau plateau, Irrigation irrigation, Point3D coord){
        plateau.poserIrrigation(irrigation, coord);
    }

    /**
     *C'est la méthode qui permet de piocher un objectif Jardinier.
     */
    public final void actionPiocheObjectifJardinier(){
        addObjectif(Deck.getInstance().piocheObjectifJardinier());
    }

    /**
     *C'est la méthode qui permet de piocher un objectif Panda.
     */
    public final void actionPiocheObjectifPanda(){
        addObjectif(Deck.getInstance().piocheObjectifPanda());
    }

    /**
     *C'est la méthode qui permet de piocher un objectif Parcelle.
     */
    public final void actionPiocheObjectifParcelle(){
        addObjectif(Deck.getInstance().piocheObjectifParcelle());
    }


    /**
     * ajoute une victoire au joueur
     */
    public void addVictoire(){
        nbVictoire++;
    }


    /**
     * renvoie un choix de coordonne pour la pose des parcelles parmis une liste de possibilités
     * @param possibilites
     * @param parcelle
     * @return
     */
    public Point3D choixCoordonnePoseParcelle(ArrayList<Point3D> possibilites, Parcelle parcelle) {
        return null;
    }

    /**
     * renvoie un choix de coordonne pour la pose d'une irrigation parmis une liste de possibilités
     * @param possibilites
     * @return
     */
    public Point3D choixCoordonnePoseIrrigation(ArrayList<Point3D> possibilites) {
        return null;
    }

    /**
     * renvoie un choix de parcelle parmis une liste de possibilités
     * @param possibilites
     * @return
     */
    public Parcelle choixParcellePioche(ArrayList<Parcelle> possibilites) {
        return null;
    }

    /**
     * renvoie un choix de coordonne pour le deplacement du jardinier parmis une liste de possibilités
     * @param possibilites
     * @return
     */
    public Point3D choixDeplacementJardinier(ArrayList<Point3D> possibilites) {
        return null;
    }

    /**
     * renvoie un choix de coordonne pour le deplacement du panda parmis une liste de possibilités
     * @param possibilites
     * @return
     */
    public Point3D choixDeplacementPanda(ArrayList<Point3D> possibilites) {
        return null;
    }

    /**
     * renvoie un choix d'action parmis une liste de possibilités
     * @param possibilites
     * @return
     */
    public Enums.Action choixTypeAction(ArrayList<Enums.Action> possibilites) {
        return null;
    }

    /**
     * renvoie un objectif ciblé en priorité par le joueur
     * @return
     */
    public Objectif choixObjectifPrioritaire() {
        return null;
    }

}
