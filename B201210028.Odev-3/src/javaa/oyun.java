package javaa;

import java.util.*;

public class oyun
{
	public static void oyunn(String coloniNumb)
	{
		
		colonies colonies = new colonies();//kullanıcıdan alınan sayıların colonies sınıfının çağırılmasıyla dönüştürülmesi
		colonies.koloni(coloniNumb);
		int colonyCoun = colonies.population.length;
		int otherArray[]=colonies.getArray();
		
		List<Integer>colonie=new ArrayList<>();//verilerin listede tutulması
		for (int i=0;i<colonyCoun;i++) 
		{
		    colonie.add(otherArray[i]);
		}
		int colonyCount=colonie.size();
		
		
		List<tactics> tactics = new ArrayList<>();//savaş taktiklerinin bir liste içerisinde koloni sayısı adedince tutulması
		for (int i = 0; i < colonyCount; i++)
		{
			tactics tactic;
		  	if (i % 2 == 0) 
		  	{
			  tactic = new Tac1();
		  	} 
		  	else 
		  	{
			  tactic = new Tac2();
		  	}
		    tactics.add(tactic);
	    }
		
		
		List<Integer> foodAmounts = new ArrayList<>();//Başlangıçta her bir koloninin yiyecek miktarı için liste
		for (int i = 0; i < colonyCount; i++)//listeye eklenmesi
		{
		    foodAmounts.add(i,colonie.get(i)*colonie.get(i));
		}

	    List<uret> uretim = new ArrayList<>();//üretim sınıfında gelen rastgele miktarların tutulması için liste
	    for (int i = 0; i < colonyCount; i++) 
	    {
	    	uret urun;
		    if (i % 2 == 0) 
		    {
		      urun = new uret1();
            }
		    else 
		    {
		       urun = new uret2();
		    }
	        uretim.add(urun);
	     }

		 Random random = new Random();

		 List<Character> symbols = new ArrayList<>();//karakterlerin tutulması için liste
		 for (int i = 0; i < colonies.symbols.size(); i++)
		 {
		    int randomIndex = random.nextInt(colonies.symbols.size());
	        Character symbol = colonies.symbols.get(randomIndex);
	        symbols.add(symbol);
	     }

		 
	     int matchCount = (colonyCount * (colonyCount - 1)) / 2;//1 tur içinde yapılacak olan maçların hesaplanması
	     
	     //ilk verilerin sıfır olarak verilmesi
	     int turSayisi = 0;
	     
	     //kaanılan ve kaybedilen maçların tutulması için listeler
	     List<Integer> kazan = new ArrayList<>();
	     for(int i=0;i<colonyCount;i++)
	     {
	    	 kazan.add(i,0);
	     }
   	     List<Integer> kayıp = new ArrayList<>();
   	     for(int i=0;i<colonyCount;i++)
   	     {
   	    	 kayıp.add(i, 0);
   	     }
   	     
   	     //default
   	     System.out.println("Tur sayısı: " + 0);
		 System.out.println("Koloni     Populasyon     Yemek Stoğu     Kazanma     Kaybetme");
		 for(int i=0;i<colonyCount;i++)
		 {
			 System.out.println("  "+symbols.get(i)+"            "+colonie.get(i)
			                   +"            "+foodAmounts.get(i)+"             "
					            +"--"+"           "+"--");
		 }
		 System.out.println("---------------------------------------------------------------");
		 
		 
	     
		 int counter=colonyCount;//ölen kolonilerin çıkartılması ve döngü için array
		//oyun başlangıcı
		 while (counter>1) //1 koloni kaldığındadöngü biter
		 {
			 
			 turSayisi++;//tur sayısının tutulması
			
			 //tur içerisindeki maçlar için döngü
			 for (int m = 0; m < matchCount; m++) 
			 {
				 int randomColonyIndex1 = random.nextInt(colonyCount);//savaşacak olan 2 koloninin rastgele seçilmesi
				 int randomColonyIndex2 = random.nextInt(colonyCount);
				 
				 while (randomColonyIndex1 == randomColonyIndex2)//indexleri eşit olması durumunda 2. koloni değiştirilir
				 {													//sembolerin indexlerinde de kullanılacak
					 randomColonyIndex2 = random.nextInt(colonyCount);
			     }
				 while (colonie.get(randomColonyIndex1) ==0)//indexte bulunan koloni popülasyonu 0 isefarklı bir index seçilecek
				 {													
					 randomColonyIndex1 = random.nextInt(colonyCount);
			     }
				 while (colonie.get(randomColonyIndex2)==0)//indexleri eşit olması durumunda 2. koloni değiştirilir
				 {													//sembolerin indexlerinde de kullanılacak
					 randomColonyIndex2 = random.nextInt(colonyCount);
			     }
				 
		           
				 //seçilen kolonilere taktikler atanır
				 tactics tactic1 = tactics.get(randomColonyIndex1);
				 tactics tactic2 = tactics.get(randomColonyIndex2);
				 int fightResult1 = tactic1.savas();
				 int fightResult2 = tactic2.savas();
				 
				 //savaş sonuç belirleme
				    //1.koloninin kazanması
		            if (fightResult1 > fightResult2)
		            {
		            	int calculate=(fightResult1-fightResult2)/10; //savaş farkının 1000'e göre yüzdesinin hesaplanması
		                int loser = colonies.population[randomColonyIndex2] ; //kaybeden tarafın indexinin tutulması
		                int lostCalculate=loser*calculate; //kayıbın/kazancın hesaplanması
		                loser-=lostCalculate;//kaybeden taraftan popülasyonun düşülmesi
		                foodAmounts.set(randomColonyIndex2, foodAmounts.get(randomColonyIndex2)-lostCalculate);
		                foodAmounts.set(randomColonyIndex1,foodAmounts.get(randomColonyIndex1)+lostCalculate);
		                kayıp.set(randomColonyIndex2, kayıp.get(randomColonyIndex2)+1);
		                kazan.set(randomColonyIndex1, kazan.get(randomColonyIndex1)+1);
		            } 
		            //2. koloninin kazanması
		            else if (fightResult1 < fightResult2) 
		            {
		            	int calculate=(fightResult2-fightResult1)/10;
		            	int loser=colonies.population[randomColonyIndex1];
		            	int lostCalculate=loser*calculate;
		            	loser-=lostCalculate;
		            	foodAmounts.set(randomColonyIndex2, foodAmounts.get(randomColonyIndex1)-lostCalculate);
		            	foodAmounts.set(randomColonyIndex2, foodAmounts.get(randomColonyIndex2)+lostCalculate);
		            	kayıp.set(randomColonyIndex1, kayıp.get(randomColonyIndex1)+1);
		                kazan.set(randomColonyIndex2, kazan.get(randomColonyIndex2)+1);
		            }
		            //berabere durumu
		            //popülasyon büyüklükleri kontrol ediliyor
		            //böyle bir durumda popülasyon aktarımı ve yemek stoğu hakkında bilgi verilmediği için direkt yarısı aktarılmıştır
		            else
		            {	//1. popülasyonun fazla olması durumu
		                if (colonie.get(randomColonyIndex1) > colonie.get(randomColonyIndex2))
		                {
		                	int loser=colonie.get(randomColonyIndex2);
		                	int calculate=loser/2;
		                	loser-=calculate;
		                	foodAmounts.set(randomColonyIndex2, foodAmounts.get(randomColonyIndex2)-calculate);
		                	foodAmounts.set(randomColonyIndex2, foodAmounts.get(randomColonyIndex1)+calculate);
		                	kayıp.set(randomColonyIndex2, kayıp.get(randomColonyIndex2)+1);
			                kazan.set(randomColonyIndex1, kazan.get(randomColonyIndex1)+1);
		                } 
		              //2. popülasyonun fazla olması durumu
		                else if (colonie.get(randomColonyIndex1) < colonie.get(randomColonyIndex2)) 
		                {
		                	int loser=colonie.get(randomColonyIndex1);
		                	int calculate=loser/2;
		                	loser-=calculate;
		                	foodAmounts.set(randomColonyIndex2, foodAmounts.get(randomColonyIndex2)+calculate);
		                	foodAmounts.set(randomColonyIndex2, foodAmounts.get(randomColonyIndex1)-calculate);
		                	kayıp.set(randomColonyIndex1, kayıp.get(randomColonyIndex1)+1);
			                kazan.set(randomColonyIndex2, kazan.get(randomColonyIndex2)+1);
		                } 
		                //popülasyonlar eşitse rastgele birisi kazanır
		                else
		                {
		                    int randomWinnerIndex = random.nextInt(2);
		                    if (randomWinnerIndex == 0) 
		                    {
		                    	int loser=colonie.get(randomColonyIndex2);
			                	int calculate=loser/2;
			                	loser-=calculate;
			                	foodAmounts.set(randomColonyIndex2, foodAmounts.get(randomColonyIndex2)-calculate);
			                	foodAmounts.set(randomColonyIndex2, foodAmounts.get(randomColonyIndex1)+calculate);
			                	kayıp.set(randomColonyIndex2, kayıp.get(randomColonyIndex2)+1);
				                kazan.set(randomColonyIndex1, kazan.get(randomColonyIndex1)+1);
		                    }
		                    else
		                    {
		                    	int loser=colonie.get(randomColonyIndex1);
			                	int calculate=loser/2;
			                	loser-=calculate;
			                	foodAmounts.set(randomColonyIndex2, foodAmounts.get(randomColonyIndex2)+calculate);
			                	foodAmounts.set(randomColonyIndex2, foodAmounts.get(randomColonyIndex1)-calculate);
			                	kayıp.set(randomColonyIndex1, kayıp.get(randomColonyIndex1)+1);
				                kazan.set(randomColonyIndex2, kazan.get(randomColonyIndex2)+1);
		                    }
		                }
		            }
		            	
		            //uret1 ve ıret 2 sınıflarından urett fonksiyonları çağırılır ve döndürülen rastgele değerlre değişken içinde tutulur
		            int foodProduced1 = uretim.get(randomColonyIndex1).urett();
		            int foodProduced2 = uretim.get(randomColonyIndex2).urett();
		            //kolonilerin yiyecek stoklarına eklenir ve güncellenir
		            foodAmounts.set(randomColonyIndex1, foodAmounts.get(randomColonyIndex1) + foodProduced1);
		            foodAmounts.set(randomColonyIndex2, foodAmounts.get(randomColonyIndex2) + foodProduced2);

		            
		            
		            //kolonilerin popülasyonu tur sonunda %20 arttırılır
		            colonie.set(randomColonyIndex1,colonie.get(randomColonyIndex1)+(int)Math.ceil(randomColonyIndex1* 0.2));
		            colonie.set(randomColonyIndex2,colonie.get(randomColonyIndex2)+(int)Math.ceil(randomColonyIndex2* 0.2));
		            
		            //Yemek stoğu (GüncelPopülasyon x 2)oranında azalır.
		            foodAmounts.set(randomColonyIndex1, foodAmounts.get(randomColonyIndex1) - colonie.get(randomColonyIndex1) * 2);
		            foodAmounts.set(randomColonyIndex2, foodAmounts.get(randomColonyIndex2) - colonie.get(randomColonyIndex2) * 2);

		            //kolonilerin popülasyon ve yemek durumuna göre yaşam durumları kontrol ediliyor
		            if (foodAmounts.get(randomColonyIndex1) <= 0 || colonie.get(randomColonyIndex1) <= 0) 
		            {
		               //randomColonyIndex1 ölür
		            	colonie.set(randomColonyIndex1, 0);
		            	foodAmounts.set(randomColonyIndex1,0);
		            	counter--;
		            }
		            if (foodAmounts.get(randomColonyIndex2) <= 0 || colonie.get(randomColonyIndex2) <= 0) 
		            {
		            	colonie.set(randomColonyIndex2, 0);
		            	foodAmounts.set(randomColonyIndex2,0);
		            	counter--;
		            }
		        }
			 
			 System.out.println("Tur sayısı: " + turSayisi);
			 System.out.println("Koloni     Populasyon     Yemek Stoğu     Kazanma     Kaybetme");
			 for(int i=0;i<colonyCount;i++)
			 {
				 System.out.println("  "+symbols.get(i)+"            "+colonie.get(i)
				                   +"            "+foodAmounts.get(i)+"               "
						            +kazan.get(i)+"           "+kayıp.get(i));
			 }
			 System.out.println("---------------------------------------------------------------");
			 
			 
		 }
	}
}

		

