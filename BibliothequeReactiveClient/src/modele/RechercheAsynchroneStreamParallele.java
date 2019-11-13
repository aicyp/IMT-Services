package modele;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.Outils;

public class RechercheAsynchroneStreamParallele extends RechercheAsynchroneAbstraite implements AlgorithmeRecherche {

	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques,
			Client client) {
		Outils.afficherInfoTache(nom().getNom());
		return bibliotheques.parallelStream().map(item -> rechercheAsync(item, l, client)).map(Outils::remplirPromesse)
				.filter(item -> item.isPresent()).findFirst().orElse(null);
	}

	@Override
	public NomAlgorithme nom() {
		return new ImplemNomAlgorithme("recherche async stream 8");
	}

}
