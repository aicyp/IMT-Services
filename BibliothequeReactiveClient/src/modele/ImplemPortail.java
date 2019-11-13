package modele;

import static configuration.JAXRS.CHEMIN_PORTAIL;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.container.AsyncResponse;

import configuration.Initialisation;
import configuration.Orchestrateur;
import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.HyperLiens;
import infrastructure.jaxrs.LienVersRessource;

@Singleton
@Path(CHEMIN_PORTAIL)
public class ImplemPortail implements Portail {

	private ConcurrentMap<NomAlgorithme, AlgorithmeRecherche> table;
	private Client client;
	private AlgorithmeRecherche algo;
	private List<HyperLien<BibliothequeArchive>> bibliotheques;

	public ImplemPortail() {

		table = new ConcurrentHashMap<NomAlgorithme, AlgorithmeRecherche>();
		client = Orchestrateur.clientJAXRS();
		algo = null;
		bibliotheques = Initialisation.bibliotheques();

		AlgorithmeRecherche a1 = new RechercheSynchroneSequentielle();
		table.put(a1.nom(), a1);

		AlgorithmeRecherche a2 = new RechercheSynchroneMultiTaches();
		table.put(a2.nom(), a2);

		AlgorithmeRecherche a3 = new RechercheSynchroneStreamParallele();
		table.put(a3.nom(), a3);

		AlgorithmeRecherche a4 = new RechercheSynchroneStreamRx();
		table.put(a4.nom(), a4);

		AlgorithmeRecherche a5 = new RechercheAsynchroneSequentielle();
		table.put(a5.nom(), a5);

		AlgorithmeRecherche a6 = new RechercheAsynchroneMultiTaches();
		table.put(a6.nom(), a6);

		AlgorithmeRecherche a7 = new RechercheAsynchroneStreamParallele();
		table.put(a7.nom(), a7);

		AlgorithmeRecherche a8 = new RechercheAsynchroneStreamRx();
		table.put(a8.nom(), a8);

	}

	@Override
	public Future<Optional<HyperLien<Livre>>> chercherAsynchrone(Livre l, AsyncResponse ar) {

		bibliotheques.parallelStream().map(lien -> LienVersRessource.proxy(client, lien, BibliothequeArchive.class))
				.forEach(item -> ImplementationAppelsAsynchrones.rechercheAsynchroneBibliotheque(item, l, ar));

		return null;

	}

	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l) {
		return algo.chercher(l, bibliotheques, client);
	}

	@Override
	public HyperLiens<Livre> repertorier() {

		Stream<HyperLien<BibliothequeArchive>> stream = bibliotheques.stream();

		Stream<BibliothequeArchive> streamBiblio = stream
				.map(item -> LienVersRessource.proxy(client, item, BibliothequeArchive.class));
		Stream<List<HyperLien<Livre>>> streamListeHyperlienLivre = streamBiblio
				.map(item -> item.repertorier().getLiens());

		List<HyperLien<Livre>> listeHyperlienLivre = streamListeHyperlienLivre
				.reduce((x, y) -> Stream.concat(x.stream(), y.stream()).collect(Collectors.toList())).get();

		return new HyperLiens<Livre>(listeHyperlienLivre);

	}

	@Override
	public void changerAlgorithmeRecherche(NomAlgorithme nom) {
		if (table.containsKey(nom)) {
			this.algo = table.get(nom);
		}
	}
}