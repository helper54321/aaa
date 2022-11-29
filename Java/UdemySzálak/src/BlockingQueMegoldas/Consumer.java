package BlockingQueMegoldas;


import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
	
	BlockingQueue<Integer> questionQue;
	
	
	public Consumer(BlockingQueue<Integer> questionQue) {
		this.questionQue = questionQue;
	}

	@Override
	public void run() {
		
		while(true) {
			try {
				Thread.sleep(1000);
				System.out.println("Answered question " + questionQue.take()); //A take egyben vissza is adja és ki is törli a sorból
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
