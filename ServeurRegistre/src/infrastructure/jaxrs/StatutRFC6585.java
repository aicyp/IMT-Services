package infrastructure.jaxrs;

public enum StatutRFC6585 {
	
	PRECONDITION_REQUIRED(428);
	
	private int code;
	
	private StatutRFC6585(int n){
		this.code = n;
	}
	
	public int getCodeStatut(){
		return this.code;
	}
}
