package modele;

import infrastructure.jaxrs.HyperLien;

public interface Archive {

	Livre sousRessource(IdentifiantLivre id) ;
	
	Livre getRepresentation(IdentifiantLivre id);
	
	HyperLien<Livre> ajouter(Livre l);
}
