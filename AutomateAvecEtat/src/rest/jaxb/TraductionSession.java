package rest.jaxb;

import javax.ws.rs.ext.Provider;

import rest.ImplemSession;
import rest.Session;

@Provider
public class TraductionSession extends javax.xml.bind.annotation.adapters.XmlAdapter<ImplemSession, Session> {

	public TraductionSession(){
		System.out.println("Chargement de l'adaptateur JAXB de type : " + this.getClass() );
	}
	
	@Override
	public ImplemSession marshal(Session s0) throws Exception {
		ImplemSession s1 = new ImplemSession();
		s1.setNumero(s0.getNumero());
		return s1;
	}

	@Override
	public Session unmarshal(ImplemSession s) throws Exception {
		return s;
	}

}
