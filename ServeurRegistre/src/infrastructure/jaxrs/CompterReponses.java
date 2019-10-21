package infrastructure.jaxrs;

import java.util.concurrent.atomic.AtomicInteger;
import infrastructure.jaxrs.annotations.StatRequetes;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@StatRequetes
@Priority(Priorities.AUTHORIZATION)
public class CompterReponses implements ContainerResponseFilter {

	private AtomicInteger out = new AtomicInteger(0);

	private AtomicInteger repOK200GET = new AtomicInteger(0);
	private AtomicInteger rep0K200PUT = new AtomicInteger(0);
	private AtomicInteger repNotModified304 = new AtomicInteger(0);
	private AtomicInteger repPreconditionFailed412 = new AtomicInteger(0);
	private AtomicInteger repPreconditionRequired428GET = new AtomicInteger(0);
	private AtomicInteger repPreconditionRequired428PUT = new AtomicInteger(0);

	public CompterReponses() {
		System.out.println("* Initialisation du filtre " + this + " : " + this.getClass());
	}

	@Override
	public void filter(ContainerRequestContext requete,
			ContainerResponseContext reponse) throws IOException {
		out.incrementAndGet();
		int statut = reponse.getStatus();
		if (statut == Response.Status.OK.getStatusCode()) {
			if(requete.getMethod().equalsIgnoreCase("GET")){
				repOK200GET.incrementAndGet();				
			}
			if(requete.getMethod().equalsIgnoreCase("PUT")){
				rep0K200PUT.incrementAndGet();
			}
		}
		if (statut == Response.Status.NOT_MODIFIED.getStatusCode()) {
			repNotModified304.incrementAndGet();
		}
		if (statut == Response.Status.PRECONDITION_FAILED.getStatusCode()) {
			repPreconditionFailed412.incrementAndGet();
		}
		
		if (statut == StatutRFC6585.PRECONDITION_REQUIRED.getCodeStatut()) {
			if(requete.getMethod().equalsIgnoreCase("GET")){
				repPreconditionRequired428GET.incrementAndGet();
			}
			if(requete.getMethod().equalsIgnoreCase("PUT")){
				repPreconditionRequired428PUT.incrementAndGet();
			}
		}
		
		System.out.println(this + " - Réponses - total : " + out.get()
				+ " dont 200 (GET) : " + repOK200GET.get() 
				+ " , 20O (PUT) : " + rep0K200PUT.get()
				+ " , 304 (GET) : " + repNotModified304.get() 
				+ " , 412 (PUT) : " + repPreconditionFailed412.get()
				+ " , 428 (GET) : " + repPreconditionRequired428GET.get()
				+ " , 428 (PUT) : " + repPreconditionRequired428PUT.get());
	}

}
