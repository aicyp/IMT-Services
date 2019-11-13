package modele;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "algo")
public interface NomAlgorithme {
	@XmlAttribute(name = "nom")
	String getNom();
}
