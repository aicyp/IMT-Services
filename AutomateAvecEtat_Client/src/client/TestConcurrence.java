package client;

import java.util.stream.Stream;

import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.proxy.WebResourceFactory;

import rest.Automate;
import rest.Session;

public class TestConcurrence {

	public static void main(String[] args) {
	  String adresse = "http://localhost:8080/AutomateAvecEtat/automate";
	  System.out.println("*************");

	  WebTarget cible = AppliCliente.clientJAXRS().target(adresse);
	  Automate automateProxyJersey = WebResourceFactory.newResource(Automate.class, cible);

	  int REQUETES = 10000;
	  final long incrementations = Stream
	    .generate(() -> 0)
	    .limit(REQUETES)
	    .parallel()
	    .map(j -> automateProxyJersey.initier().getNumero())
	    .distinct()
	    .count();

	  System.out.println("Pertes en écriture : " + (REQUETES - incrementations) + "/ " + REQUETES + " requêtes.");
	  System.out.println("*************");
	}

}
