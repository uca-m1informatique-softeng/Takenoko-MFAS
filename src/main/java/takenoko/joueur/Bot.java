package takenoko.joueur;

import javafx.geometry.Point3D;
import takenoko.moteur.*;
import takenoko.moteur.objectifs.Objectif;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import takenoko.moteur.objectifs.ObjectifPanda;
import takenoko.moteur.personnages.Jardinier;
import takenoko.moteur.personnages.Panda;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *C'est la classe des bots
 */
@Component
@Scope("prototype")
public class Bot implements IA {


    private ArrayList<Enums.Action> listAction = new ArrayList<>();
    private Enums.CouleurBot couleur;
    private int nombreObjectifsRemplis = 0;
    private ArrayList<Objectif> listObjectifs = new ArrayList<>();
    private HashMap<Enums.TypeParcelle, Integer> listBambou;
    private ArrayList<Enums.TypeParcelle> keylist=new ArrayList<>();
    private int score = 0;
    private ArrayList<Irrigation> listIrrigation = new ArrayList<>();
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

    public Enums.CouleurBot getCouleur() {
        return couleur;
    }

    public void setCouleur(Enums.CouleurBot couleur) {
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

    public HashMap<Enums.TypeParcelle, Integer> getListBambou() {
        return listBambou;
    }

    public void setListBambou(HashMap<Enums.TypeParcelle, Integer> listBambou) {
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

    public int getNbBambouDeCouleur(Enums.TypeParcelle couleur){
        int index=keylist.indexOf(couleur);
        if (index != -1) {
            Enums.TypeParcelle p2=keylist.get(index);
            return listBambou.get(p2);
        }
        return -1;
    }

    public int setNbBambouDeCouleur(Enums.TypeParcelle couleur,int nb){
        int index=keylist.indexOf(couleur);
        if (index != -1) {
            Enums.TypeParcelle p2=keylist.get(index);
            int anciennb= listBambou.get(p2);
            listBambou.replace(p2,nb);
        }
        return 0;
    }

    public int addNbBambouDeCouleur(Enums.TypeParcelle couleur,int nb){
        int index=keylist.indexOf(couleur);
        if (index != -1) {
            Enums.TypeParcelle p2=keylist.get(index);
            int anciennb= listBambou.get(p2);
            listBambou.replace(p2,anciennb+nb);
        }
        return 0;
    }

    //////////////////////////////Méthodes//////////////////////////////

    /**
     * Supprime nb bambous d'une certaine couleur des bambous possedés par le joueur.
     * @param couleur
     * @param nb
     */
    public void supprBambou(Enums.TypeParcelle couleur,int nb) {
        addNbBambouDeCouleur(couleur,-nb);
    }

    /**
     * Reinitialise la liste des actions effectuees par le joueur dans un tour.
     */
    public void resetListAction(){
        setListAction(new ArrayList<Enums.Action>());
    }

    /**
     * Ajoute une action dans la liste des actions réalisé par le joueur durant le tour.
     * @param action
     */
    public void addListAction(Enums.Action action){
        listAction.add(action);
    }



    /**
     * Reinitialise le joueur pour commencer une nouvelle partie.
     */
    public void resetJoueur(){
        setNombreObjectifsRemplis(0);

        keylist.clear();
        keylist.add(Enums.TypeParcelle.JAUNE);
        keylist.add(Enums.TypeParcelle.ROSE);
        keylist.add(Enums.TypeParcelle.VERTE);

        setListObjectifs(new ArrayList<Objectif>());
        setListBambou(new HashMap<>());
        setListIrrigation(new ArrayList<Irrigation>());
        setListAction(new ArrayList<>());
        setScore(0);
        actionPiocheObjectifJardinier();
        actionPiocheObjectifPanda();
        actionPiocheObjectifParcelle();
        setNombreObjectifsRemplis(0);

        for (Enums.TypeParcelle T: keylist) {
            listBambou.put(T,0);
        }

    }

    /**
     * Ajoute une irrigation à la liste des irrigations du joueur
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
     * Vérifie si une action est possible
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
     * Vérifie que l'action de piocher un objectif parcelle est possible
     * @return
     */
    private final boolean verifActionPiocherObjParcelle(){
        return (!Deck.getInstance().isDeckObjectifParcelleVide() && this.getListObjectifs().size()<5);
    }

    /**
     * Vérifie que l'action de poser une irrigation est possible
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
            addNbBambouDeCouleur((Plateau.getInstance().getMap().get(coord).getType()),1);
        }
    }

    /**
     * Permet de piohcer une irrigation
     * @return
     */
    public final Irrigation piocheUneIrrigation(){
        return Deck.getInstance().piocheIrrigation();
    }

    /**
     * C'est la methode qui permet de poser une irrigation
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
     * Ajoute une victoire au joueur
     */
    public void addVictoire(){
        nbVictoire++;
    }

    //////////////////////////////Méthodes//////////////////////////////

    /**
     * La méthode qui retourne les possibilités pour poser une parcelle.
     * @param possibilites
     * @param parcelle
     * @return
     */
    @Override
    public Point3D choixCoordonnePoseParcelle(ArrayList<Point3D> possibilites, Parcelle parcelle) {
        return possibilites.get(0);
    }

    /**
     * La méthode qui retourne les possibilités pour poser une irrigation.
     * @param possibilites
     * @return
     */
    @Override
    public Point3D choixCoordonnePoseIrrigation(ArrayList<Point3D> possibilites) {
        return possibilites.get(0);
    }

    /**
     * La méthode qui retourne les possibilités pour piocher une parcelle
     * @param possibilites
     * @return
     */
    @Override
    public Parcelle choixParcellePioche(ArrayList<Parcelle> possibilites) {
        return possibilites.get(0);
    }

    /**
     * La méthode qui retourne les possibilités pour déplacer le jardinier
     * @param possibilites
     * @return
     */
    @Override
    public Point3D choixDeplacementJardinier(ArrayList<Point3D> possibilites) {
        return possibilites.get(0);
    }

    /**
     * La méthode qui retourne les possibilités pour déplacer le panda
     * @param possibilites
     * @return
     */
    @Override
    public Point3D choixDeplacementPanda(ArrayList<Point3D> possibilites) {
        return possibilites.get(0);
    }

    /**
     * Renvoie un choix d'action parmis une liste de possibilités
     * @param possibilites
     * @return
     */
    @Override
    public Enums.Action choixTypeAction(ArrayList<Enums.Action> possibilites) {
        return possibilites.get(0);
    }

    /**
     * Renvoie un objectif ciblé en priorité par le joueur
     * @return
     */
    @Override
    public Objectif choixObjectifPrioritaire() {
        return this.getListObjectifs().get(0);
    }
}
