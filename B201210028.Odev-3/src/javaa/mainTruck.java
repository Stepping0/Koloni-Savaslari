package javaa;
import java.util.*;
public class mainTruck
{
	
	public static void main(String[] args) 
	{
		Scanner in =new Scanner(System.in);
		System.out.println("Boşluk bırakarak ayıları giriniz : ");
		//kullanıcıdan popülasyon sayılarının alınması
		String coloniNumb=in.nextLine();
		//oyuna parametre olarak gönderilecek
		oyun oyun=new oyun();
		oyun.oyunn(coloniNumb);
	}
}