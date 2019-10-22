package modele;

import java.util.Optional;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.HyperLiens;

public interface Bibliotheque {
		
	Optional<HyperLien<Livre>> chercher(Livre l);

	HyperLiens<Livre> repertorier();
	
}
