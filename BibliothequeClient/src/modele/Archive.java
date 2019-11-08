package modele;

import static configuration.JAXRS.CHEMIN_BIBLIO;
import static configuration.JAXRS.TYPE_MEDIA;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import infrastructure.jaxrs.HyperLien;

@Path(CHEMIN_BIBLIO)
public interface Archive {

	Livre sousRessource(IdentifiantLivre id) ;
	
	@GET
	@Path("{id}")
	@Produces(TYPE_MEDIA)
	Livre getRepresentation(IdentifiantLivre id);
	
	@POST
	@Consumes(TYPE_MEDIA)
	@Produces(TYPE_MEDIA)
	HyperLien<Livre> ajouter(Livre l);
}
