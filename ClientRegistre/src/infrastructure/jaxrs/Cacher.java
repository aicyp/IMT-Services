package infrastructure.jaxrs;

import java.io.IOException;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.HEADER_DECORATOR)
public class Cacher implements ClientResponseFilter {

	private Cache cache;
	private ErreurPrecondition412 gestionnaireErreur;

	@Inject
	public Cacher(Cache c, ErreurPrecondition412 g) {
		gestionnaireErreur = g;
		cache = c;
		System.out.println("Filtre initialisé " + this + " : " + this.getClass());
		System.out.println("- Cache partagé : " + this.cache);
		System.out.println("- Gestionnaire 412 partagé : " + this.gestionnaireErreur);
	}

	@Override
	/*
	 * Filtre / réponse
	 * 
	 * On utilise le statut de la réponse pour discriminer les réponses. 200 (OK) : GET ou
	 * PUT, 304 (NOT_MODIFIED) : GET, 412 (PRECONDITION_FAILED) : PUT. 
	 * On peut aussi utiliser le paramètre requete pour récupérer des informations concernant la requête,
	 * par exemple la méthode Http (PUT ou GET).
	 */
	public synchronized void filter(ClientRequestContext requete, ClientResponseContext reponse) throws IOException {
		int statut = reponse.getStatus();
		// * Statut OK (200) - GET ou PUT
		if (statut == Response.Status.OK.getStatusCode()) {
			// Initialiser le cache avec la réponse :
			// - type média (variable typeContenu),
			// - version stockée dans le champ EntityTag (variable version),
			// - contenu obtenu par un appel à Flots.convertirFlotEnOctets
			// (variable contenu).
			// TODO 
			// Attention : comme la fonction Flots.convertirFlotEnOctets vide le
			// flux entrant,
			// il est nécessaire de réinitialiser le flux EntityStream de la
			// réponse
			// en utilisant un ByteArrayInputStream.
			// TODO 
			return;
		}
		// * Statut NOT_MODIFIED (304)
		if (statut == Response.Status.NOT_MODIFIED.getStatusCode()) {
			// Compléter la réponse en utilisant le cache.
			// - Initialiser le flux entrant EntityStream en utilisant un
			// ByteArrayInputStream construit à partir de cache.contenu.
			// - Initialiser le statut à OK.
			// - Initialiser les en-têtes suivants :
			// - HttpHeaders.CONTENT_LENGTH
			// - HttpHeaders.CONTENT_TYPE
			// TODO 
			return;
		}
		// Cas d'une erreur 412 (PUT)
		if (statut == Response.Status.PRECONDITION_FAILED.getStatusCode()) {
			// Initialiser le cache avec la réponse :
			// - type média (variable typeContenu),
			// - version stockée dans le champ EntityTag (variable version),
			// - contenu obtenu par un appel à Flots.convertirFlotEnOctets
			// (variable contenu).
			// TODO 
			// Attention : comme la fonction Flots.convertirFlotEnOctets vide le
			// flux entrant,
			// il est nécessaire de réinitialiser le flux EntityStream de la
			// réponse
			// en utilisant un ByteArrayInputStream.
			// TODO
			// Changer le statut de la réponse à OK.
			// Lever une erreur 412 dans le gestionnaire d'erreur.
			// TODO
		    
			return;
		}

	}
}
