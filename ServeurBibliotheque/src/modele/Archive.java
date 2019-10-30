package modele;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import infrastructure.jaxrs.HyperLien;

public interface Archive {

	Livre sousRessource(IdentifiantLivre id) ;
	
	@GET
	Livre getRepresentation(IdentifiantLivre id);
	
	@PUT
	HyperLien<Livre> ajouter(Livre l);
}
