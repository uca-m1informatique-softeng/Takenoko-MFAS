package takenoko.moteur;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")

public class Serveur {
    Plateau plateau = Plateau.getInstance();
    Deck deck = Deck.getInstance();
    Stock stock = Stock.getInstance();

    @GET
    @Produces("text/plain")
    public String getMessage() {
        this.deck.initialiserDeckParcelle();
        this.plateau.resetPlateau();
        return "Bienvenue";
    }
}
