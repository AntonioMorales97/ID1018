package ou3;

class DenKortasteVagen {
	public static int[] mellanstationer(double[] a, double[][] b, double[] c) {
		int m = a.length;
		int n = c.length;
		double[][] v = new double[m][n];

		for (int i = 0; i < m; i++)// går till 3
		{
			for (int j = 0; j < n; j++)// går till 4
			{
				v[i][j] = a[i] + b[i][j] + c[j]; // högsta c[3]
			}
		}

		int[] min = new int[3];
		double tempMin = v[1][1];// aktuella minsta avståndet

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (v[i][j] < tempMin) {
					tempMin = v[i][j];
					min[1] = i;
					min[2] = j; // OBS! här börjar man med index 0.
				}

			}
		}

		return min;
	}

	public static double langd(double[] a, double[][] b, double[] c) {
		int[] mellanstationer = mellanstationer(a, b, c);
		int förstaMellanstation = mellanstationer[1];
		int andraMellanstation = mellanstationer[2];

		double langd = a[förstaMellanstation] + b[förstaMellanstation][andraMellanstation] + c[andraMellanstation];
		return langd;
	}
}
