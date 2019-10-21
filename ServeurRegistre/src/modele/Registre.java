package modele;

import javax.inject.Singleton;
import javax.ws.rs.Path;

@Path("optimiste")
@Singleton
public class Registre implements ServiceRegistre {

	private Ressource n;
	
	public Registre(Ressource i) {
		System.out.println("Initialisation du registre " + this + " : " + this.getClass());
		System.out.println("- Ressource " + i + " : " + i.getClass());
		n = i;
	}
	
	@Override
	public Ressource set(Ressource n) {
		this.n.setI(n.getI());
		return this.n;
	}
	
	@Override
	public Ressource get() {
		return new Ressource(this.n.getI());
	}

}



