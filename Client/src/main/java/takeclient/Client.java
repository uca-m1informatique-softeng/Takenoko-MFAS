package takeclient;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * C'est la classe du Client
 */
@RestController
public class Client {

    int client;

    private int identifiant;

    private String nomClient;

    private RestTemplate serveur;

    private String serveurHTTP;

    private String clientHTTP;

    private int portClient;
    private String hostClient;

    private int portServeur;
    private String hostServeur;

    @Autowired
    private JeuClient jeuClient;

    //////////////////////////////GETTER et SETTER//////////////////////////////

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
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
        this.nomClient = "test";

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

    public JSONObject getDeck() throws Exception{
        return new JSONObject(serveur.getForObject(serveurHTTP + "/deck", String.class));
    }

    public JSONObject getPlateau() throws Exception{
        return new JSONObject(serveur.getForObject(serveurHTTP + "/plateau", String.class));
    }

    public JSONObject getJardinier() throws Exception{
        return new JSONObject(serveur.getForObject(serveurHTTP + "/jardinier", String.class));
    }

    public JSONObject getPanda() throws Exception{
        return new JSONObject(serveur.getForObject(serveurHTTP + "/panda", String.class));
    }


    @RequestMapping("/debut")
    public int Debut() throws Exception{
        return jeuClient.init();
    }

    @RequestMapping("/piocher")
    public boolean pio_poser() throws Exception{
        jeuClient.piocher_poser();
        return true;
    }

    @RequestMapping("/partie")
    public boolean partie() throws Exception{
        jeuClient.par();
        return true;
    }



    public int getClient() {
        return client;
    }
}
