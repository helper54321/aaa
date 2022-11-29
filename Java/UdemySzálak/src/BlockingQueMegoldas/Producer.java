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
					//Ha nem akarn�nk a ki�rat�st ak�r nem is lenne sz�ks�g atomic-ra tenni? (ebben a p�ld�ban)
					//De csak az�rt mert ez az egyetlen thread ami n�veli az �rt�k�t(ha t�bb helyen is n�veln�nk akkor mindenk�ppen atomic kell)!!!!!!
					//A m�sik sz�l ugyan szint�n hozz�f�r, de az nem fogja n�velni (de csin�lhat�nk ak�r t�bb producert �s t�bb consumert is, akkor t�bb helyen n�vel�dne)
					//Nem vissz�k ki egy k�l�n met�dusba, mert akkor az is synchronized-ra kellene, az m�r t�ls�gosan lelass�tan� a k�dot
					int nextQuestion = questionNo++;
					System.out.println("Got new question: " + nextQuestion);
					questionQue.put(nextQuestion); //Az�rt nem az add met�dus, mert az nem thread safe
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
