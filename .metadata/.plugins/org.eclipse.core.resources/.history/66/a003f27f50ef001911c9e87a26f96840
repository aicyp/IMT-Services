package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface Automate {
	
	@POST
	@Path("etat/initial")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Session initier();
	
	@POST
	@Path("etat/suivant/{lettre}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Resultat accepter(@PathParam("lettre") char x, Session id); 
	
	@PUT
	@Path("fin")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void clore(Session id);
}

