package takenoko.joueur;

import javafx.geometry.Point3D;
import javafx.print.PageLayout;
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

public class IANormale extends Bot{

    private Point3D premiereDestination, deuxiemeDestination;

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

    public IANormale(Enums.CouleurBot couleur) {
        super(couleur);
    }


    /**
     * Les differents choix du Botjardinier.
     * @param possibilites
     * @return
     */
    @Override
    public Enums.Action choixTypeAction(ArrayList<Enums.Action> possibilites) {

        Plateau plateau=Plateau.getInstance();

        if(possibilites.contains(Enums.Action.PIOCHEROBJECTIFJARDINIER) && this.getListObjectifs().size()+getNombreObjectifsRemplis() < 10) {
            return Enums.Action.PIOCHEROBJECTIFJARDINIER;
        }
        if(possibilites.contains(Enums.Action.PIOCHEROBJECTIFPANDA) && !possibilites.contains(Enums.Action.PIOCHEROBJECTIFJARDINIER ) && this.getListObjectifs().size()+getNombreObjectifsRemplis() < 10) {
            return Enums.Action.PIOCHEROBJECTIFPANDA;
        }
        if(possibilites.contains(Enums.Action.PIOCHERPARCELLE) && !plateau.parcellesAdjacentesMemeCouleur(this.couleurParcelleDestination(0), this)) {
            return Enums.Action.PIOCHERPARCELLE;
        }
        if(possibilites.contains(Enums.Action.DEPLACERJARDINIER)) {
            return Enums.Action.DEPLACERJARDINIER;
        }
        Objectif objectif = possedeObjectifPanda();
        if(possibilites.contains(Enums.Action.DEPLACERPANDA) && objectif != null && couleurSurPlateau(objectif.getCouleur())){
            return Enums.Action.DEPLACERPANDA;

        }
        if(possibilites.contains(Enums.Action.PIOCHERPARCELLE) && plateau.getKeylist().size() < 20) {
            return Enums.Action.PIOCHERPARCELLE;
        }

        if(possibilites.contains(Enums.Action.PIOCHEROBJECTIFJARDINIER)) {
            return Enums.Action.PIOCHEROBJECTIFJARDINIER;
        }

        if(possibilites.contains(Enums.Action.PIOCHEROBJECTIFPANDA)) {
            return Enums.Action.PIOCHEROBJECTIFPANDA;
        }
        if(possibilites.contains(Enums.Action.DEPLACERJARDINIER)){
            return Enums.Action.DEPLACERJARDINIER;
        }
        return possibilites.get(0);
    }


    public Objectif possedeObjectifPanda() {
        for(Objectif objectif:getListObjectifs()){
            if(objectif instanceof ObjectifPanda){
                return objectif;
            }
        }
        return null;
    }




    /**
     * @param perso : 0 pour le jardinier sinon panda
     * @return la couleur de parcelle la plus représenté parmi ses objectifs jardinier ou panda
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
     * Renvoie un choix d'action parmis une liste de possibilités
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

    public Point3D parcelleCommune(){
        ArrayList<Point3D> destinationsPossibles = Panda.getInstance().destinationsPossibles();
        Point3D oldcoor = Panda.getInstance().getCoord();
        Panda.getInstance().setCoord(rechercheParcelle());
        ArrayList<Point3D> newDestinationsPossibles = Panda.getInstance().destinationsPossibles();

        // On replace le panda a sa position originelle
        Panda.getInstance().setCoord(oldcoor);

        for (int i = 0; i < destinationsPossibles.size() ; i++) {
            if(destinationsPossibles.contains(newDestinationsPossibles.get(i))){
                return newDestinationsPossibles.get(i);
            }
        }

        return destinationsPossibles.get(0);
    }




}
