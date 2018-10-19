package joueur;

import javafx.geometry.Point3D;
import moteur.Enums;
import moteur.Parcelle;
import moteur.Partie;
import moteur.Plateau;
import moteur.objectifs.ObjectifJardinier;
import moteur.objectifs.ObjectifPanda;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BotPandaTest {

    Partie partie=new Partie();
    Plateau plateau =partie.getPlateau();
    Parcelle parcelleJaune = new Parcelle(Enums.TypeParcelle.JAUNE);
    BotPanda botPanda = new BotPanda(Enums.CouleurBot.ROUGE);

    @Test
    public void joueurDeplaceJardinierZeroParcelleCouleurVoulu() {
        plateau.poser(new Parcelle(Enums.TypeParcelle.VERTE),new Point3D(0,1,-1));
        plateau.poser(new Parcelle(Enums.TypeParcelle.VERTE),new Point3D(1,0,-1));
        botPanda.addObjectif(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));
        botPanda.addObjectif(new ObjectifPanda(4, Enums.TypeParcelle.VERTE,2));

        botPanda.joueurDeplaceJardinier(partie.getJardinier());

        assertEquals(partie.getJardinier().getCoord(),new Point3D(0,1,-1));
    }

    @Test
    public void joueurDeplaceJardinierUneParcelleCouleurVoulu(){
        plateau.poser(new Parcelle(Enums.TypeParcelle.JAUNE),new Point3D(0,1,-1));
        plateau.poser(new Parcelle(Enums.TypeParcelle.VERTE),new Point3D(1,0,-1));
        botPanda.addObjectif(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));
        botPanda.addObjectif(new ObjectifPanda(4, Enums.TypeParcelle.VERTE,2));

        botPanda.joueurDeplaceJardinier(partie.getJardinier());

        assertEquals(partie.getJardinier().getCoord(),new Point3D(1,0,-1));
    }

    @Test
    public void joueurDeplaceJardinierDeuxParcellesCouleurVoulu(){
        plateau.poser(new Parcelle(Enums.TypeParcelle.VERTE),new Point3D(0,1,-1));
        plateau.poser(new Parcelle(Enums.TypeParcelle.JAUNE),new Point3D(1,0,-1));
        plateau.poser(new Parcelle(Enums.TypeParcelle.VERTE),new Point3D(1,-1,0));
        botPanda.addObjectif(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));
        botPanda.addObjectif(new ObjectifPanda(4, Enums.TypeParcelle.VERTE,2));

        botPanda.joueurDeplaceJardinier(partie.getJardinier());

        assertEquals(partie.getJardinier().getCoord(),new Point3D(0,1,-1));

    }


    @Test
    public void joueurDeplacePandaZeroParcelleCouleurVoulu() {
        plateau.poser(new Parcelle(Enums.TypeParcelle.VERTE),new Point3D(0,1,-1));
        plateau.poser(new Parcelle(Enums.TypeParcelle.VERTE),new Point3D(1,0,-1));
        botPanda.addObjectif(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));
        botPanda.addObjectif(new ObjectifPanda(4, Enums.TypeParcelle.VERTE,2));

        botPanda.joueurDeplacePanda(partie.getPanda());

        assertEquals(partie.getPanda().getCoord(),new Point3D(0,1,-1));
    }

    @Test
    public void joueurDeplacePandaUneParcelleCouleurVoulu(){
        plateau.poser(new Parcelle(Enums.TypeParcelle.JAUNE),new Point3D(0,1,-1));
        plateau.poser(new Parcelle(Enums.TypeParcelle.VERTE),new Point3D(1,0,-1));
        botPanda.addObjectif(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));
        botPanda.addObjectif(new ObjectifPanda(4, Enums.TypeParcelle.VERTE,2));

        botPanda.joueurDeplacePanda(partie.getPanda());

        assertEquals(partie.getPanda().getCoord(),new Point3D(1,0,-1));
    }

    @Test
    public void joueurDeplacePandaDeuxParcellesCouleurVoulu(){
        plateau.poser(new Parcelle(Enums.TypeParcelle.VERTE),new Point3D(0,1,-1));
        plateau.poser(new Parcelle(Enums.TypeParcelle.JAUNE),new Point3D(1,0,-1));
        plateau.poser(new Parcelle(Enums.TypeParcelle.VERTE),new Point3D(1,-1,0));
        botPanda.addObjectif(new ObjectifJardinier(6, Enums.TypeParcelle.JAUNE,4));
        botPanda.addObjectif(new ObjectifPanda(4, Enums.TypeParcelle.VERTE,2));

        botPanda.joueurDeplacePanda(partie.getPanda());

        assertEquals(partie.getPanda().getCoord(),new Point3D(0,1,-1));

    }


}