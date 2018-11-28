package takenoko.joueur;

import javafx.geometry.Point3D;
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

    int choixchange = 0;

    Point3D premiereDestination, deuxiemeDestination;
    Point3D oldcoor ;




    public IANormale(Enums.CouleurBot couleur) {
        super(couleur);
    }

    private void switchchoix(){
        choixchange=(choixchange+1)%2;
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
     * Renvoie un choix d'action parmis une liste de possibilités
     * @param possibilites
     * @return
     */
    @Override
    public Point3D choixDeplacementJardinier(ArrayList<Point3D> possibilites){
        if(this.getListObjectifs().isEmpty()) return super.choixDeplacementJardinier(possibilites);

        Enums.TypeParcelle couleur = couleurParcelleDestination(0);
        if(possibilites.contains(premiereDestination)){
            return premiereDestination;
        }
        if(possibilites.contains(deuxiemeDestination)){
            return deuxiemeDestination;
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

    @Override
    public Point3D choixDeplacementPanda(ArrayList<Point3D> possibilites) {
        boolean boul = false ; // il n'y a pas de couleur voulue sur le plateau

        Plateau plateau=Plateau.getInstance();
        if(this.getListObjectifs().isEmpty()) {
            return super.choixDeplacementPanda(possibilites);
        }
        Objectif objPanda=choixObjectifPrioritaire();
        Enums.TypeParcelle coul = objPanda.getCouleur();

        for (Point3D coordonne : plateau.getKeylist()) {
            if (plateau.getParcelle(coordonne).getType() == coul && plateau.getParcelle(Panda.getInstance().getCoord()).getType() != coul){
                boul = true;
            }
        }

        // si la couleur dispo sur le plateeau on regarde s'il est accessible sinon on appelle la méthode
        if (boul){
            for (int maxBambou=4;maxBambou>0;maxBambou--){
                for (Point3D coordonne : possibilites){
                    if(plateau.getParcelle(coordonne).getType() == objPanda.getCouleur() && Plateau.getInstance().getParcelle(coordonne).getNbBambou()==maxBambou){
                        return coordonne;
                    }
                }
            }
            return this.parcelleCommune();
        }
        else{

            // à modifier
            for (int maxBambou=4;maxBambou>0;maxBambou--){
                for (Point3D coordonne : possibilites){
                    if(plateau.getParcelle(coordonne).getType() == Enums.TypeParcelle.VERTE && Plateau.getInstance().getParcelle(coordonne).getNbBambou()==maxBambou){
                        return coordonne;
                    }
                }
            }

            for (int maxBambou=4;maxBambou>0;maxBambou--){
                for (Point3D coordonne : possibilites){
                    if(plateau.getParcelle(coordonne).getType() == JAUNE && Plateau.getInstance().getParcelle(coordonne).getNbBambou()==maxBambou){
                        return coordonne;
                    }
                }
            }

            for (int maxBambou=4;maxBambou>0;maxBambou--){
                for (Point3D coordonne : possibilites){
                    if(plateau.getParcelle(coordonne).getType() == ROSE && Plateau.getInstance().getParcelle(coordonne).getNbBambou()==maxBambou){
                        return coordonne;
                    }
                }
            }

            return super.choixDeplacementPanda(possibilites);
        }

    }

    public Point3D rechercheParcelle(){
        Point3D res = new Point3D(0,0,0) ; //facultatif
        Plateau plateau=Plateau.getInstance();
        Objectif objPanda=choixObjectifPrioritaire();
        // Chercher la parcelle avec la couleur voulu //
        for (int maxBambou=4;maxBambou>0;maxBambou--) {
            for (Point3D coordonne : plateau.getKeylist()) {
                if (plateau.getParcelle(coordonne).getType() == objPanda.getCouleur() && Plateau.getInstance().getParcelle(coordonne).getNbBambou() == maxBambou && plateau.getParcelle(Panda.getInstance().getCoord()).getType() != objPanda.getCouleur()) {
                    res = coordonne;
                }
            }
        }
        return res;

    }

    public Point3D parcelleCommune(){
        oldcoor = Panda.getInstance().getCoord();
        Point3D destination = oldcoor; //facultatif
        Panda.getInstance().setCoord(this.rechercheParcelle());
        ArrayList<Point3D> destinationsPossibles = Panda.getInstance().destinationsPossibles();
        // On replace le panda a sa position originelle
        Panda.getInstance().setCoord(oldcoor);
        ArrayList<Point3D> destinationsPossiblesold = Panda.getInstance().destinationsPossibles();

        for (int i = 0; i <destinationsPossibles.size() ; i++) {
            if(destinationsPossiblesold.contains(destinationsPossibles.get(i))){
                destination = destinationsPossibles.get(i);
                //Panda.getInstance().setCoord(destination);
            }
        }
        return destination;
    }


}
