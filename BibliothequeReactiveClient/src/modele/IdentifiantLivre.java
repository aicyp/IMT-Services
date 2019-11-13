package modele;

public interface IdentifiantLivre {
	public static IdentifiantLivre fromString(String id){
		return new ImplemIdentifiantLivre(id);
	}
	String getId();
}
