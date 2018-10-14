package ou1;

// Scanner, Locale
import java.util.Locale;
import java.util.Scanner;

class Temperaturer {
	public static void main(String[] args) {
		System.out.println("TEMPERATURER\n");

		// inmatningsverktyg
		Scanner in = new Scanner(System.in);
		in.useLocale(Locale.US);
		// mata in uppgifter om antalet veckor och antalet mätningar
		System.out.print("antalet veckor: ");
		int antalVeckor = in.nextInt();
		System.out.print("antalet mätningar per vecka: ");
		int antalMatningarPerVecka = in.nextInt();
		// plats att lagra temperaturer
		double[][] t = new double[antalVeckor + 1][antalMatningarPerVecka + 1];
		// mata in temperaturerna
		for (int vecka = 1; vecka <= antalVeckor; vecka++) {
			System.out.println("temperaturer - vecka " + vecka + ":");
			for (int matning = 1; matning <= antalMatningarPerVecka; matning++)
				t[vecka][matning] = in.nextDouble();
		}
		System.out.println();
		// visa temperaturerna
		System.out.println("temperaturerna:");
		for (int vecka = 1; vecka <= antalVeckor; vecka++) {
			for (int matning = 1; matning <= antalMatningarPerVecka; matning++)
				System.out.print(t[vecka][matning] + " ");
			System.out.println();
		}
		System.out.println();
		// den minsta, den största och medeltemperaturen – veckovis
		double[] minT = new double[antalVeckor + 1];
		double[] maxT = new double[antalVeckor + 1];
		double[] sumT = new double[antalVeckor + 1];
		double[] medelT = new double[antalVeckor + 1];
		// koden ska skrivas här
		for (int vecka = 1; vecka <= antalVeckor; vecka++){
			double tempMin = t[vecka][1]; 									
			double tempMax = t[vecka][1]; 								
			for (int matning = 1; matning <= antalMatningarPerVecka; matning++){
				if (t[vecka][matning] <= tempMin){
					tempMin = t[vecka][matning];
				}
				if (t[vecka][matning] >= tempMax){
					tempMax = t[vecka][matning];
				}
				sumT[vecka] += t[vecka][matning];
			}
			minT[vecka] = tempMin;
			maxT[vecka] = tempMax;
			medelT[vecka] = sumT[vecka] / antalMatningarPerVecka;
		}

		// visa den minsta, den största och medeltemperaturen för varje vecka
		// koden ska skrivas här
		System.out.println("Vecka \t Min \t Max \t MedelT");
		for (int vecka = 1; vecka <= antalVeckor; vecka++) {
			System.out.print(vecka + "\t" + minT[vecka] + "\t" + maxT[vecka] + "\t" + medelT[vecka]);
			System.out.println();
		}
		// den minsta, den största och medeltemperaturen - hela mätperioden
		double minTemp = minT[1];
		double maxTemp = maxT[1];
		double sumTemp = 0;
		double medelTemp = 0;
		// koden ska skrivas här
		for (int vecka = 1; vecka <= antalVeckor; vecka++){
			if (minT[vecka] <= minTemp){
				minTemp = minT[vecka];
			}
			if (maxT[vecka] >= maxTemp) {
				maxTemp = maxT[vecka];
			}
			sumTemp += medelT[vecka];
		}
		medelTemp = sumTemp / antalVeckor;
		// visa den minsta, den största och medeltemperaturen i hela mätperioden
		// koden ska skrivas här
		System.out.println("För hela mätperioden");
		System.out.println("Minsta: " + minTemp + " Största: " + maxTemp + " Medeltemp: " + medelTemp);
	}
}
