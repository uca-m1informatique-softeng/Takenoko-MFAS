package moteur.personnages;

import moteur.Affichage;
import moteur.Plateau;
import javafx.geometry.Point3D;

import java.util.ArrayList;

/**
 * C'est la classe du jardinier
 */
public class Jardinier extends Personnage {

    public Jardinier(Plateau p)
    {
        super(p);
    }

    //////////////////////////////Méthodes//////////////////////////////

    /**
     * @param point3D
     * @return
     */
    public boolean faireActionBambou(Point3D point3D) {
        boolean reponse = false;
        Affichage.affichageJardinier(point3D,getPlateau());
        ArrayList<Point3D> pointOuPousser =  ouPousserBambou(point3D);
        for(Point3D pt : pointOuPousser){
            if(getPlateau().getParcelle(pt).pousserBambou()){
                Affichage.affichageNombreBambou(getPlateau(),pt);
                reponse = true;
            }
        }
        return reponse;
    }

    /**
     * @param point3D
     * @return
     */
    public ArrayList<Point3D> ouPousserBambou(Point3D point3D) {

        ArrayList<Point3D> listParcelle = getPlateau().getParcelleVoisineMemeCouleur(point3D);
        ArrayList<Point3D> listMemeCouleur = new ArrayList<>();
        if(getPlateau().getParcelle(point3D).isIrriguee()){
            listMemeCouleur.add(point3D);
        }
        for(Point3D pointMemeCouleur : listParcelle){
            if(getPlateau().getParcelle(pointMemeCouleur).isIrriguee()){
                listMemeCouleur.add(pointMemeCouleur);
            }
        }
        return listMemeCouleur;
    }

}
