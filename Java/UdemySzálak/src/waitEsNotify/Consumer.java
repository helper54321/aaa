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
				//A wait csak synchronized blokkban használható
				questionList.wait(); //Notify-ra vár
			}
		}
		
		synchronized (questionList) {
			Thread.sleep(5000);
			System.out.println("Answered question: " + questionList.remove(0)); //Mint egy sornál (a legrégebb óta várót vesszük ki)
			//A notify is csak synchronized blokkban használható
			questionList.notify(); //Felébreszti azt a szálat, ami erre a lockra vár(a questionListet használjuk zárnak, ezt adtuk meg a synchronized-ban) (a notifyAll minden váró szálat)
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
