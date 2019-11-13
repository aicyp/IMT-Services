package modele;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;
import configuration.JAXRS;


@XmlRootElement(name="livre")
public interface Livre {
	public static Livre fromString(String titre){
		return new ImplemLivre(titre);
	}
	
	@GET
	@Path(JAXRS.SOUSCHEMIN_TITRE)
	String getTitre();
}
