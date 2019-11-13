package modele;

import java.util.Optional;
import java.util.concurrent.Future;

import infrastructure.jaxrs.HyperLien;
import infrastructure.langage.Types;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;

public abstract class RechercheAsynchroneAbstraite implements AlgorithmeRecherche {

	protected Future<Optional<HyperLien<Livre>>> rechercheAsync(HyperLien<BibliothequeArchive> h, Livre l,
			Client client) {
		WebTarget target = client.target(h.getUri());
		return target.request().async().put(Entity.entity(l, configuration.JAXRS.TYPE_MEDIA),
				Types.typeRetourChercherAsync());
	}

	protected Future<Optional<HyperLien<Livre>>> rechercheAsyncAvecRappel(HyperLien<BibliothequeArchive> h, Livre l,
			Client client, InvocationCallback<Optional<HyperLien<Livre>>> retour) {
		WebTarget target = client.target(h.getUri());
		return target.request().async().put(Entity.entity(l, configuration.JAXRS.TYPE_MEDIA), retour);
	}
}
