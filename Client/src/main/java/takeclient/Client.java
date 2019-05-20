package takeclient;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * C'est la classe du Client
 */
@RestController
public class Client {

    int client;
    private int identifiant;
    private int bot;

    private boolean type;

    private String nomClient;

    private RestTemplate serveur;

    private String serveurHTTP;

    private String clientHTTP;

    private int portClient;
    private String hostClient;

    private int portServeur;
    private String hostServeur;


    //////////////////////////////GETTER et SETTER//////////////////////////////

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public int getBot() {
        return bot;
    }

    public int setBot(int i) {
        return this.bot = i;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public RestTemplate getServeur() {
        return serveur;
    }

    public void setServeur(RestTemplate serveur) {
        this.serveur = serveur;
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
    public Client(@Qualifier("portServeur") int portServeur, @Qualifier("hostServeur") String hostServeur, @Qualifier("portClient") int portClient, @Qualifier("hostClient") String hostClient) {
        this.serveur = new RestTemplate();
        this.nomClient = "test";

        this.hostServeur = hostServeur;
        this.portServeur = portServeur;

        this.type = false;
        this.bot = 4;

        this.hostClient = hostClient;
        this.portClient = portClient;

        this.serveurHTTP = "http://" + hostServeur + ":" + portServeur;
        this.clientHTTP = "http://" + hostClient + ":" + portClient;
    }


    /**
     * La connexion du client
     * @return
     */
    public boolean connect() {
        identifiant = serveur.getForObject(serveurHTTP + "/nouvelle-connexion", Integer.class);
        if (this.identifiant < 0)
            return false;
        return true;
    }

    /**
     *
     * @return
     */
    public boolean envoie() {
        int n = serveur.getForObject(serveurHTTP + "/type_bot", Integer.class);
        if (n < 0) {
            return false;
        }
        return true;
    }


    /**
     * @return
     * @throws Exception
     */
    public String getScore() throws Exception {
        return serveur.getForObject(serveurHTTP + "/partie", String.class);
    }


    /**
     * Le bot panda
     * @return
     */
    @RequestMapping("/panda")
    public Integer panda() {
        setBot(1);
        return bot;
    }

    /**
     * Le bot jardinier
     * @return
     */
    @RequestMapping("/jardinier")
    public Integer jardinier() {
        setBot(2);
        return bot;
    }

    /**
     * Le bot parcelle
     * @return
     */
    @RequestMapping("/parcelle")
    public Integer parcelle() {
        setBot(3);
        return bot;
    }

    /**
     * Le bot random
     * @return
     */
    @RequestMapping("/random")
    public Integer random() {
        setBot(4);
        return bot;
    }


    /**
     * @return
     */
    @RequestMapping("/type")
    public String type() {
        this.type = true;
        return bot + "";
    }

    /**
     * Renvoie le score
     * @return
     * @throws Exception
     */
    @RequestMapping("/mon_score")
    public String score() throws Exception {
        return getScore();
    }

}



