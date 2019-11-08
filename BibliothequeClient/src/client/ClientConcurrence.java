package client;


import javax.ws.rs.client.WebTarget;

import infrastructure.jaxrs.HyperLien;
import modele.BibliothequeArchive;
import modele.ImplemLivre;
import modele.Livre;

import org.glassfish.jersey.client.proxy.WebResourceFactory;

import configuration.JAXRS;

public class ClientConcurrence {
	private static final String ADRESSE = JAXRS.SERVEUR + JAXRS.CHEMIN;

	public static void main(String[] args) {
		System.out.println("*************");
		
		WebTarget cible = JAXRS.client().target(ADRESSE);
		BibliothequeArchive biblio  = WebResourceFactory.newResource(BibliothequeArchive.class, cible);

		System.out.println("*** 1. Ajouter des livres ***");
		
		Livre l1 = new ImplemLivre("Restful Java with JAX-RS");
		
		HyperLien<Livre> r1 = null;
		
		for (int i = 0; i < 100; i++) {
			r1 = biblio.ajouter(l1); 
		}
		System.out.println("URI : " + r1.getUri());
		System.exit(0);
	}

}
