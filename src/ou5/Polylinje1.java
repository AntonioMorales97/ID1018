package ou5;

public class Polylinje1 {
	private Punkt[]    horn;
	private String     farg;
	private int		   bredd = 1;
	
	public Polylinje1 (){
		this.horn = new Punkt[0];
	}
	
	public Polylinje1 (Punkt[] horn){
		this.horn = new Punkt[horn.length];
		for (int i = 0; i < horn.length; i++)
			this.horn[i] = new Punkt (horn[i]);
	}
	
	public String toString(){
		String polylinje = "{";
		for (int i = 0; i < this.horn.length; i++){
			polylinje = polylinje + horn[i];
		}
		polylinje = polylinje +"}"+", "+farg+", "+bredd;
		return polylinje;
	}

	public Punkt[] getHorn (){ 
		return horn;				 
	}							
								
	public String getFarg (){
		return farg;
	}
	
	public int getBredd (){
		return bredd;
	}
	
	public void setFarg(String farg){
		this.farg = farg;
	}
	
	public void setBredd (int bredd){
		this.bredd = bredd;
	}
	
	public double langd (){
		double langd = 0;
		for (int i = 0; i < (this.horn.length-1); i++){  //index
			langd += horn[i].avstand(horn[i+1]);
		}
		return langd;
	}
	
	public void laggTill (Punkt horn){
		Punkt[] h = new Punkt[this.horn.length+1];
		int i = 0;
		for (i=0; i < this.horn.length;i++)
			h[i]=this.horn[i];
		h[i] = new Punkt (horn);
		
		this.horn=h;
	}
	
	public void laggTillFramfor(Punkt horn, String namn){
		Punkt[] h = new Punkt[this.horn.length+1];
		int position = 0;
		
		for (int i = 0; i < this.horn.length; i++){
			if(namn==this.horn[i].getNamn()){
				h[i]=horn;
				position = i;
				break;
				}
			h[i]=this.horn[i];
			}
		for (int j = position+1; j<=this.horn.length;j++){
			h[j]=this.horn[j-1];
		}
		
		this.horn=h;
	}
	
	public void taBort (String namn){
		Punkt[] h = new Punkt[this.horn.length-1];
		int j = 0;
		for (int i = 0; i<=(this.horn.length-1);i++){
			if (namn!=horn[i].getNamn()){
				h[j]=horn[i];
				j++;
			}
		}
		this.horn = h;
	}
}
