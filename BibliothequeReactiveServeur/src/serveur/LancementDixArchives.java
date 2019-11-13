package serveur;


import org.glassfish.grizzly.http.server.HttpServer;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import configuration.Initialisation;

import configuration.Orchestrateur;
import configuration.ServiceBibliotheque;
import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.LienVersRessource;
import modele.Archive;
import modele.BibliothequeArchive;
import modele.ImplemLivre;

public class LancementDixArchives {

	public static void main(String[] args) {
		
		ResourceConfig config = new ServiceBibliotheque();
		
		int v = 0;
		for(HyperLien<?> h : Initialisation.serveurs()){
			HttpServer serveur1 = GrizzlyHttpServerFactory.createHttpServer(h.getUri(), config);	
			System.out.println("* Serveur Grizzly démarré : " + serveur1.isStarted());			
			System.out.println("** Adresse : " + h.getUri());
			ajouterLivres(Initialisation.biblio(h), v);
			v++;
		}

	}

	private static void ajouterLivres(HyperLien<BibliothequeArchive> h, int v) {
		Archive b = 
				LienVersRessource.proxy(Orchestrateur.clientJAXRS(), h, BibliothequeArchive.class);
		for(int i = 0; i < 10; i++){
			b.ajouter(new ImplemLivre("Services" + v + "." + i));
		}
	}
	
}
