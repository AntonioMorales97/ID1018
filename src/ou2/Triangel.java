package ou2;

public class Triangel {
	//F�r in triangelns tre sidor och returnerar arean
	public static double triangelArea(double a, double b, double c){
		double vinkelA = Math.acos((Math.pow(b, 2) + Math.pow(c, 2) - Math.pow(a, 2)) / (2 * b * c)); // cosinussatsen																							
		double area = (b * c * Math.sin(vinkelA)) / 2; // areasatsen
		return area;
	}
	//F�r in triangelns tre sidor och returnerar inre cirkelns radie
	public static double innerCirkelRadie(double a, double b, double c) {
		double area = triangelArea(a, b, c); 
		double omkrets = triangelOmkrets(a, b, c); 
		double radie = (2 * area) / omkrets; 
		return radie;
	}
	//F�r in triangelns tre sidor och returnerar yttre cirkelns radie
	public static double yttreCirkelRadie(double a, double b, double c) {
		double radie;
		radie = (a * b * c) / (Math.sqrt((a + b + c) * (b + c - a) * (c + a - b) * (a + b - c))); 
		return radie;
	}
	//F�r in triangelns tre sidor och returnerar omkretsen
	public static double triangelOmkrets(double a, double b, double c) {
		double omkrets = a + b + c;
		return omkrets;
	}
	//F�r in triangelns bas och h�jd och returnerar arean
	public static double triangelAreaBasHojd(double bas, double hojd) {
		double area = (bas * hojd) / 2;
		return area;
	}
	//F�r in tv� sidor av triangeln samt vinkeln mellan dessa sidor
	//och returnerar bisektrisen f�r denna vinkeln
	public static double bisektris(double b, double c, double alfa) { 
		double p = 2 * b * c * Math.cos(alfa / 2);					 
		double bis = p / (b + c);
		return bis;
	}
}
