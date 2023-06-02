package javaa;
import java.lang.Math;
public class Tac1 extends tactics
{	
	//dışardan erişmek için türün tanımlanması
	public static String tur;
	public int savas()
	{
		//taktiğin isimlendirilmesi 
		String kturu="Hilal Kuşatması";
		tur=kturu;
		//0-1000 arası rastgele sayının tanımlanması
		int rand=(int)(Math.random()*1000);
		return rand;
	}
}
