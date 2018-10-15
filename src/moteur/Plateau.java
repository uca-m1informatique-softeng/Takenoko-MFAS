package moteur;

import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.HashMap;
import moteur.Enums.TypeParcelle;
/**
 *C'est la classe du plateau
 */
public class Plateau {

    private HashMap<Point3D, Parcelle> map;
    private ArrayList<Point3D> keylist;


    /**
     *Le constructeur
     */
    public Plateau(){

        Point3D coordonne = new Point3D(0,0,0);
        Parcelle parcelle = new Parcelle(TypeParcelle.ETANG);
        keylist = new ArrayList<Point3D>();
        keylist.add(coordonne);
        map = new HashMap<>();
        map.put(coordonne,parcelle);

    }

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

    public Parcelle getParcelle(Point3D p){
        int index=keylist.indexOf(p);
        Point3D p2=keylist.get(index);

        return map.get(p2);
    }

    public ArrayList<Parcelle> getAllParcelle()
    {
        ArrayList result=new ArrayList<Parcelle>();
        for (int i=0;i<this.map.size();i++)
        {
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
            if(keylist.contains(list.get(i)))
            {
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
    public ArrayList<Point3D> getParcelleVoisineMemeCouleur(Point3D coordonne){
        ArrayList<Point3D> list=this.getParcelleVoisine(coordonne);

        for (int i = list.size()-1; i >=0; i--) {
            if(!keylist.contains(list.get(i)) || getParcelle(list.get(i)).getType() != getParcelle(coordonne).getType()) {
                list.remove(i);
            }
        }
        return list;

    }

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
        System.out.println("Parcelle "+parcelle.getType()+" posée en " + coordonne.getX() + ", " + coordonne.getY() + ", " + coordonne.getZ());
    }

    public boolean parcelleSuivanteLibre(Point3D pointCourant, int i){
        ArrayList<Point3D> pointsVoisin = getParcelleVoisine(pointCourant);
        ArrayList<Point3D> pointsVoisinOccupe = getParcelleVoisineOccupe(pointCourant);
        if(!(pointsVoisinOccupe.contains(pointsVoisin.get(i)))){
            return true;
        }
        return false;
    }

    public boolean chercheMotifParcelle (Point3D pointCourant, TypeParcelle couleurObjectif, int type){

        if(map.get(pointCourant).getType() == couleurObjectif) { //on vérifie si la parcelle correspond à la couleur de l'objectif
            ArrayList<Point3D> pointsVoisin = getParcelleVoisine(pointCourant);

            for (int j = 0; j < 6; j++) {//on cherche si le motif est complet
                Point3D secondPoint = pointsVoisin.get(j);

                switch (type){
                    case 0:
                        if(parcelleSuivanteMotif(pointCourant,couleurObjectif,j) && parcelleSuivanteMotif(secondPoint,couleurObjectif,j)){
                            return true;
                        }
                    case 1:
                        if(parcelleSuivanteMotif(pointCourant,couleurObjectif,j) && parcelleSuivanteMotif(secondPoint,couleurObjectif,(j+1)%6)){
                            return true;
                        }
                    case 2:
                        if(parcelleSuivanteMotif(pointCourant,couleurObjectif,j) && parcelleSuivanteMotif(pointCourant,couleurObjectif,(j+1)%6)){
                            return true;
                        }
                    case 3:
                        if(parcelleSuivanteMotif(pointCourant,couleurObjectif,j) && parcelleSuivanteMotif(pointCourant,couleurObjectif,(j+1)%6) &&
                                parcelleSuivanteMotif(pointCourant,couleurObjectif,(j+2)%6)){
                            return true;
                        }
                }
            }
        }
        return false;
    }

    public boolean parcelleSuivanteMotif(Point3D pointCourant, TypeParcelle couleurObjectif,int i){
        ArrayList<Point3D> pointsVoisin = getParcelleVoisine(pointCourant);
        ArrayList<Point3D> pointsVoisinOccupe = getParcelleVoisineOccupe(pointCourant);
        if(pointsVoisinOccupe.contains(pointsVoisin.get(i)) && getParcelle(pointsVoisin.get(i)).getType() == couleurObjectif) {
            return true;
        }
        return false;
    }




}