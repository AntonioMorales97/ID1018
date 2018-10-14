package eu4;

public class VPolylinje implements Polylinje { //iterator metod?
	private Punkt[]    horn; 
	private String farg = "Svart";
	private int		   bredd = 1;
	//Konstruktor som skapar VPolylinje med 0 hörn
	public VPolylinje (){
		this.horn = new Punkt[0];
	}
	//Konstruktor som skapar VPolylinje-objekt från Punkt-vektorn
	// i parameterreferensen
	public VPolylinje (Punkt[] horn){
		this.horn = new Punkt[horn.length];
		for (int i = 0; i < horn.length; i++)
			this.horn[i] = new Punkt (horn[i]);
	}
	//Returnerar en sträng med objektets information (hörn (punkterna),färg, bredd)
	public String toString(){
		String VPolylinje = "{";
		for (int i = 0; i < this.horn.length; i++){
			VPolylinje = VPolylinje + horn[i];
		}
		VPolylinje = VPolylinje +"}"+", "+farg+", "+bredd;
		return VPolylinje;
	}
	//Returnerar en vektor som är en kopia av objektets hörn
	public Punkt[] getHorn (){ 
		Punkt[] kopiaHorn = new Punkt[this.horn.length];
		for (int i = 0; i<=this.horn.length-1;i++){
			
			kopiaHorn[i]=new Punkt (horn[i]); //kopia
		}
		return kopiaHorn;				 
	}							
	//Returnerar objektets färg som en sträng							
	public String getFarg (){
		return farg;
	}
	//Returnerar objektets bredd som ett heltal
	public int getBredd (){
		return bredd;
	}
	//Ändrar objektets färg till färg-strängen i parametern
	public void setFarg(String farg){
		this.farg = farg;
	}
	//Ändrar objektets bredd till heltalet i parametern
	public void setBredd (int bredd){
		this.bredd = bredd;
	}
	//Returnerar objektets längd (summan av längderna mellan punkterna i VPolylinjen)
	public double langd (){
		double langd = 0;
		for (int i = 0; i < (this.horn.length-1); i++){  //index
			langd += horn[i].avstand(horn[i+1]);
		}
		return langd;
	}
	//Lägger till Punkt-objektet i parametern i VPolylinje-objektet
	public void laggTill (Punkt horn){
		Punkt[] h = new Punkt[this.horn.length+1];
		int i = 0;
		for (i=0; i < this.horn.length;i++)
			h[i]=this.horn[i];
		h[i] = new Punkt (horn);
		
		this.horn=h;
	}
	//Lägger till punkt-objektet i parametern framför den
	//redan existerande punkten i VPolylinjen med namnet enligt parametern
	public void laggTillFramfor(Punkt horn, String namn){
		Punkt[] h = new Punkt[this.horn.length+1];
		Punkt nyHorn = horn;
		int position = 0;
		
		boolean hittadNamn = false;
		for (int i = 0; i < this.horn.length && !hittadNamn; i++){
			if(namn==this.horn[i].getNamn()){
				h[i]=nyHorn;
				position = i;
				hittadNamn=true;
			}
			
			else{
			h[i]=this.horn[i];
			}
		}
		for (int j = position+1; j<=this.horn.length;j++){
			h[j]=this.horn[j-1];
		}
		
		this.horn=h;
	}
	//Tar bort hörnet med namnet enligt parametern
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
	/*//returnerar en VPolylinjeIterator-objekt för VPolylinje-objektet
	public VPolylinjeIterator getIterator(){
		return new VPolylinje.VPolylinjeIterator();
	}*/
	
	public class VPolylinjeIterator implements java.util.Iterator<Punkt>{ 
		private int aktuell = -1;
		private int raknare = VPolylinje.this.horn.length;
		//Konstruktor
		public VPolylinjeIterator(){
			if(VPolylinje.this.horn.length>0){
				aktuell=0;
			}
		}
		
		public boolean hasNext(){
			
			return raknare >0;//VPolylinje.this.horn[aktuell+1]!=null;
		}
		//returnerar den aktuella punkt-objektet i VPolylinje-objektet
		public Punkt next() throws java.util.NoSuchElementException{
			Punkt horn = null;
			if (this.aktuell ==-1 || this.aktuell > VPolylinje.this.horn.length){
				throw new java.util.NoSuchElementException(
										"slut av iterationen");
				
			}
			horn = VPolylinje.this.horn[aktuell];
			aktuell++;
			raknare--;
			
			return horn;
		}	
	}

	public java.util.Iterator<Punkt> iterator(){
		return new VPolylinjeIterator();
	}
}




	

