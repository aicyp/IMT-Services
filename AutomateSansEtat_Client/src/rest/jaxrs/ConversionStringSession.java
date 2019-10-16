package rest.jaxrs;

import rest.ImplemSession;
import rest.Session;

public class ConversionStringSession {
	public static Session fromString(String x) {
		return new ImplemSession(x);
	}
}
