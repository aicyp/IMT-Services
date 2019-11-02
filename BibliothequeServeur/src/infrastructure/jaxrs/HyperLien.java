package infrastructure.jaxrs;


import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="hyperlien")
public class HyperLien <T>{
	
	private URI uri;
	
	public HyperLien(URI uri, Class<?> typeRessource){
		this.uri = UriBuilder.fromUri(uri).path(typeRessource).build();
	}
	
	public HyperLien(URI uri, Class<?> typeRessource, String nomMethode){
		this.uri = UriBuilder.fromUri(uri).path(typeRessource).path(typeRessource, nomMethode).build();
	}
	
	public HyperLien(URI uri){
		this.uri = uri;
	}
	
	public HyperLien(String uri) {
		super();
		try {
			this.uri = new URI(uri);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public HyperLien(){
		this("");
	}

	@XmlAttribute(name="uri")
	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}
	
	public String toString(){
		return this.getUri().toString();
	}
}
