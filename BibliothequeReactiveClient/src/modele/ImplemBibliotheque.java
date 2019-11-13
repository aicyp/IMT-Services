package modele;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.HyperLiens;
import infrastructure.jaxrs.Outils;

import java.net.URI;
import java.util.concurrent.ConcurrentHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Set;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;

import configuration.JAXRS;



@Singleton
@Path(JAXRS.CHEMIN_BIBLIO)
public class ImplemBibliotheque implements BibliothequeArchive {

	private ConcurrentMap<IdentifiantLivre, Livre> catalogue;
	private int compteur; // dernier identifiant utilisé (-1 : non utilisé)
	private Lock verrou;

	public ImplemBibliotheque() {
		System.out.println("Déploiement de " + this + " : " +this.getClass());
		catalogue = new ConcurrentHashMap<IdentifiantLivre, Livre>();
		compteur = -1;
		verrou = new ReentrantLock();
	}

	@Override
	public HyperLien<Livre> ajouter(Livre l) {
		IdentifiantLivre id = null;
		verrou.lock();
		try {
			compteur++;
			id = new ImplemIdentifiantLivre(Integer.toString(compteur));
		} finally {
			verrou.unlock();
		}
		catalogue.put(id, l);
		final URI adresse = URI.create("bibliotheque/" + id.getId());
		return new HyperLien<Livre>(Response.created(adresse).build().getLocation());
	}

	@Override
	public Livre sousRessource(IdentifiantLivre id) {
		return catalogue.get(id);
	}

	@Override
	public Livre getRepresentation(IdentifiantLivre id) {
		return catalogue.get(id);
	}
	
	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l) {
		// renvoie null si non trouvé
		Outils.afficherInfoTache("recherche synchrone");
		for (ConcurrentMap.Entry<IdentifiantLivre, Livre> couple : catalogue
				.entrySet()) {
			if (couple.getValue().equals(l)) {
				IdentifiantLivre id = couple.getKey();
				URI adresse = URI.create("bibliotheque/" + id.getId());
				return Optional.of(
						new HyperLien<Livre>(Response.created(adresse).build().getLocation()));
			}
		}
		
		Outils.patienter(15);
		return Optional.empty();
	}
	
	@Override
	public Future<Optional<HyperLien<Livre>>> chercherAsynchrone(Livre l, AsyncResponse ar) {
		return ImplementationAppelsAsynchrones.rechercheAsynchroneBibliotheque(this, l, ar);
	}
	
	@Override
	public HyperLiens<Livre> repertorier() {
		Set<IdentifiantLivre> ids = catalogue.keySet();
		List<HyperLien<Livre>> catalogueRef = new LinkedList<HyperLien<Livre>>();
		for (IdentifiantLivre id : ids) {
			URI adresse = URI.create("bibliotheque/" + id.getId());
			catalogueRef.add(new HyperLien<Livre>(Response
					.created(adresse).build().getLocation()));
		}
		return new HyperLiens<Livre>(catalogueRef);
	}

}
