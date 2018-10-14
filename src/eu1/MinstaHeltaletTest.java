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
	// Om samlingen är tom, kastas ett undantag av typen IllegalArgumentException
	public static int min (int[] element) throws IllegalArgumentException{
		if(element.length==0)
			throw new IllegalArgumentException ("tom samling");
		
		//hör ihop med spårutskriften 2:
		int antalVarv = 1;
		
		int[] sekvens = element; //Varför? Bättre att skapa kopior i metoder? och return kopia?
		int antaletPar = sekvens.length /2 ; 
		int antaletOparadeElement = sekvens.length % 2; 
		int antaletTankbaraElement = antaletPar + antaletOparadeElement; 
		int[] delsekvens = new int[antaletTankbaraElement]; 
		int i = 0;
		int j = 0;
		while(antaletPar >= 1){ //sista jämförelsen är med sista paret //sekvens.length <= 1 oändlig
			//skilj ur en delsekvens med de tänkbara elementen
			i=0;
			j=0;
			while(j<antaletPar){
				delsekvens[j++]=(sekvens[i]<sekvens[i+1]?sekvens[i]:sekvens[i+1]); //if:else
				i+=2;
			}
			if (antaletOparadeElement==1)
				delsekvens[j]=sekvens[(antaletPar*2)];
			
			//utgå nu ifrån delsekvensen
			sekvens = delsekvens;
			antaletPar = antaletTankbaraElement /2; //modulo 2, 0 eller 1, halverar inte och därmed exikveras loopen 1 gång resp 0
			antaletOparadeElement = antaletTankbaraElement %2;
			antaletTankbaraElement = antaletPar + antaletOparadeElement;
			
			//spårutskrift 1 - för att följa sekvensen
			System.out.println(java.util.Arrays.toString(sekvens));
			
			//spårutskrift 2 - för att avsluta loopen i förväg
			//(för att kunna se vad som händer i början)
			//if(antalVarv++==10)
			  // System.exit(0);
		}
		//sekvens[0] är det enda återstående tänkbara elemtentet
		// - det är det minsta elementet
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
