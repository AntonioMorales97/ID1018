package ou2;
/***************************************************
 Detta är OU2.
 I denna klass finns huvudmetoden där man matar in en triangels
 alla tre sidor och använder metoder i klassen Triangel för att 
 räkna ut värden. I denna version så räknar man ut radien av 
 cirkeln inuti triangeln och skriver ut det. Sedan räknar man ut
 radien av cirkeln utanför triangeln och skriver ut det.
 **************************************************/
import java.util.*;

public class EnTriangelOchDessCirklar {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		in.useLocale(Locale.US);
		System.out.println("Mata in triangelns sidor: ");
		double a = in.nextDouble();
		double b = in.nextDouble();
		double c = in.nextDouble();
		in.close();
		//använder metoderna i Triangel för att räkna ut inre resp yttre cirkelns radie
		double innerCirkelRadie = Triangel.innerCirkelRadie(a, b, c);
		System.out.println("Den inre cirkelns radie är: "+innerCirkelRadie);
		double yttreCirkelRadie = Triangel.yttreCirkelRadie(a, b, c);
		System.out.println("Den yttre cirkelns radie är: "+yttreCirkelRadie);
	}
}
