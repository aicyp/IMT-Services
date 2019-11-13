package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.InvocationCallback;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.Outils;

public class RechercheAsynchroneMultiTaches extends RechercheAsynchroneAbstraite implements AlgorithmeRecherche {

	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques,
			Client client) {
		Outils.afficherInfoTache(nom().getNom());
		ExecutorService exec = Executors.newCachedThreadPool();
		CountDownLatch countdown = new CountDownLatch(bibliotheques.size());
		List<Optional<HyperLien<Livre>>> ret = new ArrayList<>();

		for (HyperLien<BibliothequeArchive> lien : bibliotheques) {
			exec.submit(() -> {
				rechercheAsyncAvecRappel(lien, l, client, new InvocationCallback<Optional<HyperLien<Livre>>>() {
					@Override
					public void completed(Optional<HyperLien<Livre>> result) {
						if (!result.isPresent()) {
							countdown.countDown();
						} else {
							ret.add(result);
							while (countdown.getCount() > 0) {
								countdown.countDown();
							}
						}
					}
					@Override
					public void failed(Throwable throwable) {
						throwable.printStackTrace();
					}
				});
			});
		}
		try {
			countdown.await();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret.get(0);

	}

	@Override
	public NomAlgorithme nom() {
		return new ImplemNomAlgorithme("recherche async multi");
	}

}
