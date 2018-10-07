package Joueur;

import Moteur.*;
import Moteur.Objectifs.ObjectifPanda;
import Moteur.Personnages.Jardinier;
import Moteur.Personnages.Panda;
import javafx.geometry.Point3D;

import java.util.ArrayList;
import Moteur.Enums.CouleurBot;

public class BotPanda extends Bot {

    public BotPanda (CouleurBot s){
        super(s);
    }

    public boolean choixAction(int numeroActionDansLeTour, Partie P){
        joueurDeplaceJardinier(P.getJardinier());
        joueurDeplacePanda(P.getPanda());
        return true;
    }

    public void joueurDeplaceJardinier(Jardinier jardinier){
        Plateau plateau=jardinier.getPlateau();
        ArrayList<Point3D> listdeplacementJardinier=jardinier.DestinationsPossibles();
        boolean pasDeplacer = true;
        Point3D pointJaridnier = new Point3D(0,0,0);
        if(listdeplacementJardinier.size() > 0){
            ObjectifPanda objPanda=(ObjectifPanda)getListObjectifs().get(1);
            for (Point3D p : listdeplacementJardinier){
                if(plateau.getParcelle(p).getType() == objPanda.getCouleur()){
                    pointJaridnier = p;
                    pasDeplacer = false;
                    break;
                }
            }
            if(pasDeplacer){ pointJaridnier = listdeplacementJardinier.get(0);}
            jardinier.Deplacer(pointJaridnier);
            System.out.println("Il y a " + plateau.getParcelle(pointJaridnier).getNbBambou() + " bambou sur cette case");
        }
    }

    public void joueurDeplacePanda(Panda panda){
        Plateau plateau=panda.getPlateau();
        ArrayList<Point3D> listdeplacementPanda = panda.DestinationsPossibles();
        boolean pasDeplacer = true;
        Point3D pointPanda = new Point3D(0,0,0);
        if(listdeplacementPanda.size() > 0){
            ObjectifPanda objPanda=(ObjectifPanda)getListObjectifs().get(1);
            for (Point3D p : listdeplacementPanda){
                if(plateau.getParcelle(p).getType() == objPanda.getCouleur()){
                    pointPanda = p;
                    pasDeplacer = false;
                    break;
                }
            }
            if(pasDeplacer){ pointPanda = listdeplacementPanda.get(0);}
            if(panda.Deplacer(pointPanda)){
                getListBambou().add(new Bambou(plateau.getMap().get(pointPanda).getType()));
            }
            System.out.println("Il y a " + plateau.getParcelle(pointPanda).getNbBambou() + " bambou sur cette case");
        }
    }
}


