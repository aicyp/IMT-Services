package infrastructure.jaxrs;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.proxy.WebResourceFactory;

import configuration.JAXRS;

public class ClientRessource {

	public static <T> T proxy(HyperLien<T> lien, Class<T> type) {
		WebTarget cible = JAXRS.client().target(lien.getUri());
		return WebResourceFactory.newResource(type, cible);
	}
	
	public static <T> T representation(HyperLien<T> lien, Class<T> type, MediaType typeContenu) {
		WebTarget cible = JAXRS.client().target(lien.getUri());
		return cible.request(typeContenu).get().readEntity(type); 
	}

}
