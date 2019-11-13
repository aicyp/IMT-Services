package infrastructure.jaxrs;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.Priority;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.Priorities;

import infrastructure.jaxrs.annotations.ReponsesPUTOption;

@Provider
@ReponsesPUTOption
@Priority(Priorities.HEADER_DECORATOR)
public class AdapterServeurReponsesPUTOptionEn404OuValeur implements ReaderInterceptor, ContainerResponseFilter {

	public AdapterServeurReponsesPUTOptionEn404OuValeur() {
		System.out.println("** Initialisation du filtre de type " + this.getClass());
	}
	private static String DESCRIPTION_LIVRE = "descriptionLivre";
	
	private void convertirEn404(ContainerRequestContext requete, ContainerResponseContext reponse) {
		System.out.println("recherche : 404 NOT FOUND !");
		String contenu = requete.getUriInfo().getRequestUri().toString();
		// Utilisation de la propriété pour récupérer la description du livre recherché.
		contenu = contenu + " - " + requete.getProperty(DESCRIPTION_LIVRE);
		reponse.setEntity(contenu, null, MediaType.TEXT_PLAIN_TYPE);
		reponse.setStatus(Response.Status.NOT_FOUND.getStatusCode());
	}

	@Override
	public void filter(ContainerRequestContext contexteRequete, ContainerResponseContext contexteReponse)
			throws IOException {
		Object obj = contexteReponse.getEntity();
		if (obj instanceof Optional<?>) {
			Optional<?> option = (Optional<?>) obj;
			if (!option.isPresent()) {
				convertirEn404(contexteRequete, contexteReponse);
			} else {
				contexteReponse.setEntity(option.get());
			}
		}
	}

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext contexteRequete) throws IOException, WebApplicationException {
		System.out.println("recherche : interception !");
		Object descriptionLivre = contexteRequete.proceed();
		contexteRequete.setProperty(DESCRIPTION_LIVRE, descriptionLivre);
		return descriptionLivre;
	}

}
