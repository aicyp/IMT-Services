package configuration;

import javax.ws.rs.core.MediaType;

public class JAXRS {
	// Adresses
	public static final String ADRESSE_BIBLIO = "http://localhost/BibliothequeServeur"; // TODO
	
	// Chemins et requêtes
	public static final String CHEMIN_BIBLIO = "bibliotheque";
	public static final String SOUSCHEMIN_CATALOGUE = "catalogue";
	public static final String SOUSCHEMIN_TITRE = "titre";

	// Types
	public static final String TYPE_MEDIA = MediaType.APPLICATION_XML;
}
