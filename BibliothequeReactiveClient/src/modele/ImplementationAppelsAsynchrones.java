package modele;

import java.util.Optional;
import java.util.concurrent.Future;

import javax.ws.rs.container.AsyncResponse;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.Outils;

public class ImplementationAppelsAsynchrones {

	// Version asynchrone
	public static Future<Optional<HyperLien<Livre>>> rechercheAsynchroneBibliotheque(
			Bibliotheque bib, Livre l, 
			AsyncResponse ar) {
		Outils.afficherInfoTache("recherche aynchrone");
		Optional<HyperLien<Livre>> h = bib.chercher(l);
		ar.resume(h);
		return null; // Le résultat n'importe pas mais permet de typer la fonction 
			         // côté serveur de la même manière que côté client.
			         // Fonctionnement d'un appel asynchrone
			         // - Le client réalise un appel de la méthode distante. Cet appel renvoie immédiatement 
		             //   une promesse de type Future, qui contient le canal de retour sur lequel la réponse arrivera.
		             // - Le serveur reçoit la requête correspondante et la traite. Il envoie la réponse au client
		             //   via le canal de retour : voir ar.resume.
		             // - Le client peut tester si la promesse est réalisée ou non. Elle l'est lorsque la réponse 
			         //   du serveur parvient au client. 
	}
}
