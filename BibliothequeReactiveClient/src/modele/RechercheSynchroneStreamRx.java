package modele;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.Outils;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class RechercheSynchroneStreamRx extends RechercheSynchroneAbstraite implements AlgorithmeRecherche {

	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques,
			Client client) {

		Outils.afficherInfoTache(nom().getNom());
		Observable<HyperLien<BibliothequeArchive>> obs = Observable.fromIterable(bibliotheques);
		return obs.flatMap(h -> Observable.fromCallable(() -> rechercheSync(h, l, client))).subscribeOn(Schedulers.io())
				.filter(item -> item.isPresent()).blockingFirst(null);
	}

	@Override
	public NomAlgorithme nom() {
		return new ImplemNomAlgorithme("recherche sync stream rx");
	}

}
