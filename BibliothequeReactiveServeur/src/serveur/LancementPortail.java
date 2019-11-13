package serveur;

import java.net.URI;
import java.net.URISyntaxException;

import org.glassfish.grizzly.http.server.HttpServer;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import configuration.JAXRS;
import configuration.ServicePortail;

public class LancementPortail {

	public static void main(String[] args) {
		try {
			ResourceConfig config = new ServicePortail();
			HttpServer serveur1 = 
					GrizzlyHttpServerFactory.createHttpServer(new URI(JAXRS.ADRESSE_PORTAIL), config);
			System.out.println("* Serveur principal Grizzly démarré : " + serveur1.isStarted());
			System.out.println("** Adresse : " + JAXRS.ADRESSE_PORTAIL);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
