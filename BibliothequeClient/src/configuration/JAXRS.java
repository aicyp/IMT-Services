package configuration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;

import infrastructure.jaxrs.AdapterClientReponsesPUT404EnOption;
import infrastructure.jaxrs.AdapterClientReponsesPOSTCreated;

public class JAXRS {
	
	// Adresse
	public static final String SERVEUR = "http://localhost:8080";
	public static final String CHEMIN = "/BibliothequeServeur/bibliotheque";

	// Chemins et requêtes
	public static final String CHEMIN_BIBLIO = "bibliotheque";
	public static final String SOUSCHEMIN_CATALOGUE = "catalogue";
	public static final String SOUSCHEMIN_TITRE = "titre";


	// Types
	public static final String TYPE_MEDIA = MediaType.APPLICATION_XML;
	public static final MediaType OBJET_TYPE_MEDIA = MediaType.APPLICATION_XML_TYPE;

	// Configuration du client
	public static Client client() {
		ClientConfig config = new ClientConfig();

		config.register(LoggingFeature.class);
		config.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO");
		config.register(infrastructure.jaxb.FournisseurTraduction.class);
		config.register(JacksonFeature.class);
		
		// TODO Adaptateurs
		config.register(AdapterClientReponsesPOSTCreated.class);
		config.register(new AdapterClientReponsesPUT404EnOption());

		return ClientBuilder.newClient(config);
	}
}
