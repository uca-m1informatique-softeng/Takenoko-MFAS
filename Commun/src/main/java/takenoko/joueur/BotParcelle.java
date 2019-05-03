package takenoko.joueur;

import takenoko.moteur.Enums;
import takenoko.moteur.Parcelle;
import takenoko.moteur.Plateau;
import javafx.geometry.Point3D;
import takenoko.moteur.objectifs.Objectif;
import takenoko.moteur.objectifs.ObjectifParcelle;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * La classe du bot Jardinier
 */
@Component
@Scope("prototype")
public class BotParcelle extends Bot{

    int choixchange = 0;

    private void switchchoix(){
        choixchange=(choixchange+1)%4;
    }


    //////////////////////////////Méthodes//////////////////////////////

    /**
     * Les differents choix du Botparcelle.
     * @param possibilites
     * @return
     */
    @Override
    public Enums.Action choixTypeAction(ArrayList<Enums.Action> possibilites){
        if(possibilites.contains(Enums.Action.PIOCHEROBJECTIFPARCELLE)){
            return Enums.Action.PIOCHEROBJECTIFPARCELLE;
        }
        if(possibilites.contains(Enums.Action.PIOCHEROBJECTIFPANDA)){
            return Enums.Action.PIOCHEROBJECTIFPANDA;
        }
        if(possibilites.contains(Enums.Action.PIOCHEROBJECTIFJARDINIER)){
            return Enums.Action.PIOCHEROBJECTIFJARDINIER;
        }
        if(possibilites.contains(Enums.Action.PIOCHERPARCELLE)){
            return Enums.Action.PIOCHERPARCELLE;
        }
        if(possibilites.contains(Enums.Action.POSERIRRIGATION)&& choixchange==0){
            switchchoix();
            return Enums.Action.POSERIRRIGATION;
        }
        if(possibilites.contains(Enums.Action.DEPLACERPANDA)&& choixchange>0){
            switchchoix();
            return Enums.Action.DEPLACERPANDA;
        }
        if(possibilites.contains(Enums.Action.POSERIRRIGATION)){
            switchchoix();
            return Enums.Action.POSERIRRIGATION;
        }
        if(possibilites.contains(Enums.Action.DEPLACERPANDA)){
            switchchoix();
            return Enums.Action.DEPLACERPANDA;
        }
        if(possibilites.contains(Enums.Action.DEPLACERJARDINIER)){
            return Enums.Action.DEPLACERJARDINIER;
        }
        return null;
    }

    /**
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
     * @param possibilites
     * @return
     */
    @Override
    public Point3D choixDeplacementPanda(ArrayList<Point3D> possibilites) {
        for (int maxBambou=4;maxBambou>0;maxBambou--){
            for (Point3D coordonne : possibilites){
                if(Plateau.getInstance().getParcelle(coordonne).getNbBambou()>=maxBambou){
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
        if(this.getListObjectifs().isEmpty()) return super.choixParcellePioche(possibilites);
        Objectif objParcelle = choixObjectifPrioritaire();
        for(Parcelle parcelle:possibilites){
            if(objParcelle.getCouleur()==parcelle.getType()){
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
            if(objectif instanceof ObjectifParcelle){
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
