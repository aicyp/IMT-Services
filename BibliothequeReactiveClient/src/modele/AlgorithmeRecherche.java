package modele;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;

public interface AlgorithmeRecherche {
	Optional<HyperLien<Livre>> 
		chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques, Client client);
	NomAlgorithme nom();
}
