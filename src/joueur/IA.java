package joueur;

import javafx.geometry.Point3D;
import moteur.Enums;
import moteur.Parcelle;
import moteur.objectifs.Objectif;

import java.util.ArrayList;

/**
 * L'interface de l'IA
 */
public interface IA {
    Point3D choixCoordonnePoseParcelle(ArrayList<Point3D> possibilites, Parcelle parcelle);
    Point3D choixCoordonnePoseIrrigation(ArrayList<Point3D> possibilites);
    Parcelle choixParcellePioche(ArrayList<Parcelle> possibilites);
    Point3D choixDeplacementJardinier(ArrayList<Point3D> possibilites);
    Point3D choixDeplacementPanda(ArrayList<Point3D> possibilites);
    Enums.Action choixTypeAction(ArrayList<Enums.Action> possibilites);
    Objectif choixObjectifPrioritaire();
}
