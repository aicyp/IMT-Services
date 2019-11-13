package client;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

import javax.ws.rs.client.WebTarget;
import org.junit.Assert;

import org.glassfish.jersey.client.proxy.WebResourceFactory;
import configuration.JAXRS;
import configuration.Orchestrateur;
import infrastructure.jaxrs.HyperLien;
import modele.ImplemLivre;
import modele.ImplemNomAlgorithme;
import modele.Livre;
import modele.Portail;

public class Test {

	private static final String ADRESSE = JAXRS.ADRESSE_PORTAIL + "/" + JAXRS.CHEMIN_PORTAIL;

	private static final String[] algos = {"recherche sync seq", "recherche sync multi", "recherche sync stream 8","recherche sync stream rx","recherche async seq", "recherche async multi", "recherche async stream 8", "recherche async stream rx"};
	public static void main(String[] args) {

		WebTarget cible = Orchestrateur.clientJAXRS().target(ADRESSE);
		Portail portail = WebResourceFactory.newResource(Portail.class, cible);
		HyperLien<Livre> expected = new HyperLien<Livre>("http://localhost:8095/bib5/bibliotheque/6");
		Livre l1 = new ImplemLivre("Services5.6");

		for (String nom : algos) {
			portail.changerAlgorithmeRecherche(new ImplemNomAlgorithme(nom));
			Instant startSync = Instant.now();
			Optional<HyperLien<Livre>> lienRecupSync = portail.chercher(l1);
			Instant stopSync = Instant.now();
			Assert.assertEquals(expected.getUri(), lienRecupSync.get().getUri());
			System.out.println(nom);
			System.out.println("duree"+Duration.between(startSync, stopSync).toMillis() + "\n\n");
		}

		System.exit(0);
	}

}
