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






}
