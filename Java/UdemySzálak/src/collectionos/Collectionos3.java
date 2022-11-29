package collectionos;

public class Collectionos3 {

	public static void main(String[] args) throws InterruptedException {
		InventoryManager manager = new InventoryManager();
		
		Thread inventoryTask = new Thread(new Runnable() {
			
			@Override
			public void run() {
				manager.populateSoldProducts();
				
			}
		});
		
		
		Thread displayTask = new Thread(new Runnable() {
			
			@Override
			public void run() {
				manager.displaySoldProducts();
				
			}
		});
		
		inventoryTask.start();
		Thread.sleep(2000); //A main thread alszik
		//inventoryTask.join(); //Addig fog várni, amíg az inventorytask befejezõdött
		displayTask.start();

	}

}
