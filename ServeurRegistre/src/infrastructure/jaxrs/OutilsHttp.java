package infrastructure.jaxrs;

import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

public class OutilsHttp {
	public static void setEtag(ContainerResponseContext reponse, int n){
		MultivaluedMap<String, Object> enTetes = reponse.getHeaders();
		enTetes.putSingle(HttpHeaders.ETAG, new EntityTag(Integer.toString(n)));
	}
	public static EntityTag etag(int n){
		return new EntityTag(Integer.toString(n));
	}
}
