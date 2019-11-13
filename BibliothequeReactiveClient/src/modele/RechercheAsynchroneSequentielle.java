package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.Outils;

public class RechercheAsynchroneSequentielle extends RechercheAsynchroneAbstraite implements AlgorithmeRecherche {

	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques,
			Client client) {
		
		Outils.afficherInfoTache(nom().getNom());
		List<Future<Optional<HyperLien<Livre>>>> listePr = new ArrayList<>();
		
		for (HyperLien<BibliothequeArchive> lien : bibliotheques) {
			listePr.add(rechercheAsync(lien, l, client));
		}
		
		for (Future<Optional<HyperLien<Livre>>> pr : listePr) {
			try {
				if (pr.get() != null && pr.get().isPresent()) {
					return pr.get();
				}
			} catch (InterruptedException | ExecutionException e) {
			}
		}
		return null;
	}

	@Override
	public NomAlgorithme nom() {
		return new ImplemNomAlgorithme("recherche async seq");
	}

}
