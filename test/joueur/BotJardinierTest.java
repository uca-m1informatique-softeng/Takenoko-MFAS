package joueur;

import javafx.geometry.Point3D;
import moteur.Enums;
import moteur.Parcelle;
import moteur.Partie;
import moteur.Plateau;
import moteur.objectifs.ObjectifJardinier;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class BotJardinierTest {
    Partie partie=new Partie();
    Plateau plateau =partie.getPlateau();
    Parcelle parcelleJaune = new Parcelle(Enums.TypeParcelle.JAUNE);
    BotJardinier botJardinier = new BotJardinier(Enums.CouleurBot.ROUGE);

    @Test
    public void joueurDeplaceJardinierZeroParcelleCouleurVoulu() {
        plateau.poser(new Parcelle(Enums.TypeParcelle.VERTE),new Point3D(0,1,-1));
        plateau.poser(new Parcelle(Enums.TypeParcelle.VERTE),new Point3D(1,0,-1));
        botJardinier.addObjectif(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));

        botJardinier.joueurDeplaceJardinier(partie.getJardinier());

        assertEquals(partie.getJardinier().getCoord(),new Point3D(0,1,-1));
    }

    @Test
    public void joueurDeplaceJardinierUneParcelleCouleurVoulu(){
        plateau.poser(new Parcelle(Enums.TypeParcelle.VERTE),new Point3D(0,1,-1));
        plateau.poser(new Parcelle(Enums.TypeParcelle.JAUNE),new Point3D(1,0,-1));
        botJardinier.addObjectif(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));

        botJardinier.joueurDeplaceJardinier(partie.getJardinier());

        assertEquals(partie.getJardinier().getCoord(),new Point3D(1,0,-1));
    }

    @Test
    public void joueurDeplaceJardinierDeuxParcellesCouleurVoulu(){
        plateau.poser(new Parcelle(Enums.TypeParcelle.VERTE),new Point3D(0,1,-1));
        plateau.poser(new Parcelle(Enums.TypeParcelle.JAUNE),new Point3D(1,0,-1));
        plateau.poser(new Parcelle(Enums.TypeParcelle.JAUNE),new Point3D(1,-1,0));
        botJardinier.addObjectif(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));

        botJardinier.joueurDeplaceJardinier(partie.getJardinier());

        assertEquals(partie.getJardinier().getCoord(),new Point3D(1,0,-1));

    }

    @Test
    public void choixAction(){
        assertEquals(1,plateau.getKeylist().size());
        assertEquals(new Point3D(0,0,0),partie.getJardinier().getCoord());
        botJardinier.addObjectif(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));
        botJardinier.choixAction(1,partie);
        assertEquals(2,plateau.getKeylist().size());
        assertEquals(new Point3D(0,1,-1),partie.getJardinier().getCoord());
    }


}