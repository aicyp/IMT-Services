package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.HyperLiens;

import java.net.URI;
import java.util.concurrent.ConcurrentHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Set;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import configuration.JAXRS;

@Singleton
@Path(JAXRS.CHEMIN_BIBLIO)
public class ImplemBibliotheque implements BibliothequeArchive {

	private ConcurrentMap<IdentifiantLivre, Livre> catalogue;
	private int compteur; // dernier identifiant utilisé (-1 : non utilisé)
	private Lock lock = new ReentrantLock();

	public ImplemBibliotheque() {
		System.out.println("Déploiement de " + this + " : " + this.getClass());
		catalogue = new ConcurrentHashMap<IdentifiantLivre, Livre>();
		compteur = -1;
	}

	@Override
	public HyperLien<Livre> ajouter(Livre l) {
		final URI adresse;
		lock.lock();
		try {
			IdentifiantLivre id = null;
			compteur++;
			id = new ImplemIdentifiantLivre(Integer.toString(compteur));
			catalogue.put(id, l);
			adresse = URI.create("bibliotheque/" + id.getId());
		} finally {
			lock.unlock();
		}
		return new HyperLien<Livre>(Response.created(adresse).build().getLocation());
	}

	private Livre possibleLivre(IdentifiantLivre id) {
		return catalogue.get(id);
	}

	@Override
	public Livre sousRessource(IdentifiantLivre id) {
		return possibleLivre(id);
	}

	@Override
	public Livre getRepresentation(IdentifiantLivre id) {
		return possibleLivre(id);
	}

	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l) {
		// renvoie null si non trouvé
		for (ConcurrentMap.Entry<IdentifiantLivre, Livre> couple : catalogue.entrySet()) {
			if (couple.getValue().equals(l)) {
				IdentifiantLivre id = couple.getKey();
				URI adresse = URI.create("bibliotheque/" + id.getId());
				return Optional.of(new HyperLien<Livre>(Response.created(adresse).build().getLocation()));
			}
		}
		return Optional.empty();
	}

	@Override
	public HyperLiens<Livre> repertorier() {
		Set<IdentifiantLivre> ids = catalogue.keySet();
		List<HyperLien<Livre>> catalogueRef = new LinkedList<HyperLien<Livre>>();
		for (IdentifiantLivre id : ids) {
			URI adresse = URI.create("bibliotheque/" + id.getId());
			catalogueRef.add(new HyperLien<Livre>(Response.created(adresse).build().getLocation()));
		}
		return new HyperLiens<Livre>(catalogueRef);
	}

}
