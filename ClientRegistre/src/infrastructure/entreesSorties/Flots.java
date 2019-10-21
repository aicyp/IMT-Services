package infrastructure.entreesSorties;

import java.io.IOException;

public class Flots {

	public static byte[] convertirFlotEnOctets(Object flot) {
		byte[] caracteres = null;
		if(flot instanceof java.io.InputStream){
			java.io.InputStream flotReel = (java.io.InputStream)flot;
			try {
				caracteres = new byte[flotReel.available()];
				flotReel.read(caracteres);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return caracteres;
	}
	public static String convertirFlotEnMot(Object flot) {
		String mot = "";
		if(flot instanceof java.io.InputStream){
			java.io.InputStream flotReel = (java.io.InputStream)flot;
			try {
				byte[] lettres = new byte[flotReel.available()];
				flotReel.read(lettres);
				mot = new String(lettres);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return mot;
	}
	
	
	
}
