package modele;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.Outils;

public class RechercheSynchroneSequentielle extends RechercheSynchroneAbstraite implements AlgorithmeRecherche {

	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<BibliothequeArchive>> bibliotheques,
			Client client) {
		Optional<HyperLien<Livre>> ret = null;
		Outils.afficherInfoTache(nom().getNom());
		
		for (HyperLien<BibliothequeArchive> lien : bibliotheques) {
			ret = rechercheSync(lien, l, client);
			if (ret.isPresent()) {
				return ret;
			}
		}
		return ret;
	}

	@Override
	public NomAlgorithme nom() {
		return new ImplemNomAlgorithme("recherche sync seq");
	}

}
