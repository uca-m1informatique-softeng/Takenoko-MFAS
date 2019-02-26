package takenoko.moteur;

import javafx.geometry.Point3D;

import java.util.ArrayList;

public class Stock {

    private static Stock instance=null;
    ArrayList<Parcelle> StockParcelle =new ArrayList<Parcelle>();
    ArrayList<Point3D> StockPoint =new ArrayList<Point3D>();

    void SetStock(ArrayList<Parcelle> s,ArrayList<Point3D> sp){
        StockParcelle.addAll(s);
        StockPoint.addAll(sp);
    }

    public final static Stock getInstance() {
        if (Stock.instance == null) {
            Stock.instance = new Stock();
        }

        return Stock.instance;
    }
}
