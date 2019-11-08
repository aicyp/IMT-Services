package client;

import java.lang.reflect.Proxy;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.WebTarget;

import infrastructure.jaxrs.ClientRessource;
import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.Outils;
import modele.BibliothequeArchive;
import modele.ImplemLivre;
import modele.Livre;

import org.glassfish.jersey.client.proxy.WebResourceFactory;

import configuration.JAXRS;

public class AppliCliente {

	private static final String ADRESSE = JAXRS.SERVEUR + JAXRS.CHEMIN;

	public static void main(String[] args) {

		System.out.println("*************");

		WebTarget cible = JAXRS.client().target(ADRESSE);
		BibliothequeArchive biblio = WebResourceFactory.newResource(BibliothequeArchive.class, cible);

		System.out.println("Biblio (proxy) : " + Proxy.isProxyClass(biblio.getClass()));

		System.out.println("*** 1. Ajouter des livres ***");
		Livre l1 = new ImplemLivre("Restful Java with JAX-RS");
		Livre l2 = new ImplemLivre("Restful Java with JAX-RS 2.0");

		HyperLien<Livre> r1 = null;
		HyperLien<Livre> r2 = null;

		r1 = biblio.ajouter(l1); // POST 1
		System.out.println("POST 1 : " + r1.getClass());
		r2 = biblio.ajouter(l2); // POST 2
		System.out.println("POST 2 : " + r2.getClass());

		System.out.println("POST 1 - uri : " + r1.getUri());
		System.out.println("POST 2 - uri : " + r2.getUri());

		System.out.println("*** 2. Récupérer un livre à partir de l'hyperlien ***");

		Livre l3 = ClientRessource.proxy(r2, Livre.class);
		System.out.println("Proxy (true) ? " + Proxy.isProxyClass(l3.getClass()));
		System.out.println("Classe du proxy : " + l3.getClass());
		System.out.println("Classe parente du proxy : " + l3.getClass().getSuperclass());
		String titre3 = l3.getTitre();
		System.out.println("GET 3 (titre : 2.0) : " + titre3);
		Livre l3b = ClientRessource.representation(r2, Livre.class, JAXRS.OBJET_TYPE_MEDIA);
		System.out.println("GET 4 (ImplemLivre) : " + l3b.getClass());
		System.out.println("livre (2.0) : " + l3b);

		l3 = ClientRessource.proxy(new HyperLien<Livre>(ADRESSE + "/123"), Livre.class);

		try {
			System.out.println("GET 5 - titre livre : " + l3.getTitre());
		} catch (WebApplicationException e) {
			System.out.println("GET 5 - exception - " + Outils.messageErreur(e.getResponse()));
		}

		System.out.println("*** 3. Chercher un livre ***");

		System.out.println("GET 6 - uri : " + biblio.chercher(l3b));
		System.out.println("GET 7 - livre absent : " + biblio.chercher(new ImplemLivre("absent")));
		

		System.out.println("*** 4. Répertorier les livres ***");

		System.out.println("GET 8 - catalogue : " + biblio.repertorier());

		System.exit(0);
	}

}
