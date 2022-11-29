package Teszt;

import java.io.Serializable;
import java.util.Iterator;

import javax.management.RuntimeErrorException;

public class Main extends Object implements Serializable{

	public static void main(String[] args) {
		Main m = new Main();
		System.out.println(m.toReturn(300, 36445));
		
		System.out.println("---------------");
		System.out.println(m.toReturnComplete(828, 627));
		System.out.println(m.toReturnComplete(628, 625));
		System.out.println(m.toReturnComplete(628, 829));
		
		
	}
	
	
	
	
	//827 625
	//827 626
	//828 627
	
	
	public int toReturnComplete(int amountToPay, int receivedAmount) {
		
		
		//1. eset (nem kell visszadni)
		int newPayable = amountToPay;
		
		if(newPayable % 5 >= 3) {
			newPayable += 5;
		}
		
		newPayable -= newPayable % 5;
		
		if(newPayable == receivedAmount) return 0;
		
		//2. eset (még fizetnie kell)
		newPayable = amountToPay;
		if(newPayable > receivedAmount) {
			if(newPayable % 5 >= 3) {
				newPayable += 5;
			}
			
			newPayable -= newPayable % 5;
			
			return receivedAmount - newPayable;
		}
		
		
		//3. eset (van visszajáró)
		newPayable = amountToPay;
		int[] currencies = new int[] {20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5};
		
		if(newPayable % 5 >= 3) {
			newPayable += 5;
		}
		
		newPayable -= newPayable % 5;
		
		while(newPayable > 0) {
			for(int i = 0; i < currencies.length; i++) {
				while(newPayable >= currencies[i]) {
					newPayable -= currencies[i];
					receivedAmount -= currencies[i];
				}
			}
		}
		
		if(receivedAmount % 5 >= 3) {
			receivedAmount += 5;
		}
		
		receivedAmount -= receivedAmount % 5;
		
		return receivedAmount;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int toReturn(int amountToPay, int receivedAmount) {
		int[] currencies = new int[] {20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5};
		
		if(amountToPay > receivedAmount) return -1;
		
		if(amountToPay == receivedAmount) return 0;
		
		int pending = receivedAmount - amountToPay;
		int payBack = 0;
		
		int steps = 0;
		
		while(pending > 5) {
			for(int i = 0; i < currencies.length; i++) {
				while(pending >= currencies[i]) {
					pending -= currencies[i];
					payBack += currencies[i];
					steps++;
				}
			}
		}
		
		System.out.println("Steps: " + steps);
		
		return payBack;
		
	}
}
