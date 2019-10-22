package serveur;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import configuration.JAXRS;
import configuration.Service;

public class Lancement {

	public static void main(String[] args) {
		
		ResourceConfig config = new Service();

		URI baseUri = UriBuilder.fromUri(JAXRS.ADRESSE_BIBLIO).port(8081).build();
		
	    HttpServer serveur = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
	    
		System.out.println("Serveur " + serveur + " de la classe " + serveur.getClass());
		System.out.println("Serveur Grizzly démarré : " + serveur.isStarted());
		

	}

}
