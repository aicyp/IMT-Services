package client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;

import modele.Ressource;
import modele.ServiceRegistre;

public class TestRegistre {

	public static Client clientJAXRS() {
		ClientConfig config = new ClientConfig();
		config.register(LoggingFeature.class);
		config.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO");
		config.register(JacksonFeature.class);

		return ClientBuilder.newClient(config);
	}

	public static void main(String[] args) {

		final String adresse = "http://localhost:8080/ServeurRegistre";

		WebTarget cible = clientJAXRS().target(adresse);

		final ServiceRegistre proxyRegistre = WebResourceFactory.newResource(ServiceRegistre.class, cible);

		for (int i = 0; i < 100; i++) {
			System.out.println("*** 1. Get ***");

			Ressource s = proxyRegistre.get();
			System.out.println("*** Résultat 1 : " + s.getI());

			System.out.println("*** 2. Set ***");

			s.setI(s.getI() + 1);
			s = proxyRegistre.set(s);

			System.out.println("*** Résultat 2 : " + s.getI());
		}
		
		System.exit(0);
	}

}
