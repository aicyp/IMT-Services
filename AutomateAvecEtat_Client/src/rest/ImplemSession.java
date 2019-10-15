
package rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="session")
public class ImplemSession implements Session {
	private int numero;

	public ImplemSession() {
		this("0");
	}
	
	public ImplemSession(String x){
		numero = Integer.parseInt(x);
	}
	/**
     * Obtient la valeur de la propriété numero.
     * 
     */
	public int getNumero() {
		return numero;
	}

	/**
     * Définit la valeur de la propriété numero.
     * 
     */
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
		return Integer.toString(this.getNumero());
	}
}
