package infrastructure.langage;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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
}
