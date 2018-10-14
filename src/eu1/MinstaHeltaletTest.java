package eu1;

public class MinstaHeltaletTest {

	public static void main(String[] args){
		int[] list = {3,8,34,0,
					  9,-3,-10,3,
					  13,45,21,4,
					  2,81,98,37};
		int[] list1 = {3,8,34,0,
				  9,-3,-10,-33,
				  13,45,21,4,
				  2,81,98,37,-36,44,99};
		//System.out.println(min(list1));
		System.out.println(minNy(list1));
		
		//System.out.println(min(list1));
		//System.out.println(minNy(list1));
	}
	// min returnerar det minsta elementet i en sekventiell samling
	// Om samlingen �r tom, kastas ett undantag av typen IllegalArgumentException
	public static int min (int[] element) throws IllegalArgumentException{
		if(element.length==0)
			throw new IllegalArgumentException ("tom samling");
		
		//h�r ihop med sp�rutskriften 2:
		int antalVarv = 1;
		
		int[] sekvens = element; //Varf�r? B�ttre att skapa kopior i metoder? och return kopia?
		int antaletPar = sekvens.length /2 ; 
		int antaletOparadeElement = sekvens.length % 2; 
		int antaletTankbaraElement = antaletPar + antaletOparadeElement; 
		int[] delsekvens = new int[antaletTankbaraElement]; 
		int i = 0;
		int j = 0;
		while(antaletPar >= 1){ //sista j�mf�relsen �r med sista paret //sekvens.length <= 1 o�ndlig
			//skilj ur en delsekvens med de t�nkbara elementen
			i=0;
			j=0;
			while(j<antaletPar){
				delsekvens[j++]=(sekvens[i]<sekvens[i+1]?sekvens[i]:sekvens[i+1]); //if:else
				i+=2;
			}
			if (antaletOparadeElement==1)
				delsekvens[j]=sekvens[(antaletPar*2)];
			
			//utg� nu ifr�n delsekvensen
			sekvens = delsekvens;
			antaletPar = antaletTankbaraElement /2; //modulo 2, 0 eller 1, halverar inte och d�rmed exikveras loopen 1 g�ng resp 0
			antaletOparadeElement = antaletTankbaraElement %2;
			antaletTankbaraElement = antaletPar + antaletOparadeElement;
			
			//sp�rutskrift 1 - f�r att f�lja sekvensen
			System.out.println(java.util.Arrays.toString(sekvens));
			
			//sp�rutskrift 2 - f�r att avsluta loopen i f�rv�g
			//(f�r att kunna se vad som h�nder i b�rjan)
			//if(antalVarv++==10)
			  // System.exit(0);
		}
		//sekvens[0] �r det enda �terst�ende t�nkbara elemtentet
		// - det �r det minsta elementet
		return sekvens[0];
	}
	
	public static int minNy (int[] element){
		int min = element[0];
		for (int i =0; i<element.length;i++){
			min = (element[i]<min)?element[i]:min;
		}
		return min;
	}
}
