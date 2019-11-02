package modele;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.annotations.ReponsesGETNull404;
import infrastructure.jaxrs.annotations.ReponsesPOSTCreated;

public interface Archive {

	Livre sousRessource(IdentifiantLivre id) ;
	
	@GET
	@Path("id")
	@Consumes(MediaType.APPLICATION_XML)
	@ReponsesGETNull404
	Livre getRepresentation(IdentifiantLivre id);
	
	@POST
	@Path("")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	@ReponsesPOSTCreated
	HyperLien<Livre> ajouter(Livre l);
}
