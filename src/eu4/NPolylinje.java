package eu4;

public class NPolylinje implements Polylinje {
	
	//En nod-n�stlad-klass
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
	//f�rsta hornet i listan blir polylinjens f�rsta nod
	//h�rnet efter blir den nodens n�sta-nod osv (sista blir null)
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
	
	//Presenterar Polylinjen som en str�ng
	//Visar f�rsta noden och nodens referens, dvs noden efter
	//osv f�r alla noder som finns i polylinjen
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
	
		//r�knar antal noder i objektet
		//sedan skapar den en ny Punkt-objekt som �r en kopia av punkt-objektet f�r resp nod
		//och l�gger till i en vektor av Punkt-objket
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
	
		//Returnerar objektets f�rg som en str�ng							
		public String getFarg (){
			return this.farg;
		}
		//Returnerar objektets bredd som ett heltal
		public int getBredd (){
			return this.bredd;
		}
		//�ndrar objektets f�rg till f�rg-str�ngen i parametern
		public void setFarg(String farg){
			this.farg = farg;
		}
		//�ndrar objektets bredd till heltalet i parametern
		public void setBredd (int bredd){
			this.bredd = bredd;
		}

		//Om det finns minst en nod, s� g�r den igenom varje nod
		//och r�knar ut avst�ndet mellan en nod och noden efter
		//genom att anv�nda Punkt-metoden avstand.
		//returnerar l�ngden som double-typ.
		public double langd (){
			double langd = 0;
			if (this.forstaNod != null){
				Nod nod = this.forstaNod;
				while (nod.nastaNod != null){
					langd += nod.horn.avstand(nod.nastaNod.horn);
					nod = nod.nastaNod; //"flyttas fram"
				}
			}else{
				System.out.println("Denna NPolylinje �r tom.");
			}
			return langd;
		}
		
		//L�gger till Punkt-objektet i parametern i en nod i NPolylinje-objektets som f�rsta nod
		public void laggTill (Punkt horn){ 
			Nod nyNod = new Nod (horn);
			nyNod.nastaNod = this.forstaNod; //nyNod's "nastaNod" ska vara nuvarande forstaNod, dvs en referens till tidigare nod
			this.forstaNod = nyNod; //forstaNod blir nu nyNod, tar dennes plats
		}
		
		//Skapar en ny Nod-objekt som inneh�ller Punkt-objektet i referensen
		//och l�gger till denna nod framf�r noden som inneh�ller Punkt-objektet
		//som motsvarar namnet i referensen
		public void laggTillFramfor(Punkt horn, String namn){ 
			if (this.forstaNod != null){ 
				Nod nod = this.forstaNod;
				while(nod.nastaNod != null){ //s� l�nge det finns noder
					if(nod.nastaNod.horn.getNamn()==namn){ //om den inte �r i f�rsta noden
						Nod temp = nod.nastaNod; //l�gger in nuvarande-n�sta nod i en tempor�r nod
						nod.nastaNod = new Nod(horn); //nuvarande-n�sta nod, ny nod med Punkt-objektet refereras ist�llet
						nod.nastaNod.nastaNod = temp; //och den nya nodens referens (n�sta nod) �r den tempor�ra noden
						return; //l�mna metoden				
					} else if (this.forstaNod.horn.getNamn()==namn){ //om det �r f�rsta noden
						/*Nod temp = this.forstaNod;
						this.forstaNod = new Nod (new Punkt(horn)); //ska det vara en kopia av referensen?
						forstaNod.nastaNod = temp;*/
						laggTill(horn); 
					}
					nod = nod.nastaNod;
				}			
			}
			else{ //om den �r tom
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
				return nuvarande != null; //om det �r nuvarande.nastaNod s� kommer det bli false p� sista
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
