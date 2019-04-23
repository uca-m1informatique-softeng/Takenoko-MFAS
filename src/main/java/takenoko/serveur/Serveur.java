package takenoko.serveur;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
public class Serveur {

    // nombre de joueur pour commencer une partie
    private int nbClient;
    // nombre de client pret à jouer
    private int nbClientReady;

    private int portServeur;
    private String hostServeur;

    public Serveur(@Qualifier("portServeur") int portServeur, @Qualifier("hostServeur") String hostServeur, @Qualifier("client") int nbClient ){
        this.portServeur = portServeur;
        this.hostServeur = hostServeur;
        this.nbClient = nbClient;
        this.nbClientReady = 0;
    }

    public int getPort(){
        return portServeur;
    }

    public String getHost() {return  hostServeur;}

    public boolean isPartiePrete(){
        if(nbClientReady >= nbClient) {return true;}
        return false;
    }
}
