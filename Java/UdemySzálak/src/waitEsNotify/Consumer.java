package waitEsNotify;

import java.util.List;

public class Consumer implements Runnable{

	List<Integer> questionList = null;
	
	public Consumer(List<Integer> questionList) {
		this.questionList = questionList;
	}
	
	public void answerQuestion() throws InterruptedException {
		synchronized (questionList) {
			while(questionList.isEmpty()) {
				System.out.println("NNo questions to answer.. waiting for producer to get questions");
				//A wait csak synchronized blokkban haszn�lhat�
				questionList.wait(); //Notify-ra v�r
			}
		}
		
		synchronized (questionList) {
			Thread.sleep(5000);
			System.out.println("Answered question: " + questionList.remove(0)); //Mint egy sorn�l (a legr�gebb �ta v�r�t vessz�k ki)
			//A notify is csak synchronized blokkban haszn�lhat�
			questionList.notify(); //Fel�breszti azt a sz�lat, ami erre a lockra v�r(a questionListet haszn�ljuk z�rnak, ezt adtuk meg a synchronized-ban) (a notifyAll minden v�r� sz�lat)
		}
		
	}
	
	
	@Override
	public void run() {
		
		while(true) {
			try {
				answerQuestion();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}


	
}
