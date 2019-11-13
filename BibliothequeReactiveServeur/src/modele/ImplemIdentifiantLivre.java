package modele;

public class ImplemIdentifiantLivre implements IdentifiantLivre {
	private String id;
	
	public ImplemIdentifiantLivre(){
	}
	
	public ImplemIdentifiantLivre(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof  IdentifiantLivre){
			IdentifiantLivre id = (IdentifiantLivre)obj; 
			return this.getId().equals(id.getId());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Integer.parseInt(this.getId());
	}
	@Override
	public String toString() {
		return this.getId();
	}

	
}
