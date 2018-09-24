
public class Parcelle {
    final TypeParcelle type; //Pour le moment il y a que etang
    private boolean irriguee;

    public Parcelle(TypeParcelle type){
        this.type = type;
        if(this.type  == TypeParcelle.etang ){
            this.irriguee = true;
        }
        else{
            this.irriguee = false;
        }
    }

    @Override
    public String toString() {
        return "parcelle ok";
    }
}
