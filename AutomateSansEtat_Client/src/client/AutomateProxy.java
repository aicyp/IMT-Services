package client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import rest.Automate;
import rest.Resultat;
import rest.Session;

public class AutomateProxy implements Automate {
	private WebTarget cibleInitier;
	private WebTarget cibleAccepter;
	private MediaType typeContenu;
	
	public AutomateProxy(String uriBase, MediaType typeContenu){
		WebTarget cible = AppliCliente.clientJAXRS().target(uriBase);
		cibleInitier = cible.path("etat/initial");
		cibleAccepter = cible.path("etat/suivant");
		this.typeContenu = typeContenu;
	}
	
	@Override
	public Session initier() {
		return this.cibleInitier.request(this.typeContenu).post(null, Session.class);
	}

	@Override
	public Resultat accepter(char x, Session id) {
		return this.cibleAccepter.path(Character.toString(x)).request(this.typeContenu)
				.post(Entity.entity(id, this.typeContenu), Resultat.class);
	}

}
