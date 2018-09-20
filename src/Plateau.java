import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.HashMap;

public class Plateau {

    private HashMap<Point3D, Parcelle> map = new HashMap<>();
    private ArrayList<Point3D> keylist = new ArrayList<Point3D>();

    public HashMap<Point3D, Parcelle> getMap() {
        return map;
    }

    public void setMap(HashMap<Point3D, Parcelle> map) {
        this.map = map;
    }

    public Plateau(){

        Point3D p = new Point3D(0,0,0);
        Parcelle par = new Parcelle();
        ArrayList<Point3D> keylist = new ArrayList<Point3D>();
        keylist.add(p);
        HashMap<Point3D, Parcelle> map = new HashMap<>();
        map.put(p,par);

    }

    public Parcelle getParcelle(Point3D p){
        int index=keylist.indexOf(p);
        Point3D p2=keylist.get(index);

        return map.get(p2);
    }


}
