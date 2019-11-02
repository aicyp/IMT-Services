package modele;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import configuration.JAXRS;

@XmlRootElement(name="livre")
public interface Livre {
	
	@GET
	@Path("id/" + JAXRS.SOUSCHEMIN_TITRE)
	@Consumes(MediaType.APPLICATION_XML)
	String getTitre();
	
	public static Livre fromString(String x) {
		return new ImplemLivre(x);
	}
}
