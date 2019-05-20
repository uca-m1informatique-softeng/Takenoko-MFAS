package takeserveur;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import takenoko.joueur.Bot;

/**
 * C'est la classe de configuration du Serveur
 */
@Configuration
@ComponentScan({"takenoko.moteur","takenoko.joueur"})
public class ConfigurationServeur{

    /**
     * Le port du serveur
     * @return
     */
    @Bean("portServeur")
    public int portServeur(){
        return 8080;
    }

    /**
     * L'hôte du serveur
     * @return
     */
    @Bean("hostServeur")
    public String hostServeur(){
        return "localhost";
    }

    /**
     * Le client
     * @return
     */
    @Bean("client")
    public int client(){
        return 1;
    }

    /**
     * Les joueurs
     * @return
     */
    @Bean("joueurs")
    public ArrayList<Bot> joueurs(){
        ArrayList<Bot> joueurs = new ArrayList<>();
        for(int i = 0; i < client(); i++){
            joueurs.add(new Bot());
        }
        return joueurs;
    }
}

