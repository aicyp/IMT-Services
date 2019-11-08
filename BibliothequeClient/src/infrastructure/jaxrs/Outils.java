package infrastructure.jaxrs;

import infrastructure.entreesSorties.Flots;

import java.io.InputStream;

import javax.ws.rs.core.Response;

public class Outils {
	/*
	 * A utiliser avec des réponses correspondant à des erreurs.
	 */
	public static String messageErreur(Response rep){
		String msg = rep.getStatus() + " : ";
		Object contenu = rep.getEntity();
		if (contenu instanceof InputStream) {
			msg = msg + new String(
					Flots.convertirFlotEnOctets((InputStream) contenu));
		}
		return msg;
	}
}
