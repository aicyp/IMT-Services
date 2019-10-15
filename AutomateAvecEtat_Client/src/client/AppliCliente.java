package client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;

import rest.Automate;
import rest.Resultat;
import rest.Session;
import rest.jaxb.FournisseurTraduction;

public class AppliCliente {

	public static Client clientJAXRS() {
		ClientConfig config = new ClientConfig();
		config.register(LoggingFeature.class);
		config.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO");
		config.register(FournisseurTraduction.class);
		config.register(JacksonFeature.class);
		return ClientBuilder.newClient(config);
	}

	public static void main(String[] args) {
		
		String adresse = "http://localhost:8080/AutomateAvecEtat/automate";
		
		System.out.println("*************");
		
		// TODO
		WebTarget cible = clientJAXRS().target(adresse);
		Automate proxy = WebResourceFactory.newResource(Automate.class, cible);
		test(proxy);
		
		System.out.println("*************");
		
		
	}

	private static void test(Automate automate) {
		char[] mot = { 'a', 'b', 'a', 'b', 'a', 'b' };
		
		// TODO
		Resultat resultat;
		boolean isValide = true;
		Session session = automate.initier();
		
		for(int i = 0; i < mot.length; i++) {
			resultat = automate.accepter(mot[i], session);
			
			if (isValide == true) {
				isValide = resultat.isValide();
			} else {
				isValide = false;
			}
		}
		
		if(isValide) {
			System.out.println("Le mot est valide");
		} else {
			System.out.println("Le mot n'est pas valide");
		}
		
		automate.clore(session);
	}

}
