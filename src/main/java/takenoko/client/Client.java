package takenoko.client;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


/**
 * C'est la classe du Client
 */
@RestController
public class Client {

    private int identifiant;

    private int client;

    private RestTemplate serveur;

    private String SERVEUR_HTTP;

    private String CLIENT_HTTP;

    private int portClient;
    private String hostClient;



    private int portServeur;
    private String hostServeur;

    //////////////////////////////GETTER et SETTER//////////////////////////////

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public RestTemplate getServeur() {
        return serveur;
    }

    public void setServeur(RestTemplate serveur) {
        this.serveur = serveur;
    }

    public String getSERVEUR_HTTP() {
        return SERVEUR_HTTP;
    }

    public void setSERVEUR_HTTP(String SERVEUR_HTTP) {
        this.SERVEUR_HTTP = SERVEUR_HTTP;
    }

    public String getCLIENT_HTTP() {
        return CLIENT_HTTP;
    }

    public void setCLIENT_HTTP(String CLIENT_HTTP) {
        this.CLIENT_HTTP = CLIENT_HTTP;
    }

    public int getPortClient() {
        return portClient;
    }

    public void setPortClient(int portClient) {
        this.portClient = portClient;
    }

    public String getHostClient() {
        return hostClient;
    }

    public void setHostClient(String hostClient) {
        this.hostClient = hostClient;
    }

    public int getPortServeur() {
        return portServeur;
    }

    public void setPortServeur(int portServeur) {
        this.portServeur = portServeur;
    }

    public String getHostServeur() {
        return hostServeur;
    }

    public void setHostServeur(String hostServeur) {
        this.hostServeur = hostServeur;
    }


    //////////////////////////////Méthodes//////////////////////////////


    /**
     * Le constructeur
     * @param portServeur
     * @param hostServeur
     * @param portClient
     * @param hostClient
     */
    public Client(@Qualifier("portServeur") int portServeur, @Qualifier("hostServeur") String hostServeur , @Qualifier("portClient") int portClient, @Qualifier("hostClient") String hostClient){
        this.serveur = new RestTemplate();


        this.hostServeur = hostServeur;
        this.portServeur = portServeur;

        this.hostClient = hostClient;
        this.portClient = portClient;

        this.SERVEUR_HTTP = "http://" + hostServeur + ":" + portServeur;
        this.CLIENT_HTTP = "http://" + hostClient + ":" + portClient;
    }





}
