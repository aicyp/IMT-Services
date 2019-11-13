package configuration;

import javax.ws.rs.core.MediaType;

public class JAXRS {
	public static final String ADRESSE_PORTAIL = "http://localhost:8081/PortailServeur2"; 
	// Chemins et requêtes
	public static final String CHEMIN_PORTAIL = "portail";
	public static final String CHEMIN_BIBLIO = "bibliotheque";
	public static final String CLE_TITRE = "titre";
	public static final String SOUSCHEMIN_CATALOGUE = "catalogue";
	public static final String SOUSCHEMIN_ASYNC = "async";
	public static final String SOUSCHEMIN_LIVRE = "livre";
	public static final String SOUSCHEMIN_TITRE = "titre";
	public static final String SOUSCHEMIN_REPLIVRE = "";
	public static final String SOUSCHEMIN_ALGO_RECHERCHE = "admin/recherche";

	// Types Media
	public static final String TYPE_MEDIA = MediaType.APPLICATION_XML;
	public static final MediaType TYPE_MEDIATYPE = MediaType.APPLICATION_XML_TYPE;
}
