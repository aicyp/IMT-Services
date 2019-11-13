package infrastructure.jaxrs;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

@Priority(Priorities.HEADER_DECORATOR)
public class AdapterClientReponsesPUT404EnOption implements ClientRequestFilter, ClientResponseFilter, ReaderInterceptor {


	public AdapterClientReponsesPUT404EnOption() {
		System.out.println("** Initialisation du filtre de type " + this.getClass());
	}

	private static final String METHODE_PUT = "HTTP_PUT";

	private static final String ERREUR404 = "ERREUR404";

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext reponse) throws IOException, WebApplicationException {
		if((Boolean)reponse.getProperty(ERREUR404)) {
			if (reponse.getType().equals(Optional.class)) {
				System.out.println("Erreur 404 en option vide.");
				return Optional.empty();
			}
			// possibilité d'autres traitements
		}
		return reponse.proceed();
	}

	@Override
	public void filter(ClientRequestContext requete) throws IOException {
		if(requete.getMethod().equals("PUT")) {
			System.out.println("Méthode http PUT (recherche).");
			requete.setProperty(METHODE_PUT, true);
		}else{
			System.out.println("Méthode http différente de PUT (non recherche).");
			requete.setProperty(METHODE_PUT, false);
		}
		
	}
	
	@Override
	public void filter(ClientRequestContext requete, ClientResponseContext reponse) throws IOException {
		requete.setProperty(ERREUR404, false);
		//String chemin = requete.getUri().getPath();
		//if (chemin.equals(JAXRS.CHEMIN)
		if((Boolean)requete.getProperty(METHODE_PUT) && (reponse.getStatus() == Response.Status.NOT_FOUND.getStatusCode())) {
			System.out.println("Recherche : erreur 404 !");
			requete.setProperty(ERREUR404, true);
			reponse.setStatusInfo(Response.Status.OK);
		}
	}


}
