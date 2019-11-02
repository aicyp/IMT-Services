package modele;

import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.HyperLiens;
import configuration.JAXRS;

public interface Bibliotheque {
	
	@PUT
	@Path("")
	@Consumes(MediaType.APPLICATION_XML)
	Optional<HyperLien<Livre>> chercher(Livre l);

	@GET
	@Path(JAXRS.SOUSCHEMIN_CATALOGUE)
	@Consumes(MediaType.APPLICATION_XML)
	HyperLiens<Livre> repertorier();
	
}
