package rest;

import javax.inject.Singleton;
import javax.ws.rs.Path;

@Path("automate/concurrent")
@Singleton
public class A_B_point_EtoileConcurrent implements Automate {

	private int compteur;

	private int numeroSession;

	public A_B_point_EtoileConcurrent() {
		System.out.println("Déploiement de " + this.getClass());
		numeroSession = 0;
		compteur = 0;
	}

	@Override
	public synchronized Session initier() {
		ImplemSession res = new ImplemSession();
		res.setNumero(numeroSession);
		numeroSession++;
		res.setEtatExecution(Etat.UN);
		return res;
	}

	@Override
	public Resultat accepter(char x, Session id) {
		synchronized(this) {
			compteur++;
			System.out.println("************** requête accepter numéro " + compteur + " *************");
		}
		ImplemResultat res = new ImplemResultat();
		ImplemSession s = new ImplemSession();
		res.setId(s);
		s.setNumero(id.getNumero());
		Etat e = id.getEtatExecution();
		if (e.equals(Etat.UN) && (x == 'a')) {
			s.setEtatExecution(Etat.DEUX);
			res.setValide(true);
			return res;
		}
		if (e.equals(Etat.DEUX) && (x == 'b')) {
			s.setEtatExecution(Etat.UN);
			res.setValide(true);
			return res;
		}
		s.setEtatExecution(e);
		res.setValide(false);
		return res;
	}

}
