package infrastructure.jaxrs;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

import infrastructure.jaxrs.annotations.ReponsesPUTOption;
import modele.ImplemLivre;
import modele.Livre;

@Provider
@ReponsesPUTOption
@Priority(Priorities.HEADER_DECORATOR)
public class AdapterServeurReponsesPUTOptionEn404OuValeur implements ContainerResponseFilter, ReaderInterceptor {
	public AdapterServeurReponsesPUTOptionEn404OuValeur() {
		System.out.println("** Initialisation du filtre de type "
				+ this.getClass());
	}
	
	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		Object sourceObject = context.proceed();
		return null;
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

}
