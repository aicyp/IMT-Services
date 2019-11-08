package modele;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import configuration.JAXRS;
import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.annotations.ReponsesGETNull404;
import infrastructure.jaxrs.annotations.ReponsesPOSTCreated;

@Path(JAXRS.CHEMIN_BIBLIO)
public interface Archive {

	@Path("{id}")
	@Produces(JAXRS.TYPE_MEDIA)
	Livre sousRessource(@PathParam("id") IdentifiantLivre id) ;
	
	@GET
	@Path("{id}")
	@Produces(JAXRS.TYPE_MEDIA)
	@ReponsesGETNull404
	Livre getRepresentation(@PathParam("id") IdentifiantLivre id);
	
	@POST
	@Produces(JAXRS.TYPE_MEDIA)
	@Consumes(JAXRS.TYPE_MEDIA)
	@ReponsesPOSTCreated
	HyperLien<Livre> ajouter(Livre l);
}
