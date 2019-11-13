package configuration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;

import infrastructure.jaxrs.AdapterServeurReponsesPOSTEnCreated;
import infrastructure.jaxrs.AdapterClientReponsesPUT404EnOption;

public class Orchestrateur {
	public static Client clientJAXRS() {
		ClientConfig config = new ClientConfig();
		
		config.register(LoggingFeature.class);
		config.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, "INFO");
		config.register(infrastructure.jaxb.FournisseurTraduction.class);
		config.register(JacksonFeature.class);
		config.register(AdapterServeurReponsesPOSTEnCreated.class);
		// TODO Adaptateur
		config.register(new AdapterClientReponsesPUT404EnOption());
				
		return ClientBuilder.newClient(config);
	}
}
