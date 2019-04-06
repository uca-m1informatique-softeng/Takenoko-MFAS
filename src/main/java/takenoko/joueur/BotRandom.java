package takenoko.joueur;

import takenoko.moteur.Enums;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

/**
 * La classe du bot Random(pour le moment elle n'est plus utiliser)
 */
@Component
@Scope("prototype")
public class BotRandom extends Bot{

    //////////////////////////////Méthodes//////////////////////////////

    /**
     * Les differents choix du Botrandom.
     * @param possibilites
     * @return
     */
    @Override
    public Enums.Action choixTypeAction(ArrayList<Enums.Action> possibilites) {
        int random = new Random().nextInt(2);
        if(possibilites.contains(Enums.Action.PIOCHEROBJECTIFJARDINIER)){
            return Enums.Action.PIOCHEROBJECTIFJARDINIER;
        }
        if(possibilites.contains(Enums.Action.PIOCHEROBJECTIFPANDA)){
            return Enums.Action.PIOCHEROBJECTIFPANDA;
        }
        if(possibilites.contains(Enums.Action.PIOCHEROBJECTIFPARCELLE)){
            return Enums.Action.PIOCHEROBJECTIFPARCELLE;
        }
        if(possibilites.contains(Enums.Action.PIOCHERPARCELLE)&&random==0){
            return Enums.Action.PIOCHERPARCELLE;
        }
        if(possibilites.contains(Enums.Action.POSERIRRIGATION)&&random==1){
            return Enums.Action.POSERIRRIGATION;
        }
        if(possibilites.contains(Enums.Action.PIOCHERPARCELLE)){
            return Enums.Action.PIOCHERPARCELLE;
        }
        if(possibilites.contains(Enums.Action.POSERIRRIGATION)){
            return Enums.Action.POSERIRRIGATION;
        }
        if(possibilites.contains(Enums.Action.DEPLACERJARDINIER)){
            return Enums.Action.DEPLACERJARDINIER;
        }
        if(possibilites.contains(Enums.Action.DEPLACERPANDA)){
            return Enums.Action.DEPLACERPANDA;
        }
        return null;
    }

}
