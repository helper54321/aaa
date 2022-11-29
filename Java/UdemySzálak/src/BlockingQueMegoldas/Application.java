package BlockingQueMegoldas;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class Application {

	public static void main(String[] args) {
		
		//Megadjuk paraméterként a limitet (tehát, hogy hány elem lehet egyszerre benne maximum)
		//Az elõzõ megoldásnál (wait és notify-os) ez volt a limit
		BlockingQueue<Integer> questionQueue = new ArrayBlockingQueue<Integer>(5);
		
		Thread t1 = new Thread(new Producer(questionQueue));
		Thread t2 = new Thread(new Consumer(questionQueue));
		
		t1.start();
		t2.start();
	}

}
