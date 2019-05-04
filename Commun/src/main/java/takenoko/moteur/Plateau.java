package takenoko.moteur;

import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import takenoko.moteur.Enums.TypeParcelle;
import org.springframework.stereotype.Component;

/**
 *C'est la classe du plateau
 */
@Component
@Scope("singleton")
public class Plateau {

    private static Plateau instance=null;

    private HashMap<Point3D, Parcelle> map;
    private ArrayList<Point3D> keylist;
    private HashMap<Point3D, Irrigation> mapIrrigation;
    private ArrayList<Point3D> keylistIrrigation;




    //////////////////////////////GETTER et SETTER//////////////////////////////


    public HashMap<Point3D, Irrigation> getMapIrrigation() {
        return mapIrrigation;
    }

    public void setMapIrrigation(HashMap<Point3D, Irrigation> mapIrrigation) {
        this.mapIrrigation = mapIrrigation;
    }

    public ArrayList<Point3D> getKeylistIrrigation() {
        return keylistIrrigation;
    }

    public void setKeylistIrrigation(ArrayList<Point3D> keylistIrrigation) {
        this.keylistIrrigation = keylistIrrigation;
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


    //////////////////////////////Méthodes//////////////////////////////

    public static final Plateau getInstance() {
        if (Plateau.instance == null) {
            Plateau.instance = new Plateau();
            Plateau.instance.resetPlateau();
        }
        return Plateau.instance;
    }

    /**
     * Réinitialise le plateau
     */
    public void resetPlateau(){
        Point3D coordonne = new Point3D(0,0,0);
        Parcelle parcelle = new Parcelle();
        parcelle.setType(TypeParcelle.ETANG);
        parcelle.setIrriguee(true);
        keylist = new ArrayList<Point3D>();
        keylist.add(coordonne);
        map = new HashMap<>();
        map.put(coordonne,parcelle);

        //6 premiers irrigations autour de l'etang
        Point3D [] tabCoordIrrigation = new Point3D[6];
        tabCoordIrrigation[0]=new Point3D(0.5,0,-0.5);
        tabCoordIrrigation[1]=new Point3D(0.5,-0.5,0);
        tabCoordIrrigation[2]=new Point3D(0,-0.5,0.5);
        tabCoordIrrigation[3]=new Point3D(-0.5,0,0.5);
        tabCoordIrrigation[4]=new Point3D(-0.5,0.5,0);
        tabCoordIrrigation[5]=new Point3D(0,0.5,-0.5);

        mapIrrigation=new HashMap<>();
        keylistIrrigation = new ArrayList<Point3D>();
        for(int i=0;i<6;i++) {
            keylistIrrigation.add(tabCoordIrrigation[i]);
            mapIrrigation.put(tabCoordIrrigation[i],new Irrigation());
        }
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
     * Renvoie une irrigation pour une coordonne
     * @param p
     * @return
     */
    public Irrigation getIrrigation(Point3D p){
        int index=keylistIrrigation.indexOf(p);
        if (index != -1) {
        Point3D p2=keylistIrrigation.get(index);
        return mapIrrigation.get(p2);
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
     * Renvoie toute les irigations sur le plateau
     * @return
     */
    public ArrayList<Parcelle> getAllIrrigation() {
        ArrayList result=new ArrayList<Irrigation>();
        for (int i=0;i<this.mapIrrigation.size();i++) {
            result.add(mapIrrigation.get(keylistIrrigation.get(i)));
        }
        return result;
    }

    /**
     * Retourne les coordonnes autour des coordonnes d'une parcelle
     * @param coordonne
     * @return
     */
    public ArrayList<Point3D> getIrrigationVoisineDeParcelle(Point3D coordonne){
        double x=coordonne.getX();
        double y=coordonne.getY();
        double z=coordonne.getZ();

        ArrayList<Point3D> list = new ArrayList<Point3D>();
        list.add(new Point3D(x+0.5,y+0,z-0.5));
        list.add(new Point3D(x+0.5,y-0.5,z+0));
        list.add(new Point3D(x+0,y-0.5,z+0.5));
        list.add(new Point3D(x-0.5,y+0,z+0.5));
        list.add(new Point3D(x-0.5,y+0.5,z+0));
        list.add(new Point3D(x+0,y+0.5,z-0.5));
        return list;
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
     * Renvoie le sens de l'irrigation des coordonne d'une irrigation
     * @param coordonne
     * @return
     */
    public int sensIrrigation (Point3D coordonne){
        double x=coordonne.getX();
        double y=coordonne.getY();
        double z=coordonne.getZ();

        /* 3 CAS POUR 3 SENS D'IRRIGATION
         *  \ <- 1
         *  | <- 2
         *  / <- 3
         *
         *  Dans le cas 1 (\) on renvoie
         *  1 ->  |
         *  2 -> / \ / <- 3
         *          | <- 4
         *
         *  Dans le cas 2 (|) on renvoie
         *  1 -> \ / <- 2
         *        |
         *  3 -> / \ <- 4
         *
         * Dans le cas 3 (/) on renvoie
         *         |  <- 1
         * 3 -> \ / \ <- 2
         * 4 ->  |
         * */
        if(x%1==0){
            return 3;
        }
        if(y%1==0){
            return 1;
        }
        if(z%1==0){
            return 2;
        }
        //erreur
        return 0;
    }

    /**
     * Renvoie les irrigation voisine d'une coordonne d'irrigation
     * @param coordonne
     * @return
     */
    public ArrayList<Point3D> getIrrigationVoisine(Point3D coordonne){
        int sens=sensIrrigation(coordonne);
        double x=coordonne.getX();
        double y=coordonne.getY();
        double z=coordonne.getZ();

        ArrayList<Point3D> listVoisin = new ArrayList<Point3D>();
        if(sens==3){ //cas 3
            //1
            listVoisin.add(new Point3D(x+0.5,y+0,z-0.5));
            //2
            listVoisin.add(new Point3D(x+0.5,y-0.5,z+0));
            //3
            listVoisin.add(new Point3D(x-0.5,y+0.5,z+0));
            //4
            listVoisin.add(new Point3D(x-0.5,y+0,z+0.5));
        }
        if(sens==1){ //cas 1
            //1
            listVoisin.add(new Point3D(x+0,y+0.5,z-0.5));
            //2
            listVoisin.add(new Point3D(x-0.5,y+0.5,z+0));
            //3
            listVoisin.add(new Point3D(x+0.5,y-0.5,z+0));
            //4
            listVoisin.add(new Point3D(x+0,y-0.5,z+0.5));
        }
        if(sens==2){ //cas 2
            //1
            listVoisin.add(new Point3D(x+0,y+0.5,z-0.5));
            //2
            listVoisin.add(new Point3D(x+0.5,y+0,z-0.5));
            //3
            listVoisin.add(new Point3D(x-0.5,y+0,z+0.5));
            //4
            listVoisin.add(new Point3D(x+0,y-0.5,z+0.5));
        }

        return listVoisin;
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
     * Renvoie les irrigations voisine libre a partir d'une coordonne d'irrigation
     * @param coordonne
     * @return
     */
    public ArrayList<Point3D> getIrrigationVoisineLibre(Point3D coordonne){
        ArrayList<Point3D> list=this.getIrrigationVoisine(coordonne);

        for (int i = list.size()-1; i >=0; i--) {
            if(keylistIrrigation.contains(list.get(i))){
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
     * Renvoie les irrigation voisine d'une coordonne d'irrigation
     * @param coordonne
     * @return
     */
    public ArrayList<Point3D> getIrrigationVoisineOccupe(Point3D coordonne){
        ArrayList<Point3D> list=this.getIrrigationVoisine(coordonne);

        for (int i = list.size()-1; i >=0; i--) {
            if(!keylistIrrigation.contains(list.get(i))) {
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
     * Renvoie si une irrigation est occupée a une coordonne donnée
     * @param coordonne
     * @return
     */
    public boolean isIrrigationOccupee (Point3D coordonne){
        return keylistIrrigation.contains(coordonne);
    }

    /**
     * Renvoie les coordonnes des parcelles adjacentes a une irrigation
     * @param coordonne
     * @return
     */
    public ArrayList<Point3D> getcoordonneParcelleAdjacenteIrrigation (Point3D coordonne){
        double x=coordonne.getX();
        double y=coordonne.getY();
        double z=coordonne.getZ();

        int sens=sensIrrigation(coordonne);
        ArrayList<Point3D> result=new ArrayList<>();
        Point3D p1;
        Point3D p2;
        if(sens==1){
            p1=new Point3D(x-0.5,y+0,z+0.5);
            p2=new Point3D(x+0.5,y+0,z-0.5);

            result.add(p1);
            result.add(p2);
        }
        if(sens==2){
            p1=new Point3D(x-0.5,y+0.5,z+0);
            p2=new Point3D(x+0.5,y-0.5,z+0);

            result.add(p1);
            result.add(p2);
        }
        if(sens==3){
            p1=new Point3D(x+0,y+0.5,z-0.5);
            p2=new Point3D(x+0,y-0.5,z+0.5);

            result.add(p1);
            result.add(p2);
        }
        return result;

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
     * Renvoie si il est possible de poser une irrigation a une coordonne
     * @param coordonne
     * @return
     */
    public boolean isEmplacementIrrigationAutorise(Point3D coordonne){
        //verifie pas si irrigation deja occupée
        ArrayList<Point3D> list=this.getIrrigationVoisineOccupe(coordonne);

        ArrayList<Point3D>listdePoints=getcoordonneParcelleAdjacenteIrrigation(coordonne);
        boolean parcellePresente=false;
        for(Point3D coordParcelle:listdePoints){
            if(getParcelle(coordParcelle)!= null) {
                parcellePresente=true;
                break;
            }
        }

        return (!list.isEmpty() && parcellePresente);
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
     * Renvoie la liste des emplacement ou on peut poser une irrigation
     * @return
     */
    public ArrayList<Point3D> emplacementsAutoriseIrrigation(){
        ArrayList<Point3D> maListe = new ArrayList<>();
        ArrayList<Point3D> listTemp;
        Point3D point;
        Point3D pointVoisin;

        for(int i = 0; i < keylistIrrigation.size(); i++){

            point = keylistIrrigation.get(i);

            listTemp = getIrrigationVoisineLibre(point);
            if(!listTemp.isEmpty()){
                for(int j = 0; j < listTemp.size(); j++){
                    pointVoisin = listTemp.get(j);
                    if(isEmplacementIrrigationAutorise(pointVoisin) && !maListe.contains(pointVoisin) ){
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

        ArrayList<Point3D> listCoordIrrigationVoisine=getIrrigationVoisineDeParcelle(coordonne);
        for(Point3D coord:listCoordIrrigationVoisine){
            if (keylistIrrigation.contains(coord)){
                if(!parcelle.isIrriguee()) {
                    parcelle.setIrriguee(true);
                    parcelle.pousserBambou();
                }
                break;
            }
        }

        Affichage.affichagePoseParcelle(parcelle,coordonne);
        Affichage.affichageNombreBambou(this,coordonne);
    }

    /**
     * La methode qui parmet de poser une irrigation
     * @param irrigation
     * @param coordonne
     */
    public void poserIrrigation(Irrigation irrigation, Point3D coordonne){
        keylistIrrigation.add(coordonne);
        mapIrrigation.put(coordonne,irrigation);
        for(Point3D point: getcoordonneParcelleAdjacenteIrrigation(coordonne)){
            Parcelle parcelleVoisineIrrigation=getParcelle(point);
            if(parcelleVoisineIrrigation!=null){
                if(!parcelleVoisineIrrigation.isIrriguee()){
                    parcelleVoisineIrrigation.setIrriguee(true);
                    parcelleVoisineIrrigation.pousserBambou();
                }
            }
        }
        Affichage.affichagePoseIrrigation(coordonne);
    }

    /**
     * C'est une méthode qui retourne True si le motif est bien reconnu.
     * @param pointCourant
     * @param couleurObjectif
     * @param type
     * @return
     */
    public boolean chercheMotifParcelle (Point3D pointCourant, TypeParcelle couleurObjectif, int type){

        if(map.get(pointCourant).getType() == couleurObjectif && map.get(pointCourant).isIrriguee()) { //on vérifie si la parcelle correspond à la couleur de l'objectif
            ArrayList<Point3D> pointsVoisin = getParcelleVoisine(pointCourant);

            for (int j = 0; j < 6; j++) {//on cherche si le motif est complet
                Point3D secondPoint = pointsVoisin.get(j);

                switch (type){
                    case 0:
                        if(parcelleSuivanteMotif(pointCourant,couleurObjectif,j) && parcelleSuivanteMotif(secondPoint,couleurObjectif,j)){
                            return true;
                        }
                        break;
                    case 1:
                        if(parcelleSuivanteMotif(pointCourant,couleurObjectif,j) && parcelleSuivanteMotif(secondPoint,couleurObjectif,(j+1)%6)){
                            return true;
                        }
                        break;
                    case 2:
                        if(parcelleSuivanteMotif(pointCourant,couleurObjectif,j) && parcelleSuivanteMotif(pointCourant,couleurObjectif,(j+1)%6)){
                            return true;
                        }
                        break;
                    case 3:
                        if(parcelleSuivanteMotif(pointCourant,couleurObjectif,j) && parcelleSuivanteMotif(pointCourant,couleurObjectif,(j+1)%6) &&
                                parcelleSuivanteMotif(pointCourant,couleurObjectif,(j+2)%6)){
                            return true;
                        }
                        break;
                    default:
                        return false;
                }
            }
        }
        return false;

    }



    /**
     * Renvoie la parcelle suivante permettant de completer un motif donnee
     * @param pointCourant
     * @param couleurObjectif
     * @param i
     * @return
     */
    public boolean parcelleSuivanteMotif(Point3D pointCourant, TypeParcelle couleurObjectif,int i){
        ArrayList<Point3D> pointsVoisin = getParcelleVoisine(pointCourant);
        ArrayList<Point3D> pointsVoisinOccupe = getParcelleVoisineOccupe(pointCourant);
        if(pointsVoisinOccupe.contains(pointsVoisin.get(i)) && getParcelle(pointsVoisin.get(i)).getType() == couleurObjectif && getParcelle(pointsVoisin.get(i)).isIrriguee()) {
            return true;
        }
        return false;
    }




}
