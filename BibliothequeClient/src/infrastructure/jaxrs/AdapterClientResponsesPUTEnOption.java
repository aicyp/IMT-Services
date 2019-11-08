package infrastructure.jaxrs;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

import infrastructure.langage.Types;

@Provider
@Priority(Priorities.HEADER_DECORATOR + 2)
public class AdapterClientResponsesPUTEnOption implements ReaderInterceptor {

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		Object ret;
		if (context.getType().equals(Optional.class)) {
			ParameterizedType genericType = (ParameterizedType) context.getGenericType();
			Type actualType = genericType.getActualTypeArguments()[0];

			context.setType(Types.convertirTypeEnClasse(actualType));
			ret = Optional.of(context.proceed());
		} else {
			ret = context.proceed();
		}
		return ret;
	}

}
