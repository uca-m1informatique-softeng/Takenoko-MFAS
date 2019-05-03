package takenokocucumber;


import javafx.geometry.Point3D;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import takenoko.Main;
import takenoko.moteur.Parcelle;
import takenoko.moteur.Plateau;


@SpringBootTest(classes = Main.class)
@ContextConfiguration()
public class PoserParcelleSpring {

    static Plateau plateau = Plateau.getInstance();
    static Parcelle parcelle = new Parcelle();

    public void init(){
        plateau.resetPlateau();
    }

    public void placerParcelle(){
        init();
        plateau.poser(parcelle ,new Point3D(1,0,-1));
    }
}
