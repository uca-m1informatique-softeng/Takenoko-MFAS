
public class Parcelle {
    final TypeParcelle type; //Pour le moment il y a que etang
    private boolean irrigue;

    public Parcelle(TypeParcelle type, boolean irrigue){
        this.type = type;
        this.irrigue = irrigue;
    }

    @Override
    public String toString() {
        return "parcelle ok";
    }
}
