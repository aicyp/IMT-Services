package rest.jaxb;

import javax.ws.rs.ext.Provider;

import rest.ImplemResultat;
import rest.Resultat;

@Provider
public class TraductionResultat extends javax.xml.bind.annotation.adapters.XmlAdapter<ImplemResultat, Resultat> {

	public TraductionResultat(){
		System.out.println("Chargement de l'adaptateur JAXB de type : " + this.getClass() );
	}
	
	@Override
	public ImplemResultat marshal(Resultat r0) throws Exception {
		ImplemResultat r1 = new ImplemResultat();
		r1.setId(r0.getId());
		r1.setValide(r0.isValide());
		return r1;
	}

	@Override
	public Resultat unmarshal(ImplemResultat r) throws Exception {
		return r;
	}

}
