package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.Outils;

public class RechercheSynchroneMultiTaches extends RechercheSynchroneAbstraite implements AlgorithmeRecherche {

	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques,
			Client client) {
		Outils.afficherInfoTache(nom().getNom());
		ExecutorService exec = Executors.newCachedThreadPool();
		CountDownLatch countDown = new CountDownLatch(bibliotheques.size());
		List<Optional<HyperLien<Livre>>> ret = new ArrayList<Optional<HyperLien<Livre>>>();

		for (HyperLien<BibliothequeArchive> lien : bibliotheques) {
			exec.submit(() -> {
				Optional<HyperLien<Livre>> res = rechercheSync(lien, l, client);
				if (!res.isPresent()) {
					countDown.countDown();
				} else {
					while (countDown.getCount() > 0) {
						countDown.countDown();
					}
					ret.add(res);
				}
			});
		}
		try {
			countDown.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret.get(0);
	}

	@Override
	public NomAlgorithme nom() {
		return new ImplemNomAlgorithme("recherche sync multi");
	}
}
