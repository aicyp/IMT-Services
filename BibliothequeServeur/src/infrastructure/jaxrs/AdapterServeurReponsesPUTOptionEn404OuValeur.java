package infrastructure.jaxrs;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

import infrastructure.jaxrs.annotations.ReponsesPUTOption;

@Provider
@ReponsesPUTOption
@Priority(Priorities.HEADER_DECORATOR)
public class AdapterServeurReponsesPUTOptionEn404OuValeur implements ContainerResponseFilter, ReaderInterceptor {
	public AdapterServeurReponsesPUTOptionEn404OuValeur() {
		System.out.println("** Initialisation du filtre de type "
				+ this.getClass());
	}
	
	private static String DESCRIPTION_LIVRE = "descriptionLivre";
	
	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		Object sourceObject = context.proceed();
		context.setProperty("Description", sourceObject);
		return context.getProperty("Description");
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		if (requestContext.getMethod().equalsIgnoreCase("PUT") 
				&& (responseContext.getStatus() == Response.Status.OK.getStatusCode())
				&& (responseContext.getEntity() instanceof Optional<?>)) {
			Optional<?> entite = (Optional<?>) responseContext.getEntity();
			if (!entite.isPresent()) {
				convertirEn404(requestContext, responseContext);
				return;
			} else {
				responseContext.setEntity(entite.get());
			}
		}
		
	}
	
	private void convertirEn404(ContainerRequestContext requete, ContainerResponseContext reponse) {
		System.out.println("recherche : 404 NOT FOUND !");
		String contenu = requete.getUriInfo().getRequestUri().toString();
		// Utilisation de la propriété pour récupérer la description du livre recherché.
		contenu = contenu + " - " + requete.getProperty(DESCRIPTION_LIVRE);
		reponse.setEntity(contenu, null, MediaType.TEXT_PLAIN_TYPE);
		reponse.setStatus(Response.Status.NOT_FOUND.getStatusCode());
	}

}
