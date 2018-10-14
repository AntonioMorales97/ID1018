package ou3;

class DenKortasteVagen {
	public static int[] mellanstationer(double[] a, double[][] b, double[] c) {
		int m = a.length;
		int n = c.length;
		double[][] v = new double[m][n];

		for (int i = 0; i < m; i++)// g�r till 3
		{
			for (int j = 0; j < n; j++)// g�r till 4
			{
				v[i][j] = a[i] + b[i][j] + c[j]; // h�gsta c[3]
			}
		}

		int[] min = new int[3];
		double tempMin = v[1][1];// aktuella minsta avst�ndet

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (v[i][j] < tempMin) {
					tempMin = v[i][j];
					min[1] = i;
					min[2] = j; // OBS! h�r b�rjar man med index 0.
				}

			}
		}

		return min;
	}

	public static double langd(double[] a, double[][] b, double[] c) {
		int[] mellanstationer = mellanstationer(a, b, c);
		int f�rstaMellanstation = mellanstationer[1];
		int andraMellanstation = mellanstationer[2];

		double langd = a[f�rstaMellanstation] + b[f�rstaMellanstation][andraMellanstation] + c[andraMellanstation];
		return langd;
	}
}
