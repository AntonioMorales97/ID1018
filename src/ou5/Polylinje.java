package ou5;

public class Polylinje {
	private Punkt[]    horn; 
	private String     farg;
	private int		   bredd = 1;
	//Konstruktor som skapar polylinje med 0 h�rn
	public Polylinje (){
		this.horn = new Punkt[0];
	}
	//Konstruktor som skapar polylinje-objekt fr�n Punkt-vektorn
	// i parameterreferensen
	public Polylinje (Punkt[] horn){
		this.horn = new Punkt[horn.length];
		for (int i = 0; i < horn.length; i++)
			this.horn[i] = new Punkt (horn[i]);
	}
	//Returnerar en str�ng med objektets information (h�rn (punkterna),f�rg, bredd)
	public String toString(){
		String polylinje = "{";
		for (int i = 0; i < this.horn.length; i++){
			polylinje = polylinje + horn[i];
		}
		polylinje = polylinje +"}"+", "+farg+", "+bredd;
		return polylinje;
	}
	//Returnerar en vektor som �r en kopia av objektets h�rn
	public Punkt[] getHorn (){ 
		Punkt[] kopiaHorn = new Punkt[this.horn.length];
		for (int i = 0; i<=this.horn.length-1;i++){
			
			kopiaHorn[i]=new Punkt (horn[i]); //kopia
		}
		return kopiaHorn;				 
	}							
	//Returnerar objektets f�rg som en str�ng							
	public String getFarg (){
		return farg;
	}
	//Returnerar objektets bredd som ett heltal
	public int getBredd (){
		return bredd;
	}
	//�ndrar objektets f�rg till f�rg-str�ngen i parametern
	public void setFarg(String farg){
		this.farg = farg;
	}
	//�ndrar objektets bredd till heltalet i parametern
	public void setBredd (int bredd){
		this.bredd = bredd;
	}
	//Returnerar objektets l�ngd (summan av l�ngderna mellan punkterna i polylinjen)
	public double langd (){
		double langd = 0;
		for (int i = 0; i < (this.horn.length-1); i++){  //index
			langd += horn[i].avstand(horn[i+1]);
		}
		return langd;
	}
	//L�gger till Punkt-objektet i parametern i polylinje-objektet
	public void laggTill (Punkt horn){
		Punkt[] h = new Punkt[this.horn.length+1];
		int i = 0;
		for (i=0; i < this.horn.length;i++)
			h[i]=this.horn[i];
		h[i] = new Punkt (horn);
		
		this.horn=h;
	}
	//L�gger till punkt-objektet i parametern framf�r den
	//redan existerande punkten i polylinjen med namnet enligt parametern
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
	//Tar bort h�rnet med namnet enligt parametern
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
	//returnerar en polylinjeIterator-objekt f�r polylinje-objektet
	public PolylinjeIterator getIterator(){
		return new Polylinje.PolylinjeIterator();
	}
	
	class PolylinjeIterator{ 
		private int aktuell = -1;
		//Konstruktor
		public PolylinjeIterator(){
			if(Polylinje.this.horn.length>0){
				aktuell=0;
			}
		}
		//returnerar true eller false beroende p� instansvariabeln aktuell 
		//om aktuell �r -1, s� finns det inga h�rn i polylinjen
		public boolean finnsHorn(){
			return aktuell !=-1;
		}
		//returnerar den aktuella punkt-objektet i polylinje-objektet
		public Punkt horn() throws java.util.NoSuchElementException{
			if (!this.finnsHorn())
				throw new java.util.NoSuchElementException(
										"slut av iterationen");
			Punkt horn = Polylinje.this.horn[aktuell];
			return horn;
		}
		//G�r fram i polylinje-objektet om det finns h�rn kvar 
		//genom att addera aktuell med 1. Annars s�tts aktuell till
		//-1 och betyder att det ej finns h�rn kvar att g� till.
		public void gaFram(){
			if(aktuell >= 0 &&
			   aktuell < Polylinje.this.horn.length-1)
			   aktuell++;
			else
				aktuell=-1;
		}
	}	
}




	

