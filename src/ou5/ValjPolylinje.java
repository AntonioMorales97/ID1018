package ou5;
import java.util.*;
public class ValjPolylinje {
	public static final Random rand = new Random();
	public static final int ANTAL_POLYLINJER = 10;
	
	public static void main (String[] args){
		//Skapa ett antal slumpmässiga polylinjer
		Polylinje[] polylinjer = new Polylinje[ANTAL_POLYLINJER];
		for (int i = 0; i < ANTAL_POLYLINJER;i++)
			polylinjer[i]=slumpPolylinje();
		
		System.out.println("Detta är polylinjerna:");
		for (int i = 0; i <ANTAL_POLYLINJER;i++){
			System.out.println(polylinjer[i]);
		}
		double kortastLangd = 10000; //ge ett "gult" startvärde, onödigt?
		int kortastGulPolyIndex = -1; //något overkligt
		for (int i = 0; i< polylinjer.length;i++ ){ 
			if (polylinjer[i].getFarg()=="Gul"&&polylinjer[i].langd()<kortastLangd){ //jämföra gula OCH längderna
				kortastLangd = polylinjer[i].langd();
				kortastGulPolyIndex = i;
			}
		}
		if(kortastGulPolyIndex==-1){
			System.out.println("Finns inga gula polylinjer, testa igen!");
		}
		else{
			System.out.println("Detta är den kortaste gula polylinjen:");
			System.out.print(polylinjer[kortastGulPolyIndex]);
		}
	}
	/*
	 slumpPunkt returnerar en punkt med ett slump-
	 mässigt namn, som är en stor bokstav i det
	 engelska alfabetet, och slumpmässiga ko-
	 ordinater.
	 */
	public static Punkt slumpPunkt(){
		String n = ""+(char)(65+rand.nextInt(26));
		int x = rand.nextInt(11);
		int y = rand.nextInt(11);
		
		return new Punkt (n,x,y);
	}
	/*
	 slumpPolylinje returnerar en slumpmässig polylinje,
	 vars färg är antingen blå, röd eller gul. Namn på
	 polylinjens hörn stora bokstäver i det engelska
	 alfabetet. Två hörn kan inte ha samma namn.
	 */
	public static Polylinje slumpPolylinje(){
		Polylinje polylinje = new Polylinje();
		int antalHorn = 2+rand.nextInt(7);
		int antalValdaHorn = 0;
		boolean [] valdaNamn = new boolean[26]; //default false
		Punkt valdPunkt = null;
		char valtChar = 0; //funkade ej, gick långsamt eller fastnade i loopen?
		while (antalValdaHorn < antalHorn){ 
			valdPunkt = slumpPunkt();
			valtChar = valdPunkt.getNamn().charAt(0);
			while(valdaNamn[(int)valdPunkt.getNamn().charAt(0)%65]){ //skulle varit här, håller på tills false
				valdPunkt.setNamn(""+(char)(65+rand.nextInt(26))); //slumpar ny namn
			}
			polylinje.laggTill(valdPunkt);
			valdaNamn[(int)valdPunkt.getNamn().charAt(0)%65]=true; //och här, sätter valda namnet till true i listan
			antalValdaHorn++;
		}
		polylinje.setFarg(randFarg());
		return polylinje;
	}
	//Returnerar en slumpad färg som en sträng
	public static String randFarg (){
		String[]farger={"Blå","Gul","Rod"};
		String farg =farger[rand.nextInt(3)];
		return farg;
	}

}
