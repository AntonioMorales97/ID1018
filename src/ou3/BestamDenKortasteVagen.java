package ou3;
import java.util.*;

public class BestamDenKortasteVagen {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		in.useLocale(Locale.US);

		System.out.print("Mata in antal stationer i zon 2 (m): ");
		int m = in.nextInt();
		System.out.print("Mata in antal stationer i zon 3 (n): ");
		int n = in.nextInt();

		double[] a = new double[m];
		double[][] b = new double[m][n];
		double[] c = new double[n];

		for (int i = 0; i <= (m - 1); i++) {
			System.out.print("Mata in väglängden mellan startstation och mellanstation " + (i + 1) + " i zon 2: ");
			a[i] = in.nextDouble();
		}
		for (int i = 0; i <= (m - 1); i++) {
			for (int j = 0; j <= (n - 1); j++) {
				System.out.print("Mata in väglängden mellan mellanstation " + (i + 1) + " i zon 2 och mellanstation "
						+ (j + 1) + " i zon 3: ");
				b[i][j] = in.nextDouble();
			}
		}
		for (int i = 0; i <= (n - 1); i++) {
			System.out.print("Mata in väglängden mellan mellanstation " + (i + 1) + " och slutstation: ");
			c[i] = in.nextDouble();
		}
		in.close();
		/*
		 * double[] a = {2,3,4}; double [][]b=
		 * {{7,8,10,12},{7,6,8,9},{14,8,5,2}}; double[]c= {6,11,15,5};
		 */
		int[] v = DenKortasteVagen.mellanstationer(a, b, c);
		double langd = DenKortasteVagen.langd(a, b, c);

		System.out.println("Den kortaste vägen är genom mellanstation " + (v[1] + 1)
				+ " i zon 2 och sedan genom mellanstation " + (v[2] + 1) + " i zon 3. Längden är " + langd);
	}
}
