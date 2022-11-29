package threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {

	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(2); //Szál újrahasznosítás (2szálat hoztunk létre, de 3 feladatot osztottunk ki)
		
		Runnable processor = new MessageProcessor(2);
		executor.execute(processor);
		
		Runnable processor2 = new MessageProcessor(3);
		executor.execute(processor2);
		
		Runnable processor3 = new MessageProcessor(4);
		executor.execute(processor3);
		
		Runnable processor4 = new MessageProcessor(5);
		executor.execute(processor4);
		
		executor.shutdown(); //Ezzel megmondjuk, hogy több feladatot már ne fogadjon el (lényegében leállítjuk)
		//executor.shutdownNow(); //Azonnal leállítja a szálakat, akkoris ha egy nagyobb feladat közepén járnak (azért ez nem annyira használatos)
		
		//Itt megmondjuk, hogy amíg nem állt le, addig ne menjen tovább a main szál (a shutdown rész nélkül végtelen ciklus lenne)
//		while(!executor.isTerminated()) {
//			  
//		}
		
		//Ez egy másik megoldás a while helyett (konkrétan megmondjuk, hogy meddig várunk, és ha az nem volt elég, így járt, a main akkor is megy tovább)
		try {
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Submitted all tasks"); //Szinte rögtön kiírná, mivel a main szál attól fut tovább hogy más szálak dolgoznak (ezért raktuk elé a while-t)
	}

}
