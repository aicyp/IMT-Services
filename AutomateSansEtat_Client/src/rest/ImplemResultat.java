
package rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "resultat")
public class ImplemResultat implements Resultat {
	private Session id;
	private boolean valide;

	/**
     * Obtient la valeur de la propriété valide.
     * 
     */
	public boolean isValide() {
		return valide;
	}

	/**
     * Définit la valeur de la propriété valide.
     * 
     */
	public void setValide(boolean estValide) {
		this.valide = estValide;
	}

	/**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link ImplemSession }
     *     
     */
	public Session getId() {
		return id;
	}

	/**
     * Définit la valeur de la propriété id.
     * 
     * @param id
     *     allowed object is
     *     {@link ImplemSession }
     *     
     */
	public void setId(Session id) {
		this.id = id;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Resultat))
			return false;
		Resultat r = (Resultat) o;
		return this.getId().equals(r.getId()) && (this.isValide() == r.isValide());
	}

	public String toString() {
		return this.getId() + "-" + this.isValide();
	}
}