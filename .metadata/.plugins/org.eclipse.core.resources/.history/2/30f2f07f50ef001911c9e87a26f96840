package rest;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import rest.jaxb.TraductionResultat;

@XmlJavaTypeAdapter(TraductionResultat.class)
@XmlRootElement(name = "resultat")
public interface Resultat {
	public boolean isValide();
	public Session getId();
}
