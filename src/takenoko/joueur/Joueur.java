package takenoko.joueur;

import takenoko.moteur.*;
import takenoko.moteur.objectifs.Objectif;
import takenoko.moteur.objectifs.ObjectifJardinier;
import takenoko.moteur.objectifs.ObjectifPanda;
import takenoko.moteur.personnages.Jardinier;
import takenoko.moteur.personnages.Panda;
import javafx.geometry.Point3D;

import java.util.ArrayList;
import takenoko.moteur.Enums.CouleurBot;

import static takenoko.moteur.Enums.TypeParcelle.JAUNE;
import static takenoko.moteur.Enums.TypeParcelle.ROSE;


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
    private int nbVictoire;
    private int nbObjectifPandarealise;


    /**
     * Le constructeur
     * @param couleur
     */
    public Joueur(CouleurBot couleur) {
        this.couleur = couleur;
        this.nombreObjectifsRemplis=0;
        this.listObjectifs=new ArrayList<>();
        this.listBambou=new ArrayList<>();
        this.listAction=new ArrayList<>();
        this.nbObjectifPandarealise=0;
        this.score=0;
        this.nbVictoire=0;
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

    public int getNbObjectifPandarealise() {
        return nbObjectifPandarealise;
    }

    public void setNbObjectifPandarealise(int nbObjectifPandarealise) {
        this.nbObjectifPandarealise = nbObjectifPandarealise;
    }


    //////////////////////////////Méthodes//////////////////////////////

    /**
     * Supprime nombres de bambous d'une certaine couleur des bambous possedés par le joueur
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
     * Reinitialise la liste des actions effectuees par le joueur dans un tour
     */
    public void resetListAction(){
        setListAction(new ArrayList<Enums.Action>());
    }

    /**
     * Ajoute une action dans la liste des actions réalisé par le joueur durant le tour
     * @param action
     */
    public void addListAction(Enums.Action action){
        listAction.add(action);
    }



    /**
     * Reinitialise le joueur pour commencer une nouvelle partie
     */
    public void resetJoueur(){
        setNombreObjectifsRemplis(0);

        setListObjectifs(new ArrayList<Objectif>());
        setListBambou(new ArrayList<Bambou>());
        setListAction(new ArrayList<>());
        setScore(0);
        actionPiocheObjectifJardinier();
        actionPiocheObjectifPanda();
        setNombreObjectifsRemplis(0);
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
                if(!choixValiderUnObjectif())
                    continue;
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
            if(action==Enums.Action.PIOCHEROBJECTIFJARDINIER || action==Enums.Action.PIOCHEROBJECTIFPANDA){
                if((!verifActionPossible(action)) ||listAction.contains(Enums.Action.PIOCHEROBJECTIFJARDINIER)||listAction.contains(Enums.Action.PIOCHEROBJECTIFPANDA)){
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
     * Vérifie si une action est possible
     * @param action
     * @return
     */
    private final boolean verifActionPossible(Enums.Action action){
        switch (action){
            case DEPLACERPANDA:return verifActionDeplacerPanda();
            case PIOCHERPARCELLE:return verifActionPoserParcelle();
            case DEPLACERJARDINIER:return verifActionDeplacerJardinier();
            case PIOCHEROBJECTIFPANDA:return verifActionPiocherObjPanda();
            case PIOCHEROBJECTIFJARDINIER:return verifActionPiocherObjJardinier();
        }
        return false;
    }

    /**
     * Vérifie que l'action du deplacement du panda est possible
     * @return
     */
    private final boolean verifActionDeplacerPanda(){
        return (Plateau.getInstance().getKeylist().size()>1);
    }

    /**
     * Vérifie que l'action du deplacement du jardinier est possible
     * @return
     */
    private final boolean verifActionDeplacerJardinier(){
        return (Plateau.getInstance().getKeylist().size()>1);
    }

    /**
     * Vérifie que l'action de poser une parcelle est possible
     * @return
     */
    private final boolean verifActionPoserParcelle(){
        return (!Deck.getInstance().isDeckParcelleVide());
    }

    /**
     * Vérifie que l'action de piocher un objectif panda est possible
     * @return
     */
    private final boolean verifActionPiocherObjPanda(){
        return (!Deck.getInstance().isDeckObjectifPandaVide() && this.getListObjectifs().size()<5);
    }

    /**
     * Vérifie que l'action de piocher un objectif jardinier est possible
     * @return
     */
    private final boolean verifActionPiocherObjJardinier(){
        return (!Deck.getInstance().isDeckObjectifJardinierVide() && this.getListObjectifs().size()<5);
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
            case PIOCHEROBJECTIFJARDINIER:
                actionPiocheObjectifJardinier();
                addListAction(action);
                return true;
            case PIOCHEROBJECTIFPANDA:
                actionPiocheObjectifPanda();
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
     * Pose la parcelle dans le plateau
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
     * Déplace le jardinier
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
     * Déplace le panda
     * @param panda
     * @param coord
     */
    public final void joueurDeplacePanda(Panda panda, Point3D coord){
        if(panda.deplacer(coord)){
            listBambou.add(new Bambou(Plateau.getInstance().getMap().get(coord).getType()));
        }
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
     * Ajoute une victoire au joueur
     */
    public void addVictoire(){
        nbVictoire++;
    }


    /**
     * Renvoie un choix de coordonne pour la pose des parcelles parmis une liste de possibilités
     * @param possibilites
     * @param parcelle
     * @return
     */
    public Point3D choixCoordonnePoseParcelle(ArrayList<Point3D> possibilites, Parcelle parcelle) {
        return null;
    }


    /**
     * Renvoie un choix de parcelle parmis une liste de possibilités
     * @param possibilites
     * @return
     */
    public Parcelle choixParcellePioche(ArrayList<Parcelle> possibilites) {
        return null;
    }

    /**
     * Renvoie un choix de coordonne pour le deplacement du jardinier parmis une liste de possibilités
     * @param possibilites
     * @return
     */
    public Point3D choixDeplacementJardinier(ArrayList<Point3D> possibilites) {
        return null;
    }

    /**
     * Renvoie un choix de coordonne pour le deplacement du panda parmis une liste de possibilités
     * @param possibilites
     * @return
     */
    public Point3D choixDeplacementPanda(ArrayList<Point3D> possibilites) {
        return null;
    }

    /**
     * Renvoie un choix d'action parmis une liste de possibilités
     * @param possibilites
     * @return
     */
    public Enums.Action choixTypeAction(ArrayList<Enums.Action> possibilites) {
        return null;
    }

    /**
     * Renvoie un objectif ciblé en priorité par le joueur
     * @return
     */
    public Objectif choixObjectifPrioritaire() {
        return null;
    }

    /**
     * Renvoi un boolean pour valider un objectif
     * @return
     */
    public boolean choixValiderUnObjectif(){return false;}





    /**
     * Renvoie un Objectif panda de la liste d'objectif
     * @return
     */
    public Objectif possedeObjectifPanda() {
        for(Objectif objectif:getListObjectifs()){
            if(objectif instanceof ObjectifPanda){
                return objectif;
            }
        }
        return null;
    }

    /**
     * Renvoie la couleur de la parcelle la plus repésenté parmi ses objectifs jardinier ou panda
     * @param perso : 0 pour le jardinier sinon panda
     * @return
     */
    public Enums.TypeParcelle couleurParcelleDestination(int perso){
        int vert = 0, jaune = 0, rose = 0;

        for (Objectif objectif : getListObjectifs()) {
            if ((perso == 0 && objectif instanceof ObjectifJardinier) || (perso != 0 && objectif instanceof ObjectifPanda)) {
                switch (objectif.getCouleur()) {
                    case VERTE:
                        vert++;
                        break;
                    case JAUNE:
                        jaune++;
                        break;
                    case ROSE:
                        rose++;
                        break;
                    default:
                }
            }
        }

        if(rose >= vert && rose >= jaune){
            return ROSE;
        }
        if(jaune > rose && jaune >= vert){
            return JAUNE;
        }
        if(vert > rose && vert > jaune){
            return Enums.TypeParcelle.VERTE;
        }
        return ROSE;
    }


}
