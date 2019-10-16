package rest;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import rest.jaxb.TraductionSession;
import rest.jaxrs.ConversionStringSession;

@XmlJavaTypeAdapter(TraductionSession.class)
@XmlRootElement(name="session")
public interface Session {
	
	public static Session fromString(String x){
		return ConversionStringSession.fromString(x);
	}
	
	public int getNumero();
	public Etat getEtatExecution();
}
