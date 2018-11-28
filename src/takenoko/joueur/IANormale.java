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




}
