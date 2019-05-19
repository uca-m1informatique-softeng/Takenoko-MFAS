package takenokocucumber;


import javafx.geometry.Point3D;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import takenoko.Main;
import takenoko.moteur.Parcelle;
import takenoko.moteur.Plateau;
import takenoko.moteur.personnages.Jardinier;



@SpringBootTest(classes = Main.class)
@ContextConfiguration()
public class PoserJardinierSpring {

    static Plateau plateau = Plateau.getInstance();
    static Parcelle parcelle = new Parcelle();
    Jardinier jardinier = new Jardinier();
    Point3D point =  new Point3D(1,0,-1);

    public void init(){
        plateau.resetPlateau();
        plateau.poser(parcelle ,point);

    }

    public void deplacer_jardinier(){
        jardinier.deplacer(point);
    }
}
