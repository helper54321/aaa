package BlockingQueMegoldas;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
	
	int questionNo;
	BlockingQueue<Integer> questionQue;
	
	
	public Producer(BlockingQueue<Integer> questionQue) {
		this.questionQue = questionQue;
	}
	
	
	@Override
	public void run() {
		
		while(true) {
			try {
				synchronized (this) {
					//Ha nem akarnánk a kiíratást akár nem is lenne szükség atomic-ra tenni? (ebben a példában)
					//De csak azért mert ez az egyetlen thread ami növeli az értékét(ha több helyen is növelnénk akkor mindenképpen atomic kell)!!!!!!
					//A másik szál ugyan szintén hozzáfér, de az nem fogja növelni (de csinálhatánk akár több producert és több consumert is, akkor több helyen növelõdne)
					//Nem visszük ki egy külön metódusba, mert akkor az is synchronized-ra kellene, az már túlságosan lelassítaná a kódot
					int nextQuestion = questionNo++;
					System.out.println("Got new question: " + nextQuestion);
					questionQue.put(nextQuestion); //Azért nem az add metódus, mert az nem thread safe
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
