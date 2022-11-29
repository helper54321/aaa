package BlockingQueMegoldas;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class Application {

	public static void main(String[] args) {
		
		//Megadjuk param�terk�nt a limitet (teh�t, hogy h�ny elem lehet egyszerre benne maximum)
		//Az el�z� megold�sn�l (wait �s notify-os) ez volt a limit
		BlockingQueue<Integer> questionQueue = new ArrayBlockingQueue<Integer>(5);
		
		Thread t1 = new Thread(new Producer(questionQueue));
		Thread t2 = new Thread(new Consumer(questionQueue));
		
		t1.start();
		t2.start();
	}

}
