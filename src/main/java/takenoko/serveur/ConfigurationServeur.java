package takenoko.serveur;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import takenoko.joueur.Bot;
import java.util.ArrayList;

@Configuration
public class ConfigurationServeur{

    @Bean("portServeur")
    public int portServeur(){
        return 8080;
    }

    @Bean("hostServeur")
    public String hostServeur(){
        return "localhost";
    }

    @Bean("client")
    public int client(){
        return 1;
    }

    @Bean("joueurs")
    public ArrayList<Bot> joueurs(){
        ArrayList<Bot> joueurs = new ArrayList<>();
        for(int i = 0; i < client(); i++){
            joueurs.add(new Bot());
        }
        return joueurs;
    }
}

