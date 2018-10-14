package ou5;

class Punkt {
	private String namn = "";
	private int x = 0;
	private int y = 0;
	//Konstruktor
	public Punkt(String n, int i, int j) {
		namn = n;
		x = i;
		y = j;
	}
	//Konstruktor som skapar en kopia av Punkt-objektet i parametern
	public Punkt(Punkt p){ 
		x = p.x;
		y = p.y;
		namn = p.namn;
	}
	//Returnerar objektets information (namn, x, y) i form av en sträng
	public String toString() {
		String s = "";
		s = "(" + namn + ", " + x + ", " + y + ")";
		return s;
	}
	//Returnerar objektets namn som en sträng
	public String getNamn (){
		return namn;
	}
	//Returnerar objektets x-koordinat som ett heltal
	public int getX(){
		return x;
	}
	//Returnerar objektets y-koordinat som ett heltal
	public int getY(){
		return y;
	}
	//Returnerar objektets avstånd till Punkt-objektet i parameterreferensen
	public double avstand (Punkt p){
		int pX = p.getX();
		int pY = p.getY();
		
		double avstand = Math.sqrt((Math.pow((pX-x), 2))+Math.pow((pY-y),2)); //avståndsformeln
		return avstand;
	}
	//Ändrar objektets namn
	public void setNamn(String namn){
		this.namn = namn;
	}
	//ändrar objektets x-koordinat
	public void setX(int i){
		this.x = i;
	}
	//ändrar objektets y-koordinat
	public void setY(int j){
		this.y = j;
	}
	
}
