package Moteur;

import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *C'est la classe du plateau
 */
public class Plateau {

    private HashMap<Point3D, Parcelle> map;
    private ArrayList<Point3D> keylist;
    private Jardinier Jardinier;
    private Panda Panda;


    /**
     *Le constructeur
     */
    public Plateau(){

        Point3D p = new Point3D(0,0,0);
        Parcelle par = new Parcelle(TypeParcelle.etang);
        keylist = new ArrayList<Point3D>();
        keylist.add(p);
        map = new HashMap<>();
        map.put(p,par);
        this.Jardinier=new Jardinier();
        this.Panda = new Panda();

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

    /**
     * La méthode qui donne un point et renvois les 6 voisins
     * @param p
     * @return
     */
    public ArrayList<Point3D> getParcelleVoisine(Point3D p){
        double x=p.getX();
        double y=p.getY();
        double z=p.getZ();

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
     * @param p
     * @return
     */
    public ArrayList<Point3D> getParcelleVoisineLibre(Point3D p){
        ArrayList<Point3D> list=this.getParcelleVoisine(p);

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
     * @param p
     * @return
     */
    public ArrayList<Point3D> getParcelleVoisineOccupe(Point3D p){
        ArrayList<Point3D> list=this.getParcelleVoisine(p);

        for (int i = list.size()-1; i >=0; i--) {
            if(!keylist.contains(list.get(i))) {
                list.remove(i);
            }
        }
        return list;

    }

    public boolean isParcelleOccupee (Point3D p){
        return keylist.contains(p);
    }

    /**
     * La méthode qui rentre un point et confirme si on peut poser une parcelle
     * @param p
     * @return
     */
    public boolean isEmplacementAutorise(Point3D p){
        ArrayList<Point3D> list=this.getParcelleVoisineOccupe(p);
        if(list.size() > 1 || list.contains(new Point3D(0,0,0)) ){
            return true;
        }
        return false;
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
            if(listTemp.size() > 0){
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
     * @param p
     * @param coord
     */
    public void poser(Parcelle p, Point3D coord){
        keylist.add(coord);
        map.put(coord,p);
    }

    /**
     * Renvoyer la liste des deplacement possibles pour le jardinier (seul les voisins  pour le moment)
     * @return
     */
    //
    public ArrayList<Point3D> DestinationsPossiblesJaridnier() {
        ArrayList<Point3D> listvoisin = getParcelleVoisine(new Point3D(0,0,0));
        ArrayList<Point3D> resultat=new ArrayList<>();

        for (int i=0;i<6;i++) {
            Point3D direction=listvoisin.get(i);
            Point3D jardinier=this.Jardinier.getCoord();
            Point3D temp= new Point3D(jardinier.getX()+direction.getX(),jardinier.getY()+direction.getY(),jardinier.getZ()+direction.getZ());
            while(isParcelleOccupee(temp)){
                resultat.add(temp);
                double tempX=temp.getX();
                double tempY=temp.getY();
                double tempZ=temp.getZ();
                temp= new Point3D(tempX+direction.getX(),tempY+direction.getY(),tempZ+direction.getZ());
            }
        }
        return resultat;
    }


    /**
     * La méthode qui permet de déplacer le jardinier sur le plateau et de lui faire pousser le bambou
     * @param p
     */
    //pour le deplacement (pas de verification)
    public void DeplacerJardinier(Point3D p){
        Jardinier.setCoord(p);
        this.getParcelle(p).pousserBambou();
    }



    public ArrayList<Point3D> DestinationsPossiblesPanda(){
        ArrayList<Point3D> listvoisin = getParcelleVoisine(new Point3D(0,0,0));
        ArrayList<Point3D> resultat=new ArrayList<>();

        for (int i=0;i<6;i++) {
            Point3D direction=listvoisin.get(i);
            Point3D panda=this.Panda.getCoord();
            Point3D temp= new Point3D(panda.getX()+direction.getX(),panda.getY()+direction.getY(),panda.getZ()+direction.getZ());
            while(isParcelleOccupee(temp)){
                resultat.add(temp);
                double tempX=temp.getX();
                double tempY=temp.getY();
                double tempZ=temp.getZ();
                temp= new Point3D(tempX+direction.getX(),tempY+direction.getY(),tempZ+direction.getZ());
            }
        }
        return resultat;
    }

    public void DeplacerPanda(Point3D p){
        Panda.setCoord(p);
        this.getParcelle(p).mangerBambou();
    }





}
