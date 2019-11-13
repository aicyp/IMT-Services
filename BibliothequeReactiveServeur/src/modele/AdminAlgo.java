package modele;

import static configuration.JAXRS.SOUSCHEMIN_ALGO_RECHERCHE;
import static configuration.JAXRS.TYPE_MEDIA;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

public interface AdminAlgo {

	@PUT
	@Path(SOUSCHEMIN_ALGO_RECHERCHE)
	@Consumes(TYPE_MEDIA)
	void changerAlgorithmeRecherche(NomAlgorithme algo);
}
