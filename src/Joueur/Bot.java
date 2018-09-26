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
    private Objectif objectif = new Objectif();

    /**
     * Le constructeur
     * @param couleur
     */
    public Bot(String couleur){
        this.couleur = couleur;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public Objectif getObjectif() {
        return objectif;
    }

    public void setObjectif(Objectif objectif) {
        this.objectif = objectif;
    }

    protected Parcelle piocherParcelle(){
        return new Parcelle(TypeParcelle.etang);
    }

    /**
     * Le déroulement des actions du bot
     * @param plateau
     */
    public void play(Plateau plateau){ // tester plus tard que coord correspond bien à une parcelle


        verifierMonObjectif(plateau.getMap(),plateau.getKeylist());
        joueurPose(plateau);

        joueurDeplaceJardinier(plateau);

        verifierMonObjectif(plateau.getMap(),plateau.getKeylist());


    }

    /**
     * La méthode qui vérifie l'objectif du bot
     * @param map
     * @param keylist
     */
    public void verifierMonObjectif(HashMap<Point3D,Parcelle> map, ArrayList<Point3D> keylist){
        for(int i = 0; i < map.size(); i++){
            int ob = objectif.getNbBambouObjectif();
            if( map.get( keylist.get(i) ).getNbBambou() == ob ){
                System.out.println("Le joueur "+ couleur +" réalise son objectif de faire pousser "+ ob + " bambous");
                nombreObjectifs++;
                break;
            }
        }
    }

    public void joueurPose(Plateau plateau){
        ArrayList<Point3D> list = plateau.emplacementsAutorise();
        Parcelle p = piocherParcelle();
        if(list.size() > 0){
            Point3D point = list.get(0);
            plateau.poser(p, point);
            System.out.println("( " + point.getX() + ", " + point.getY() + ", " + point.getZ() + ") tuile posé par le joueur " + couleur);

        }
    }

    public void joueurDeplaceJardinier(Plateau plateau){
        ArrayList<Point3D> listdeplacementJardinier=plateau.DestinationsPossiblesJardinier();
        if(listdeplacementJardinier.size() > 0){
            Point3D pointJaridnier = listdeplacementJardinier.get(0);
            plateau.DeplacerJardinier(pointJaridnier);
            System.out.println("le joueur " + couleur + " a deplacé le jardinier en (" + pointJaridnier.getX() + ", " + pointJaridnier.getY() + ", " + pointJaridnier.getZ() + ")");
            System.out.println("Il y a " + plateau.getParcelle(pointJaridnier).getNbBambou() + " bambou.");
        }
    }


    public int getNombreObjectifs() {
        return nombreObjectifs;
    }


    public void setNombreObjectifs(int nombreObjectifs) {
        this.nombreObjectifs = nombreObjectifs;
    }
}
