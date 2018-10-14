package eu2;
import java.util.*;
public class SorteraTest {

	public static void main(String[] args) {
		int[] lista = {5,4,3,2,1};
		int n = lista.length;
		System.out.println(Arrays.toString(utbytessort(n,lista)));
	}

	public static int[] utbytessort(int n, int[] lista) {
		int i = 0; //index börjar med 0!
		int temp;
		int j;
		while (i < n) {
			j=i+1;
			while (j < n){
				if(lista[j]<lista[i]){
					temp = lista[i];
					lista[i]=lista[j];
					lista[j]=temp;
				}
				j++;
			}
			i++;
		}
		return lista;
	}
}