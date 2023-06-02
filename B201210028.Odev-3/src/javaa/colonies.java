package javaa;
import java.util.*;
public class colonies 
{
	//populasyonların tutulması ve dışarıdan erişim sağlanabilmesi için değişken
	public static int[] population;
	
	//karakterler için dışardan ulaşılabilir liste
	public static List<Character> symbols = new ArrayList<>();
	
	static void koloni(String coloniNumb)
	{
	 	
	 String[] list = coloniNumb.split("\\s+");//verilen sayıların boşluklardan regex ifade kullanarak ayrıştırılması 
	 
	 int [] pop = new int [list.length]; //sayıların atanması için yeni array, verilen sayı adedini uzunluk olarak tutar
     for(int i=0; i<list.length; i++) 
     {
   	  pop[i] = Integer.parseInt(list[i]);//sayıların arraye atanması
     }
     population=pop;
     
	 List<Character> symbolsList = new ArrayList<>();//kolonilerin sembollerini taşıması için bir arraylist
	 symbolsList.add('\u0194'); // Ɣ
     symbolsList.add('\u0040'); // @
     symbolsList.add('\u002B'); // +
     symbolsList.add('\u0021'); // !
     symbolsList.add('\u0023'); // #
     symbolsList.add('\u0025'); // %
     symbolsList.add('\u0026'); // &
     symbolsList.add('\u002A'); // *
     symbolsList.add('\u00B6'); // ¶
     symbolsList.add('\u00B5'); // µ
     symbolsList.add('\u00BF'); // ¿
     symbolsList.add('\u0195'); // ƕ
     symbols=symbolsList;
	}
	public int[] getArray()
	{
		return population;
	}
}
