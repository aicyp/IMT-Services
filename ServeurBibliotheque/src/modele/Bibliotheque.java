package modele;

import java.util.Optional;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.HyperLiens;

public interface Bibliotheque {
		
	@PUT
	@Path("")
	@Produces(MediaType.APPLICATION_XML)
	Optional<HyperLien<Livre>> chercher(Livre l);
	
	@Path("catalogue")
	HyperLiens<Livre> repertorier();
	
}
