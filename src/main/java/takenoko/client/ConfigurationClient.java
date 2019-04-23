package takenoko.client;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * C'est la classe de configuration du client
 */
@Configuration
public class ConfigurationClient implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Bean("CLIENT_PORT")
    public int clientPort(){
        return 8088;
    }

    @Bean("CLIENT_HOST")
    public String clientHost(){
        return "localhost";
    }

    @Bean("SERVER_PORT")
    public int serverPort(){
        return 8080;
    }

    @Bean("SERVER_HOST")
    public String serverHost(){
        return "localhost";
    }

    @Override
    public void customize(ConfigurableServletWebServerFactory server) {
        server.setPort(clientPort());
    }

}
