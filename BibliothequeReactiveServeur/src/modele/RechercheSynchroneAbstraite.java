package modele;

import java.util.Optional;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.LienVersRessource;

public abstract class RechercheSynchroneAbstraite implements AlgorithmeRecherche {

	protected Optional<HyperLien<Livre>> rechercheSync(HyperLien<BibliothequeArchive> h, Livre l, Client client) {
		return LienVersRessource.proxy(client, h, BibliothequeArchive.class).chercher(l);
	}
}
