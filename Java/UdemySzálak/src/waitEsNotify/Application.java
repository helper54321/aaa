package waitEsNotify;

import java.util.ArrayList;
import java.util.List;

public class Application {

	public static void main(String[] args) {
		
		//Ez a megoldás(tehát wait és notify használata) még mindig elég primitív, ha lehet kerüljük is el. Van jobb megoldás.
		
		List<Integer> questionList = new ArrayList<>();
		
		Thread t1 = new Thread(new Producer(questionList));
		Thread t2 = new Thread(new Consumer(questionList));
		
		t1.start();
		t2.start();
		
	}
}
