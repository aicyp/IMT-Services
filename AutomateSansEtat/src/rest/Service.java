package rest;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import rest.jaxb.FournisseurTraduction;

public class Service extends ResourceConfig {
	public Service(){
		System.out.println("Chargement de " + this.getClass());
		this.register(LoggingFeature.class); 
		this.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER, "INFO");
		this.register(FournisseurTraduction.class);
		this.register(JacksonFeature.class);
		this.register(A_B_point_Etoile.class);
		this.register(A_B_point_EtoileConcurrent.class);
	}
}
