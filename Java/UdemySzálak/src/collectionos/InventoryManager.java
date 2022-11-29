package collectionos;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class InventoryManager {

//	List<Product> soldProductsList = new ArrayList<>();
//	List<Product> purchasedProductsList = new ArrayList<>();
	
	//Nagyjából ugyanaz mint a sima arraylist, annyi különbséggel, hogy ennek a gyári metódusai szinkronizáltak, így nem nekünk kell azzá tenni
	//Van pár ehhez hasonló még (másik packageben, nem a sima utilban)
	List<Product> soldProductsList = new CopyOnWriteArrayList<>();
	
	//Vector vector = new Vector(); //Ez is szinkronizált?
	
	public void populateSoldProducts() {
		for (int i = 0; i < 1000; i++) {
			Product prod = new Product(i, "test_product_" + i);
			soldProductsList.add(prod);
			System.out.println("Added: " + prod);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void displaySoldProducts() {
		for(Product product : soldProductsList) {
			System.out.println("Printing the sold product: " + product);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
