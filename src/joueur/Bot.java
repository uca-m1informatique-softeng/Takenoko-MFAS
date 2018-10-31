package joueur;

import javafx.geometry.Point3D;
import moteur.*;
import moteur.Enums.CouleurBot;
import moteur.objectifs.Objectif;

import java.util.ArrayList;

/**
 *C'est la classe des bots
 */
public class Bot extends Joueur {

    /**
     * Le constructeur
     * @param couleur
     */
    public Bot(CouleurBot couleur){
        super(couleur);
    }

    public Point3D choixCoordonnePoseParcelle(ArrayList<Point3D> possibilites, Parcelle parcelle) {
        return possibilites.get(0);
    }
    public Point3D choixCoordonnePoseIrrigation(ArrayList<Point3D> possibilites) {
        return possibilites.get(0);
    }
    public Parcelle choixParcellePioche(ArrayList<Parcelle> possibilites) {
        return possibilites.get(0);
    }
    public Point3D choixDeplacementJardinier(ArrayList<Point3D> possibilites) {
        return possibilites.get(0);
    }
    public Point3D choixDeplacementPanda(ArrayList<Point3D> possibilites) {
        return possibilites.get(0);
    }
    public Enums.Action choixTypeAction(ArrayList<Enums.Action> possibilites) {
        return possibilites.get(0);
    }
    public Objectif choixObjectifPrioritaire() {
        return this.getListObjectifs().get(0);
    }
}
