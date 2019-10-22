package infrastructure.jaxrs;

import java.io.IOException;

import infrastructure.jaxrs.annotations.ReponsesGETNull404;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@ReponsesGETNull404
@Priority(Priorities.HEADER_DECORATOR)
public class AdapterServeurReponsesGETNullEn404 implements ContainerResponseFilter {
	public AdapterServeurReponsesGETNullEn404() {
		System.out.println("** Initialisation du filtre de type "
				+ this.getClass());
	}

	@Override
	public void filter(ContainerRequestContext requete,
			ContainerResponseContext reponse) throws IOException {
		// Cas GET
		if (requete.getMethod().equalsIgnoreCase("GET")) {
			if(reponse.getStatus() == Response.Status.NO_CONTENT.getStatusCode()){
				System.out.println("404 NOT FOUND !");
				reponse.setStatus(Response.Status.NOT_FOUND.getStatusCode());
				reponse.setEntity(requete.getUriInfo().getRequestUri().toString(), null, MediaType.TEXT_PLAIN_TYPE);
				return;
			}
			if(reponse.getStatus() == Response.Status.NOT_FOUND.getStatusCode()){
				System.out.println("404 NOT FOUND !");
				reponse.setEntity(requete.getUriInfo().getRequestUri().toString(), null, MediaType.TEXT_PLAIN_TYPE);
				return;
			}
		}
	}

}
