package infrastructure.jaxrs;

import infrastructure.jaxrs.annotations.VersionReponses;
import modele.Versionneur;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;

@Provider
@VersionReponses
@Priority(Priorities.HEADER_DECORATOR)
public class AjouterVersionAuxReponses implements ContainerResponseFilter {

	private Versionneur ressourceVersionnee;
	
	@Inject
	public AjouterVersionAuxReponses(Versionneur r) {
		this.ressourceVersionnee = r;
		System.out.println("* Initialisation du filtre " + this + " : " + this.getClass());
		System.out
		.println("- Partage du versionneur " + this.ressourceVersionnee);
	}

	@Override
	public void filter(ContainerRequestContext requete,
			ContainerResponseContext reponse) throws IOException {
		OutilsHttp.setEtag(reponse, ressourceVersionnee.getVersion());
	}

}
