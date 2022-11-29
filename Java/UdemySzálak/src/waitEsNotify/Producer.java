package waitEsNotify;

import java.util.List;

public class Producer implements Runnable{

	List<Integer> questionList = null;
	final int LIMIT = 5;
	private int questionNo;
	
	public Producer(List<Integer> questionList) {
		this.questionList = questionList;
	}
	
	public void readQuestion(int questionNo) throws InterruptedException {
		synchronized (questionList) {
			while(questionList.size() == LIMIT) {
				System.out.println("Questions have piled up.. wait for answers");
				//A wait csak synchronized blokkban használható
				questionList.wait(); //Notify-ra vár
			}
		}
		
		synchronized (questionList) {
			System.out.println("New question: " + questionNo);
			questionList.add(questionNo);
			Thread.sleep(100);
			//A notify is csak synchronized blokkban használható
			questionList.notify(); //Felébreszti azt a szálat, ami erre a lockra vár(a questionListet használjuk zárnak, ezt adtuk meg a synchronized-ban) (a notifyAll minden váró szálat)
		}
		
	}
	
	
	@Override
	public void run() {
		
		while(true) {
			try {
				readQuestion(questionNo++);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	
}
