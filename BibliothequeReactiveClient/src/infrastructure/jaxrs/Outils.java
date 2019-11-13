package infrastructure.jaxrs;

import infrastructure.entreesSorties.Flots;

import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
	/*
	 * Réalisation d'une promesse (via une Future).
	 */
	public static <T> T remplirPromesse(Future<T> prom) {
		try {
			return prom.get();
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Exception promesse - " + e.getMessage());
			return null;
		}
	}
	
	public static void afficherInfoTache(String description) {
		System.out.println("Thread (" + description + ") : "+ Thread.currentThread().getName() + ".");
	}
	
	public static void patienter(long dixiemesSeconde){
		long tempsInitial = System.currentTimeMillis();
		long duree = 0;
		while(duree < dixiemesSeconde * 100){
			duree = System.currentTimeMillis() - tempsInitial;
		}
		System.out.println("Patience pendant " + duree + " ms.");
	}

}
