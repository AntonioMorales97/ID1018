package ou5;

public class PolylinjeTest {
	public static void main(String[] args) {
		Punkt punktA = new Punkt("A", 3, 4);
		Punkt punktB = new Punkt("B", 5, 6);
		Punkt[] vektorPunkter = { punktA, punktB };
		Polylinje A = new Polylinje(vektorPunkter);
		A.setFarg("Blå");
		System.out.println(A);
		System.out.println(A.langd());
		Punkt punktC = new Punkt("C", 7, 8);
		A.laggTillFramfor(punktC, "B");
		System.out.println(A);
		System.out.println(A.getHorn());
		// A.taBort("B");
		//System.out.println(A);
		System.out.println(" ");
		
		Polylinje.PolylinjeIterator polyIterator;

		polyIterator = A.getIterator();
		boolean finnsHorn = polyIterator.finnsHorn();
		Punkt aktuellPunkt = polyIterator.horn();
		System.out.println("Finns det hörn kvar: " + finnsHorn);

		while (polyIterator.finnsHorn()) {
			aktuellPunkt = polyIterator.horn();
			System.out.println(aktuellPunkt.toString());
			polyIterator.gaFram();

			finnsHorn = polyIterator.finnsHorn();
			System.out.println("Finns det hörn kvar: " + finnsHorn);
			
		}
		//pd.horn(); //ifall det inte finns fler hörn
	}

}
