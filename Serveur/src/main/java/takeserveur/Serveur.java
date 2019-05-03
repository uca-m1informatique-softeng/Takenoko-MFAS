package takeserveur;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * C'est la classe du serveur
 */
@RestController
public class Serveur {


    private int nbClient;

    private int nbClientReady;



    private int portServeur;
    private String hostServeur;

    //////////////////////////////GETTER et SETTER//////////////////////////////

    public int getNbClient() {
        return nbClient;
    }

    public void setNbClient(int nbClient) {
        this.nbClient = nbClient;
    }

    public int getNbClientReady() {
        return nbClientReady;
    }

    public void setNbClientReady(int nbClientReady) {
        this.nbClientReady = nbClientReady;
    }

    public int getPort(){
        return portServeur;
    }

    public String getHost() {
        return  hostServeur;
    }

    public void setPortServeur(int portServeur) {
        this.portServeur = portServeur;
    }

    public void setHostServeur(String hostServeur) {
        this.hostServeur = hostServeur;
    }

    /**
     * Le constructeur
     * @param portServeur
     * @param hostServeur
     * @param nbClient
     */
    public Serveur(@Qualifier("portServeur") int portServeur, @Qualifier("hostServeur") String hostServeur, @Qualifier("client") int nbClient ){
        this.portServeur = portServeur;
        this.hostServeur = hostServeur;
        this.nbClient = nbClient;
        this.nbClientReady = 0;
    }



    public boolean isPartiePrete(){
        if(nbClientReady >= nbClient) {return true;}
        return false;
    }
}
