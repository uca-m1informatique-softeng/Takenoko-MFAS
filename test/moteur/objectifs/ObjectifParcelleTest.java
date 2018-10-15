package moteur.objectifs;

import javafx.geometry.Point3D;
import joueur.Bot;
import moteur.Enums;
import moteur.Parcelle;
import moteur.Partie;
import moteur.Plateau;
import moteur.personnages.Panda;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectifParcelleTest {
    Partie partie=new Partie();
    Plateau plateau = partie.getPlateau();
    Bot joueur = new Bot(Enums.CouleurBot.ROUGE);
    Parcelle parcelleRose = new Parcelle(Enums.TypeParcelle.ROSE);

    @Test
    public void validation() throws Exception {

        //////////  Objectif Motif Courbé

        ObjectifParcelle ob = new ObjectifParcelle(4, Enums.TypeParcelle.ROSE,1);
        joueur.addObjectif(ob);

        plateau.poser(new Parcelle(Enums.TypeParcelle.ROSE),new Point3D(0,1,-1));
        plateau.poser(new Parcelle(Enums.TypeParcelle.ROSE),new Point3D(1,0,-1));

        assertFalse(ob.validation(partie,joueur));

        plateau.poser(new Parcelle(Enums.TypeParcelle.ROSE),new Point3D(1,-1,0));

        assertTrue(ob.validation(partie,joueur));

    }

    @Test
    public void validation2() throws Exception {
        ///////////  Objectif Motif Droit

        ObjectifParcelle ob2 = new ObjectifParcelle(4, Enums.TypeParcelle.ROSE,0);
        joueur.addObjectif(ob2);


        plateau.poser(new Parcelle(Enums.TypeParcelle.ROSE),new Point3D(-1,1,0));
        plateau.poser(new Parcelle(Enums.TypeParcelle.ROSE),new Point3D(0,1,-1));

        assertFalse(ob2.validation(partie,joueur));

        plateau.poser(new Parcelle(Enums.TypeParcelle.ROSE),new Point3D(1,1,-2));

        assertTrue(ob2.validation(partie,joueur));


    }

    @Test
    public void validation3() throws Exception {

        ////////// Objectif Motif Triangle

        ObjectifParcelle ob3 = new ObjectifParcelle(4, Enums.TypeParcelle.ROSE,2);
        joueur.addObjectif(ob3);

        plateau.poser(new Parcelle(Enums.TypeParcelle.ROSE),new Point3D(0,1,-1));
        plateau.poser(new Parcelle(Enums.TypeParcelle.ROSE),new Point3D(1,0,-1));

        assertFalse(ob3.validation(partie,joueur));

        plateau.poser(new Parcelle(Enums.TypeParcelle.ROSE),new Point3D(1,1,-2));

        assertTrue(ob3.validation(partie,joueur));


    }

    @Test
    public void validation4() throws Exception {
        /////// Quatre

        ObjectifParcelle ob4 = new ObjectifParcelle(5, Enums.TypeParcelle.ROSE,3);
        joueur.addObjectif(ob4);

        plateau.poser(new Parcelle(Enums.TypeParcelle.ROSE),new Point3D(0,1,-1));
        plateau.poser(new Parcelle(Enums.TypeParcelle.ROSE),new Point3D(1,0,-1));
        plateau.poser(new Parcelle(Enums.TypeParcelle.ROSE),new Point3D(1,1,-2));

        assertFalse(ob4.validation(partie,joueur));

        plateau.poser(new Parcelle(Enums.TypeParcelle.ROSE),new Point3D(0,2,-2));

        assertTrue(ob4.validation(partie,joueur));

    }

}