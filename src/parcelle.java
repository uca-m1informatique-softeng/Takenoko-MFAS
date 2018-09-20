import javafx.geometry.Point3D;

public class parcelle{
    private Point3D coord;


    public parcelle(Point3D coord) {
        this.coord = coord;
    }

        public Point3D getCoord() {
            return coord;
        }

        public void setCoord(Point3D coord) {
            this.coord = coord;
        }
}
