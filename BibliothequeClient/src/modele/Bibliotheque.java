package modele;
import static configuration.JAXRS.SOUSCHEMIN_CATALOGUE;
import static configuration.JAXRS.TYPE_MEDIA;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.HyperLiens;

public interface Bibliotheque {

	@PUT
	@Consumes(TYPE_MEDIA)
	@Produces(TYPE_MEDIA)
	Optional<HyperLien<Livre>> chercher(Livre l);
	
	@GET
	@Produces(TYPE_MEDIA)
	@Path(SOUSCHEMIN_CATALOGUE)
	HyperLiens<Livre> repertorier();
	
}
