package modele;


public class Versionneur {

	private int version;
	private Object ressource;
	
	public Versionneur(Object d) {
		System.out.println("Initialisation d'un versionneur " + this + " : " + this.getClass());
		System.out.println("- Version initiale : 0");
		System.out.println("- Ressource : " + d + " : " + d.getClass());
		this.version = 0;
		this.ressource = d;
	}

	public Object getRessourceMutable(){
		return this.ressource;
	}
	
	public int getVersion() {
		return this.version;
	}
	
	public void incrementerVersion(){
		this.version++;
	}

}



