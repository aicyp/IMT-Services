package modele;

public interface IdentifiantLivre {
	String getId();	
	
	public static IdentifiantLivre fromString(String x) {
		return new ImplemIdentifiantLivre(x);
	}
}
