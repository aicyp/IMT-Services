package infrastructure.langage;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

import javax.ws.rs.core.GenericType;

import infrastructure.jaxrs.HyperLien;
import modele.Bibliotheque;
import modele.Livre;

public class Types {
	public static Class<?> convertirTypeEnClasse(Type t){
		if(t instanceof Class<?>) {
			return (Class<?>)t;
		}
		if(t instanceof ParameterizedType) {
			return (Class<?>)(((ParameterizedType) t).getRawType());
		}
		throw new UnsupportedOperationException();
	}
	
	public static GenericType<Optional<HyperLien<Livre>>> typeRetourChercherAsync(){
		return typeRetourChercherAsync;
	}
	
	private static GenericType<Optional<HyperLien<Livre>>>  typeRetourChercherAsync;
	static {
		Method m = null;
		try {
			m = Bibliotheque.class.getDeclaredMethod("chercher", Livre.class);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		typeRetourChercherAsync = new GenericType<Optional<HyperLien<Livre>>>(m.getGenericReturnType());
	}
}
