package takenoko.joueur;

import javafx.geometry.Point3D;
import javafx.print.PageLayout;
import takenoko.moteur.Commande;
import takenoko.moteur.Enums;
import takenoko.moteur.Parcelle;
import takenoko.moteur.Plateau;
import takenoko.moteur.objectifs.Objectif;
import takenoko.moteur.objectifs.ObjectifJardinier;
import takenoko.moteur.objectifs.ObjectifPanda;
import takenoko.moteur.personnages.Panda;


import java.util.ArrayList;

import static takenoko.moteur.Enums.TypeParcelle.JAUNE;
import static takenoko.moteur.Enums.TypeParcelle.ROSE;

/**
 * La classe qui de notre IANormale
 */

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class IANormale extends Bot{

    private Point3D premiereDestination, deuxiemeDestination;

    private ArrayList<Commande> listCommandes = new ArrayList<>();
    private ArrayList<Commande> listCommandes2 = new ArrayList<>();

    private List<List<Integer>> permutations, permutations2;

    private int iperm = 119; // dernier indice de permutations pour la 1ere partie des commandes

    private int iperm2 = 23; //dernier indice de permutations pour la 2eme partie des commandes

    /**
     * Le constructeur
     * @param couleur
     */
    public IANormale(Enums.CouleurBot couleur) {
        super(couleur);
        initialiseCommandes();
    }


    //////////////////////////////GETTER ET SETTER//////////////////////////////

    public Point3D getPremiereDestination() {
        return premiereDestination;
    }

    public void setPremiereDestination(Point3D premiereDestination) {
        this.premiereDestination = premiereDestination;
    }

    public Point3D getDeuxiemeDestination() {
        return deuxiemeDestination;
    }

    public void setDeuxiemeDestination(Point3D deuxiemeDestination) {
        this.deuxiemeDestination = deuxiemeDestination;
    }

    public void setIperm(int iperm) {
        this.iperm = iperm;
    }

    public void setIperm2(int iperm2) {
        this.iperm2 = iperm2;
    }
    //////////////////////////////Méthodes//////////////////////////////

    public void initialiseCommandes(){
        listCommandes.add(new Commande(Enums.Action.PIOCHEROBJECTIFJARDINIER));
        listCommandes.add(new Commande(Enums.Action.PIOCHEROBJECTIFPANDA));
        listCommandes.add(new Commande(Enums.Action.PIOCHERPARCELLE));
        listCommandes.add(new Commande(Enums.Action.DEPLACERJARDINIER));
        listCommandes.add(new Commande(Enums.Action.DEPLACERPANDA));
        permutations = permutations(listCommandes.size());

        listCommandes2.add(new Commande(Enums.Action.PIOCHERPARCELLE));
        listCommandes2.add(new Commande(Enums.Action.PIOCHEROBJECTIFJARDINIER));
        listCommandes2.add(new Commande(Enums.Action.PIOCHEROBJECTIFPANDA));
        listCommandes2.add(new Commande(Enums.Action.DEPLACERJARDINIER));
        permutations2 = permutations(listCommandes2.size());
    }


    public void actualiseCommandes(ArrayList<Enums.Action> possibilites){
        Plateau plateau=Plateau.getInstance();
        Objectif objectif = possedeObjectifPanda();
        listCommandes.get(0).setConditions(possibilites.contains(Enums.Action.PIOCHEROBJECTIFJARDINIER)
                && this.getListObjectifs().size()+getNombreObjectifsRemplis() < 10);

        listCommandes.get(1).setConditions(possibilites.contains(Enums.Action.PIOCHEROBJECTIFPANDA)
                && !possibilites.contains(Enums.Action.PIOCHEROBJECTIFJARDINIER ) && this.getListObjectifs().size()+getNombreObjectifsRemplis() < 10);

        listCommandes.get(2).setConditions(possibilites.contains(Enums.Action.PIOCHERPARCELLE)
                && !plateau.parcellesAdjacentesMemeCouleur(this.couleurParcelleDestination(0), this));

        listCommandes.get(3).setConditions(possibilites.contains(Enums.Action.DEPLACERJARDINIER));

        listCommandes.get(4).setConditions(possibilites.contains(Enums.Action.DEPLACERPANDA)
                && objectif != null && couleurSurPlateau(objectif.getCouleur()));

        listCommandes2.get(0).setConditions(possibilites.contains(Enums.Action.PIOCHERPARCELLE) && plateau.getKeylist().size() < 20);
        listCommandes2.get(0).setConditions(possibilites.contains(Enums.Action.PIOCHEROBJECTIFJARDINIER));
        listCommandes2.get(0).setConditions(possibilites.contains(Enums.Action.PIOCHEROBJECTIFPANDA));
        listCommandes2.get(0).setConditions(possibilites.contains(Enums.Action.DEPLACERJARDINIER));
    }


    /**
     * Les differents choix de l'IANormale
     * @param possibilites
     * @return
     */
    @Override
    public Enums.Action choixTypeAction(ArrayList<Enums.Action> possibilites) {

//actualiser les conditions des commandes ici
        actualiseCommandes(possibilites);

        for (int i = 0; i < listCommandes.size(); i++) {
            if(listCommandes.get(permutations.get(iperm).get(i)).isConditions()){
                return listCommandes.get(permutations.get(iperm).get(i)).getAction();
            }
        }

        for (int i = 0; i < listCommandes2.size(); i++) {
            if(listCommandes2.get(permutations2.get(iperm2).get(i)).isConditions()){
                return listCommandes2.get(permutations2.get(iperm2).get(i)).getAction();
            }
        }
        return possibilites.get(0);
    }


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






    /**
     * La méthode qui retourne les possibilités pour piocher une parcelle
     * @param possibilites
     * @return
     */
    @Override
    public Parcelle choixParcellePioche(ArrayList<Parcelle> possibilites){
        Enums.TypeParcelle couleur =  couleurParcelleDestination(0);
        for(Parcelle p : possibilites){
            if(p.getType() == couleur){
                return p;
            }
        }
        return possibilites.get(0);
    }

    /**
     * Renvoie un choix de coordonne pour la pose des parcelles parmis une liste de possibilités
     * @param possibilites
     * @param parcelle
     * @return
     */
    @Override
    public Point3D choixCoordonnePoseParcelle(ArrayList<Point3D> possibilites,Parcelle parcelle) {
        Plateau plateau=Plateau.getInstance();
        for(int sizeMax=6;sizeMax>0;sizeMax--){
            for (Point3D coordonne : possibilites){
                if(plateau.getParcelleVoisineMemeCouleur(coordonne,parcelle).size()==sizeMax){
                    return coordonne;
                }
            }
        }
        return super.choixCoordonnePoseParcelle(possibilites,parcelle);
    }



    /**
     * La méthode qui retourne les possibilités pour déplacer le jardinier
     * @param possibilites
     * @return
     */
    @Override
    public Point3D choixDeplacementJardinier(ArrayList<Point3D> possibilites){
        //if(this.getListObjectifs().isEmpty()) return super.choixDeplacementJardinier(possibilites);

        Enums.TypeParcelle couleur = couleurParcelleDestination(0);
        if(Plateau.getInstance().parcellesAdjacentesMemeCouleur(couleur,this)) {
            if (possibilites.contains(premiereDestination)) {
                return premiereDestination;
            }
            if (possibilites.contains(deuxiemeDestination)) {
                return deuxiemeDestination;
            }
        }

        for (int maxBambou=3;maxBambou>=0;maxBambou--){
            for (Point3D coordonne : possibilites) {
                if (Plateau.getInstance().getParcelle(coordonne).getType() == couleur && Plateau.getInstance().getParcelle(coordonne).getNbBambou()==maxBambou){
                    return coordonne;
                }
            }
        }
        return super.choixDeplacementJardinier(possibilites);
    }


    /**
     * Renvoie un boolean pour savoir si la couleur existe sur le plateau
     * @param couleur
     * @return
     */
    public boolean couleurSurPlateau(Enums.TypeParcelle couleur){
        Plateau plateau = Plateau.getInstance();
        for (Point3D coordonne : plateau.getKeylist()) {
            Parcelle parcelle = plateau.getParcelle(coordonne);
            if (parcelle.getType() == couleur && parcelle.getNbBambou() > 0 && Panda.getInstance().getCoord() != coordonne){
                return true;

            }
        }
        return false;
    }


    /**
     * La méthode qui retourne les possibilités pour déplacer le panda
     * @param possibilites
     * @return
     */
    @Override
    public Point3D choixDeplacementPanda(ArrayList<Point3D> possibilites) {
        Enums.TypeParcelle couleur = couleurParcelleDestination(1);
        Plateau plateau=Plateau.getInstance();
        if(this.getListObjectifs().isEmpty()) {
            return super.choixDeplacementPanda(possibilites);
        }
        boolean boul = couleurSurPlateau(couleur);

        // si la couleur dispo sur le plateau on regarde s'il est accessible sinon on appelle la méthode
        if (boul){
            for (int maxBambou = 1;maxBambou <= 4; maxBambou++){
                for (Point3D coordonne : possibilites){
                    if(plateau.getParcelle(coordonne).getType() == couleur && Plateau.getInstance().getParcelle(coordonne).getNbBambou()==maxBambou){
                        return coordonne;
                    }
                }
            }
            return this.parcelleCommune();
        }
        else{
            // à modifier
            for(Enums.TypeParcelle type : Enums.TypeParcelle.values()){
                Point3D point3D = ciblePanda(possibilites, type);
                if(point3D != null){
                    return point3D;
                }
            }
            return super.choixDeplacementPanda(possibilites);
        }
    }

    /**
     * La méthode qui renvoie le point où le panda va se déplacer
     * @param possibilites
     * @param couleur
     * @return
     */
    public Point3D ciblePanda(ArrayList<Point3D> possibilites, Enums.TypeParcelle couleur){
        for (int maxBambou = 1;maxBambou < 4; maxBambou++) {
            for (Point3D coordonne : possibilites) {
                Parcelle parcelle = Plateau.getInstance().getParcelle(coordonne);
                if (parcelle.getType() == couleur && parcelle.getNbBambou() == maxBambou) {
                    return coordonne;
                }
            }
        }
        return null;
    }

    /**
     * Une méthode qui renvoie le point où se situe la parcelle
     * @return
     */
    public Point3D rechercheParcelle(){
        Point3D res = null;
        Plateau plateau = Plateau.getInstance();
        Enums.TypeParcelle couleur = couleurParcelleDestination(1);

        // Chercher la parcelle avec la couleur voulu //
        for (int maxBambou = 1;maxBambou <= 4; maxBambou++) {
            for (Point3D coordonne : plateau.getKeylist()) {
                Parcelle parcelle = plateau.getParcelle(coordonne);
                if (parcelle.getType() == couleur && parcelle.getNbBambou() == maxBambou && Panda.getInstance().getCoord() != coordonne) {
                    res = coordonne;
                }
            }
        }
        return res;

    }

    /**
     * La méthode qui renvoie la parcelle intermédiaire
     * @return
     */
    public Point3D parcelleCommune(){
        ArrayList<Point3D> destinationsPossibles = Panda.getInstance().destinationsPossibles();
        Point3D oldcoor = Panda.getInstance().getCoord();
        Panda.getInstance().setCoord(rechercheParcelle());
        ArrayList<Point3D> newDestinationsPossibles = Panda.getInstance().destinationsPossibles();

        // On replace le panda a sa position originelle
        Panda.getInstance().setCoord(oldcoor);

        for (int i = 0; i < newDestinationsPossibles.size() ; i++) {
            if(destinationsPossibles.contains(newDestinationsPossibles.get(i))){
                return newDestinationsPossibles.get(i);
            }
        }

        return destinationsPossibles.get(0);
    }


    public static List<List<Integer>> permutations(int n) { // could these be
        // sets?
        final List<List<Integer>> v=new ArrayList<>();
        if(n<=1) {
            List<Integer> a=new ArrayList<>(1);
            a.add(0);
            v.add(a);
        } else {
            final List<List<Integer>> v1=permutations(n-1);
            int nMius1Factorial=v1.size();
            for(int i=0;i<nMius1Factorial;i++) {
                List<Integer> a=v1.get(i);
                Integer[] aa=a.toArray(new Integer[0]);
                for(int j=0;j<n;j++) { // copy a, inserting n at a2[j]
                    Integer[] a2a=new Integer[n];
                    // System.out.println("size of a: "+a.size());
                    // System.out.println("size of a2: "+a2a.length);
                    // System.out.println("j="+j);
                    System.arraycopy(aa,0,a2a,0,j);
                    a2a[j]=n-1;
                    // System.out.println("j="+j+", j+1="+(j+1)+", n="+(n-j-1));
                    System.arraycopy(aa,j,a2a,j+1,n-j-1);
                    v.add(Arrays.asList(a2a));
                }
            }
        }
        return v;
    }




}
