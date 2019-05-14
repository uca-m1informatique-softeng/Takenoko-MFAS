package takeclient;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * C'est la classe de configuration du client
 */
@Configuration
@ComponentScan({"takenoko.moteur","takenoko.joueur"})
public class ConfigurationClient implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Bean("portClient")
    public int portClient(){
        return 8088;
    }

    @Bean("hostClient")
    public String hostClient(){
        return "localhost";
    }

    @Bean("portServeur")
    public int serveur(){
        return 8080;
    }

    @Bean("hostServeur")
    public String hostServeur(){
        return "localhost";
    }

    @Override
    public void customize(ConfigurableServletWebServerFactory serveur) {
        serveur.setPort(portClient());
    }

}
