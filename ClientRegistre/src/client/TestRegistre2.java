package client;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;


import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.CustomProvidersFeature;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import infrastructure.jaxrs.AjouterPrecondition;
import infrastructure.jaxrs.Cache;
import infrastructure.jaxrs.Cacher;
import infrastructure.jaxrs.ErreurPrecondition412;
import modele.Ressource;
import modele.ServiceRegistre;

public class TestRegistre2 {

	public static CustomProvidersFeature fournisseurFiltres() {
		List<Class<?>> filtres = new LinkedList<>();
		filtres.add(AjouterPrecondition.class);
		filtres.add(Cacher.class);
		return new CustomProvidersFeature(filtres);
	}

	public static Client clientJAXRS(ErreurPrecondition412 gestionnaire) {
		ClientConfig config = new ClientConfig();
		config.register(LoggingFeature.class);
		config.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO");
		config.register(JacksonFeature.class);

		Cache cache = new Cache();
		config.register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(gestionnaire).to(ErreurPrecondition412.class);
				bind(cache).to(Cache.class);
			}
		});

		config.register(fournisseurFiltres());

		return ClientBuilder.newClient(config);
	}

	public static void main(String[] args) {

		final String adresse = "http://localhost:8080/ServeurRegistre";

		ErreurPrecondition412 gestionnaire = new ErreurPrecondition412();
		WebTarget cible = clientJAXRS(gestionnaire).target(adresse);

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
