package waitEsNotify;

import java.util.ArrayList;
import java.util.List;

public class Application {

	public static void main(String[] args) {
		
		//Ez a megold�s(teh�t wait �s notify haszn�lata) m�g mindig el�g primit�v, ha lehet ker�lj�k is el. Van jobb megold�s.
		
		List<Integer> questionList = new ArrayList<>();
		
		Thread t1 = new Thread(new Producer(questionList));
		Thread t2 = new Thread(new Consumer(questionList));
		
		t1.start();
		t2.start();
		
	}
}
