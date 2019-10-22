package modele;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="livre")
public interface Livre {
	String getTitre();
}
