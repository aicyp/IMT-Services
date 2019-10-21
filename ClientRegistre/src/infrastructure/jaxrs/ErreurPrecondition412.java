package infrastructure.jaxrs;

public class ErreurPrecondition412 {
	private boolean erreur412;
	private int reprises;
	
	public ErreurPrecondition412(){
		System.out.println("Initialisation d'un gestionnaire d'erreur 412 " + this + " : " + this.getClass());
		erreur412 = false;
		reprises = 0;
	}
	
	public int getReprises() {
		return reprises;
	}

	public boolean erreur412() {
		if (this.erreur412) {
			this.reprises++;
			this.erreur412 = false;
			return true;
		}
		return false;
	}

	public void leverErreur412(){
		this.erreur412 = true;
	}
}
