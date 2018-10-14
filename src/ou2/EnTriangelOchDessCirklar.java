package ou2;
/***************************************************
 Detta �r OU2.
 I denna klass finns huvudmetoden d�r man matar in en triangels
 alla tre sidor och anv�nder metoder i klassen Triangel f�r att 
 r�kna ut v�rden. I denna version s� r�knar man ut radien av 
 cirkeln inuti triangeln och skriver ut det. Sedan r�knar man ut
 radien av cirkeln utanf�r triangeln och skriver ut det.
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
		//anv�nder metoderna i Triangel f�r att r�kna ut inre resp yttre cirkelns radie
		double innerCirkelRadie = Triangel.innerCirkelRadie(a, b, c);
		System.out.println("Den inre cirkelns radie �r: "+innerCirkelRadie);
		double yttreCirkelRadie = Triangel.yttreCirkelRadie(a, b, c);
		System.out.println("Den yttre cirkelns radie �r: "+yttreCirkelRadie);
	}
}
