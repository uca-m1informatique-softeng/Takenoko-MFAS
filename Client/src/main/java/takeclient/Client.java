package takeclient;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


/**
 * C'est la classe du Client
 */
@RestController
public class Client {

    private int identifiant;



    private int nomClient;

    private RestTemplate serveur;


    private String serveurHTTP;

    private String clientHTTP;

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

    public int getNomClient() {
        return nomClient;
    }

    public void setNomClient(int nomClient) {
        this.nomClient = nomClient;
    }

    public String getServeurHTTP() {
        return serveurHTTP;
    }

    public void setServeurHTTP(String serveurHTTP) {
        this.serveurHTTP = serveurHTTP;
    }

    public String getClientHTTP() {
        return clientHTTP;
    }

    public void setClientHTTP(String clientHTTP) {
        this.clientHTTP = clientHTTP;
    }


    public RestTemplate getServeur() {
        return serveur;
    }

    public void setServeur(RestTemplate serveur) {
        this.serveur = serveur;
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

        this.serveurHTTP = "http://" + hostServeur + ":" + portServeur;
        this.clientHTTP = "http://" + hostClient + ":" + portClient;
    }

    public boolean connect(){
        identifiant  =serveur.getForObject(serveurHTTP + "/nouvelle-connexion" , Integer.class);
        if(this.identifiant < 0)
            return false;
        return true;
    }





}
