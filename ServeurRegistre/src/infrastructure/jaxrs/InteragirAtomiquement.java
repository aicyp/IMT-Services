package infrastructure.jaxrs;

import java.util.concurrent.atomic.AtomicInteger;

import infrastructure.jaxrs.annotations.AtomiciteRequeteReponseServeur;

import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;

@Provider
@AtomiciteRequeteReponseServeur
@Priority(Priorities.AUTHORIZATION + 10)
public class InteragirAtomiquement implements ContainerRequestFilter,
		ContainerResponseFilter {

	private ReadWriteLock verrou;
	private AtomicInteger nombreLecteurs = new AtomicInteger(0);
	private AtomicInteger nombreScribes = new AtomicInteger(0);

	public InteragirAtomiquement() {
		verrou = new ReentrantReadWriteLock();
		System.out.println("* Initialisation du filtre " + this + " : " + this.getClass());
	}

	@Override
	public void filter(ContainerRequestContext requete,
			ContainerResponseContext reponse) throws IOException {
		if (requete.getMethod().equalsIgnoreCase("PUT")) {
			nombreScribes.decrementAndGet();
			verrou.writeLock().unlock();
			return;
		}
		if (requete.getMethod().equalsIgnoreCase("GET")) {
				nombreLecteurs.decrementAndGet();
				verrou.readLock().unlock();
				return;
		}
	}

	@Override
	public void filter(ContainerRequestContext requete) throws IOException {
		if (requete.getMethod().equalsIgnoreCase("PUT")) {
			verrou.writeLock().lock();
			nombreScribes.incrementAndGet();
			System.out.println("Scribes : " + nombreScribes.get());
			return;
		}
		if (requete.getMethod().equalsIgnoreCase("GET")) {
				verrou.readLock().lock();
				nombreLecteurs.incrementAndGet();
				System.out.println("Lecteurs : " + nombreLecteurs.get());
			return;
		}
	}

}
