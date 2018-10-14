package eu4;

public class Polylinjer {
	
    public static Polylinje kortastGula (Polylinje[] linjer){
    	double kortastLangd = 10000;
    	Polylinje kortastGul = null;
    	
    	for (int i = 0; i < linjer.length; i++){
    		if (linjer[i].getFarg().equals("Gul") && linjer[i].langd()<kortastLangd){
    			kortastGul = linjer[i];
    			kortastLangd = linjer[i].langd();
    		}
    	}
    	
    	if (kortastLangd == 10000)
    		System.out.println("Finns inga gula polylinjer.");
    		else
    			System.out.println("Gul polylinje hittades.");
    	
    	return kortastGul;
    }
	
}
