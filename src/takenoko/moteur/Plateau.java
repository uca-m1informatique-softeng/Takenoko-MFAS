package takenoko.moteur;

import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.HashMap;

import takenoko.joueur.IANormale;
import takenoko.joueur.Joueur;
import takenoko.moteur.Enums.TypeParcelle;
import takenoko.moteur.personnages.Panda;

/**
 *C'est la classe du plateau
 */
public class Plateau {

    private static Plateau instance=null;

    private HashMap<Point3D, Parcelle> map;
    private ArrayList<Point3D> keylist;
    private ArrayList<Joueur> listdesjoueurs;

    /**
     * Le constructeur
     */
    public Plateau() {
        resetPlateau();
    }


    public final static Plateau getInstance() {
        if (Plateau.instance == null) {
            Plateau.instance = new Plateau();
            Plateau.instance.resetPlateau();
        }
        return Plateau.instance;
    }

    //////////////////////////////GETTER et SETTER//////////////////////////////

    public HashMap<Point3D, Parcelle> getMap() {
        return map;
    }

    public void setMap(HashMap<Point3D, Parcelle> map) {
        this.map = map;
    }

    public ArrayList<Point3D> getKeylist() {
        return keylist;
    }

    public void setKeylist(ArrayList<Point3D> keylist) {
        this.keylist = keylist;
    }

    public ArrayList<Joueur> getListdesjoueurs(){return listdesjoueurs;}

    public void setListdesjoueurs(ArrayList<Joueur> list) {
        this.listdesjoueurs = list;
    }
    //////////////////////////////Méthodes//////////////////////////////

    /**
     * Reinitialise le plateau
     */
    public void resetPlateau(){
        Point3D coordonne = new Point3D(0,0,0);
        Parcelle parcelle = new Parcelle(TypeParcelle.ETANG);
        parcelle.setType(TypeParcelle.ETANG);
        keylist = new ArrayList<Point3D>();
        keylist.add(coordonne);
        map = new HashMap<>();
        map.put(coordonne,parcelle);

    }

    /**
     * Renvoie une parcelle pour une coordonne
     * @param p
     * @return
     */
    public Parcelle getParcelle(Point3D p){
        int index=keylist.indexOf(p);
        if (index != -1) {
            Point3D p2=keylist.get(index);
            return map.get(p2);
        }
        return null;
    }

    /**
     * Renvoie toute les parcelles sur le plateau
     * @return
     */
    public ArrayList<Parcelle> getAllParcelle() {
        ArrayList result=new ArrayList<Parcelle>();
        for (int i=0;i<this.map.size();i++) {
            result.add(map.get(keylist.get(i)));
        }
        return result;
    }


    /**
     * La méthode qui donne un point et renvois les 6 voisins
     * @param coordonne
     * @return
     */
    public ArrayList<Point3D> getParcelleVoisine(Point3D coordonne){
        double x=coordonne.getX();
        double y=coordonne.getY();
        double z=coordonne.getZ();

        ArrayList<Point3D> list = new ArrayList<Point3D>();
        list.add(new Point3D(x+0,y+1,z-1));
        list.add(new Point3D(x+1,y+0,z-1));
        list.add(new Point3D(x+1,y-1,z+0));
        list.add(new Point3D(x+0,y-1,z+1));
        list.add(new Point3D(x-1,y+0,z+1));
        list.add(new Point3D(x-1,y+1,z+0));
        return list;
    }


    /**
     * La méthode qui donne un point et renvois les voisins libres
     * @param coordonne
     * @return
     */
    public ArrayList<Point3D> getParcelleVoisineLibre(Point3D coordonne){
        ArrayList<Point3D> list=this.getParcelleVoisine(coordonne);

        for (int i = list.size()-1; i >=0; i--) {
            if(keylist.contains(list.get(i))){
                list.remove(i);
            }
        }
        return list;
    }


    /**
     * La méthode qui donne un point et renvois les voisins occupés
     * @param coordonne
     * @return
     */
    public ArrayList<Point3D> getParcelleVoisineOccupe(Point3D coordonne){
        ArrayList<Point3D> list=this.getParcelleVoisine(coordonne);

        for (int i = list.size()-1; i >=0; i--) {
            if(!keylist.contains(list.get(i))) {
                list.remove(i);
            }
        }
        return list;

    }


    /**
     * La méthode qui donne un point et renvois les voisins de même couleur
     * @param coordonne
     * @return
     */
    public ArrayList<Point3D> getParcelleVoisineMemeCouleur(Point3D coordonne,Parcelle parcelle){
        ArrayList<Point3D> list=this.getParcelleVoisine(coordonne);

        for (int i = list.size()-1; i >=0; i--) {
            if(!keylist.contains(list.get(i))) {
                list.remove(i);
            }
            else{
                if(getParcelle(list.get(i)).getType() != parcelle.getType()) {
                    list.remove(i);
                }
            }
        }
        return list;

    }

    /**
     * La méthode qui donne un point et renvois les voisins de même couleur
     * @param coordonne
     * @return
     */
    public ArrayList<Point3D> getParcelleVoisineMemeCouleur(Point3D coordonne){
        return getParcelleVoisineMemeCouleur(coordonne,getParcelle(coordonne));
    }

    /**
     * Renvoie si une parcelle est occupée a une coordonne donnée
     * @param coordonne
     * @return
     */
    public boolean isParcelleOccupee (Point3D coordonne){
        return keylist.contains(coordonne);
    }

    /**
     * La méthode qui rentre un point et confirme si on peut poser une parcelle
     * @param coordonne
     * @return
     */
    public boolean isEmplacementAutorise(Point3D coordonne){
        ArrayList<Point3D> list=this.getParcelleVoisineOccupe(coordonne);
        return (list.size() > 1 || list.contains(new Point3D(0,0,0)) );
    }


    /**
     * La méthode qui renvois la liste des endroits où on peut poser une parcelle sur le plateau
     * @return
     */
    public ArrayList<Point3D> emplacementsAutorise(){
        ArrayList<Point3D> maListe = new ArrayList<>();
        ArrayList<Point3D> listTemp;
        Point3D point;
        Point3D pointVoisin;

        for(int i = 0; i < keylist.size(); i++){

            point = keylist.get(i);

            listTemp = getParcelleVoisineLibre(point);
            if(!listTemp.isEmpty()){
                for(int j = 0; j < listTemp.size(); j++){
                    pointVoisin = listTemp.get(j);
                    if(isEmplacementAutorise(pointVoisin) && !maListe.contains(pointVoisin) ){
                        maListe.add(pointVoisin);
                    }
                }
            }
        }
        return maListe;
    }



    /**
     * La méthode qui permet de poser une parcelle sur le plateau
     * @param parcelle
     * @param coordonne
     */
    public void poser(Parcelle parcelle, Point3D coordonne){
        keylist.add(coordonne);
        map.put(coordonne,parcelle);
        parcelle.pousserBambou();


        Affichage.affichagePoseParcelle(parcelle,coordonne);
        Affichage.affichageNombreBambou(this,coordonne);
    }

    /**
     * La méthode qui renvoie True s'il y a deux parcelles de la même "couleur" adjacentes
     * @param couleur
     * @return
     */
    public boolean parcellesAdjacentesMemeCouleur(Enums.TypeParcelle couleur, IANormale ia){
        for(Point3D pt : getKeylist()){
            if(getParcelle(pt).getType() == couleur && getParcelleVoisineMemeCouleur(pt).size() > 0){
                ia.setPremiereDestination(pt);
                ia.setDeuxiemeDestination(getParcelleVoisineMemeCouleur(pt).get(0));
                return true;
            }
        }
        return false;
    }


    /**
     * Renvoie un boolean pour savoir si la couleur existe sur le plateau
     * @param couleur
     * @return
     */
    public boolean couleurSurPlateau(Enums.TypeParcelle couleur){
        for (Point3D coordonne : getKeylist()) {
            Parcelle parcelle = getParcelle(coordonne);
            if (parcelle.getType() == couleur && parcelle.getNbBambou() > 0 && Panda.getInstance().getCoord() != coordonne){
                return true;

            }
        }
        return false;
    }


}
