package client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.proxy.WebResourceFactory;

import configuration.JAXRS;
import infrastructure.jaxrs.ClientRessource;
import infrastructure.jaxrs.HyperLien;
import modele.BibliothequeArchive;
import modele.ImplemLivre;
import modele.Livre;


public class TestAppli {
	private static final String ADRESSE = JAXRS.SERVEUR + JAXRS.CHEMIN;

	public static void main(String[] args) {

		System.out.println("*************");

		WebTarget cible = JAXRS.client().target(ADRESSE);
		BibliothequeArchive biblio = WebResourceFactory.newResource(BibliothequeArchive.class, cible);
		
		System.out.println("*** 1. Ajouter des livres ***");
		List<Livre> livres = new ArrayList<>();
		List<HyperLien<Livre>> hyperliens = new ArrayList<HyperLien<Livre>>();
		
		for (int i = 0; i < 10; i++) {
			Livre livre = new ImplemLivre("Livre numéro" + i);
			HyperLien<Livre> hyperlien = null;
			hyperlien = biblio.ajouter(livre);
			System.out.println("POST " + i + " : " + hyperlien.getClass());
			System.out.println("POST " + i + " - uri : " + hyperlien.getUri());
			livres.add(livre);
			hyperliens.add(hyperlien);
		}
		
		System.out.println("*** 2. Rechercher un livre ***");
		System.out.println("GET 1 - livre présent : " + biblio.chercher(livres.get(0)));
		System.out.println("GET 2 - livre absent : " + biblio.chercher(new ImplemLivre("Livre sans numéro")));
		
		System.out.println("*** 3. Récupérer un livre ***");
		
		Livre livre = ClientRessource.proxy(hyperliens.get(0), Livre.class);
		System.out.println("GET 3 - type livre" + livre.getClass());
		System.out.println("GET 4 - titre livre : " + livre.getTitre());
		
		Livre livreb = ClientRessource.representation(hyperliens.get(0), Livre.class, JAXRS.OBJET_TYPE_MEDIA);
		System.out.println("GET 5 - type livre_b" + livreb.getClass());
		System.out.println("GET 6 - titre livre_b : " + livreb.getTitre());
		
		System.exit(0);
	}
}
