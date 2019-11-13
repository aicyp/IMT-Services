package infrastructure.jaxrs;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "liste")
public class HyperLiens<T> {

	private List<HyperLien<T>> liens;

	public HyperLiens() {
		liens = new LinkedList<>();
	}

	public HyperLiens(List<HyperLien<T>> l) {
		this.liens = l;
	}

	@XmlElement(name="hyperlien")
	public List<HyperLien<T>> getLiens() {
		return liens;
	}

	public void setLiens(List<HyperLien<T>> l) {
		this.liens = l;
	}

	public String toString(){
		String res = "";
		for(HyperLien<T> l : liens){
			res = res + " " + l.toString();
		}
		return res;
	}
}