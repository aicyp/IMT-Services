package modele;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import infrastructure.jaxrs.annotations.AtomiciteRequeteReponseServeur;
import infrastructure.jaxrs.annotations.CacheClient;
import infrastructure.jaxrs.annotations.EcritureOptimiste;
import infrastructure.jaxrs.annotations.StatReponses;
import infrastructure.jaxrs.annotations.StatRequetes;
import infrastructure.jaxrs.annotations.VersionReponses;

public interface ServiceRegistre {
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	@AtomiciteRequeteReponseServeur
	@CacheClient
	@EcritureOptimiste
	@StatReponses
	@StatRequetes
	@VersionReponses
	public Ressource set(Ressource n);

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@AtomiciteRequeteReponseServeur
	@CacheClient
	@EcritureOptimiste
	@StatReponses
	@StatRequetes
	@VersionReponses
	public Ressource get();
}



