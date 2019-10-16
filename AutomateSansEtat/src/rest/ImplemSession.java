
package rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="session")
public class ImplemSession implements Session {
	
	private Etat etatExecution;
	private int numero;

	public ImplemSession() {
		this(Etat.UN, 0);
	}
	
	public ImplemSession(String x){
		String[] parties = x.split("-");
		for(Etat e : Etat.values()){
			if(e.toString().equals(parties[0])){
				this.etatExecution = e;
				break;
			}
		}
		this.numero = Integer.parseInt(parties[1]);
	}
	
	private ImplemSession(Etat e, int n){
		this.etatExecution = e;
		this.numero = n;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Session))
			return false;
		Session s = (Session) o;
		return (this.getNumero() == s.getNumero());
	}

	public int hashCode() {
		return this.getNumero();
	}

	public String toString() {
		return this.getEtatExecution().toString() + "-" + Integer.toString(this.getNumero());
	}

	@XmlElement(name = "etat")
	public Etat getEtatExecution() {
		return etatExecution;
	}

	public void setEtatExecution(Etat etatExecution) {
		this.etatExecution = etatExecution;
	}

}
