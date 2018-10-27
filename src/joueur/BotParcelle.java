package joueur;

import moteur.Enums.TypeParcelle;
import moteur.Parcelle;
import moteur.Partie;
import moteur.Plateau;
import javafx.geometry.Point3D;
import moteur.Enums.CouleurBot;
import moteur.objectifs.Objectif;
import moteur.objectifs.ObjectifParcelle;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * La classe du bot Jardinier
 */
public class BotParcelle extends Bot{

    /**
     * Le constructeur
     * @param couleur
     */
    public BotParcelle(CouleurBot couleur) {
        super(couleur);
    }


    //////////////////////////////Méthodes//////////////////////////////

    /**
     * Une méthode qui renvois un boolean pour le choix d'action du bot
     * @param numeroActionDansLeTour
     * @param partie
     * @return
     */
    public boolean choixAction(int numeroActionDansLeTour, Partie partie){
        joueurPose(partie);
        joueurPoseIrrigation(partie);
        joueurDeplaceJardinier(partie.getJardinier());
        return true;
    }

    /**
     * @param partie
     */
    public void joueurPose(Partie partie){
        finirObjectif(partie);
    }

    /**
     * Une méthode qui renvois un boolean pour savoir si l'objectif est finit.
     * @param partie
     * @return
     */
    public boolean finirObjectif(Partie partie){
        Plateau plateau = partie.getPlateau();
        HashMap<Point3D,Parcelle> map = plateau.getMap();
        ArrayList<Point3D> emplacementslibres = plateau.emplacementsAutorise();
        Parcelle parcelle = partie.getDeck().piocherParcelle();
        ObjectifParcelle objectifParcelle = null;
        Point3D pointTemporaire = null;
        Point3D pointObjectif = null;
        ArrayList<Point3D> keyList = plateau.getKeylist();
        for(Objectif objectif : getListObjectifs()){
            if (objectif instanceof ObjectifParcelle && parcelle.getType() == ((ObjectifParcelle) objectif).getCouleur()) {
                objectifParcelle = (ObjectifParcelle) objectif;
                break;
            }
        }

        if(objectifParcelle != null){
            TypeParcelle couleurObjectif = objectifParcelle.getCouleur();
            for (Point3D pointCourant : keyList) {
                if(map.get(pointCourant).getType() == couleurObjectif) {
                    ArrayList<Point3D> pointsVoisin = plateau.getParcelleVoisine(pointCourant);
                    for (int j = 0; j < 6; j++) {
                        Point3D secondPoint = pointsVoisin.get(j);
                        switch (objectifParcelle.getType()){
                            case 0:
                                if(completePattern(pointCourant,couleurObjectif,j,secondPoint,j,plateau, emplacementslibres, parcelle,pointTemporaire,pointObjectif)){
                                    return true;
                                }
                                break;
                            case 1:
                                if(completePattern(pointCourant,couleurObjectif,j,secondPoint,(j+1)%6,plateau, emplacementslibres, parcelle,pointTemporaire,pointObjectif)){
                                    return true;
                                }

                                break;
                            case 3: //on vérifie d'abord le pattern en losange car c'est une extension du pattern triangle
                                if(plateau.parcelleSuivanteLibre(pointCourant,j) && plateau.parcelleSuivanteMotif(pointCourant,couleurObjectif,(j+1)%6) &&
                                        plateau.parcelleSuivanteMotif(pointCourant,couleurObjectif,(j+2)%6)){
                                    return true;
                                }
                                if(plateau.parcelleSuivanteMotif(pointCourant,couleurObjectif,j) && plateau.parcelleSuivanteLibre(pointCourant,(j+1)%6) &&
                                        plateau.parcelleSuivanteMotif(pointCourant,couleurObjectif,(j+2)%6)){
                                    return true;
                                }
                                if(plateau.parcelleSuivanteMotif(pointCourant,couleurObjectif,j) && plateau.parcelleSuivanteMotif(pointCourant,couleurObjectif,(j+1)%6) &&
                                        plateau.parcelleSuivanteLibre(pointCourant,(j+2)%6)){
                                    return true;
                                }
                            case 2:
                                if(completePattern(pointCourant,couleurObjectif,j,pointCourant,(j+1)%6,plateau, emplacementslibres, parcelle,pointTemporaire,pointObjectif)){
                                    return true;
                                }
                                break;
                        }
                    }
                }
            }
        }
        if(pointObjectif != null){ // pointObjectif n'est pas un emplacement autorisé
            ArrayList<Point3D> pointsVoisinLibre = plateau.getParcelleVoisineOccupe(pointObjectif);
            for(Point3D point3D : pointsVoisinLibre){
                if(emplacementslibres.contains(point3D)){
                    plateau.poser(parcelle, point3D);
                    return true;
                }
            }
        }
        if(pointTemporaire != null){
            plateau.poser(parcelle, pointTemporaire);
            return true;
        }
        else{//faire des amas de couleur
            for (Point3D point3D : keyList) {
                if(map.get(point3D).getType() == parcelle.getType()){
                    ArrayList<Point3D> listPointsVoisin =  plateau.getParcelleVoisineLibre(point3D);
                    for(Point3D  pointVoisin : listPointsVoisin){
                        if(emplacementslibres.contains(pointVoisin)){
                            plateau.poser(parcelle, pointVoisin);
                            return true;
                        }
                    }
                }
            }
        }
        plateau.poser(parcelle, emplacementslibres.get(0));
        return false;
    }


    /**
     * vérifie si on peut completer l'objectif en posant la parcelle, sinon sauvegarde un point qui pourrait compléter un pattern
     * @param pointCourant
     * @param couleurObjectif
     * @param i
     * @param secondPoint
     * @param j
     * @param plateau
     * @param emplacementslibres
     * @param parcelle
     * @param pointTemporaire
     * @param pointObjectif
     * @return
     */
    public boolean completePattern(Point3D pointCourant, TypeParcelle couleurObjectif, int i, Point3D secondPoint, int j, Plateau plateau, ArrayList<Point3D> emplacementslibres, Parcelle parcelle,Point3D pointTemporaire,Point3D pointObjectif){
        if(plateau.parcelleSuivanteMotif(pointCourant,couleurObjectif,i) && plateau.parcelleSuivanteLibre(secondPoint,j)){
            return verifPose(plateau,secondPoint,j,emplacementslibres,parcelle,pointObjectif);
        }
        if(plateau.parcelleSuivanteLibre(pointCourant,i) && plateau.parcelleSuivanteMotif(secondPoint,couleurObjectif,j)){
            return verifPose(plateau,pointCourant,i,emplacementslibres,parcelle,pointObjectif);
        }
        if(plateau.parcelleSuivanteLibre(pointCourant,i) && plateau.parcelleSuivanteLibre(secondPoint,j)){
            Point3D point = plateau.getParcelleVoisine(pointCourant).get(j);
            if(pointTemporaire != null && emplacementslibres.contains(point)){
                pointTemporaire = point;
            }
        }
        return false;
    }

    /**
     * vérifie si l'endroit où on souhaite poser la parcelle est un coup valide
     * @param plateau
     * @param point3D
     * @param i
     * @param emplacementslibres
     * @param parcelle
     * @param pointObjectif
     * @return
     */
    public boolean verifPose(Plateau plateau, Point3D point3D,int i, ArrayList<Point3D> emplacementslibres,Parcelle parcelle,Point3D pointObjectif){
        Point3D point = plateau.getParcelleVoisine(point3D).get(i);
        if( emplacementslibres.contains(point) ){
            plateau.poser(parcelle, point);
            return true;
        }
        else{
            if(pointObjectif != null){
                pointObjectif = point;
            }
        }
        return false;
    }

}
