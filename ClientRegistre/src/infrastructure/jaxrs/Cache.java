package infrastructure.jaxrs;

import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;

public class Cache {
	public EntityTag version;
	public MediaType typeContenu;
	public byte[] contenu;
	public Cache(){
		System.out.println("Initialisation du cache " + this + " : " + this.getClass());
	}
}
