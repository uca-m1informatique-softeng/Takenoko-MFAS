package joueur;

import moteur.*;
import moteur.objectifs.Objectif;
import moteur.objectifs.ObjectifJardinier;
import moteur.personnages.Jardinier;
import javafx.geometry.Point3D;
import moteur.Enums.CouleurBot;

import java.util.ArrayList;

/**
 * La classe du bot Jardinier
 */
public class BotJardinier extends Bot{

    int choixchange;
    /**
     * Le constructeur
     * @param couleur
     */
    public BotJardinier(CouleurBot couleur) {
        super(couleur);
        choixchange=0;
    }

    /**
     *
     */
    private void switchchoix(){
        choixchange=(choixchange+1)%2;
    }



    //////////////////////////////Méthodes//////////////////////////////

    /**
     * Les differents choix du Botjardinier.
     * @param possibilites
     * @return
     */
    @Override
    public Enums.Action choixTypeAction(ArrayList<Enums.Action> possibilites) {
        if(possibilites.contains(Enums.Action.PIOCHEROBJECTIFJARDINIER)){
            return Enums.Action.PIOCHEROBJECTIFJARDINIER;
        }
        if(possibilites.contains(Enums.Action.PIOCHEROBJECTIFPARCELLE)){
            return Enums.Action.PIOCHEROBJECTIFPARCELLE;
        }
        if(possibilites.contains(Enums.Action.PIOCHEROBJECTIFPANDA)){
            return Enums.Action.PIOCHEROBJECTIFPANDA;
        }
        if(possibilites.contains(Enums.Action.DEPLACERJARDINIER)){
            return Enums.Action.DEPLACERJARDINIER;
        }
        if(possibilites.contains(Enums.Action.PIOCHERPARCELLE) && choixchange==0){
            switchchoix();
            return Enums.Action.PIOCHERPARCELLE;
        }
        if(possibilites.contains(Enums.Action.POSERIRRIGATION) && choixchange==1){
            switchchoix();
            return Enums.Action.POSERIRRIGATION;
        }
        if(possibilites.contains(Enums.Action.PIOCHERPARCELLE)){
            switchchoix();
            return Enums.Action.PIOCHERPARCELLE;
        }
        if(possibilites.contains(Enums.Action.POSERIRRIGATION)){
            switchchoix();
            return Enums.Action.POSERIRRIGATION;
        }
        if(possibilites.contains(Enums.Action.DEPLACERPANDA)){
            return Enums.Action.DEPLACERPANDA;
        }
        return null;
    }

    /**
     * @param possibilites
     * @return
     */
    @Override
    public Point3D choixDeplacementJardinier(ArrayList<Point3D> possibilites){
        if(this.getListObjectifs().isEmpty()) return super.choixDeplacementJardinier(possibilites);
        Objectif objJard = choixObjectifPrioritaire();
        for (int maxBambou=3;maxBambou>=0;maxBambou--){
            for (Point3D coordonne : possibilites) {
                if (Plateau.getInstance().getParcelle(coordonne).getType() == objJard.getCouleur() && Plateau.getInstance().getParcelle(coordonne).getNbBambou()==maxBambou){
                    return coordonne;
                }
            }
        }
        return super.choixDeplacementJardinier(possibilites);
    }

    /**
     * @param possibilites
     * @return
     */
    @Override
    public Parcelle choixParcellePioche(ArrayList<Parcelle> possibilites){
        if(this.getListObjectifs().isEmpty()){
            return super.choixParcellePioche(possibilites);
        }
        Objectif objJard = choixObjectifPrioritaire();
        for(Parcelle parcelle:possibilites){
            if(objJard.getCouleur()==parcelle.getType()) {
                return parcelle;
            }
        }
        return super.choixParcellePioche(possibilites);
    }

    /**
     * @return
     */
    @Override
    public Objectif choixObjectifPrioritaire() {
        for(Objectif objectif:getListObjectifs()){
            if(objectif instanceof ObjectifJardinier) {
                return objectif;
            }
        }
        return super.choixObjectifPrioritaire();
    }
}
