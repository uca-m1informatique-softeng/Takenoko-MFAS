package Joueur;

import Moteur.Parcelle;
import Moteur.Plateau;
import Moteur.TypeParcelle;
import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *C'est la classe des bots
 */
public class Bot {

    private String couleur;
    private int nombreObjectifs = 0;

    /**
     * Le constructeur
     * @param couleur
     */
    public Bot(String couleur){
        this.couleur = couleur;
    }

    private Parcelle piocherParcelle(){
        return new Parcelle(TypeParcelle.etang);
    }

    /**
     * Le déroulement des actions du bot
     * @param plateau
     * @param coord
     */
    public void play(Plateau plateau, Point3D coord){ // tester plus tard que coord correspond bien à une parcelle


        verifierMonObjectif(plateau.getMap(),plateau.getKeylist());

        ArrayList<Point3D> list = plateau.emplacementsAutorise();
        Parcelle p = piocherParcelle();
        if(list.size() > 0){
            Point3D point = list.get(0);
            plateau.poser(p, point);
            System.out.println("( " + point.getX() + ", " + point.getY() + ", " + point.getZ() + ") tuile posé par le joueur " + couleur);

        }
        ArrayList<Point3D> listdeplacementJardinier=plateau.DestinationsPossiblesJaridnier();
        if(listdeplacementJardinier.size() > 0){
            for (int i=0; i<listdeplacementJardinier.size();i++)
            {System.out.println(listdeplacementJardinier.get(i));
            }
                    Point3D pointJaridnier = listdeplacementJardinier.get(0);
            plateau.DeplacerJardinier(pointJaridnier);
            System.out.println("le joueur " + couleur + " a deplacé le jardinier en (" + pointJaridnier.getX() + ", " + pointJaridnier.getY() + ", " + pointJaridnier.getZ() + ")");
            //plateau.pousserBambouMap(); //le bambou pousse automatiquement sur toutes les parcelles
        }
        verifierMonObjectif(plateau.getMap(),plateau.getKeylist());


    }

    /**
     * La méthode qui vérifie l'objectif du bot
     * @param map
     * @param keylist
     */
    public void verifierMonObjectif(HashMap<Point3D,Parcelle> map, ArrayList<Point3D> keylist){
        for(int i = 0; i < map.size(); i++){
            if( map.get( keylist.get(i) ).getNbBambou() == 3 ){
                System.out.println("Le joueur "+ couleur +" réalise son objectif de faire pousser 3 bambous");
                nombreObjectifs++;
                break;
            }
        }
    }


    public int getNombreObjectifs() {
        return nombreObjectifs;
    }


    public void setNombreObjectifs(int nombreObjectifs) {
        this.nombreObjectifs = nombreObjectifs;
    }
}
