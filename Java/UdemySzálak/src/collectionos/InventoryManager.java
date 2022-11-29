package collectionos;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class InventoryManager {

//	List<Product> soldProductsList = new ArrayList<>();
//	List<Product> purchasedProductsList = new ArrayList<>();
	
	//Nagyj�b�l ugyanaz mint a sima arraylist, annyi k�l�nbs�ggel, hogy ennek a gy�ri met�dusai szinkroniz�ltak, �gy nem nek�nk kell azz� tenni
	//Van p�r ehhez hasonl� m�g (m�sik packageben, nem a sima utilban)
	List<Product> soldProductsList = new CopyOnWriteArrayList<>();
	
	//Vector vector = new Vector(); //Ez is szinkroniz�lt?
	
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
