package ou4;
import java.util.*;

class OperationMedNaturligaTalGivnaSomTeckenstrangar {
	public static void main(String[] args) {
		System.out.println("OPERATION MED NATURLIGA TAL GIVNA SOM TECKENSTRANGAR\n");

		// mata in två naturliga heltal
		Scanner in = new Scanner(System.in);
		System.out.println("Två naturliga heltal: ");
		String tal1 = in.next();
		String tal2 = in.next();
		in.close();
		System.out.println();

		// addera heltalen och visa resultatet
		String summa = addera(tal1, tal2);
		visa(tal1, tal2, summa, '+');

		String differens = subtrahera(tal1, tal2);
		visa(tal1, tal2, differens, '-');

	}
	/*
	Får in två tal-strängar och med sattLen-metoden så får de lika många tecken. 
	Omvandlar ett tecken i taget i varje tal-sträng till heltal och adderar samt
	uppdaterar carry-biten. Lägger sedan till den summan i form av en sträng i 
	en sträng summa. Gör detta för varje tecken. Returnerar slutsumman som en sträng. 
	*/
	public static String addera(String tal1, String tal2) {
		int len1 = tal1.length();
		int len2 = tal2.length();
		int maxLen = Math.max(len1, len2);

		String summa = new String();

		tal1 = sattLen(tal1, maxLen - len1); // kommer alltid vara tillägg 0 ty tal1 är störst..
												
		tal2 = sattLen(tal2, maxLen - len2);

		int num1 = 0;
		int num2 = 0;
		int carry = 0;

		for (int i = (maxLen - 1); i >= 0; i--) // OBS! höger till vänster.
												
		{
			num1 = Character.getNumericValue(tal1.charAt(i));
			num2 = Character.getNumericValue(tal2.charAt(i));

			if ((num1 + num2 + carry) >= 10) {
				summa = Integer.toString((num1 + num2 + carry) % 10) + summa;
				carry = 1;
			}

			else {
				summa = Integer.toString((num1 + num2 + carry) % 10) + summa;
				carry = 0;
			}
		}

		if (carry == 1) {
			summa = "1" + summa;
		}

		return summa;
	}

	// Första heltalet inte mindre än andra.
	/*
	 Får in två heltal i form av strängar. Genom metoden sattLen så får 
	 de lika många tecken. Sedan omvandlas ett tecken i taget från varje
	 sträng till heltal och beräknar differensen och uppdaterar carry-
	 biten. Detta läggs sedan till i en sträng differens. Upprepas för 
	 varje tecken och returneras till slut.
	 */
	public static String subtrahera(String tal1, String tal2) {
		int len1 = tal1.length();
		int len2 = tal2.length();
		int maxLen = Math.max(len1, len2);

		String differens = new String();

		tal1 = sattLen(tal1, maxLen - len1); // kommer alltid vara tillägg 0, ty
												// den är störst
		tal2 = sattLen(tal2, maxLen - len2); // lägger till 0:or som det behövs

		int num1 = 0;
		int num2 = 0;
		int carry = 0;

		for (int i = (maxLen - 1); i >= 0; i--) {
			num1 = Character.getNumericValue(tal1.charAt(i)) + carry;
			num2 = Character.getNumericValue(tal2.charAt(i));

			if (num1 - num2 < 0) {
				differens = Integer.toString((num1 + 10) - num2) + differens;
				carry = -1;
			}

			else {
				differens = Integer.toString(num1 - num2) + differens;
				carry = 0;
			}
		}

		return differens;
	}
	/*
	 Får in två heltal i form av strängar och dess resultat i form av 
	 en sträng och ett tecken på vilken operation som gjordes. Printar
	 sedan ut detta så det ser uppställt och så man ser vilken operation
	 det gäller.
	 */
	public static void visa(String tal1, String tal2, String resultat, char operator) {
		int len1 = tal1.length();
		int len2 = tal2.length();
		int len = resultat.length();
		int maxLen = Math.max((Math.max(len1, len2)), len);
		tal1 = sattLen(tal1, maxLen - len1);
		tal2 = sattLen(tal2, maxLen - len2);
		resultat = sattLen(resultat, maxLen - len);

		System.out.println("  " + tal1);
		System.out.println("" + operator + " " + tal2);
		for (int i = 0; i < maxLen + 2; i++)
			System.out.print("-");
		System.out.println();
		System.out.println("  " + resultat + "\n");
	}
	/*
	 Får in en sträng och ett heltal. Lägger till x antal "0" i 0:e platsen
	 i strängen enligt heltalet i parameterreferensen. Returnerar denna färdiga
	 sträng. 
	 */
	public static String sattLen(String s, int antal) {
		StringBuilder sb = new StringBuilder(s);
		for (int i = 0; i < antal; i++)
			sb.insert(0, "0");

		return sb.toString();
	}

}
