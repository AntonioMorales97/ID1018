package eu4;

public class NPolylinje implements Polylinje {
	
	//En nod-nästlad-klass
	private static class Nod {
		//instansvariabler
		public Punkt horn;
		public Nod nastaNod;
		//Konstruktor
		public Nod(Punkt horn) {
			this.horn = horn;
			nastaNod = null;
		}
	}
	//instansvariabler
	private Nod horn;
	private String farg = "Svart";
	private int bredd = 1; // pixlar
	
	private Nod forstaNod;
	
	//Konstruktor 1
	public NPolylinje() {
		this.horn = null;
		this.forstaNod = null;
	}
	
	//Konstruktor 2
	//första hornet i listan blir polylinjens första nod
	//hörnet efter blir den nodens nästa-nod osv (sista blir null)
	public NPolylinje(Punkt[] horn) {
		if (horn.length > 0) {
			Nod nod = new Nod(new Punkt(horn[0]));  
			this.forstaNod = nod; 
			int pos = 1;
			while (pos < horn.length) {
				nod.nastaNod = new Nod(new Punkt(horn[pos++])); 
				nod = nod.nastaNod; 
			}
		}
	}
	
	//Presenterar Polylinjen som en sträng
	//Visar första noden och nodens referens, dvs noden efter
	//osv för alla noder som finns i polylinjen
	public String toString(){
		String s = "";
		if (this.forstaNod != null){
			Nod nod = this.forstaNod;
			while (nod.nastaNod != null){
				s = s + nod.horn.getNamn() + "===>" 
						+ nod.nastaNod.horn.getNamn()+"\n";
				nod = nod.nastaNod;
			}
			//s = s + nod.horn.getNamn(); //Sista nod
		}
		return s;
	}
	
		//räknar antal noder i objektet
		//sedan skapar den en ny Punkt-objekt som är en kopia av punkt-objektet för resp nod
		//och lägger till i en vektor av Punkt-objket
		//returnerar denna Punkt-vektor
		public Punkt[] getHorn(){
				Nod nod = this.forstaNod;
				int antalHorn = 1;
				while (nod.nastaNod != null){
					antalHorn++;
					nod = nod.nastaNod;
				}
				Punkt [] kopiaHorn = new Punkt [antalHorn];
				nod = this.forstaNod;
				for(int i = 0; i<antalHorn; i++){
					kopiaHorn[i] = new Punkt (nod.horn);
					nod = nod.nastaNod;		
				}
			
			return kopiaHorn;
		}
	
		//Returnerar objektets färg som en sträng							
		public String getFarg (){
			return this.farg;
		}
		//Returnerar objektets bredd som ett heltal
		public int getBredd (){
			return this.bredd;
		}
		//Ändrar objektets färg till färg-strängen i parametern
		public void setFarg(String farg){
			this.farg = farg;
		}
		//Ändrar objektets bredd till heltalet i parametern
		public void setBredd (int bredd){
			this.bredd = bredd;
		}

		//Om det finns minst en nod, så går den igenom varje nod
		//och räknar ut avståndet mellan en nod och noden efter
		//genom att använda Punkt-metoden avstand.
		//returnerar längden som double-typ.
		public double langd (){
			double langd = 0;
			if (this.forstaNod != null){
				Nod nod = this.forstaNod;
				while (nod.nastaNod != null){
					langd += nod.horn.avstand(nod.nastaNod.horn);
					nod = nod.nastaNod; //"flyttas fram"
				}
			}else{
				System.out.println("Denna NPolylinje är tom.");
			}
			return langd;
		}
		
		//Lägger till Punkt-objektet i parametern i en nod i NPolylinje-objektets som första nod
		public void laggTill (Punkt horn){ 
			Nod nyNod = new Nod (horn);
			nyNod.nastaNod = this.forstaNod; //nyNod's "nastaNod" ska vara nuvarande forstaNod, dvs en referens till tidigare nod
			this.forstaNod = nyNod; //forstaNod blir nu nyNod, tar dennes plats
		}
		
		//Skapar en ny Nod-objekt som innehåller Punkt-objektet i referensen
		//och lägger till denna nod framför noden som innehåller Punkt-objektet
		//som motsvarar namnet i referensen
		public void laggTillFramfor(Punkt horn, String namn){ 
			if (this.forstaNod != null){ 
				Nod nod = this.forstaNod;
				while(nod.nastaNod != null){ //så länge det finns noder
					if(nod.nastaNod.horn.getNamn()==namn){ //om den inte är i första noden
						Nod temp = nod.nastaNod; //lägger in nuvarande-nästa nod i en temporär nod
						nod.nastaNod = new Nod(horn); //nuvarande-nästa nod, ny nod med Punkt-objektet refereras istället
						nod.nastaNod.nastaNod = temp; //och den nya nodens referens (nästa nod) är den temporära noden
						return; //lämna metoden				
					} else if (this.forstaNod.horn.getNamn()==namn){ //om det är första noden
						/*Nod temp = this.forstaNod;
						this.forstaNod = new Nod (new Punkt(horn)); //ska det vara en kopia av referensen?
						forstaNod.nastaNod = temp;*/
						laggTill(horn); 
					}
					nod = nod.nastaNod;
				}			
			}
			else{ //om den är tom
				this.forstaNod = new Nod (horn);
			}
		}
		
		
		public void taBort (String namn){
			Nod nuvarandeNod = forstaNod;
			Nod tidigareNod = forstaNod; //ty det finns ingen tidigare atm
			
			while(nuvarandeNod.horn.getNamn()!= namn){
				
				if (nuvarandeNod.nastaNod == null){
					System.out.println("Kunde ej hittas");
				}else{
					tidigareNod = nuvarandeNod;
					nuvarandeNod = nuvarandeNod.nastaNod;
				}
			}
			if (nuvarandeNod == forstaNod){
				forstaNod = forstaNod.nastaNod;
			}
			else{
				tidigareNod.nastaNod = nuvarandeNod.nastaNod;
			}
			
		}
		
		public class NPolyIterator implements java.util.Iterator<Punkt>{
			private Nod nuvarande = null;
			private Nod returnerad = null;
			
			public NPolyIterator(){
				if(NPolylinje.this.forstaNod != null){
					nuvarande = NPolylinje.this.forstaNod;
				}
			}
			
			public boolean hasNext(){
				return nuvarande != null; //om det är nuvarande.nastaNod så kommer det bli false på sista
			}
			
			public Punkt next()throws java.util.NoSuchElementException{
				if (this.nuvarande == null){
					throw new java.util.NoSuchElementException("Slut av iterationen.");
				}
				returnerad = nuvarande;
				nuvarande = nuvarande.nastaNod;
				return returnerad.horn;
			}
		}
		public java.util.Iterator<Punkt> iterator (){
			return new NPolyIterator();
		}
}
