package ou4;
import java.util.*;

class OperationMedNaturligaTalGivnaSomTeckenstrangar {
	public static void main(String[] args) {
		System.out.println("OPERATION MED NATURLIGA TAL GIVNA SOM TECKENSTRANGAR\n");

		// mata in tv� naturliga heltal
		Scanner in = new Scanner(System.in);
		System.out.println("Tv� naturliga heltal: ");
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
	F�r in tv� tal-str�ngar och med sattLen-metoden s� f�r de lika m�nga tecken. 
	Omvandlar ett tecken i taget i varje tal-str�ng till heltal och adderar samt
	uppdaterar carry-biten. L�gger sedan till den summan i form av en str�ng i 
	en str�ng summa. G�r detta f�r varje tecken. Returnerar slutsumman som en str�ng. 
	*/
	public static String addera(String tal1, String tal2) {
		int len1 = tal1.length();
		int len2 = tal2.length();
		int maxLen = Math.max(len1, len2);

		String summa = new String();

		tal1 = sattLen(tal1, maxLen - len1); // kommer alltid vara till�gg 0 ty tal1 �r st�rst..
												
		tal2 = sattLen(tal2, maxLen - len2);

		int num1 = 0;
		int num2 = 0;
		int carry = 0;

		for (int i = (maxLen - 1); i >= 0; i--) // OBS! h�ger till v�nster.
												
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

	// F�rsta heltalet inte mindre �n andra.
	/*
	 F�r in tv� heltal i form av str�ngar. Genom metoden sattLen s� f�r 
	 de lika m�nga tecken. Sedan omvandlas ett tecken i taget fr�n varje
	 str�ng till heltal och ber�knar differensen och uppdaterar carry-
	 biten. Detta l�ggs sedan till i en str�ng differens. Upprepas f�r 
	 varje tecken och returneras till slut.
	 */
	public static String subtrahera(String tal1, String tal2) {
		int len1 = tal1.length();
		int len2 = tal2.length();
		int maxLen = Math.max(len1, len2);

		String differens = new String();

		tal1 = sattLen(tal1, maxLen - len1); // kommer alltid vara till�gg 0, ty
												// den �r st�rst
		tal2 = sattLen(tal2, maxLen - len2); // l�gger till 0:or som det beh�vs

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
	 F�r in tv� heltal i form av str�ngar och dess resultat i form av 
	 en str�ng och ett tecken p� vilken operation som gjordes. Printar
	 sedan ut detta s� det ser uppst�llt och s� man ser vilken operation
	 det g�ller.
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
	 F�r in en str�ng och ett heltal. L�gger till x antal "0" i 0:e platsen
	 i str�ngen enligt heltalet i parameterreferensen. Returnerar denna f�rdiga
	 str�ng. 
	 */
	public static String sattLen(String s, int antal) {
		StringBuilder sb = new StringBuilder(s);
		for (int i = 0; i < antal; i++)
			sb.insert(0, "0");

		return sb.toString();
	}

}
