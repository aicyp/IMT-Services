package infrastructure.entreesSorties;

import java.io.IOException;

public class Flots {

	public static byte[] convertirFlotEnOctets(java.io.InputStream flot) {
		byte[] caracteres = null;
		try {
			caracteres = new byte[flot.available()];
			flot.read(caracteres);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return caracteres;
	}

}
