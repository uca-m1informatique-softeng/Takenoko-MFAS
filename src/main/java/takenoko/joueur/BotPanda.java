package takenoko.joueur;

import takenoko.moteur.objectifs.Objectif;
import takenoko.moteur.objectifs.ObjectifPanda;
import javafx.geometry.Point3D;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import takenoko.moteur.Enums;
import takenoko.moteur.Parcelle;
import takenoko.moteur.Plateau;

/**
 * La classe du bot Panda
 */

@Component
@Scope("prototype")

public class BotPanda extends Bot {

    int choixchange = 0;

    private void switchchoix(){
        choixchange=(choixchange+1)%5;
    }


    //////////////////////////////Méthodes//////////////////////////////

    /**
     * @param possibilites
     * @return
     */
    @Override
    public Enums.Action choixTypeAction(ArrayList<Enums.Action> possibilites) {
        if(possibilites.contains(Enums.Action.PIOCHEROBJECTIFPANDA)){
            return Enums.Action.PIOCHEROBJECTIFPANDA;
        }

        if(possibilites.contains(Enums.Action.PIOCHEROBJECTIFJARDINIER)){
            return Enums.Action.PIOCHEROBJECTIFJARDINIER;
        }
        if(possibilites.contains(Enums.Action.PIOCHEROBJECTIFPARCELLE)){
            return Enums.Action.PIOCHEROBJECTIFPARCELLE;
        }
        if(possibilites.contains(Enums.Action.DEPLACERPANDA)){
            return Enums.Action.DEPLACERPANDA;
        }
        if(possibilites.contains(Enums.Action.DEPLACERJARDINIER)&& choixchange<2){
            switchchoix();
            return Enums.Action.DEPLACERJARDINIER;
        }
        if(possibilites.contains(Enums.Action.PIOCHERPARCELLE)&& choixchange<4){
            switchchoix();
            return Enums.Action.PIOCHERPARCELLE;
        }
        if(possibilites.contains(Enums.Action.DEPLACERJARDINIER)){
            switchchoix();
            return Enums.Action.DEPLACERJARDINIER;
        }
        if(possibilites.contains(Enums.Action.PIOCHERPARCELLE)){
            switchchoix();
            return Enums.Action.PIOCHERPARCELLE;
        }
        if(possibilites.contains(Enums.Action.POSERIRRIGATION)){
            switchchoix();
            return Enums.Action.POSERIRRIGATION;
        }
        return null;
    }

    /**
     * @param possibilites
     * @return
     */
    @Override
    public Point3D choixDeplacementJardinier(ArrayList<Point3D> possibilites){
        Objectif objJard = choixObjectifPrioritaire();

        for (Point3D coordonne : possibilites) {
            if (Plateau.getInstance().getParcelle(coordonne).getType() == objJard.getCouleur()) {
                return coordonne;
            }
        }
        return super.choixDeplacementJardinier(possibilites);
    }

    /**
     * @param possibilites
     * @return
     */
    @Override
    public Point3D choixDeplacementPanda(ArrayList<Point3D> possibilites) {
        Plateau plateau=Plateau.getInstance();
        if(this.getListObjectifs().isEmpty()) {
            return super.choixDeplacementPanda(possibilites);
        }
        Objectif objPanda=choixObjectifPrioritaire();
        for (int maxBambou=4;maxBambou>0;maxBambou--){
            for (Point3D coordonne : possibilites){
                if(plateau.getParcelle(coordonne).getType() == objPanda.getCouleur() && Plateau.getInstance().getParcelle(coordonne).getNbBambou()==maxBambou){
                    return coordonne;
                }
            }
        }
        return super.choixDeplacementPanda(possibilites);
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
        Objectif objPanda = choixObjectifPrioritaire();
        for(Parcelle parcelle:possibilites){
            if(objPanda.getCouleur()==parcelle.getType()) {
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
            if(objectif instanceof ObjectifPanda){
                return objectif;
            }
        }
        return super.choixObjectifPrioritaire();
    }

    public int getChoixchange() {
        return choixchange;
    }

    public void setChoixchange(int choixchange) {
        this.choixchange = choixchange;
    }
}


