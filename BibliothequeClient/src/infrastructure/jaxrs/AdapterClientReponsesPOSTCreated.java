package infrastructure.jaxrs;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

@Provider
@Priority(Priorities.HEADER_DECORATOR)
public class AdapterClientReponsesPOSTCreated implements ClientResponseFilter, ReaderInterceptor {

	private static String HYPERLIEN = "Hyperlien";
	
	public AdapterClientReponsesPOSTCreated() {
		System.out.println("** Initialisation du filtre de type "
				+ this.getClass());
	}
	
	@Override
	public Object aroundReadFrom(ReaderInterceptorContext reponse)
			throws IOException, WebApplicationException {
		if((Boolean)reponse.getProperty(HYPERLIEN)){
			return new HyperLien<Object>(reponse.getHeaders().getFirst("Location"));
		}
		return reponse.proceed();
	}
	
	@Override
	public void filter(ClientRequestContext requete, ClientResponseContext reponse)
			throws IOException {
		requete.setProperty(HYPERLIEN, false);
		if(reponse.getStatus() == Response.Status.CREATED.getStatusCode()){
			requete.setProperty(HYPERLIEN, true);
		}
	}

}
