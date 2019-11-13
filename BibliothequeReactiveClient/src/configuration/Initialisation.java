package configuration;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.UriBuilder;

import infrastructure.jaxrs.HyperLien;
import modele.BibliothequeArchive;

public class Initialisation {

	public static HyperLien<BibliothequeArchive> biblio(HyperLien<?> h){
		return new HyperLien<>(UriBuilder.fromUri(h.getUri()).path(JAXRS.CHEMIN_BIBLIO).build());
	}
	/*
	 * Hyperliens vers les bibliotheques.
	 */
	public static List<HyperLien<BibliothequeArchive>> bibliotheques() {
		List<HyperLien<BibliothequeArchive>> l = new LinkedList<>();
		for(HyperLien<?> h : serveurs()){ 
			l.add(biblio(h));
		}
		return l;
	}

	/*
	 * Hyperliens vers les serveurs.
	 */
	public static List<HyperLien<?>> serveurs() {
		List<HyperLien<?>> l = new LinkedList<>();

		for(int i = 0; i < 10; i++){	
			int port = 8090 + i;
			l.add(new HyperLien<>("http://localhost:" 
					+ Integer.toString(port) 
					+ "/bib" 
					+ Integer.toString(i)));
		}
		return l;
	}
	
	
	
}
