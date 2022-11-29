package threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {

	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(2); //Sz�l �jrahasznos�t�s (2sz�lat hoztunk l�tre, de 3 feladatot osztottunk ki)
		
		Runnable processor = new MessageProcessor(2);
		executor.execute(processor);
		
		Runnable processor2 = new MessageProcessor(3);
		executor.execute(processor2);
		
		Runnable processor3 = new MessageProcessor(4);
		executor.execute(processor3);
		
		Runnable processor4 = new MessageProcessor(5);
		executor.execute(processor4);
		
		executor.shutdown(); //Ezzel megmondjuk, hogy t�bb feladatot m�r ne fogadjon el (l�nyeg�ben le�ll�tjuk)
		//executor.shutdownNow(); //Azonnal le�ll�tja a sz�lakat, akkoris ha egy nagyobb feladat k�zep�n j�rnak (az�rt ez nem annyira haszn�latos)
		
		//Itt megmondjuk, hogy am�g nem �llt le, addig ne menjen tov�bb a main sz�l (a shutdown r�sz n�lk�l v�gtelen ciklus lenne)
//		while(!executor.isTerminated()) {
//			  
//		}
		
		//Ez egy m�sik megold�s a while helyett (konkr�tan megmondjuk, hogy meddig v�runk, �s ha az nem volt el�g, �gy j�rt, a main akkor is megy tov�bb)
		try {
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Submitted all tasks"); //Szinte r�gt�n ki�rn�, mivel a main sz�l att�l fut tov�bb hogy m�s sz�lak dolgoznak (ez�rt raktuk el� a while-t)
	}

}
