package eu4;
import java.util.Random;

public class TestPolys {
	public static final Random rand = new Random();
	public static final int ANTAL_POLYLINJER = 10;
	public static void main (String [] args){
		/*
		Polylinje [] polylinjer = new Polylinje [ANTAL_POLYLINJER];
		for (int i = 0; i < polylinjer.length; i++){
			polylinjer[i] = slumpNPolylinje(); //bestäm här om V/N
			System.out.println(polylinjer[i].getFarg() + "\t"+polylinjer[i].langd()); //visar vilka polys som skapats
		}
		Polylinje kGul = Polylinjer.kortastGula(polylinjer);
		System.out.println(kGul);
		System.out.println(kGul.langd()); */
		
		//testMetoder();
		testMix();
	}
	
	public static void testMetoder (){
		Polylinje polylinje = null;
		
		Punkt [] hörn = new Punkt[3];
		hörn [0] = new Punkt ("X",3,4);
		hörn [1] = new Punkt ("Z",5,6);
		hörn [2] = new Punkt ("Y",3,5);
		
		polylinje = new VPolylinje (hörn);
		//polylinje = new VPolylinje (hörn);
		
		System.out.println(polylinje);
		
		Punkt ny = new Punkt ("A",5,6);
		
		polylinje.laggTillFramfor(ny, "Z");
		
		System.out.println(polylinje);
		
		polylinje.taBort("A");
		
		System.out.println(polylinje);
		
		System.out.println(polylinje.langd());
		
		Punkt [] kopia =  polylinje.getHorn();
		
		//System.out.println(kopia[2]);
		
		for (Punkt horn: polylinje)
			System.out.println(horn);
	}
	
	public static void testMix(){ //50/50
		Polylinje [] mixPolylinjer = new Polylinje [ANTAL_POLYLINJER];
		for(int i = 0; i < 5; i++){
			mixPolylinjer[i] = slumpNPolylinje();
			System.out.println(mixPolylinjer[i].getFarg() + "\t" + mixPolylinjer[i].langd());
		}
		for (int j = 5; j<10; j++){
			mixPolylinjer[j] = slumpVPolylinje();
			System.out.println(mixPolylinjer[j].getFarg()+"\t"+mixPolylinjer[j].langd());
		}
		Polylinje kGul = Polylinjer.kortastGula(mixPolylinjer);
		System.out.println(kGul);
		System.out.println(kGul.langd());
	}
	
	public static Punkt slumpPunkt(){
		String n = ""+(char)(65+rand.nextInt(26));
		int x = rand.nextInt(10);
		int y = rand.nextInt(10);
		
		return new Punkt (n,x,y);
	}
	
	public static Polylinje slumpNPolylinje(){
		Polylinje polylinje = new NPolylinje(); //NPoly
		int antalHorn = 2+rand.nextInt(7);
		int antalValdaHorn = 0;
		boolean [] valdaNamn = new boolean[26]; 
		Punkt valdPunkt = null;
		char valtChar = 0; 
		while (antalValdaHorn < antalHorn){ 
			valdPunkt = slumpPunkt();
			valtChar = valdPunkt.getNamn().charAt(0);
			while(valdaNamn[(int)valdPunkt.getNamn().charAt(0)%65]){ 
				valdPunkt.setNamn(""+(char)(65+rand.nextInt(26))); //slumpar ny namn
			}
			polylinje.laggTill(valdPunkt);
			valdaNamn[(int)valdPunkt.getNamn().charAt(0)%65]=true; 
			antalValdaHorn++;
		}
		polylinje.setFarg(randFarg());
		return polylinje;
	}
	
	public static Polylinje slumpVPolylinje(){
		Polylinje polylinje = new VPolylinje(); //VPoly
		int antalHorn = 2+rand.nextInt(7);
		int antalValdaHorn = 0;
		boolean [] valdaNamn = new boolean[26]; 
		Punkt valdPunkt = null;
		char valtChar = 0; 
		while (antalValdaHorn < antalHorn){ 
			valdPunkt = slumpPunkt();
			valtChar = valdPunkt.getNamn().charAt(0);
			while(valdaNamn[(int)valdPunkt.getNamn().charAt(0)%65]){ 
				valdPunkt.setNamn(""+(char)(65+rand.nextInt(26))); //slumpar ny namn
			}
			polylinje.laggTill(valdPunkt);
			valdaNamn[(int)valdPunkt.getNamn().charAt(0)%65]=true; 
			antalValdaHorn++;
		}
		polylinje.setFarg(randFarg());
		return polylinje;
	}
	//Returnerar en slumpad färg som en sträng
	public static String randFarg (){
		String[]farger={"Blå","Gul","Röd"};
		String farg =farger[rand.nextInt(3)];
		return farg;
	}

}
