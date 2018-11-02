package joueur;

import javafx.geometry.Point3D;
import moteur.Enums;
import moteur.Partie;
import moteur.Plateau;
import org.junit.Test;

import static org.junit.Assert.*;

public class BotRandomTest {

    Partie partie=new Partie();
    Plateau plateau =partie.getPlateau();
    Joueur joueur = new Joueur(Enums.CouleurBot.ROUGE);


}