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
    @Path("/Parcelle")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String Par() {
        stock.SetStock(deck.piocherParcelle(),plateau.emplacementsAutorise());
        return "1 - "+stock.StockParcelle.get(0).getType().toString()+"\n"+"2 - "+stock.StockParcelle.get(1).getType().toString()+"\n"+"3 - "+stock.StockParcelle.get(2).getType().toString()+"\n \n"+
                "1 - "+stock.StockPoint.get(0).toString()+"\n"+"2 - "+stock.StockPoint.get(1).toString()+"\n"+"3 - "+stock.StockPoint.get(2).toString() ;
    }


    @Path("/Poser/{parcelle}/{position}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String Poser(@PathParam("parcelle") int parcelle, @PathParam("position")int position){
        plateau.poser(stock.StockParcelle.get(parcelle-1),stock.StockPoint.get(position-1));
        return "Parcelle posee avec succes";
    }
}
